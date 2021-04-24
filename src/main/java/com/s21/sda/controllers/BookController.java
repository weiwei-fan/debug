package com.s21.sda.controllers;

import com.s21.sda.models.BookModel;
import com.s21.sda.service.BookFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins="*", maxAge = 3600)
@RequestMapping("/")
public class BookController {

    @Autowired
    @Qualifier("bookFinderGoogleAdapter")
    private BookFinder bookFinder;

    @CrossOrigin
    @GetMapping("/book")
    public List<BookModel> findBooks(@RequestParam(value = "keyword", defaultValue = "") String keyword) {
        return bookFinder.find(keyword);
    }
}