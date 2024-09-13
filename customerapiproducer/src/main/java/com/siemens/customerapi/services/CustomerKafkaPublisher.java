package com.siemens.customerapi.services;

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
	

	public void publishMessage(Customer customer) {
		
		  this.template.send(topicName, customer.toString());
		
	}
	
	
}
