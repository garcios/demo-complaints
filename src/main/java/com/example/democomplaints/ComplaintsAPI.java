package com.example.democomplaints;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public  class ComplaintsAPI{
		
	  private static final Logger logger = LoggerFactory.getLogger(ComplaintsAPI.class);

	
		private final ComplaintRepository repository;
		private final CommandGateway commandGateway;
		
		public ComplaintsAPI(ComplaintRepository repository,
				CommandGateway commandGateway){
			
			this.repository=repository;
			this.commandGateway=commandGateway;
		}
		
		@PostMapping
		public CompletableFuture<String> fileComplaint(@RequestBody Map<String,String> request){
			
			logger.info("fileComplaint({})", request.toString());
			
			String id = UUID.randomUUID().toString();
			return commandGateway.send(new FileComplaintCommand(id
					            ,request.get("company")
					            ,request.get("description")));
		}
		 
		
		@GetMapping
		public List<ComplaintEntity> findAll(){
			logger.info("findAll()");
			return repository.findAll();
		}
		
		@GetMapping("/{id}")
        public ComplaintEntity findOne(@PathVariable String id){
			logger.info("findOne( {} )", id);
			return repository.findOne(id);
		}
		
		
}