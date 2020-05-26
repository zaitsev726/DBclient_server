package Application.UserInterface.Frames.IssuedBook;

import Application.Entities.AllReader;

import javax.swing.*;
import java.awt.*;
import java.util.List;
/*
    Поиск читателей, при запросе о выработке библиотекаря
 */
public class ReadersInIssuedBooks extends JFrame {

    public ReadersInIssuedBooks(List<AllReader> readers){
        setTitle("Читатели");
        setSize(600, 300);
        JPanel panel = new JPanel();
        Container container = getContentPane();


        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for(AllReader reader: readers) {
            String str = "ID читателя: " + reader.getId_reader() + " Тип читателя: " + reader.getType() + " ФИО " + reader.getSurname() + " " + reader.getName() + " " + reader.getPatronymic() + " ID библиотеки: " + reader.getId_library();
            panel.add(new JLabel(str));
        }
        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }

}
