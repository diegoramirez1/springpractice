package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
    
    private static List<User> users = new ArrayList<>();
    private static int userCount = 1;

    static {
        users.add(new User(userCount++, "Adam", LocalDate.now().minusYears(30)));
        users.add(new User(userCount++, "Eve", LocalDate.now().minusYears(25)));
        users.add(new User(userCount++, "Jim", LocalDate.now().minusYears(20)));
    }

    public List<User> findAll() {
        return users;
    }

    public User findOne(Integer id) {
        //return users.get(id);
        User userRetrived = users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);

        if (Objects.isNull(userRetrived) ) {
            throw new UserNotFoundExeption("id:" + id);
        }

        return userRetrived;
    }

    public User saveUser(User user) {
        user.setId(userCount++);
        //user.setName(null);
        //user.setBirthday(null);
        
        users.add(user);
        return user;
    }

    public void deleteById(Integer id) {

        User userRetrived = users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);

        if (Objects.isNull(userRetrived) ) {
            throw new UserNotFoundExeption("id:" + id);
        }

        users.remove(userRetrived);
    }

}