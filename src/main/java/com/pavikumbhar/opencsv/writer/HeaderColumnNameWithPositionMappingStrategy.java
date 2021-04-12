package com.pavikumbhar.opencsv.writer;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.exceptions.CsvBadConverterException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class HeaderColumnNameWithPositionMappingStrategy<T> extends HeaderColumnNameMappingStrategy<T> {

    protected Map<String, String> columnMap;

    @Override
    public void setType(Class<? extends T> type) throws CsvBadConverterException {
        super.setType(type);
        columnMap = new HashMap<>(this.getFieldMap().values().size());
        Map<String, Integer> headerPositionMap = new HashMap<>(this.getFieldMap().values().size());
        for (Field field : type.getDeclaredFields()) {
            if (field.isAnnotationPresent(CsvBindByPosition.class) && field.isAnnotationPresent(CsvBindByName.class)) {
                int position = field.getAnnotation(CsvBindByPosition.class).position();
                String colName = "".equals(field.getAnnotation(CsvBindByName.class).column()) ? field.getName() : field.getAnnotation(CsvBindByName.class).column();
                headerPositionMap.put(colName.toUpperCase().trim(), position);
                columnMap.put(colName.toUpperCase().trim(), colName);
            }
        }
        super.setColumnOrderOnWrite((String o1, String o2) -> {
            if (!headerPositionMap.containsKey(o1) || !headerPositionMap.containsKey(o2)) {
                return 0;
            }
            return headerPositionMap.get(o1) - headerPositionMap.get(o2);
        });
    }

    @Override
    public String[] generateHeader(T bean) throws CsvRequiredFieldEmptyException {
        String[] headersRaw = super.generateHeader(bean);
        return Arrays.stream(headersRaw).map(h -> columnMap.get(h)).toArray(String[]::new);
    }
}