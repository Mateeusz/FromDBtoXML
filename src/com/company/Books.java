package com.company;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "books")
@XmlAccessorType(XmlAccessType.FIELD)


public class Books {

    @XmlElement(name = "book")
    private List<Book> bookList = new ArrayList<Book>();

    public Books() {
    }

    public void insertBook(Book book) {
        bookList.add(book);
    }

    public void displayBooks() {
        System.out.println("WYŚWITLAM LISTE: ");
        for (Book book : bookList) {
            System.out.println(book.getTitle() + ", " + book.getAuthorFirstName() + " " + book.getAuthorLastName());
        }
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

}
