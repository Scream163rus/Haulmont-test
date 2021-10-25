package com.scream.testtask.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class BaseEntity {
 @Id
 @GeneratedValue(generator="system-uuid")
 @GenericGenerator(name="system-uuid", strategy = "uuid")
 @Column(name = "id", unique = true)
 private String id;
}
