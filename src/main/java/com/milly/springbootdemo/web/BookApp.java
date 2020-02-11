package com.milly.springbootdemo.web;

import com.milly.springbootdemo.domain.Book;
import com.milly.springbootdemo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class BookApp {

    @Autowired
    private BookService bookService;

   //find all book list
    @GetMapping("/books")
    public List<Book> getAll() {
        return bookService.findAll();
    }


    //add one book

    @PostMapping("/books")
    public Book post(Book book) {
        return bookService.save(book);
    }
//    public Book post(@RequestParam String name,
//                     @RequestParam String author,
//                     @RequestParam String description,
//                     @RequestParam int status) {
//        Book book = new Book();
//        book.setName(name);
//        book.setAuthor(author);
//        book.setDescription(description);
//        book.setStatus(status);
//        return bookService.save(book);
//    }

    //get one book
    @GetMapping("/books/{id}")
    public Optional<Book> getOne(@PathVariable long id) {
        return bookService.findOne(id);
    }

    //update one book
    @PutMapping("/books")
    public Book update(@RequestParam long id,
                     @RequestParam String name,
                     @RequestParam String author,
                     @RequestParam String description,
                     @RequestParam int status) {
        Book book = new Book();
        book.setId(id);
        book.setName(name);
        book.setAuthor(author);
        book.setDescription(description);
        book.setStatus(status);
        return bookService.save(book);
    }

    //delete one book
    @DeleteMapping("/books/{id}")
    public void deleteOne(@PathVariable long id){
        bookService.deleteOne(id);
    }

    //find book list by author
//    @PostMapping("/books/by")
//    public List<Book> findBy(@RequestParam String author) {
//        return bookService.findByAuthor(author);
//    }

    //find book list by author and status
//    @PostMapping("/books/by")
//    public List<Book> findBy(@RequestParam String author,
//                             @RequestParam int status) {
//        return bookService.findByAuthorAndStatus(author, status);
//    }

    //find by description
//    @PostMapping("/books/by")
//    public List<Book> findBy(@RequestParam String des) {
//        return bookService.findByDescriptionEndsWith(des);
//    }
    //find by JPQL
//    @PostMapping("/books/by")
//    public List<Book> findBy(@RequestParam int len) {
//        return bookService.findByJPQL(len);
//    }
    // update by JPQL
//    @PostMapping("/books/by")
//    public int findBy(@RequestParam int status, @RequestParam long id) {
//        return bookService.updateByJPQL(status, id);
//    }
    //delete by JPQL
//    @PostMapping("/books/by")
//    public int findBy(@RequestParam long id) {
//        return bookService.deleteByJPQL(id);
//    }
    //Test transactional
    @PostMapping("/books/by")
    public int findBy(@RequestParam long id, @RequestParam int status, @RequestParam long uid) {
        return bookService.deleteAndUpdate(id, status, uid);
    }

}
