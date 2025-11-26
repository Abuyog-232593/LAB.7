package com.abuyog.Lab_7.controller;

import com.abuyog.Lab_7.BookService;
import com.abuyog.Lab_7.entity.Book;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class BookController {

    private final BookService service;

    @QueryMapping
    public List<Book> allBooks() {
        return service.findAllBooks();
    }

    @QueryMapping(name = "findBooksById")
    public List<Book> findBooksByIdList(@Argument Long id) {
        return service.findBooksById(id);
    }

    @QueryMapping
    public Book bookById(@Argument Long id) {
        return service.findById(id);
    }

    @SubscriptionMapping
    public List<Book> reactiveFetch() {
        return service.findAllBooks();
    }

    @MutationMapping
    public Book addNew(@Argument String title, @Argument int pages, @Argument Long authorId) {
        return service.addBook(title, pages, authorId);
    }

    @MutationMapping
    public Book addBook(@Argument String title, @Argument int pages, @Argument Long authorId) {
        return service.addBook(title, pages, authorId);
    }

    @MutationMapping(name = "updateTitle")
    public Book updateBookTitle(@Argument Long bookId, @Argument String newTitle) {
        return service.updateBookTitle(bookId, newTitle);
    }

    @MutationMapping
    public String deleteBook(@Argument @NonNull Long id) {
        return service.deleteBookById(id) ? "Book removed" : "Failed to remove book";
    }
}
