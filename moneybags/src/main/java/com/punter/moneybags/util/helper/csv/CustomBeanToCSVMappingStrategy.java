package com.punter.moneybags.util.helper.csv;

import com.opencsv.bean.BeanField;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.apache.commons.lang3.StringUtils;

/**
 * OpenCsv Backwards compatibility issues & solution https://stackoverflow.com/questions/45203867/opencsv-how-to-create-csv-file-from-pojo-with-custom-column-headers-and-custom
 */
public class CustomBeanToCSVMappingStrategy<T> extends ColumnPositionMappingStrategy<T> {

  @Override
  public String[] generateHeader(T bean) throws CsvRequiredFieldEmptyException {

    // header name based on field name
    String[] headersAsPerFieldName = getFieldMap().generateHeader(bean);

    String[] header = new String[headersAsPerFieldName.length];

    for (int i = 0; i <= headersAsPerFieldName.length - 1; i++) {

      BeanField beanField = findField(i);

      String boundColumnHeaderName = extractHeaderName(beanField); // @CsvBindByName annotation

      if (boundColumnHeaderName.isEmpty()) {
        boundColumnHeaderName = headersAsPerFieldName[i]; // defaults to field name
      }

      header[i] = boundColumnHeaderName;
    }

    headerIndex.initializeHeaderIndex(header);
    return header;
  }

  private String extractHeaderName(final BeanField beanField) {
    if (beanField == null || beanField.getField() == null
        || beanField.getField().getDeclaredAnnotationsByType(CsvBindByName.class).length == 0) {
      return StringUtils.EMPTY;
    }

    final CsvBindByName bindByNameAnnotation = beanField.getField()
        .getDeclaredAnnotationsByType(CsvBindByName.class)[0];
    return bindByNameAnnotation.column();
  }
}