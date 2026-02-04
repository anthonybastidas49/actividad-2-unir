package com.dwfsg7.msbookscatalogue.api;

import com.dwfsg7.msbookscatalogue.domain.Book;
import com.dwfsg7.msbookscatalogue.repo.BookRepository;
import com.dwfsg7.msbookscatalogue.repo.BookSpecifications;
import jakarta.validation.Valid;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository repo;

    public BookController(BookRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public ResponseEntity<Book> create(@Valid @RequestBody Book book) {
        if (book.getId() != null) book.setId(null);
        Book saved = repo.save(book);
        return ResponseEntity.created(URI.create("/books/" + saved.getId())).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<?> search(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) Integer rating,
            @RequestParam(required = false) Boolean visible,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate publishedFrom,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate publishedTo
    ) {
        Specification<Book> spec = Specification.where(BookSpecifications.titleContains(title))
                .and(BookSpecifications.authorContains(author))
                .and(BookSpecifications.categoryEquals(category))
                .and(BookSpecifications.isbnEquals(isbn))
                .and(BookSpecifications.ratingEquals(rating))
                .and(BookSpecifications.visibleEquals(visible))
                .and(BookSpecifications.publishedFrom(publishedFrom))
                .and(BookSpecifications.publishedTo(publishedTo));

        return ResponseEntity.ok(repo.findAll(spec));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> replace(@PathVariable Long id, @Valid @RequestBody Book book) {
        return repo.findById(id).map(existing -> {
            book.setId(id);
            return ResponseEntity.ok(repo.save(book));
        }).orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Book> patch(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        return repo.findById(id).map(existing -> {
            fields.forEach((k, v) -> {
                switch (k) {
                    case "title" -> existing.setTitle((String) v);
                    case "author" -> existing.setAuthor((String) v);
                    case "category" -> existing.setCategory((String) v);
                    case "isbn" -> existing.setIsbn((String) v);
                    case "rating" -> existing.setRating((Integer) v);
                    case "visible" -> existing.setVisible((Boolean) v);
                    case "stock" -> existing.setStock((Integer) v);
                    case "publicationDate" -> existing.setPublicationDate(LocalDate.parse((String) v));
                }
            });
            return ResponseEntity.ok(repo.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}