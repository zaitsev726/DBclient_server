package Appli.UserInterface.Pages.ReadersPage;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;

public class ReadersForm extends JPanel {
    private JPanel readersPanel;
    private JLabel readersLabel;
    public JTextField nameTextField;
    public JTextField surnameTextField;
    public JTextField patronymicTextField;
    public JTextField idTextField;
    public JButton addButton;
    public JButton searchButton;
    public JButton backButton;
    public JButton removeButton;
    public JComboBox typeComboBox;
    private JLabel nameLabel;
    private JLabel surnameLabel;
    private JLabel patronymicLabel;
    private JLabel idLabel;
    private JLabel typeLabel;

    public ReadersForm() {
        this.add(readersPanel);
        setVisible(true);
    }

}
