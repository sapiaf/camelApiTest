package it.reply.camel.controller;

import it.reply.camel.model.User;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private ProducerTemplate producerTemplate;

    @PostMapping
    public void createUser(@RequestBody User user) {
        producerTemplate.sendBody("direct:createUser", user);
    }

    @GetMapping("/{id}")
    public User readUser(@PathVariable Integer id) {
        return producerTemplate.requestBody("direct:readUser", id, User.class);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable Integer id, @RequestBody User user) {
        user.setId(id);
        producerTemplate.sendBody("direct:updateUser", user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        producerTemplate.sendBody("direct:deleteUser", id);
    }
}
