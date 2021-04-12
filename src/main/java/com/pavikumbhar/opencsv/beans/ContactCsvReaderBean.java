package com.pavikumbhar.opencsv.beans;

import java.util.Date;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

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
public class ContactCsvReaderBean {
	
	
	@CsvBindByName(column = "Queue")
	private String queue;
    /**
	@CsvBindByName(column = "StartInterval")
	private String startInterval;

	@CsvBindByName(column = "EndInterval")
	private String endInterval;
	*/
	
	/** 2021-02-03T04:30:00.000Z ->.000 is the fraction of a second and Z indicates UTC timezone.*/
	@CsvDate("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")  
	@CsvBindByName(column = "StartInterval")
	private Date startInterval;

	@CsvDate("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	@CsvBindByName(column = "EndInterval" )
	private Date endInterval;

	@CsvBindByName(column = "After contact work time")
	private Long afterContactWorkTime;
	
	@CsvBindByName(column = "Average queue answer time")
	private Long averageQueueAnswerTime;
	
	@CsvBindByName(column = "Contacts abandoned")
	private Long contactsAbandoned;
	
	@CsvBindByName(column = "Contacts abandoned in 15 seconds")
	private Long contactsAbandonedIn15Seconds;
	
	@CsvBindByName(column = "Contacts handled incoming")
	private Long contactsHandledIncoming;
	
	@CsvBindByName(column = "Contacts handled outbound")
	private Long contactsHandledOutbound;
	
	@CsvBindByName(column = "Contacts answered in 15 seconds")
	private Long contactsAnsweredIn15Seconds;
	
	@CsvBindByName(column = "Customer hold time")
	private Long customerHoldTime;
    
	@CsvBindByName(column = "Contact handle time")
	private Long contactHandleTime;

	@CsvBindByName(column = "Average outbound agent interaction time")
	private Long averageOutboundAgentInteractionTime;
	
	@CsvBindByName(column = "Average outbound after contact work time")
	private Long averageOutboundAfterContactWorkTime;
	
	
	
}
