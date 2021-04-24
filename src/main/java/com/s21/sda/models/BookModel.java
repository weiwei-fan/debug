package com.s21.sda.models;

import java.util.List;

public class BookModel {
    private String link;
    private String title;
    private String subtitle;
    private List<String> author;
    private String publisher;
    private String description;
    private String coverImgLink;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverImgLink() {
        return coverImgLink;
    }

    public void setCoverImgLink(String coverImgLink) {
        this.coverImgLink = coverImgLink;
    }
}
