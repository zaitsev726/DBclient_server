package Application.UserInterface.Frames.IssuedBook;

import Application.Entities.AllReader;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SearchReadersInIssuedBooks extends JFrame {
    private JScrollPane scrollPane;
    private JPanel panel = new JPanel();
    public SearchReadersInIssuedBooks(List<AllReader> readers){
        setTitle("Читатели");
        setSize(600, 300);
        panel = new JPanel();
        Container container = getContentPane();


        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for(AllReader reader: readers) {
            String str = "ID читателя: " + reader.getId_reader() + " Тип читателя: " + reader.getType() + " ФИО " + reader.getSurname() + " " + reader.getName() + " " + reader.getPatronymic() + " ID библиотеки: " + reader.getId_library();
            panel.add(new JLabel(str));
        }
        scrollPane = new JScrollPane(panel);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }

}
