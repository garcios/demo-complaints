package com.example.democomplaints;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
@Aggregate
public class ComplaintAggregate {

	private static final Logger logger = LoggerFactory.getLogger(ComplaintAggregate.class);
	
	@AggregateIdentifier
	private String identifier;
	
	@CommandHandler
	public ComplaintAggregate(FileComplaintCommand cmd){
		logger.info("ComplaintAggregate({})", cmd);
		apply(new ComplaintFiledEvent(cmd.getId(), cmd.getCompany(), cmd.getDescription() ));
	}
	
	
	@EventSourcingHandler
	public void on(ComplaintFiledEvent event){
		logger.info("on({})", event);
		this.identifier=event.getId();
	}
	
}
