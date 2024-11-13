import java.util.ArrayList;
import java.util.List;

public class BookManager {
    private ArrayList<Book> books;

    public BookManager() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(int index) {
        if (index >= 0 && index < books.size()) {
            books.remove(index);
        }
    }

    public List<Book> searchBook(String searchTerm) {
        List<Book> results = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(searchTerm.toLowerCase()) ||
                book.getAuthor().toLowerCase().contains(searchTerm.toLowerCase())) {
                results.add(book);
            }
        }
        return results;
    }

    public List<Book> getBooks() {
        return books;
    }
}
