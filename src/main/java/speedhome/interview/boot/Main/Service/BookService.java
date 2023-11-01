package speedhome.interview.boot.Main.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import speedhome.interview.boot.Main.Entity.Book;
import speedhome.interview.boot.Main.Entity.BookStatus;
import speedhome.interview.boot.Main.Repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    public Book updateBook(Long bookId, Book updatedBook) {
         Book existingBook = bookRepository.findById(bookId).orElse(null);
        if (existingBook != null) {
            // Update the book's attributes with the values from the updatedBook.
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setIsbn(updatedBook.getIsbn());

             return bookRepository.save(existingBook);
        }
        return null;
    }
    public boolean hasBorrowedBooks(Long memberId) {
        List<Book> borrowedBooks = bookRepository.findByBorrowerId(memberId);
        return !borrowedBooks.isEmpty();
    }
    public String removeBook(Long bookId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);

        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            String deletedBookName = book.getTitle();
            bookRepository.deleteById(bookId);
            return deletedBookName;
        } else {
            return null;
        }
    }

    public Book addBook(Book newBook) {
         return bookRepository.save(newBook);
    }
    public List<Book> getAvailableBooks() {
        return bookRepository.findByStatus(BookStatus.AVAILABLE);
    }
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    public Book borrowBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        if (book != null && book.getStatus() == BookStatus.AVAILABLE) {
            book.setStatus(BookStatus.BORROWED);
            return bookRepository.save(book);
        }
        return null;
    }

    public Book returnBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        if (book != null && book.getStatus() == BookStatus.BORROWED) {
            book.setStatus(BookStatus.AVAILABLE);
            return bookRepository.save(book);
        }
        return null;
    }

    public void returnAllBooksForMember(Long memberId) {
         List<Book> borrowedBooks = bookRepository.findByBorrowerId(memberId);
        for (Book book : borrowedBooks) {
            book.setStatus(BookStatus.AVAILABLE);
            book.setBorrowerId(null);
        }
        bookRepository.saveAll(borrowedBooks);
    }
    
 }
