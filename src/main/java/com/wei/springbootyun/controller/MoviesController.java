package com.wei.springbootyun.controller;

import com.wei.springbootyun.entity.GetMovieList;
import com.wei.springbootyun.entity.LoginResave;
import com.wei.springbootyun.entity.Movies;
import com.wei.springbootyun.entity.Movietag;
import com.wei.springbootyun.repository.MoviesRepository;
import com.wei.springbootyun.repository.MovietagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/movie")
public class MoviesController {

    @Autowired
    MoviesRepository moviesRepository;

    @Autowired
    MovietagRepository movietagRepository;

    @GetMapping("/{currentMoviePage}")
    public List<Movies> pagetest(@PathVariable("currentMoviePage")Integer currentMoviePage){
        Integer size = 10;
        currentMoviePage -=1;
        String page = currentMoviePage.toString();
        System.out.println(page);
        Page<Movies> list = findAll(page,size.toString());
        List<Movies> moviesList=new ArrayList<>();
        for (Movies m:list){
            Integer movieid = m.getMovieid();
            Movies movie = moviesRepository.findAllByMovieid(movieid);
            moviesList.add(movie);
        }
        return moviesList;
    }

    public Page<Movies> findAll(String page, String limit){
        Pageable pageable= PageRequest.of(Integer.parseInt(page), Integer.parseInt(limit));
        Page<Movies> pageinfo=moviesRepository.findAll(pageable);
        return pageinfo;
    }
        @PostMapping("/getmovielist")
    public List<Movies> login(@RequestBody GetMovieList getMovieList){
        Integer movietypeNum = getMovieList.getMovietypeNum();
        Integer movieyearNum = getMovieList.getMovieyearNum();
        Integer moviepageNum = getMovieList.getMoviepageNum();
        List<Movies> moviesList=new ArrayList<>();
        Integer size = 10;
        if (movietypeNum==0){
//            当类型为全部，时间为全部时
            if (movieyearNum==0){
                String page = moviepageNum.toString();
                Page<Movies> list = findAll(page,size.toString());

                for (Movies m:list){
                    Integer movieid = m.getMovieid();
                    Movies movie = moviesRepository.findAllByMovieid(movieid);
                    moviesList.add(movie);
                }
            }
//            当类型为全部，时间有要求时
            else {
                switch (movieyearNum){
                    case 1:
                        moviesList = findYearPageMovieList(2000,moviepageNum,size,moviesRepository);
                        break;
                    case 2:
                        moviesList = findYearPageMovieList(1999,moviepageNum,size,moviesRepository);
                        break;
                    case 3:
                        moviesList = findYearPageMovieList(1998,moviepageNum,size,moviesRepository);
                        break;
                    case 4:
                        moviesList = findYearPageMovieList(1997,moviepageNum,size,moviesRepository);
                        break;
                    case 5:
                        moviesList = findYearPageMovieList(1996,moviepageNum,size,moviesRepository);
                        break;
                    case 6:
                        moviesList = findYearsPageMovieList(1995,1990,moviepageNum,size,moviesRepository);
                        break;
                    case 7:
                        moviesList = findYearsPageMovieList(1989,1980,moviepageNum,size,moviesRepository);
                        break;
                    case 8:
                        moviesList = findYearsPageMovieList(1979,1970,moviepageNum,size,moviesRepository);
                        break;
                    default:
                        moviesList = findYears2PageMovieList(1969,moviepageNum,size,moviesRepository);
                        break;
                }
            }
        }else {
//            当类型有要求，时间为全部时
            if (movieyearNum==0){
                moviesList = findTypeidMovieList(movietypeNum,moviepageNum,size,moviesRepository,movietagRepository);
            }
//            当类型有要求，时间有要求时
            else {
                List<Movies> moviesYearList = null;
                List<Movietag> moviesTypeList = movietagRepository.findAllMovietagByMovietypeid(movietypeNum);
                switch (movieyearNum){
                    case 1:
                        moviesYearList = moviesRepository.findAllByPublished_year(2000);
                        moviesList = findTypeYearMovieList(moviesYearList,moviesTypeList,moviepageNum,size);
                        break;
                    case 2:
                        moviesYearList = moviesRepository.findAllByPublished_year(1999);
                        moviesList = findTypeYearMovieList(moviesYearList,moviesTypeList,moviepageNum,size);
                        break;
                    case 3:
                        moviesYearList = moviesRepository.findAllByPublished_year(1998);
                        moviesList = findTypeYearMovieList(moviesYearList,moviesTypeList,moviepageNum,size);
//                        System.out.println(moviesList.size());
                        break;
                    case 4:
                        moviesYearList = moviesRepository.findAllByPublished_year(1997);
                        moviesList = findTypeYearMovieList(moviesYearList,moviesTypeList,moviepageNum,size);
                        break;
                    case 5:
                        moviesYearList = moviesRepository.findAllByPublished_year(1996);
                        moviesList = findTypeYearMovieList(moviesYearList,moviesTypeList,moviepageNum,size);
                        break;
                    case 6:
                        moviesYearList = moviesRepository.findAllByYear(1995,1990);
                        moviesList = findTypeYearMovieList(moviesYearList,moviesTypeList,moviepageNum,size);
                        break;
                    case 7:
                        moviesYearList = moviesRepository.findAllByYear(1989,1980);
                        moviesList = findTypeYearMovieList(moviesYearList,moviesTypeList,moviepageNum,size);
                        break;
                    case 8:
                        moviesYearList = moviesRepository.findAllByYear(1979,1970);
                        moviesList = findTypeYearMovieList(moviesYearList,moviesTypeList,moviepageNum,size);
                        break;
                    default:
                        moviesYearList = moviesRepository.findAllByYear(1969);
                        moviesList = findTypeYearMovieList(moviesYearList,moviesTypeList,moviepageNum,size);
                        break;
                }
            }
        }

        return moviesList;
    }
//    特定年份
    public List<Movies> findYearPageMovieList(Integer year,Integer page,Integer size,MoviesRepository moviesRepository){
        List<Movies> list = new ArrayList<>();
        List<Movies> totalList = moviesRepository.findAllByPublished_year(year);
        Integer leftIndex = page*size;
        Integer rightIndex = (page+1)*size-1;
        if (rightIndex<totalList.size()){
            for (int i=0;i<size;i++){
                list.add(totalList.get(leftIndex+i));
            }
            return list;
        }else if (leftIndex>=totalList.size()){
            return null;
        }else {
            for (int i=0;i<size;i++){
                if ((i+leftIndex)==totalList.size()){
                    break;
                }
                list.add(totalList.get(leftIndex+i));
            }
            return list;
        }
    }
//    年份之间
    public List<Movies> findYearsPageMovieList(Integer maxyear,Integer minyear,Integer page,Integer size,MoviesRepository moviesRepository){
        List<Movies> list = new ArrayList<>();
        List<Movies> totalList = moviesRepository.findAllByYear(maxyear,minyear);
        Integer leftIndex = page*size;
        Integer rightIndex = (page+1)*size-1;
        if (rightIndex<totalList.size()){
            for (int i=0;i<size;i++){
                list.add(totalList.get(leftIndex+i));
            }
            return list;
        }else if (leftIndex>=totalList.size()){
            return null;
        }else {
            for (int i=0;i<size;i++){
                if ((i+leftIndex)==totalList.size()){
                    break;
                }
                list.add(totalList.get(leftIndex+i));
            }
            return list;
        }
    }
//    小于某一年份
    public List<Movies> findYears2PageMovieList(Integer year,Integer page,Integer size,MoviesRepository moviesRepository){
        List<Movies> list = new ArrayList<>();
        List<Movies> totalList = moviesRepository.findAllByYear(year);
        Integer leftIndex = page*size;
        Integer rightIndex = (page+1)*size-1;
        if (rightIndex<totalList.size()){
            for (int i=0;i<size;i++){
                list.add(totalList.get(leftIndex+i));
            }
            return list;
        }else if (leftIndex>=totalList.size()){
            return null;
        }else {
            for (int i=0;i<size;i++){
                if ((i+leftIndex)==totalList.size()){
                    break;
                }
                list.add(totalList.get(leftIndex+i));
            }
            return list;
        }
    }

//    查找符合类型的电影
    public List<Movies> findTypeidMovieList(Integer movietypeNum,Integer page,Integer size,MoviesRepository moviesRepository,MovietagRepository movietagRepository){
        List<Movietag> movietagList = movietagRepository.findAllMovietagByMovietypeid(movietypeNum);
        List<Movies> list = new ArrayList<>();
        Integer leftIndex = page*size;
        Integer rightIndex = (page+1)*size-1;
        if (rightIndex<movietagList.size()){
            for (int i=0;i<size;i++){
                Movies movies = moviesRepository.findAllByMovieid(movietagList.get(leftIndex+i).getMovieid());
                list.add(movies);
            }
            return list;
        }else if (leftIndex>=movietagList.size()){
            return null;
        }else {
            for (int i=0;i<size;i++){
                if ((i+leftIndex)==movietagList.size()){
                    break;
                }
                Movies movies = moviesRepository.findAllByMovieid(movietagList.get(leftIndex+i).getMovieid());
                list.add(movies);
            }
            return list;
        }
    }
//    查找符合年份和类型的电影
    public List<Movies> findTypeYearMovieList(List<Movies> moviesYearList,List<Movietag> moviesTypeList,Integer page,Integer size){
        List<Movies> list1 = new ArrayList<>();
        for (int i =0;i<moviesYearList.size();i++){
            for (int j=0;j<moviesTypeList.size();j++){

                if ((moviesYearList.get(i).getMovieid()-moviesTypeList.get(j).getMovieid())==0){
                    list1.add(moviesYearList.get(i));
                }
            }
        }
//        System.out.println("page: "+page);
//        System.out.println("size: "+size);
//        System.out.println("list1.size: "+list1.size());
        List<Movies> finalList = new ArrayList<>();
        Integer leftIndex = page*size;
        Integer rightIndex = (page+1)*size-1;
//        System.out.println("leftindex: "+leftIndex);
        if (rightIndex<list1.size()){
            for (int i=0;i<size;i++){
                finalList.add(list1.get(leftIndex+i));
            }
//            System.out.println("1--finalList.size: "+finalList.size());
            return finalList;
        }else if (leftIndex>=list1.size()){
//            System.out.println("2--finalList.size: "+finalList.size());
            return null;
        }else {
            for (int i=0;i<size;i++){
                if ((i+leftIndex)==list1.size()){
                    break;
                }
                finalList.add(list1.get(leftIndex+i));
            }
//            System.out.println("3--finalList.size: "+finalList.size());
            return finalList;
        }

    }

}
