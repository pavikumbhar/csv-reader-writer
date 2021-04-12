package com.pavikumbhar.opencsv;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

import com.pavikumbhar.opencsv.beans.ContactCsvReaderBean;
import com.pavikumbhar.opencsv.beans.ContactCsvWriterBean;
import com.pavikumbhar.opencsv.reader.OpenCsvReader;
import com.pavikumbhar.opencsv.writer.OpenCsvWriter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OpenCsvRunner {
	
	public static void main(String[] args) throws Exception {

		log.info("\n\n#######################-1-original ###################################\n");

		String fileName = "src/main/resources/sample/Q-2020-12-12T120000Z.csv";
		List<ContactCsvReaderBean> contacts = OpenCsvReader.csvToObjectReader(fileName, ContactCsvReaderBean.class);
		contacts.forEach(bean -> log.info("{}",bean));

		log.info("\n\n#########################-2-modified-column-order ######################\n");

		File file = new File("src/main/resources/sample/Q-2020-12-12T120000Z-modified-column-order.csv");
		List<ContactCsvReaderBean> contactList = OpenCsvReader.csvToObjectReader(file, ContactCsvReaderBean.class);
		contactList.forEach(bean -> log.info("{}",bean));

		log.info("\n\n#######################-3-removed-last-column #############################\n");

		Path path = Paths.get("src/main/resources/sample/Q-2020-12-12T120000Z-removed-last-column.csv");
		List<ContactCsvWriterBean> contactLists = OpenCsvReader.csvToObjectReader(path, ContactCsvWriterBean.class);
		contactLists.forEach(bean -> log.info("{}",bean));
	
		
		
		OpenCsvWriter.objectToCsvWriter("src/main/resources/sample/writer.csv", contactLists);
		contactLists.remove(0);
		OpenCsvWriter.objectToCsvWriter("src/main/resources/sample/ordered-writer.csv",ContactCsvWriterBean.class,contactLists); 

		/**
		 * Path directoryPath = Paths.get("D:\\watch"); 
		 * watchService(directoryPath);
		 */

	}
	

	
	public static void watchService(Path directoryPath) throws InterruptedException {
        try {
            WatchService watcher = FileSystems.getDefault().newWatchService();
          
            directoryPath.register(watcher,StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
             
            log.info("Watch Service registered for dir: {}" , directoryPath.getFileName());
             
            while (true) {
                WatchKey key;
                try {
                    key = watcher.take();
                } catch (InterruptedException e) {
                	log.error(e.getMessage());
                    return;
                }
                 
                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                     
                    @SuppressWarnings("unchecked")
                    WatchEvent<Path> ev = (WatchEvent<Path>) event;
                    Path fileName = ev.context();
                     
                    log.info("{}: {}",kind.name(), fileName);
                    String extension= fileName.toString().substring(fileName.toString().lastIndexOf('.')+1);
                     
					if (kind == StandardWatchEventKinds.ENTRY_MODIFY && extension.equals("csv")) {
						Thread.sleep(5000);
						 Path filepath = directoryPath.resolve(fileName.toString());
						 if(Files.exists(filepath)){
							 List<ContactCsvReaderBean> contactList = OpenCsvReader.csvToObjectReader(filepath, ContactCsvReaderBean.class);
								contactList.forEach(bean->log.info(bean.toString()));
								Files.delete(filepath);
						 }
						
					}
                }
                 
                boolean valid = key.reset();
                if (!valid) {
                    break;
                }
            }
             
        } catch (IOException e) {
        	e.printStackTrace();
            log.error(e.getMessage());
        }
    }
	

}
