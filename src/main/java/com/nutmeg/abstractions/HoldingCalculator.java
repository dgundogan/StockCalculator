package com.nutmeg.abstractions;

import com.nutmeg.model.Holding;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Created by DG on 07/12/2017.
 */
public interface HoldingCalculator {
    Map<String,List<Holding>> calculateHoldings(File transactionFile, LocalDate date);
}
