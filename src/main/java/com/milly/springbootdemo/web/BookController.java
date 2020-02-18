package com.milly.springbootdemo.web;

import com.milly.springbootdemo.domain.Book;
import com.milly.springbootdemo.exception.BookNotFound;
import com.milly.springbootdemo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;


@Controller
//@RequestMapping("/api/v1")
public class BookController {

    @Autowired
    private BookService bookService;

    //get books list
    @GetMapping("/books")
    public String lists(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books",books);
        return "books";
    }
    //get books details
    @GetMapping("/books/{id}")
    public String detail(@PathVariable long id, Model model) {
        Book book = bookService.findOne(id);
        System.out.println("book is not null");
        if(book == null) {
            System.out.println("book is null");
            throw new BookNotFound("Book List does not exist");
        }
        model.addAttribute("book",book);
        return "book";
    }

    //跳转input提交页面
    @GetMapping("books/input")
    public String inputPage(Model model) {
        model.addAttribute("book",new Book());
        return "input";
    }

    @GetMapping("books/{id}/input")
    public String inputEditPage(@PathVariable long id, Model model) {
        Book book = bookService.findOne(id);
        model.addAttribute("book",book);
        return "input";
    }

    @PostMapping("/books")
    public String post(Book book, final RedirectAttributes attributes) {
        Book book1 = bookService.save(book);
        if(book1 != null) {
            attributes.addFlashAttribute("message", "<<" + book1.getName() + ">> Info submitted successfully");
        }
        return "redirect:/books";
    }
    //post -> redirect -> get
    //model只能在一个请求里使用;
    //
    @GetMapping("/books/{id}/delete")
    public String delete(@PathVariable long id, final RedirectAttributes attributes) {
        bookService.deleteOne(id);
        attributes.addFlashAttribute("message", "Info deleted successfully");
        return "redirect:/books";
    }

    //Exception handler in controller
    public ModelAndView handleException(HttpServletRequest request, Exception e) {

    }
}
