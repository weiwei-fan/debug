package com.s21.sda.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.s21.sda.models.BookModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component("bookFinderGoogleAdapter")
public class BookFinderGoogleAdapter implements BookFinder{

    @Autowired
    private BookFinderGoogleAdaptee bookFinderForGoogle;

    @Override
    public List<BookModel> find(String keyword) {
        String searchResultStr = bookFinderForGoogle.findBookOnGoogle(keyword);
        if (searchResultStr == null || searchResultStr.isEmpty()) return new ArrayList<>();

        JsonNode searchResultObj = convertToJsonNode(searchResultStr);
        if (searchResultObj.get("totalItems").asInt() == 0) return new ArrayList<>();

        List<BookModel> books = convertToBooks(searchResultObj);

        return books;
    }

    private List<BookModel> convertToBooks(JsonNode searchResultObj) {
        List<BookModel> books = new ArrayList<>();
        JsonNode itemsObj = searchResultObj.get("items");
        if (itemsObj.isArray()) {
            Iterator<JsonNode> itemsItr = itemsObj.elements();
            while (true) {
                if (itemsItr.hasNext() == false) break;

                JsonNode itemObj = itemsItr.next();
                BookModel book = convertToBookModel(itemObj);

                books.add(book);
            } // end of while
        } // end of if
        
        return books;
    }

    private BookModel convertToBookModel(JsonNode itemObj) {
        BookModel book = new BookModel();

        book.setTitle(getTitle(itemObj));
        book.setAuthor(getAuthor(itemObj));
        book.setDescription(getDescription(itemObj));
        book.setSubtitle(getSubtitle(itemObj));
        book.setPublisher(getPublisher(itemObj));
        book.setLink(getLink(itemObj));
        book.setCoverImgLink(getCoverImgLink(itemObj));

        return book;
    }


    private String getCoverImgLink(JsonNode itemObj) {
        String coverImgLink = "";
        if (itemObj.has("volumeInfo")) {
            JsonNode volumeInfo = itemObj.get("volumeInfo");
            if (volumeInfo.has("imageLinks")) {
                JsonNode imageLinks = volumeInfo.get("imageLinks");
                if (imageLinks.has("thumbnail")) {
                    coverImgLink = imageLinks.get("thumbnail").asText();
                }
            }
        }
        return coverImgLink;

    }

    private String getLink(JsonNode itemObj) {
        String link = "";
        if (itemObj.has("selfLink")) {
            link = itemObj.get("selfLink").asText();
        }
        return link;
    }

    private String getPublisher(JsonNode itemObj) {
        String publisher = "";
        if (itemObj.has("volumeInfo")) {
            JsonNode volumeInfo = itemObj.get("volumeInfo");
            if (volumeInfo.has("publisher")) {
                publisher = volumeInfo.get("publisher").asText();
            }
        }
        return publisher;
    }

    private String getSubtitle(JsonNode itemObj) {
        String subtitle = "";
        if (itemObj.has("volumeInfo")) {
            JsonNode volumeInfo = itemObj.get("volumeInfo");
            if (volumeInfo.has("subtitle")) {
                subtitle = volumeInfo.get("subtitle").asText();
            }
        }
        return subtitle;
    }

    private String getDescription(JsonNode itemObj) {
        String description = "";
        if (itemObj.has("volumeInfo")) {
            JsonNode volumeInfo = itemObj.get("volumeInfo");
            if (volumeInfo.has("description")) {
                description = volumeInfo.get("description").asText();
            }
        }
        return description;
    }

    private String getTitle(JsonNode itemObj) {
        String title = "";
        if (itemObj.has("volumeInfo")) {
            JsonNode volumeInfo = itemObj.get("volumeInfo");
            if (volumeInfo.has("title")) {
                title = volumeInfo.get("title").asText();
            }
        }
        return title;
    }

    private List<String> getAuthor(JsonNode itemObj) {
        List<String> authors = new ArrayList<>();
        if (itemObj.has("volumeInfo")) {
            JsonNode volumeInfo = itemObj.get("volumeInfo");
            JsonNode authorsObj = volumeInfo.get("authors");
            if (authorsObj != null && authorsObj.isArray()) {
                Iterator<JsonNode> authorItr = authorsObj.elements();
                while (true) {
                    if (authorItr.hasNext() == false) break;

                    JsonNode authorObj = authorItr.next();
                    authors.add(authorObj.asText());
                } // end of while
            }

        }

        return authors;
    }

    private JsonNode convertToJsonNode(String str) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode obj = null;
        try {
            obj = mapper.readTree(str);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return obj;

    }
}
