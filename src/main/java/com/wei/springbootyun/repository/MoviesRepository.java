package com.wei.springbootyun.repository;

import com.wei.springbootyun.entity.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MoviesRepository extends JpaRepository<Movies,Integer> {
    @Query(value = "select m from Movies m where m.movieid=:movieid")
    Movies findAllByMovieid(@Param("movieid") Integer movieid);

    @Query(value = "select m from Movies m where m.published_year=:published_year")
    List<Movies> findAllByPublished_year(@Param("published_year") Integer published_year);


    @Query(value = "select m from Movies m where m.published_year<=?1 and m.published_year>=?2")
    List<Movies> findAllByYear(Integer maxyear,Integer minyear);

    @Query(value = "select m from Movies m where m.published_year<=?1")
    List<Movies> findAllByYear(Integer year);
}
