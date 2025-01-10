import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryManagementSystem {

    // Main frame and components
    private JFrame frame;
    private JTextField txtBookID, txtBookName, txtAuthor;
    private DefaultTableModel tableModel;

    public LibraryManagementSystem() {
        initialize();
    }

    private void initialize() {
        // Frame setup
        frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Top panel for book input
        JPanel topPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        topPanel.add(new JLabel("Book ID:"));
        txtBookID = new JTextField();
        topPanel.add(txtBookID);

        topPanel.add(new JLabel("Book Name:"));
        txtBookName = new JTextField();
        topPanel.add(txtBookName);

        topPanel.add(new JLabel("Author:"));
        txtAuthor = new JTextField();
        topPanel.add(txtAuthor);

        JButton btnAdd = new JButton("Add Book");
        btnAdd.addActionListener(this::addBook);
        topPanel.add(btnAdd);

        JButton btnDelete = new JButton("Delete Selected");
        btnDelete.addActionListener(this::deleteBook);
        topPanel.add(btnDelete);

        frame.add(topPanel, BorderLayout.NORTH);

        // Center panel for book table
        tableModel = new DefaultTableModel(new Object[]{"Book ID", "Book Name", "Author"}, 0);
        JTable table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);
        frame.add(tableScrollPane, BorderLayout.CENTER);

        // Show frame
        frame.setVisible(true);
    }

    private void addBook(ActionEvent e) {
        String bookID = txtBookID.getText();
        String bookName = txtBookName.getText();
        String author = txtAuthor.getText();

        if (bookID.isEmpty() || bookName.isEmpty() || author.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        tableModel.addRow(new Object[]{bookID, bookName, author});
        clearFields();
    }

    private void deleteBook(ActionEvent e) {
        int selectedRow = ((JTable) ((JScrollPane) frame.getContentPane().getComponent(1)).getViewport().getView()).getSelectedRow();
        if (selectedRow != -1) {
            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a row to delete!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        txtBookID.setText("");
        txtBookName.setText("");
        txtAuthor.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LibraryManagementSystem::new);
    }
}
