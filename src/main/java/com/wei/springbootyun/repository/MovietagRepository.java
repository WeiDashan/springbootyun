package com.wei.springbootyun.repository;

import com.wei.springbootyun.entity.Movies;
import com.wei.springbootyun.entity.Movietag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface MovietagRepository extends JpaRepository<Movietag,Integer>{

    @Query(value = "select m from Movietag m where m.movietypeid=?1")
    List<Movietag> findAllMovietagByMovietypeid(Integer movietypeid);

}
