package com.example.demo.jpa;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "person")
public class Person {

  @Id
  private long id;

  private String name;

  @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
  private List<Address> address = new ArrayList<>();
}
