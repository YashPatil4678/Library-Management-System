import java.util.*;

public class LibraryManagementSystem {

    // Collections
    private static ArrayList<String> books = new ArrayList<>(); // Stores all books
    private static HashSet<String> borrowedBooks = new HashSet<>(); // Stores borrowed books
    private static LinkedList<String> userQueue = new LinkedList<>(); // Users waiting for books
    private static HashMap<String, String> userBorrowedBooks = new HashMap<>(); // User -> Book mapping

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        // Prepopulate some books
        books.add("1984");
        books.add("The Alchemist");
        books.add("Moby Dick");
        books.add("The Great Gatsby");
        
        while (true) {
        	//Main Menu
        	System.out.println("\n===== Library Management System =====");
        	System.out.println("1. Add a Book");
        	System.out.println("2. View All Books");
        	System.out.println("3. Register User");
        	System.out.println("4. Borrow a Book");
        	System.out.println("5. Return a Book");
        	System.out.println("6. View Borrowed Books");
        	System.out.println("7. Exit");
        	System.out.println("Enter your choice: ");
        	choice = scanner.nextInt();
        	scanner.nextLine();
        	
        	switch (choice) {
        	case 1 -> addBook(scanner);
        	case 2 -> viewBooks();
        	case 3 -> registerUser(scanner);
        	case 4 -> borrowBook(scanner);
        	case 5 -> returnBook(scanner);
        	case 6 -> viewBorrowedBooks();
        	case 7 -> {
        		System.out.println("Existing the system. Goodbye!");
        		scanner.close();
        		System.exit(0);
        	}
        	default -> System.out.println("Invalid choice. Please try Again");
        	}
        }
        
    }
    
    //1. Add a Book
    private static void addBook(Scanner scanner) {
    	System.out.println("Enter book name to add");
    	String bookName = scanner.nextLine();
    	books.add(bookName);
    	System.out.println("Book added Successfully!");
    }
    
    //2. View All Books
    private static void viewBooks() {
    	System.out.println("\n==== Available Books ====");
    	if (books.isEmpty()) {
    		System.out.println("No Books Available.");
    	} else {
    		for (String book : books) {
    			if (!borrowedBooks.contains(book)) {
    				System.out.println("- " + book);
    			}
    		}
    	}
    }
    
    //3. Register User
    private static void registerUser(Scanner scanner) {
    	System.out.println("Enter your name to register: ");
    	String userName = scanner.nextLine();
    	if (userQueue.contains(userName)) {
    		System.out.println("User already registered.");
    	} else {
    		userQueue.add(userName);
    		System.out.println("User Registered Successfully!");
    	}
    }
    
    //4. Borrow a Book
    private static void borrowBook(Scanner scanner) {
    	System.out.println("Enter your name: ");
    	String userName = scanner.nextLine();
    	if (!userQueue.contains(userName)) {
    		System.out.println("You are not registered. Please register first.");
    		return;
    	}
    	
    	System.out.println("Enter book name to borrow: ");
    	String bookName = scanner.nextLine();
    	if (!books.contains(bookName)) {
    		System.out.println("Book not available in the library.");
    	} else if (borrowedBooks.contains(bookName)) {
    		System.out.println("Book is already borrowed by someone else.");
    	} else {
    		borrowedBooks.add(bookName);
    		userBorrowedBooks.put(userName, bookName);
    		System.out.println("Book Borrowed Successfully!");
    	}
    }
    
    //5. Return a Book
    private static void returnBook(Scanner scanner) {
    	System.out.println("Enter your name: ");
    	String userName = scanner.nextLine();
    	if (!userBorrowedBooks.containsKey(userName)) {
    		 System.out.println("You are not registered. Please register first.");
             return;
    	}
    	 if (!userBorrowedBooks.containsKey(userName)) {
             System.out.println("You have not borrowed any books.");
         } else {
             String returnedBook = userBorrowedBooks.remove(userName);
             borrowedBooks.remove(returnedBook);
             System.out.println("Book returned successfully: " + returnedBook);
         }

    }
    
    // 6. View Borrowed Books
    private static void viewBorrowedBooks() {
        System.out.println("\n=== Borrowed Books ===");
        if (userBorrowedBooks.isEmpty()) {
            System.out.println("No books are currently borrowed.");
        } else {
            for (Map.Entry<String, String> entry : userBorrowedBooks.entrySet()) {
                System.out.println(entry.getKey() + " borrowed \"" + entry.getValue() + "\"");
            }
        }
    }
}