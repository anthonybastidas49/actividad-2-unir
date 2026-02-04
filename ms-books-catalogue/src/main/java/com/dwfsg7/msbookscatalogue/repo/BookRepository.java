package com.dwfsg7.msbookscatalogue.repo;

import com.dwfsg7.msbookscatalogue.domain.Book;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    boolean existsByIsbn(String isbn);
}