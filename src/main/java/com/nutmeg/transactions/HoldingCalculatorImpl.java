package com.nutmeg.transactions;

import com.nutmeg.abstractions.HoldingCalculator;
import com.nutmeg.model.Account;
import com.nutmeg.model.Holding;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.time.format.DateTimeFormatter.BASIC_ISO_DATE;


/**
 * Created by DG on 07/12/2017.
 */
public class HoldingCalculatorImpl implements HoldingCalculator {

    public Map<String, List<Holding>> calculateHoldings(File fileName, LocalDate date) {

        try{
            //read from xml file
            Map<String, List<Account>> accounts = readFile(fileName,date);

            //groupingBy , and merge output list.
            return accounts
                                                .entrySet()
                                                .parallelStream()
                                                .flatMap(value -> value.getValue().stream())
                                                .collect(
                                                        Collectors.groupingBy(
                                                                Account::getAccount,
                                                                Collector.of(ArrayList::new,
                                                                        (list, account) -> {
                                                                            //CASH asset is calculated only next object creation
                                                                            if(!"CASH".equals(account.getAsset())) {
                                                                                Holding holding = Holding.calculateHolding(account);
                                                                                if (list.contains(holding)) {
                                                                                    Holding prevHolding = list.get(list.indexOf(holding));
                                                                                    prevHolding.calculateHolding(holding);
                                                                                } else {
                                                                                    list.add(holding);
                                                                                }
                                                                            }
                                                                            //calculate CASH assset
                                                                            Holding holdingCash = Holding.calculateHoldingCash(account);
                                                                            if(list.contains(holdingCash)){
                                                                                Holding prevHoldingCash = list.get(list.indexOf(holdingCash));
                                                                                prevHoldingCash.calculateHolding(holdingCash);
                                                                            } else {
                                                                                list.add(holdingCash);
                                                                            }
                                                                        },
                                                                        (list, outList) ->  {
                                                                            list.addAll(outList);
                                                                            return  outList;
                                                                        }
                                                                )));

        }catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }

    //read from file using Java 8 Streaming and store it in Map object like DB table
    public Map<String, List<Account>> readFile(File fileName, LocalDate date) throws IOException{
        //read file into stream
        Stream<String> lines = Files.lines(Paths.get(fileName.getAbsolutePath()));
        //stream.forEach(System.out::println);
        return lines
                    .map(line -> line.split(",")) //parsing line with "," and convert to stream
                    .filter(line -> date.compareTo(LocalDate.parse(line[1].trim().toString(),BASIC_ISO_DATE)) >= 0) //filter by given data
                    .collect(
                            Collectors.groupingBy(list -> list[0].trim(),   //grouping by Account
                                                   Collectors.mapping(list -> new Account(list[0],   //creating a lists object
                                                                                          list[1],
                                                                                          list[2],
                                                                                          list[3],
                                                                                          list[4],
                                                                                          list[5]),
                                                    Collectors.toList())
                    ));

    }





}
