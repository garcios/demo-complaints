package com.example.democomplaints;

import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component 
public class ComplaintModelUpdater {

	private static final Logger logger = LoggerFactory.getLogger(ComplaintModelUpdater.class);
	

	@Autowired
	private ComplaintRepository repository;
	
	@EventHandler
	public void handle(ComplaintFiledEvent event){
		logger.info("handle({})", event);
		repository.save(new ComplaintEntity(event.getId(), event.getCompany(), event.getDescription()));
	}
	
}
