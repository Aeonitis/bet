package com.punter.moneybags.util.helper.csv;

import com.opencsv.CSVWriter;
import com.opencsv.bean.MappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.punter.moneybags.model.dao.LiabilityEntry;
import com.punter.moneybags.model.dao.SelectionLiabilityEntry;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class BeanToCsvWriter {

  private static final String REPORT_ONE_CSV_FILE_PATH = "./ReportOne.csv";
  private static final String REPORT_TWO_CSV_FILE_PATH = "./ReportTwo.csv";

//  public static void writeReportOneToFileOLD(List<SelectionLiabilityEntry> writeReportOneList) {
//
//    try (
//        Writer writer = Files.newBufferedWriter(Paths.get(REPORT_ONE_CSV_FILE_PATH));
//    ) {
//      StatefulBeanToCsv<SelectionLiabilityEntry> beanToCsv = new StatefulBeanToCsvBuilder(writer)
//          .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
//          .build();
//
//      beanToCsv.write(writeReportOneList);
//    } catch (CsvRequiredFieldEmptyException e) {
//      e.printStackTrace();
//    } catch (CsvDataTypeMismatchException e) {
//      e.printStackTrace();
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//  }

  public static void writeReportOneToFile(List<SelectionLiabilityEntry> writeReportOneList) {
    try {

      Path filePath = Paths.get(REPORT_ONE_CSV_FILE_PATH);

      MappingStrategy<SelectionLiabilityEntry> mappingStrategy = new CustomBeanToCSVMappingStrategy<>();
      mappingStrategy.setType(SelectionLiabilityEntry.class);

      BufferedWriter bufferedWriter = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8);
      StatefulBeanToCsv<SelectionLiabilityEntry> beanToCsv = new StatefulBeanToCsvBuilder<SelectionLiabilityEntry>(
          bufferedWriter)
          .withMappingStrategy(mappingStrategy)
          .withOrderedResults(true)
          .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
          .build();

      beanToCsv.write(writeReportOneList);
      bufferedWriter.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void writeReportTwoToFile(List<LiabilityEntry> writeReportTwoList) {
    BufferedWriter bufferedWriter = null;
    try {

      Path filePath = Paths.get(REPORT_TWO_CSV_FILE_PATH);

      MappingStrategy<LiabilityEntry> mappingStrategy = new CustomBeanToCSVMappingStrategy<>();
      mappingStrategy.setType(LiabilityEntry.class);

      bufferedWriter = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8);
      StatefulBeanToCsv<LiabilityEntry> beanToCsv = new StatefulBeanToCsvBuilder<LiabilityEntry>(
          bufferedWriter)
          .withMappingStrategy(mappingStrategy)
          .withOrderedResults(true)
          .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
          .build();

      beanToCsv.write(writeReportTwoList);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
        if (bufferedWriter != null) {
          try {
            bufferedWriter.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }

    }
  }

}
