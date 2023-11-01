package speedhome.interview.boot.Main.Entity;

public class CreateBooksResponse {

    private String message;
    private Book book;

    public CreateBooksResponse(String message) {
        this.message = message;
    }

    public CreateBooksResponse(String message, Book book) {
        this.message = message;
        this.book = book;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}