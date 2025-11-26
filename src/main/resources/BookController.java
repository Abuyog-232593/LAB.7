package com.abuyog.graphql_example.controller;

import com.abuyog.graphql_example.BookService;
import com.abuyog.graphql_example.entity.Book;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class BookController {

    private final BookService service;

    @QueryMapping
    public List<Book> allBooks() {
        return service.findAllBooks();
    }

    @SubscriptionMapping
    public List<Book> reactiveFetch() {
        return service.findAllBooks();
    }

    @MutationMapping
    public Book addNew(Book book) {
        return null;
    }

    @QueryMapping("findBooksById")
    public List<Book> allBooks(@Argument Long id) {
        return service.findAllBooks();
    }

    @QueryMapping
    public Book bookById(@Argument Long id) {
        return service.findById(id);
    }

    @MutationMapping
    public Book addBook(
            @Argument String title,
            @Argument int pages,
            @Argument Long authorId
    ) {
        return service.addBook(title, pages, authorId);
    }

    @MutationMapping(name = "updateTitle")
    public Book updateBookTitle(
            @Argument Long bookId,
            @Argument String newTitle
    ) {
        return service.updateBookTitle(bookId, newTitle);
    }

    @MutationMapping
    public String deleteBook(@Argument @NonNull Long id) {
        return service.deleteBookById(id) ? "Book removed" : "Failed to remove book";
    }
}