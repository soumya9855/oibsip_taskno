import java.sql.*;
import java.util.*;

public class LibraryManagementSystem {

    private Connection connection;
    private Statement statement;

    public LibraryManagementSystem() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
        statement = connection.createStatement();
    }

    public void addBook(String bookName, String author, String category) throws SQLException {
        statement.executeUpdate("INSERT INTO books (book_name, author, category) VALUES ('" + bookName + "', '" + author + "', '" + category + "')");
    }

    public void addUser(String username, String password) throws SQLException {
        statement.executeUpdate("INSERT INTO users (username, password) VALUES ('" + username + "', '" + password + "')");
    }

    public void issueBook(String username, String bookName) throws SQLException {
        statement.executeUpdate("INSERT INTO issues (username, book_name, issue_date, due_date) VALUES ('" + username + "', '" + bookName + "', CURRENT_DATE, CURRENT_DATE + 7)");
    }

    public void returnBook(String username, String bookName) throws SQLException {
        statement.executeUpdate("DELETE FROM issues WHERE username = '" + username + "' AND book_name = '" + bookName + "'");
    }

    public List<Book> getBooks() throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT * FROM books");
        List<Book> books = new ArrayList<>();
        while (rs.next()) {
            Book book = new Book();
            book.setBookName(rs.getString("book_name"));
            book.setAuthor(rs.getString("author"));
            book.setCategory(rs.getString("category"));
            books.add(book);
        }
        return books;
    }

    public List<User> getUsers() throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT * FROM users");
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            User user = new User();
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            users.add(user);
        }
        return users;
    }

    public static void main(String[] args) throws SQLException {
        LibraryManagementSystem libraryManagementSystem = new LibraryManagementSystem();
        // Add some books
        libraryManagementSystem.addBook("The Lord of the Rings", "J.R.R. Tolkien", "Fantasy");
        libraryManagementSystem.addBook("Harry Potter and the Sorcerer's Stone", "J.K. Rowling", "Children's");
        libraryManagementSystem.addBook("The Hunger Games", "Suzanne Collins", "Dystopian");

        // Add some users
        libraryManagementSystem.addUser("admin", "password");
        libraryManagementSystem.addUser("user1", "password1");
        libraryManagementSystem.addUser("user2", "password2");

        // Issue a book to the user
        libraryManagementSystem.issueBook("user1", "The Lord of the Rings");

        // List all the books
        List<Book> books = libraryManagementSystem.getBooks();
        for (Book book : books) {
            System.out.println(book.getBookName());
        }

        // List all the users
        List<User> users = libraryManagementSystem.getUsers();
        for (User user : users) {
            System.out.println(user.getUsername());
        }
    }
}