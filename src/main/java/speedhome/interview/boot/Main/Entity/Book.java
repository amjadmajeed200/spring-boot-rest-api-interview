package speedhome.interview.boot.Main.Entity;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String isbn;
    @Column(nullable = true)

    private Long borrowerId;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookStatus status;
    public Book() {

    }
    public Book(String title, String author, String isbn, Long borrowerId, BookStatus status) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.borrowerId = borrowerId;
        this.status = status;
    }
    public Book(String title, String author, String isbn, BookStatus status) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.status = status;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }



     public Long getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(Long borrowerId) {
        this.borrowerId = borrowerId;
    }
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", status=" + status +
                '}';
    }
}
