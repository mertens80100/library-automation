import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library("books.txt");

        while (true) {
            System.out.println("\n=== Library Menu ===");
            System.out.println("1 - Add Book");
            System.out.println("2 - List Books");
            System.out.println("3 - Search Book");
            System.out.println("4 - Borrow Book");
            System.out.println("5 - Return Book");
            System.out.println("6 - Remove Book");
            System.out.println("0 - Exit");
            System.out.print("Choice: ");

            String input = scanner.nextLine().trim();

            if (input.equals("1")) {
                System.out.print("Book title: ");
                String title = scanner.nextLine().trim();
                System.out.print("Author: ");
                String author = scanner.nextLine().trim();

                if (title.isEmpty() || author.isEmpty()) {
                    System.out.println("Title and author cannot be empty.");
                } else {
                    library.addBook(title, author);
                }
            } else if (input.equals("2")) {
                library.listBooks();
            } else if (input.equals("3")) {
                System.out.print("Search keyword: ");
                String keyword = scanner.nextLine().trim();
                library.searchBook(keyword);
            } else if (input.equals("4")) {
                System.out.print("Book ID to borrow: ");
                int id = Integer.parseInt(scanner.nextLine().trim());
                System.out.print("Student / Member name: ");
                String member = scanner.nextLine().trim();
                library.borrowBook(id, member);
            } else if (input.equals("5")) {
                System.out.print("Book ID to return: ");
                int id = Integer.parseInt(scanner.nextLine().trim());
                library.returnBook(id);
            } else if (input.equals("6")) {
                System.out.print("Book ID to remove: ");
                int id = Integer.parseInt(scanner.nextLine().trim());
                library.removeBook(id);
            } else if (input.equals("0")) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }
}
