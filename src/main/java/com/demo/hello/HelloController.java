// filepath: /d:/WebDev/SpringBoot/hello/src/main/java/com/demo/hello/HelloController.java
package com.demo.hello;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

@RestController
public class HelloController {

    @Autowired
    private MessageRepository messageRepository;

    @RequestMapping("")
    public String sayHello() {
        return "Hello, World!";
    }

    @GetMapping("/get")
    public String get() {
        return "GET Request";
    }

    @PostMapping("/post")
    public String post(@RequestBody Message message) throws IOException {
        // Save to H2 database
        messageRepository.save(message);

        // Save to JSON file
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("message.json"), message);

        return "POST Request with body: " + message.getContent();
    }

    @PutMapping("/put")
    public String put(@RequestBody Message message) throws IOException {
        messageRepository.save(message);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("message.json"), message);

        return "PUT Request with body: " + message.getContent();
    }
}