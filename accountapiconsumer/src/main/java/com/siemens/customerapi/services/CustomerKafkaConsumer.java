package com.siemens.customerapi.services;

import com.google.gson.Gson;
import com.siemens.customerapi.dtos.Customer;
import com.siemens.customerapi.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
@KafkaListener(topics = "siemenscustomersep2024", groupId = "task-group")
public class CustomerKafkaConsumer {

	@Autowired
	private CustomerRepository customerRepository;
	@KafkaHandler(isDefault = true)
	public void handleMessage(String message) {

		System.out.println(message);

		Gson gson = new Gson();
		Customer customer=gson.fromJson(message, Customer.class);
		System.out.println(customer);
		customerRepository.save(customer);
	}


}
