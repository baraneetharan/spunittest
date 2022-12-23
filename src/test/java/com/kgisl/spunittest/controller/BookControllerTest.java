package com.kgisl.spunittest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.kgisl.spunittest.entity.Book;
import com.kgisl.spunittest.service.BookService;

/**
 * BookController
 */

@ExtendWith(MockitoExtension.class)

public class BookControllerTest {
  @Mock
  private BookService bookService;

  @InjectMocks
  private BookController bookController;

  public static List<Book> expected;
  Book book1 = new Book();
  Book book2 = new Book();

  // public Book book2 = new BookBuilder().id(2L).name("aravinth").build();

  @Test
  public void allTest() {

    expected = Arrays.asList(book1, book2);
    System.out.println(expected);
    when(bookService.getBooks()).thenReturn(expected);
    ResponseEntity<List<Book>> actual = bookController.all();
    assertNotNull(actual);
    assertEquals(expected, actual.getBody());
    assertEquals(HttpStatus.OK, actual.getStatusCode());
  }

  @Test
  public void getBookByIdTest() {
    Long id = 1L;
    when(bookService.findByBookId(id)).thenReturn(null);
    ResponseEntity<Book> actual = bookController.getBookById(id);
    assertNotNull(actual);
  }

  @Test
  public void createBookTest() {
    when(bookService.createBook(book1)).thenReturn(book1);
    bookController.createBook(book1);
  }

  @Test
  public void updateBookTest() {
    // Book edit = new BookBuilder().name("shanmugam").build();
    Long id = 1L;
    // when(bookService.updateBook(id, book1)).thenReturn(book1);
    ResponseEntity<Book> actual = bookController.updateBook(id, book1);
    assertNotNull(actual);
    System.out.println("Actual is  "+actual.getBody());
    System.out.println("expected-->" + expected);
    // assertEquals(edit, actual.getBody());
  }

  @Test
  public void deleteBookTest() {
    Long id = 1L;
    when(bookService.findByBookId(id)).thenReturn(book1);
    bookController.deleteBook(id);
    verify(bookService).deleteBookById(id);
  }
}