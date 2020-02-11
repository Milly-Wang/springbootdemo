package com.milly.springbootdemo.web;

import com.milly.springbootdemo.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

//@Controller
@RestController
@RequestMapping("api/v2")
public class HellloController {
//
//    @Autowired
//    private Book book;

    @Value("${book:name}")
    private String name;

    @Value("${book:author}")
    private String author;

    @Value("${book:description}")
    private String description;
//    @RequestMapping(value = "/say", method = RequestMethod.GET)
    @PostMapping("/say")
    public String Hello() {
        return "Hello Spring Boot";
    }
    @GetMapping("/books")
   // @ResponseBody
    public Object getAll() {
        Map<String,Object> map = new HashMap<>();
        map.put("name", "hello");
        map.put("age", 18);
        return map;
    }
    @GetMapping("/books/{id}/{username}")
    public Object getOne(@PathVariable long id, @PathVariable String username) {

        System.out.println("__id" + id);

        Map<String, Object> book = new HashMap<>();
        book.put("name", name);
        book.put("num", "8374y28y5r2");
        book.put("author", author);
        book.put("username", username);
        book.put("description", description);
        return book;
    }
    @PostMapping("/books")
    public Object post(@RequestParam("name") String name,
                       @RequestParam("author") String author,
                       @RequestParam("name") String isbn) {
        Map<String, Object> book = new HashMap<>();
        book.put("name", name);
        book.put("author", author);
        return  book;
    }

}
