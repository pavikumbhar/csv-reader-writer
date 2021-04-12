package com.pavikumbhar.opencsv.beans;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;




/**
 * 
 * @author pavikumbhar
 *
 */
@Getter
@Setter
@ToString
public class ContactCsvWriterBean {
	
	
	@CsvBindByName(column = "Queue")
	@CsvBindByPosition(position = 0)
	private String queue;
  
	@CsvBindByName(column = "StartInterval")
	@CsvBindByPosition(position = 1)
	private String startInterval;

	@CsvBindByName(column = "EndInterval" )
	@CsvBindByPosition(position = 2)
	private String endInterval;

	@CsvBindByName(column = "After contact work time")
	@CsvBindByPosition(position = 3)
	private String afterContactWorkTime;
	
	@CsvBindByName(column = "Average queue answer time")
	@CsvBindByPosition(position = 4)
	private String averageQueueAnswerTime;
	
	@CsvBindByName(column = "Contacts abandoned")
	@CsvBindByPosition(position = 5)
	private String contactsAbandoned;
	
	@CsvBindByName(column = "Contacts abandoned in 15 seconds")
	@CsvBindByPosition(position = 6)
	private String contactsAbandonedIn15Seconds;
	
	@CsvBindByName(column = "Contacts handled incoming")
	@CsvBindByPosition(position = 7)
	private String contactsHandledIncoming;
	
	@CsvBindByName(column = "Contacts handled outbound")
	@CsvBindByPosition(position = 8)
	private String contactsHandledOutbound;
	
	@CsvBindByName(column = "Contacts answered in 15 seconds")
	@CsvBindByPosition(position = 9)
	private String contactsAnsweredIn15Seconds;
	
	@CsvBindByName(column = "Customer hold time")
	@CsvBindByPosition(position = 10)
	private String customerHoldTime;
    
	@CsvBindByName(column = "Contact handle time")
	@CsvBindByPosition(position = 11)
	private String contactHandleTime;

	@CsvBindByName(column = "Average outbound agent interaction time")
	@CsvBindByPosition(position = 12)
	private String averageOutboundAgentInteractionTime;
	
	@CsvBindByName(column = "Average outbound after contact work time")
	@CsvBindByPosition(position = 13)
	private String averageOutboundAfterContactWorkTime;
	
	
	
}
