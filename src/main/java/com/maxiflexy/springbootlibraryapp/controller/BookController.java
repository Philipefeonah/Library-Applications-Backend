package com.maxiflexy.springbootlibraryapp.controller;

import com.maxiflexy.springbootlibraryapp.entity.Book;
import com.maxiflexy.springbootlibraryapp.payloads.response.ShelfCurrentLoansResponse;
import com.maxiflexy.springbootlibraryapp.service.BookService;
import com.maxiflexy.springbootlibraryapp.utils.ExtractJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("https://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @PutMapping("/secure/checkout")
    public Book checkoutBook( @RequestHeader(value = "Authorization") String token, @RequestParam Long bookId) throws Exception{
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        return bookService.checkoutBook(userEmail, bookId);
    }

    @GetMapping("/secure/is-checkout/by-user")
    public boolean checkoutBookByUser(@RequestHeader(value = "Authorization") String token, @RequestParam Long bookId){
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        return bookService.checkoutBookByUser(userEmail, bookId);
    }

    @GetMapping("/secure/current-loans/count")
    public int currentLoansCount(@RequestHeader(value = "Authorization") String token){
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        return bookService.currentLoansCount(userEmail);
    }

    @GetMapping("/secure/current-loans")
    public List<ShelfCurrentLoansResponse> currentLoans(@RequestHeader(value = "Authorization") String token)
            throws Exception {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        return bookService.currentLoans(userEmail);
    }

    @PutMapping("/secure/return")
    public void returnBook(@RequestHeader(value = "Authorization") String token, @RequestParam Long bookId)
        throws Exception {

        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        bookService.returnBook(userEmail, bookId);
    }

    @PutMapping("/secure/renew/loan")
    public void renewLoan(@RequestHeader(value = "Authorization") String token, @RequestParam Long bookId)
            throws Exception {

        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        bookService.renewLoan(userEmail, bookId);
    }
}
