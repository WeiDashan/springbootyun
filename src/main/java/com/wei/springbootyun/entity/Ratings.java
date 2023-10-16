package com.wei.springbootyun.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Ratings {
    @Id
    private Integer usrid;
    private Integer movieid;
    private Integer ratingvalue;
    private Integer ratingtimestamp;
}

