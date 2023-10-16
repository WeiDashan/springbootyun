package com.wei.springbootyun.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Movietag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movietagid;
    private Integer movieid;
    private Integer movietypeid;
}
