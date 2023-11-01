package speedhome.interview.boot.Main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import speedhome.interview.boot.Main.Entity.Book;
import speedhome.interview.boot.Main.Entity.CreateBooksResponse;
import speedhome.interview.boot.Main.Entity.CreateMemberResponse;
import speedhome.interview.boot.Main.Entity.Member;
import speedhome.interview.boot.Main.Service.BookService;
import speedhome.interview.boot.Main.Service.MemberService;
import javax.validation.Valid;

import java.util.List;

@RestController
@PreAuthorize("hasRole('LIBRARIAN')")
@RequestMapping("/librarian")
public class LibrarianController {
    @Autowired
    private BookService bookService;
    @Autowired
    private MemberService memberService;
    @PreAuthorize("hasRole('LIBRARIAN')")
    @PostMapping("/books/add")
    public ResponseEntity<CreateBooksResponse> addBook(@Valid @RequestBody Book book) {
        if (book == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(  new CreateBooksResponse("Request body is null"));
        }

        // Check individual fields
        if (book.getTitle() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( new CreateBooksResponse("Title is required"));
        }
        if (book.getAuthor() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( new CreateBooksResponse("Author is required"));
        }
        if (book.getIsbn() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( new CreateBooksResponse("ISBN is required"));
        }

        if (book.getStatus() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( new CreateBooksResponse("Status is required"));
        }

        Book addedBook = bookService.addBook(book);

        if (addedBook != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body( new CreateBooksResponse("Book created successfully",addedBook));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( new CreateBooksResponse("Failed to add book"));
        }
    }

    @PreAuthorize("hasRole('LIBRARIAN')")
    @GetMapping("/getAllBooks")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }
    @PreAuthorize("hasRole('LIBRARIAN')")
    @GetMapping("/getAllMembers")
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }
    @PreAuthorize("hasRole('LIBRARIAN')")
    @PutMapping("/books/update/{bookId}")
    public ResponseEntity<CreateBooksResponse> updateBook(@PathVariable Long bookId, @Valid @RequestBody Book book) {
        Book updatedBook = bookService.updateBook(bookId, book);

        if (updatedBook != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new CreateBooksResponse("Book Updated successfully",updatedBook));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @PreAuthorize("hasRole('LIBRARIAN')")
    @DeleteMapping("/books/remove/{bookId}")
    public ResponseEntity<String> removeBook(@PathVariable Long bookId) {
        String deletedBookName = bookService.removeBook(bookId);

        if (deletedBookName != null) {
            return ResponseEntity.status(HttpStatus.OK).body("Book with ID " + bookId + " (Name: " + deletedBookName + ") has been removed.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book with ID " + bookId + " not found.");
        }
    }
    @PreAuthorize("hasRole('LIBRARIAN')")
    @PostMapping("/members/add")
    public ResponseEntity<CreateMemberResponse> addMember(@Valid @RequestBody Member member) {
        if (member.getUsername() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CreateMemberResponse("Username is required"));
        }
        if (member.getEmail() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CreateMemberResponse("Email is required"));
        }
        if (member.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CreateMemberResponse("Password is required"));
        }
        if (member.getRoles() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CreateMemberResponse("Role is required"));
        }
        if (member.getFirstName() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CreateMemberResponse("First Name is required"));
        }
        if (member.getLastName() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CreateMemberResponse("Last Name is required"));
        }

        // Check for duplicate email
        Member existingMember = memberService.findByEmail(member.getEmail());
        if (existingMember != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CreateMemberResponse("Email already exists"));
        }

        Member newMember = memberService.addMember(member);
        if (newMember != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new CreateMemberResponse("Member created successfully", newMember));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CreateMemberResponse("Failed to create member"));
        }
    }

    @PreAuthorize("hasRole('LIBRARIAN')")
    @PutMapping("/members/update/{memberId}")
    public Member updateMember(@PathVariable Long memberId, @RequestBody Member member) {
        return memberService.updateMember(memberId, member);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation Error: " + ex.getMessage());
    }
    @PreAuthorize("hasRole('LIBRARIAN')")
    @DeleteMapping("/members/remove/{memberId}")
    public ResponseEntity<String> removeMember(@PathVariable Long memberId) {
        boolean deleted = memberService.deleteMember(memberId);
        if (deleted) {
            return ResponseEntity.status(HttpStatus.OK).body("Member with ID " + memberId + " deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member with ID " + memberId + " not found.");
        }
    }

}
