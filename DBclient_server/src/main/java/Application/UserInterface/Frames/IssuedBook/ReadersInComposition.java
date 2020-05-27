package Application.UserInterface.Frames.IssuedBook;

import Application.Controllers.IssuedPageController;
import Application.Entities.AllReader;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReadersInComposition extends JFrame {
    public ReadersInComposition(Map<AllReader, String> readers){
        setTitle("Читатели");
        setSize(600, 300);
        JPanel panel = new JPanel();
        Container container = getContentPane();


        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for(Map.Entry<AllReader,String> entry: readers.entrySet()) {
            AllReader reader = entry.getKey();
            String str = "ID читателя: " + reader.getId_reader() + " Тип читателя: " + reader.getType() + " ФИО " + reader.getSurname() +
                    " " + reader.getName() + " " + reader.getPatronymic() + " ID библиотеки: " + reader.getId_library() + " НАЗВАНИЕ ПРОИЗВЕДЕНИЯ: " + entry.getValue();
            panel.add(new JLabel(str));
        }
        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }
}