package com.mahmoud.services;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import com.mahmoud.utils.ConfigReader;
import io.restassured.specification.RequestSpecification;

public class BookService {
    private static final String BOOKS_ENDPOINT = "/api/v1/Books";
    private static final String BOOK_BY_ID_ENDPOINT = BOOKS_ENDPOINT + "/{id}";

    private final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri(ConfigReader.get("api.base.url"))
            .setContentType(ContentType.JSON)
            .log(LogDetail.METHOD)
            .log(LogDetail.URI)
            .build();

    public Response getAllBooks() {
        return given()
                .spec(requestSpec)
                .when()
                .get(BOOKS_ENDPOINT);
    }

    public Response getBookById(int bookId) {
        return given()
                .spec(requestSpec)
                .pathParam("id", bookId)
                .when()
                .get(BOOK_BY_ID_ENDPOINT);
    }

    public Response createBook(String requestBody) {
        return given()
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .post(BOOKS_ENDPOINT);
    }

    public Response updateBook(int bookId, String requestBody) {
        return given()
                .spec(requestSpec)
                .pathParam("id", bookId)
                .body(requestBody)
                .when()
                .put(BOOK_BY_ID_ENDPOINT);
    }

    public Response deleteBook(int bookId) {
        return given()
                .spec(requestSpec)
                .pathParam("id", bookId)
                .when()
                .delete(BOOK_BY_ID_ENDPOINT);
    }
}
