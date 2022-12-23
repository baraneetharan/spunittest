package com.kgisl.spunittest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kgisl.spunittest.entity.Book;
import com.kgisl.spunittest.service.BookService;

@CrossOrigin(origins = "*")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
   private BookService bookService;

   // @Autowired
   // private ModelMapper modelMapper;

   @PostMapping(value = "/", headers = "Accept=application/json")
   public ResponseEntity<Book> createBook(@RequestBody Book book) {
       Book actualBook=bookService.createBook(book);
       HttpHeaders headers = new HttpHeaders();
       // headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(book.getBookid()).toUri());
       return new ResponseEntity<>(actualBook,headers, HttpStatus.CREATED);
   }

   @GetMapping("/")
   public @ResponseBody ResponseEntity<List<Book>> all() {
       return new ResponseEntity<>(bookService.getBooks(), HttpStatus.OK);
   }

   @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<Book> getBookById(@PathVariable("id") long id) {
       Book book = bookService.findByBookId(id);
       if (book == null) {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
       return new ResponseEntity<>(book, HttpStatus.OK);
   }

   @PutMapping(value = "/{id}", headers="Accept=application/json")
   public ResponseEntity<Book> updateBook(@PathVariable("id") long id,@RequestBody Book currentBook)
   {
       Book book=bookService.updateBook(id,currentBook);
       return new ResponseEntity<>(book,HttpStatus.OK);
   }

   @DeleteMapping(value="/{id}", headers ="Accept=application/json")
   public ResponseEntity<Book> deleteBook(@PathVariable("id") Long id){
       Book user = bookService.findByBookId(id);
       if (user == null) {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
       bookService.deleteBookById(id);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }

}
