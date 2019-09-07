package com.company;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {

        List<Book> bookList = new ArrayList<>();
        Connection connection = DBconnector.getConnection();
        Books books = new Books();
        try {
            ResultSet rs = connection.createStatement().executeQuery("SELECT ISBN, title, authorFirstName, authorLastName, shelfId, isHired\n" +
                    "FROM bookType INNER JOIN book ON book.bookTypeId = bookType.bookTypeId\n" +
                    "WHERE isHired = true;\n");
            while (rs.next()) {
                Book book = new Book();
                book.setISBN(rs.getString(1));
                book.setTitle(rs.getString(2));
                book.setAuthorFirstName(rs.getString(3));
                book.setAuthorLastName(rs.getString(4));
                book.setShelfId((Integer.parseInt(rs.getString(5))));
                book.setHired(Boolean.parseBoolean(rs.getString(6)));
                books.insertBook(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        books.displayBooks();

        for (Book book: books.getBookList()) {
            bookList.add(book);
        }



        JAXBContext jaxbContext = null;

        try {
            jaxbContext = JAXBContext.newInstance(Books.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            System.out.println(books.getBookList().size());
            marshaller.marshal(books.getBookList(), new File("net.xml"));
        } catch (PropertyException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }


//        readWriteXML.readFromFile();


        try {
            connection.createStatement().execute("INSERT INTO book(bookTypeId, shelfId, isHired) VALUES(3, 9, FALSE);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
