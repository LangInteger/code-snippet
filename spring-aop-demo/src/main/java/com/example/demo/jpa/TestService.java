package com.example.demo.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

  @Autowired
  PersonRepository personRepository;

  public void test() {
    // 1. create person
    Person person = new Person();
    person.setId(1L);
    person.setName("Mike");

    // 2. create address
    Address address = new Address();
    address.setId(1L);
    address.setCode("8888");
    address.setProvince("SomeWhere");
    address.setCity("SomeWhere");
    address.setPerson(person);

    // 3. save person with address
    person.getAddress().add(address);
    personRepository.save(person);

    // here we get address and person saved properly

    // 4. change address and save
    address.setCode("9999");
    address.setProvince("SomeWhereElse");
    address.setCity("SomeWhereElse");
    personRepository.save(address.getPerson());
  }

}
