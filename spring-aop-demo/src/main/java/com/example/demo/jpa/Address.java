package com.example.demo.jpa;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "address")
public class Address {

  @Id
  private long id;

  private String province;

  private String city;

  private String code;

  @ManyToOne
  @JoinColumn(name = "person_id", nullable = false)
  private Person person;
}
