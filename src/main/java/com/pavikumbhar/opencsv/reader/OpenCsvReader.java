package com.pavikumbhar.opencsv.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author pavikumbhar
 *
 */

@Slf4j
@UtilityClass
public class OpenCsvReader {
	
    /**
     * 
     * @param fileName
     * @return
     */
	public static List<String[]> csvToObjectReader(String fileName) {
		List<String[]> entries = new ArrayList<>();
		 
		try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
			entries = reader.readAll();
		} catch (IOException | CsvException e) {
			log.error("{}  An exception occurred!", fileName, e);
		}
		return entries;

	}

	/**
	 * 
	 * @param <T>
	 * @param fileName
	 * @param type
	 * @return
	 */
	public static  <T> List<T> csvToObjectReader(String fileName,Class<T> type) {
		List<T> csvBeans = new ArrayList<>();
		try {
			csvBeans = new CsvToBeanBuilder<T>(new FileReader(fileName))
					.withType(type)
					.build()
					.parse();
		} catch (IllegalStateException | FileNotFoundException e) {
			log.error("{}, {} ! An exception occurred!", fileName, type, e);
			
		}
		return csvBeans;
	}
     
	/**
	 * 
	 * @param <T>
	 * @param file
	 * @param type
	 * @return
	 */
	public static  <T> List<T> csvToObjectReader(File file,Class<T> type) {
		List<T> csvBeans = new ArrayList<>();
		try {
			csvBeans = new CsvToBeanBuilder<T>(new FileReader(file))
					.withType(type)
					.build()
					.parse();
		} catch (IllegalStateException | FileNotFoundException e) {
			log.error("{}, {}! An exception occurred!", file, type, e);
		}
		return csvBeans;
	}
	
	/**
	 * 
	 * @param <T>
	 * @param path
	 * @param type
	 * @return
	 */
	public static  <T> List<T> csvToObjectReader(Path path,Class<T> type) {
		List<T> csvBeans = new ArrayList<>();
		try(Reader reader = Files.newBufferedReader(path) ) {
		 	csvBeans = new CsvToBeanBuilder<T>(reader)
					.withType(type)
					.build()
					.parse();
		} catch (IllegalStateException | IOException e) {
			log.error("{}, {}! An exception occurred!", path, type, e);
		}
		return csvBeans;
	}

}
