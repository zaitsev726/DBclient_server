package Application.UserInterface.Frames.Edition.InvertaryInfo;


import Application.Entities.Rule;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/*
    Поиск правил внутри EditionTable
 */
public class RulesInEdition extends JFrame {
    private final JScrollPane scrollPane;

    public RulesInEdition(List<Rule> rules) {
        setTitle("Правила издания");
        setSize(600, 300);
        JPanel panel = new JPanel();
        Container container = getContentPane();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for (Rule rule : rules) {
            String str = "ID правила: " + rule.getId_rule() + " ID издания: " + rule.getId_edition() + " Правило: " + rule.getRule();
            panel.add(new JLabel(str));
        }
        scrollPane = new JScrollPane(panel);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }
}
