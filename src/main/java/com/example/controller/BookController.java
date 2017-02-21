package com.example.controller;

import com.example.model.Book;
import com.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @RequestMapping(params = "getByBookName",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public List<Book> getByBookName(){
        Book book = new Book();
        book.setBookName("111");
        book.setAutor("222");
        bookRepository.save(book);
        List<Book> bookList = bookRepository.getByBookName("spring boot");
        return bookList;
    }
}
