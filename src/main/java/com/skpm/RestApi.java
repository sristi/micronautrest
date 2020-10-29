package com.skpm;

import com.skpm.pojo.User;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller("/apis")
public class RestApi {
    List<User> users = new ArrayList<User>();

    @Post("/users/")
    public List<User> add(User user){
        Long id = 0L;
        if(users.size()>0) {
            User userOfMaxId = users.stream().reduce((a, b) -> a.getId().compareTo(b.getId()) > 0 ? a : b).get();
            //Long id = Long.valueOf(users.size()+1);
            id = Long.valueOf(userOfMaxId == null ? 1 : userOfMaxId.getId() + 1);
        }else
            id = 1L;
        user.setId(id);
        /*user.setName("user"+id);
        user.setPassword("pwd"+id);
        user.setUserName("u"+id);*/
        users.add(user);
        return users;
    }

    @Get("/users/{uid}")
    public Optional<User> findById(Long uid){
        return users.stream()
                .filter(item->item.getId().equals(uid))
                .findFirst();
    }

    @Get("/users")
    public List<User> list(){
        return users;
    }
    @Get
    public Object ping(){
        return "Hello from micronaut API...";
    }
    @Delete("/users/{userId}")
    public List<User> remove (Long userId){
        users.removeIf(usr->usr.getId().equals(userId));
        return users;
    }

}
