package com.pavikumbhar.opencsv.writer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.comparators.FixedOrderComparator;

import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author pavikumbhar
 *
 */

@Slf4j
@UtilityClass
public class OpenCsvWriter {

	/**
	 * 
	 * @param <T>
	 * @param fileName
	 * @param csvObjectList
	 */
	public static <T> void objectToCsvWriter(String fileName, List<T> csvObjectList) {
		try (Writer writer = new FileWriter(fileName)) {
			StatefulBeanToCsv<T> statefulBeanToCsv = new StatefulBeanToCsvBuilder<T>(writer).build();
			statefulBeanToCsv.write(csvObjectList);
		} catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
			log.error("{}, An exception occurred!", fileName, e);
		}

	}

	/**
	 * 
	 * @param <T>
	 * @param fileName
	 * @param type
	 * @param csvObjectList
	 * @param columnNames
	 */
	public static <T> void objectToCsvWriter(String fileName, Class<T> type, List<T> csvObjectList,
			List<String> columnNames) {

		Objects.requireNonNull(columnNames, "The list of column names must not be null");

		try (Writer writer = new FileWriter(fileName)) {

			HeaderColumnNameMappingStrategy<T> mappingStrategy = new HeaderColumnNameMappingStrategy<>();
			mappingStrategy.setType(type);
			mappingStrategy.setColumnOrderOnWrite(new FixedOrderComparator<>(columnNames));

			StatefulBeanToCsv<T> statefulBeanToCsv = new StatefulBeanToCsvBuilder<T>(writer)
					.withMappingStrategy(mappingStrategy).build();
			statefulBeanToCsv.write(csvObjectList);
		} catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
			log.error("{},  An exception occurred!", fileName, e);
		}

	}

	/***
	 * @apiNote : need to work on this method ...not production ready  yet... 
	 * @param <T>
	 * @param fileName
	 * @param type
	 * @param csvObjectList
	 */
	public static <T> void objectToCsvWriter(String fileName, Class<T> type, List<T> csvObjectList) {

		try (Writer writer = new FileWriter(fileName)) {

			HeaderColumnNameWithPositionMappingStrategy<T> mappingStrategy = new HeaderColumnNameWithPositionMappingStrategy<>();
            mappingStrategy.setType(type);

			StatefulBeanToCsv<T> statefulBeanToCsv = new StatefulBeanToCsvBuilder<T>(writer)
					.withMappingStrategy(mappingStrategy).build();
			statefulBeanToCsv.write(csvObjectList);
		} catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
			log.error("{},  An exception occurred!", fileName, e);
		}

	}

}
