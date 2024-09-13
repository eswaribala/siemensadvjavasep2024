package com.siemens.customerapi.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.siemens.customerapi.models.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class CustomerKafkaPublisher {
	@Autowired 
	private KafkaTemplate<Object, Object> template; 

	@Value("${topicname}")
	private String topicName;
	

	public void publishMessage(Customer customer) throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(customer);
		  this.template.send(topicName, json);
		
	}
	
	
}
