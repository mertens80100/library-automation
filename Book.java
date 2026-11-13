public class Book {
    private int id;
    private String title;
    private String author;
    private boolean borrowed;
    private String borrowedBy;

    public Book(int id, String title, String author, boolean borrowed, String borrowedBy) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.borrowed = borrowed;
        this.borrowedBy = borrowedBy;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public String getBorrowedBy() {
        return borrowedBy;
    }

    public void borrowBook(String memberName) {
        this.borrowed = true;
        this.borrowedBy = memberName;
    }

    public void returnBook() {
        this.borrowed = false;
        this.borrowedBy = "-";
    }

    public String toFileLine() {
        return id + ";" + title + ";" + author + ";" + borrowed + ";" + borrowedBy;
    }

    @Override
    public String toString() {
        String status = borrowed ? "Borrowed by " + borrowedBy : "Available";
        return "[" + id + "] " + title + " - " + author + " (" + status + ")";
    }
}
