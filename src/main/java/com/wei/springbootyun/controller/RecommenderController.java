package com.wei.springbootyun.controller;

import com.wei.springbootyun.algorithm.MyAlgorithm;
import com.wei.springbootyun.entity.Movies;
import com.wei.springbootyun.repository.MoviesRepository;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/recommender")
public class RecommenderController {

    @Autowired
    MoviesRepository moviesRepository;

    @GetMapping("/slopeone/{userid}")
    public List<Movies> slopeoneRecom(@PathVariable("userid")Integer userid){
        List<RecommendedItem> list = null;
        List<Movies> moviesList = new ArrayList<>();
        MyAlgorithm myAlgorithm = new MyAlgorithm();
        Integer size = 10;
        Integer movieid=0;
        Movies movie = null;
        list = myAlgorithm.myslopeoneRecommender(userid,size);
        for (RecommendedItem r:list){
            movieid = (int)r.getItemID();
            movie = moviesRepository.findAllByMovieid(movieid);
            moviesList.add(movie);
        }
        return moviesList;
    }

    @GetMapping("/user/{userid}")
    public List<Movies> userRecom(@PathVariable("userid")Integer userid){
        List<RecommendedItem> list = null;
        List<Movies> moviesList = new ArrayList<>();
        MyAlgorithm myAlgorithm = new MyAlgorithm();
        Integer size = 10;
        Integer movieid=0;
        Movies movie = null;
        list = myAlgorithm.userrecommender(userid,size);
        for (RecommendedItem r:list){
            movieid = (int)r.getItemID();
            movie = moviesRepository.findAllByMovieid(movieid);
            moviesList.add(movie);
        }
        return moviesList;
    }

    @GetMapping("/item/{userid}")
    public List<Movies> itemRecom(@PathVariable("userid")Integer userid){
        List<RecommendedItem> list = null;
        List<Movies> moviesList = new ArrayList<>();
        MyAlgorithm myAlgorithm = new MyAlgorithm();
        Integer size = 10;
        Integer movieid=0;
        Movies movie = null;
        list = myAlgorithm.itemrecommender(userid,size);
        for (RecommendedItem r:list){
            movieid = (int)r.getItemID();
            movie = moviesRepository.findAllByMovieid(movieid);
            moviesList.add(movie);
        }
        return moviesList;
    }
}
