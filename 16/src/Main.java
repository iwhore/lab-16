import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Книжкова база даних");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        BookManager bookManager = new BookManager();

        // Меню
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Файл");
        JMenuItem aboutItem = new JMenuItem("Про програму");
        fileMenu.add(aboutItem);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);

        // Список для відображення книг
        DefaultListModel<String> bookListModel = new DefaultListModel<>();
        JList<String> bookList = new JList<>(bookListModel);
        frame.add(new JScrollPane(bookList), BorderLayout.CENTER);

        // Панель з кнопками
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Додати книгу");
        JButton deleteButton = new JButton("Видалити книгу");
        JButton searchButton = new JButton("Пошук книги");
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(searchButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Дія додавання книги
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField titleField = new JTextField();
                JTextField authorField = new JTextField();
                JTextField yearField = new JTextField();
                Object[] fields = {
                    "Назва:", titleField,
                    "Автор:", authorField,
                    "Рік видання:", yearField
                };

                int option = JOptionPane.showConfirmDialog(frame, fields, "Додати книгу", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    String title = titleField.getText();
                    String author = authorField.getText();
                    int year = Integer.parseInt(yearField.getText());
                    Book newBook = new Book(title, author, year);
                    bookManager.addBook(newBook);
                    bookListModel.addElement(newBook.toString());
                }
            }
        });

        // Дія видалення книги
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = bookList.getSelectedIndex();
                if (selectedIndex != -1) {
                    bookManager.removeBook(selectedIndex);
                    bookListModel.remove(selectedIndex);
                } else {
                    JOptionPane.showMessageDialog(frame, "Виберіть книгу для видалення.");
                }
            }
        });

        // Дія пошуку книги
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchTerm = JOptionPane.showInputDialog(frame, "Введіть назву або автора книги для пошуку:");
                if (searchTerm != null && !searchTerm.trim().isEmpty()) {
                    java.util.List<Book> results = bookManager.searchBook(searchTerm);
                    bookListModel.clear();
                    for (Book book : results) {
                        bookListModel.addElement(book.toString());
                    }
                }
            }
        });

        // Дія "Про програму"
        aboutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Книжкова база даних\nВерсія 1.0\nАвтор: [Ваше ім'я]");
            }
        });

        frame.setVisible(true);
    }
}