package Appli.UserInterface.Frames.Edition.InvertaryInfo;


import Appli.Entities.Rule;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SearchRulesInEditionForm extends JFrame {
    private JScrollPane scrollPane;
    private JPanel panel = new JPanel();
    public SearchRulesInEditionForm(List<Rule> rules){
        setTitle("Правила издания");
        setSize(600, 300);
        panel = new JPanel();
        Container container = getContentPane();


        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for(Rule rule: rules) {
            String str = "ID правила: " + rule.getId_rule() + " ID издания: " + rule.getId_edition() + " Правило: " + rule.getRule();
            panel.add(new JLabel(str));
        }
        scrollPane = new JScrollPane(panel);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }
}
