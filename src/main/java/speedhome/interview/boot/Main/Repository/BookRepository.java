package speedhome.interview.boot.Main.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import speedhome.interview.boot.Main.Entity.Book;
import speedhome.interview.boot.Main.Entity.BookStatus;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByStatus(BookStatus status);

    List<Book> findByBorrowerId(Long borrowerId);
}
