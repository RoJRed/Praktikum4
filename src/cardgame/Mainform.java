package cardgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Mainform {
    private JPanel mainPanel;
    private JLabel labDeck;
    private JLabel labPos1;
    private JButton neueKarte;
    private JButton naechste;

    private JDialog blackRed;

    private String[][] deck = new String[52][2];


    public Mainform() {
        neueKarte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                karteLegen();
            }
        });

        naechste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                naechsteKarte();
            }
        });
    }


    public void deckAnlegen() {
        for (int i=1; i<=52; i++) {
            String path ="./res/card" + i + ".png";

            deck[i-1][0] = path;

            if (i <= 13) {
                deck[i-1][1] = i.toString();
                deck[i-1][2] = "red";
            }
            else if (i>13 && i <=25) {
                deck[i-1][1] = (i-13).toString();
                deck[i-1][2] = "red";
            }
            else if (i>13 && i <=38) {
                deck[i-1][1] = (i-25).toString();
                deck[i-1][2] = "black";
            }
            else if (i>13 && i <=52) {
                deck[i-1][1] = (i-38).toString();
                deck[i-1][2] = "black";
            }
        }
    }

    public void mixDeck() {
        for (int i=1; i<=100; i++) {
            Random r = new Random();
            int card1 = r.nextInt(52);
            int card2 = r.nextInt(52);

            String path="./res/card" + card1 + ".png";
            String number =deck[card1][1];
            String color = deck[card1][2];

            deck[card1][0] = "./res/card" + card2 + ".png";
            deck[card1][1] = deck[card2][1];
            deck[card1][2] = deck[card2][1];

            deck[card2][0] = path;
            deck[card2][1] = number;
            deck[card2][2] = color;
        }
    }

    public void naechsteKarte() {
        for (int i = 1; i<=4; i++) {
            switch (i) {
                case 1:
                    if (deck[i][2] = ""
                    testRed();

                    Dialog dlg = new Dialog(this, Colorform);
                    String result = dlg.showDialog();
                    break;
                case 2:
                    testHigher();
                    break;
                case 3:
                    testInOut();
                    break;
                case 4:
                    testSymbol();
                    break;
                    i = 1;
            }
        }
    }

    public boolean testRed() {
        if (number <= 1) {
            return true;
        }
        else {
            return false;
        }
    }

    public void karteLegen() {
        Random r = new Random();
        String path ="./res/card" +r.nextInt(52) + ".png";
        java.net.URL imgUrl = getClass().getResource(path);
        labPos1.setIcon(new ImageIcon(imgUrl, "Karte"));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mein Kartenspiel");
        frame.setContentPane(new Mainform().mainPanel);

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
