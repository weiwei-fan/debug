package com.s21.sda.service;

import com.s21.sda.models.BookModel;

import java.util.List;

public interface BookFinder {
    public List<BookModel> find(String keyword);
}
