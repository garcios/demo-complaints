package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

 

@SpringBootApplication
public class DemoComplaintsApplication {

	private static final Logger logger = LoggerFactory.getLogger(DemoComplaintsApplication.class);
	private static final String COMPLAINT_EVENTS = "ComplaintEvents";


	public static void main(String[] args) {
		SpringApplication.run(DemoComplaintsApplication.class, args);
	}
	

	@Bean
	public Queue queue(){
		return QueueBuilder.durable(COMPLAINT_EVENTS).build();
	}
	
	@Bean
	public Exchange exchange(){
		return ExchangeBuilder.fanoutExchange(COMPLAINT_EVENTS).build();
	}
	
	@Bean
	public Binding binding(){
		return BindingBuilder.bind(queue()).to(exchange()).with("*").noargs();
	}
	
	
	@Autowired
	public void configure(AmqpAdmin admin){
		logger.info("configure");
		
		//declare in the following order below
		admin.declareQueue(queue());
		admin.declareExchange(exchange());
		admin.declareBinding(binding());
	}

}	




