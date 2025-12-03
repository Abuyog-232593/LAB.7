package com.abuyog.Lab_7.;

import com.abuyog.Lab_7.entity.Author;
import com.abuyog.Lab_7.entity.Book;
import com.abuyog.Lab_7.repository.AuthorRepository;
import com.abuyog.Lab_7.repository.BookRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repository;
    private final AuthorRepository authorRepository;

    // This pre-populates the database after the construction of this class
    @PostConstruct
    public void init() {
        List<Author> authors = List.of(
                new Author(null, "J.R.R Tolkien", new ArrayList<>()),
                new Author(null, "Sir Arthur Conan Doyle", new ArrayList<>()),
                new Author(null, "Jules Verne", new ArrayList<>())
        );

        authorRepository.saveAll(authors);

        List<Book> books = List.of(
                new Book(null,
                        "Lord of the Rings",
                        1000,
                        authorRepository.findById(1L)
                                .orElseThrow()),
                new Book(null, "The Adventures of Sherlock Holmes", 1000, authorRepository.findById(2L).orElseThrow())
        );

        repository.saveAll(books);
    }

    public List<Book> findAllBooks() {
        return repository.findAll();
    }

    public Book findById(Long bookId) {
        return repository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found."));
    }




    public Book addBook(NewBookDto newBookDto) {
        // The succeeding code should be included in a utility class to make the code more readable
        // and follow the principle of SOC.
        var book = new Book(
                newBookDto.authId(),
                newBookDto.title(),
                newBookDto.pages(),
                authorRepository.findById(newBookDto
                                .authId())
                        .orElseThrow());

        return repository.save(book);
    }

    public void updateBook(Long bookId, NewBookDto updatedBook) {
        repository.findById(bookId).ifPresent(book -> {
            book.setTitle(updatedBook.title());
            book.setPages(updatedBook.pages());
            book.setAuthor(authorRepository.findById(updatedBook.authId())
                    .orElseThrow());
            repository.save(book);
        });
    }

    public boolean deleteBookById(Long id) {
        repository.deleteById(id);
        return repository.findById(id).isEmpty();
    }
}