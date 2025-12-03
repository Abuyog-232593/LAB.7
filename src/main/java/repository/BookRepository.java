package com.abuyog.Lab_7.repository;

import com.abuyog.Lab_7.entity.Author;
import com.abuyog.Lab_7.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * Inside the angle bracket for the JpaRepository, we define the Type of object we are handling
 * with this repo and the datatype used for the ID field of the object.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    /**
     * SELECT * FROM book as b
     * LEFT JOIN author as a
     * ON b.auth_id = a.auth_id
     * WHERE a.auth_name = ?
     */
    @Query("""
           SELECT b FROM Book b
           WHERE b.author.authName LIKE :authName
           """)
    List<Book> findByAuthorName(String authName);


    List<Book> findByAuthor(Author author);

    /**
     * try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD) {
     *    PreparedStatement ps = conn.prepareStatement("""
     *      SELECT * FROM book
     *      WHERE book.title = ?
     *    """);
     *
     *    ps.setString("Java How to program");
     * }
     */
}