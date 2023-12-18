package cardgame;

import javax.swing.*;
import java.awt.event.*;

public class Colorform extends JDialog {
    private JPanel contentPane;
    private JButton buttonCancel;
    private JButton rotButton;
    private JButton schwarzButton;

    public Colorform() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonCancel);

        rotButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onRot();
            }
        });

        schwarzButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onSchwarz();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onRot() {
        // add your code here
        dispose();
    }

    private void onSchwarz() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        Colorform dialog = new Colorform();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    public ReturnValue() {
        setVisible(true);
        return result;
    }
}
