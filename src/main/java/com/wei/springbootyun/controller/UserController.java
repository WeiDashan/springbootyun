package com.wei.springbootyun.controller;



import com.wei.springbootyun.entity.LoginResave;
import com.wei.springbootyun.entity.Movies;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
//    @Autowired
//    private UsersRepository usersRepository;

//    @GetMapping("/findAll/{page}/{size}")
//    public Page<Movie> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
//        Pageable pageable = PageRequest.of(page-1,size);
//        return usersRepository.findAll(pageable);
//    }

    //    登录逻辑
    @PostMapping("/login")
    public Movies login(@RequestBody LoginResave loginResave){
        System.out.println(loginResave);

        return null;
    }


//
//
//    @PostMapping("/save")
//    public String save(@RequestBody Users users){
//        Users result = usersRepository.save(users);
//        if (result != null){
//            return "success";
//        }else{
//            return "error";
//        }
//    }
//
//    @GetMapping("/findById/{userid}")
//    public Users findById(@PathVariable("userid") Integer userid){
//        return usersRepository.findById(userid).get();
//    }
//
//    @PutMapping("/update")
//    public String update(@RequestBody Users users){
//        Users result = usersRepository.save(users);
//        if(result!=null){
//            return "success";
//        }else{
//            return "error";
//        }
//    }
//
//    @DeleteMapping("/deleteById/{userid}")
//    public void deleteById(@PathVariable("userid") Integer userid){
//        usersRepository.deleteById(userid);
//    }
//
//    @GetMapping("/findAllByDepartmentid/{departmentid}")
//    public List<Users> findAllByDepartmentid(@PathVariable("departmentid") Integer departmentid){
//        return usersRepository.findAllByDepartmentcategoryid(departmentid);
//    }

}

