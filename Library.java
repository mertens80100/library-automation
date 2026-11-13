import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Library {
    private ArrayList<Book> books;
    private String fileName;

    public Library(String fileName) {
        this.fileName = fileName;
        this.books = new ArrayList<>();
        loadFromFile();
    }

    public void addBook(String title, String author) {
        int nextId = 1;
        for (Book b : books) {
            if (b.getId() >= nextId) {
                nextId = b.getId() + 1;
            }
        }
        Book newBook = new Book(nextId, title, author, false, "-");
        books.add(newBook);
        saveToFile();
        System.out.println("Book added: " + newBook);
    }

    public void listBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }
        for (Book b : books) {
            System.out.println(b);
        }
    }

    public void searchBook(String keyword) {
        String lower = keyword.toLowerCase();
        boolean found = false;
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(lower) || b.getAuthor().toLowerCase().contains(lower)) {
                System.out.println(b);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No matching books found.");
        }
    }

    public void borrowBook(int id, String memberName) {
        for (Book b : books) {
            if (b.getId() == id) {
                if (b.isBorrowed()) {
                    System.out.println("This book is already borrowed by " + b.getBorrowedBy());
                } else {
                    b.borrowBook(memberName);
                    saveToFile();
                    System.out.println("Book borrowed successfully.");
                }
                return;
            }
        }
        System.out.println("Book ID not found.");
    }

    public void returnBook(int id) {
        for (Book b : books) {
            if (b.getId() == id) {
                if (!b.isBorrowed()) {
                    System.out.println("This book is already in the library.");
                } else {
                    b.returnBook();
                    saveToFile();
                    System.out.println("Book returned successfully.");
                }
                return;
            }
        }
        System.out.println("Book ID not found.");
    }

    public void removeBook(int id) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == id) {
                Book removed = books.remove(i);
                saveToFile();
                System.out.println("Removed: " + removed.getTitle());
                return;
            }
        }
        System.out.println("Book ID not found.");
    }

    private void loadFromFile() {
        File file = new File(fileName);
        if (!file.exists()) {
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 5) {
                    int id = Integer.parseInt(parts[0]);
                    String title = parts[1];
                    String author = parts[2];
                    boolean borrowed = Boolean.parseBoolean(parts[3]);
                    String borrowedBy = parts[4];
                    books.add(new Book(id, title, author, borrowed, borrowedBy));
                }
            }
        } catch (Exception e) {
            System.out.println("Could not read file: " + e.getMessage());
        }
    }

    private void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            for (Book b : books) {
                pw.println(b.toFileLine());
            }
        } catch (Exception e) {
            System.out.println("Could not save file: " + e.getMessage());
        }
    }
}
