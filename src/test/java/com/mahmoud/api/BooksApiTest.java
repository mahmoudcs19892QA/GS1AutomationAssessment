package com.mahmoud.api;

import com.mahmoud.services.BookService;
import com.mahmoud.utils.ConfigReader;
import com.mahmoud.utils.ExcelReader;
import com.mahmoud.utils.JsonReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.nio.file.Paths;

import static org.hamcrest.Matchers.*;

public class BooksApiTest {
    private final BookService bookService = new BookService();

    private String getTestDataPath(String fileName) {
        return Paths.get(ConfigReader.get("base.directory"), fileName)
                .toAbsolutePath()
                .toString();
    }

    @Test(priority = 1)
    public void getAllBooks() {
        Response response = bookService.getAllBooks();

        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("size()", greaterThan(0))
                .body("[0].id", notNullValue())
                .body("[0].title", notNullValue())
                .body("[0].pageCount", notNullValue());
    }

    @DataProvider(name = "bookIds")
    public Object[][] bookIds() {
        return ExcelReader.getTestData(
                getTestDataPath("books.xlsx"),
                "Books"
        );
    }

    @DataProvider(name = "invalidBookIds")
    public Object[][] invalidBookIds() {
        return ExcelReader.getTestData(
                getTestDataPath("books.xlsx"),
                "invalidBooks"
        );
    }

    @Test(priority = 2, dataProvider = "bookIds")
    public void getBookById(int bookId) {
        Response response = bookService.getBookById(bookId);

        response.then()
                .statusCode(200)
                .body("id", equalTo(bookId))
                .body("title", notNullValue());
    }

    @Test(priority = 3, dataProvider = "invalidBookIds")
    public void getBookByIdWithInvalidId(int invalidBookId) {
        Response response = bookService.getBookById(invalidBookId);

        response.then()
                .statusCode(404)
                .body("title", equalTo("Not Found"))
                .body("status", equalTo(404));
    }

    @Test(priority = 4)
    public void createBook() {
        String requestBody = JsonReader.readJsonFile(
                getTestDataPath("books-create.json")
        );
        Response response = bookService.createBook(requestBody);

        response.then()
                .statusCode(200)
                .body("id", equalTo(0))
                .body("title", equalTo("Automation Test Book"))
                .body("description", equalTo("Book created during API automation assessment"))
                .body("pageCount", equalTo(100))
                .body("excerpt", equalTo("Test excerpt"))
                .body("publishDate", notNullValue());
    }

    @Test(priority = 5)
    public void updateBook() {
        String requestBody = JsonReader.readJsonFile(
                getTestDataPath("books-update.json")
        );

        Response response = bookService.updateBook(1, requestBody);

        response.then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("title", equalTo("Updated Automation Test Book"))
                .body("description", equalTo("Book updated during API automation assessment"))
                .body("pageCount", equalTo(150))
                .body("excerpt", equalTo("Updated test excerpt"))
                .body("publishDate", notNullValue());
    }

    @Test(priority = 6)
    public void deleteBook() {
        Response response = bookService.deleteBook(1);

        response.then()
                .statusCode(200);
    }
}
