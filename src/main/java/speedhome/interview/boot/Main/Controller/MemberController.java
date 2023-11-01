package speedhome.interview.boot.Main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import speedhome.interview.boot.Main.Entity.Book;
import speedhome.interview.boot.Main.Entity.Member;
import speedhome.interview.boot.Main.Service.BookService;
import speedhome.interview.boot.Main.Service.MemberService;

import java.util.List;

@RestController
@PreAuthorize("hasRole('MEMBER')")
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private BookService bookService;
    @Autowired
    private MemberService memberService;
    @PreAuthorize("hasRole('MEMBER')")
    @GetMapping("/books/getAvailableBooks")
    public List<Book> viewAvailableBooks() {
        return bookService.getAvailableBooks();
    }

    @PostMapping("/books/{bookId}/borrow")
    @PreAuthorize("hasRole('MEMBER')")
    public Book borrowBook(@PathVariable Long bookId) {
        return bookService.borrowBook(bookId);
    }
    @PreAuthorize("hasRole('MEMBER')")
    @PostMapping("/books/{bookId}/return")
    public Book returnBook(@PathVariable Long bookId) {
        return bookService.returnBook(bookId);
    }
    @PreAuthorize("hasRole('MEMBER')")
    @DeleteMapping("/account/delete/{memberId}")
    public ResponseEntity<String>  deleteMemberAccount(@PathVariable Long memberId) {
        boolean hasBorrowedBooks = bookService.hasBorrowedBooks(memberId);

        if (hasBorrowedBooks) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot delete account. Please return all borrowed books first.");
        }

        memberService.deleteMember(memberId);
        return ResponseEntity.status(HttpStatus.OK).body("Account deleted successfully.");

    }

}
