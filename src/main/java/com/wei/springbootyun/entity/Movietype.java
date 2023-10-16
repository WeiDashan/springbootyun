package com.wei.springbootyun.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Movietype {
    @Id
    private Integer movietypeid;
    private String movietypename;
}
