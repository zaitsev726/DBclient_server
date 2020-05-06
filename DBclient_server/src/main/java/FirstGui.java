import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;

public class FirstGui extends JFrame {

    private JPanel nextPanel;
    private JButton startButton;
    private JButton exitButton;
    private JPanel menuPanel;

    public FirstGui() {
        setContentPane(menuPanel);
        setVisible(true);

    }

}
