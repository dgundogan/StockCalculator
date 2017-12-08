package com.dgundogan.transactions;

import com.dgundogan.model.Account;
import com.dgundogan.model.Holding;
import com.dgundogan.transactions.HoldingCalculatorImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.format.DateTimeFormatter.BASIC_ISO_DATE;
import static org.junit.Assert.*;

/**
 * Created by DG on 07/12/2017.
 */
public class HoldingCalculatorImplTest {

    private HoldingCalculatorImpl holdingCalculator;

    @Mock
    Account account;

    @Before
    public void initialize(){
        holdingCalculator = new HoldingCalculatorImpl();
    }

    //Unit Test Case 1: read file and write output on commond line.
    @Test
    public final void readFile() throws IOException {

        File file = new File("resources/test.csv");
        String dateString = "20170201";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYYMMDD");
        LocalDate localDate =  LocalDate.parse(dateString,BASIC_ISO_DATE);

        Map<String,List<Account>> outputs = holdingCalculator.readFile(file,localDate);

        assertNotNull(outputs);
    }

    //Unit Test Case 2: calculate and print command line
    @Test
    public final void calculateHoldings() throws IOException {

        File file = new File("resources/test.csv");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYYMMDD");

        String dateString = "20170201";

        LocalDate localDate =  LocalDate.parse(dateString,BASIC_ISO_DATE);
        System.out.println("Current Holdings on "+dateString+":");
        Map<String, List<Holding>> output =  holdingCalculator.calculateHoldings(file,localDate);
        //SortedMap<String, List<Holding>> sortedMap = new TreeMap<String, List<Holding>>(output);
        printScreen(output);

    }

    public final void printScreen(Map<String, List<Holding>> output){
        Iterator<Map.Entry<String, List<Holding>>> iter = output.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .collect(Collectors.toList()).iterator();
        while (iter.hasNext()){
            Map.Entry<String, List<Holding>> entry = iter.next();
            System.out.println(String.format("%12s", entry.getKey()));
            List<Holding> holdings = entry.getValue();

            holdings = holdings.stream().sorted(Comparator.comparing(Holding::getAsset).reversed()).collect(Collectors.toList());
            for(Holding holding:holdings){
                System.out.println(String.format("%16s\t%-20.4f",holding.getAsset(),Math.floor(holding.getHolding() * 10000) / 10000.0));
            }
        }
    }

    //Unit Test Case 3: changed date
    @Test
    public final void calculateHoldingsTwo() throws IOException {

        File file = new File("resources/test.csv");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYYMMDD");

        String dateString = "20171201";

        LocalDate localDate =  LocalDate.parse(dateString,BASIC_ISO_DATE);
        System.out.println("Current Holdings on "+dateString+":");
        Map<String, List<Holding>> output =  holdingCalculator.calculateHoldings(file,localDate);
        //SortedMap<String, List<Holding>> sortedMap = new TreeMap<String, List<Holding>>(output);
        printScreen(output);

    }


    //Unit Test Case 4: calculate and print command line
    @Test
    public final void calculateHoldingsTree() throws IOException {

        File file = new File("resources/test2.csv");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYYMMDD");

        String dateString = "20170201";

        LocalDate localDate =  LocalDate.parse(dateString,BASIC_ISO_DATE);
        System.out.println("Current Holdings on "+dateString+":");
        Map<String, List<Holding>> output =  holdingCalculator.calculateHoldings(file,localDate);
        //SortedMap<String, List<Holding>> sortedMap = new TreeMap<String, List<Holding>>(output);
        printScreen(output);

    }

    //Unit Test Case 4: calculate and print command line
    @Test
    public final void calculateHoldingsManualPerformans() throws IOException {

        File file = new File("resources/test3.csv");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYYMMDD");

        String dateString = "20170201";

        LocalDate localDate =  LocalDate.parse(dateString,BASIC_ISO_DATE);
        System.out.println("Current Holdings on "+dateString+":");
        Map<String, List<Holding>> output =  holdingCalculator.calculateHoldings(file,localDate);
        //SortedMap<String, List<Holding>> sortedMap = new TreeMap<String, List<Holding>>(output);
        printScreen(output);

    }


}