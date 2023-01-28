package com.order.management.system.order;

import com.order.management.system.order.entity.Customer;
import com.order.management.system.order.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

@SpringBootTest
class OrderApplicationTests {

	@Autowired
	CustomerRepository customerRepository;
	@Test
	void createCustomerTest() {
		Customer customer = new Customer();
		customer.setName("AMT");
		customer.setMobile("1234567890");
		customer.setEmail("amt@gmail.com");
		customerRepository.save(customer);
		List<Customer> customerList = customerRepository.findAll();
		Assertions.assertEquals(customerList.get(0).getEmail(), "amt@gmail.com");
	}

}
