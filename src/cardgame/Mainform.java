package cardgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Random;

public class Mainform {
    private JPanel mainPanel;
    private JLabel labDeck;
    private JLabel labPos1;
    private JButton neueKarte;
    private JButton naechste;
    private JButton Button1;
    private JButton Button2;

    private JDialog blackRed;

    private String[][] deck = new String[52][2];
    private int cardNo = 1;
    private int round = 1;


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
                naechsteKarte("red");
            }
        });

        Button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                naechsteKarte("opt1");
            }
        });

        Button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                naechsteKarte("opt2");
            }
        });
    }


    public void deckAnlegen() {
        for (int i=1; i<=52; i++) {
            String path ="./res/card" + i + ".png";

            deck[i-1][0] = path;

            if (i <= 13) {
                deck[i-1][1] = String.valueOf(i);
                deck[i-1][2] = "red";
            }
            else if (i>13 && i <=25) {
                deck[i-1][1] = String.valueOf((i-13));
                deck[i-1][2] = "red";
            }
            else if (i>13 && i<=38) {
                deck[i-1][1] = String.valueOf((i-25));
                deck[i-1][2] = "black";
            }
            else if (i>13 && i<=52) {
                deck[i-1][1] = String.valueOf((i-38));
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

    public void naechsteKarte(String opt) {
        cardNo++;

        URL iconURL = getClass().getResource("./res/card" + cardNo + ".png");
        ImageIcon icon = new ImageIcon(iconURL);
        Button1.setIcon(icon);

        switch (round) {
            case 1:
                if ( (opt == "red" && deck[cardNo-1][2] == "red") | (opt == "black" && deck[cardNo-1][2] == "black") ) {

                    round = 2;
                    Button1.setText("Höher");
                    Button1.setText("Tiefer");
                }
                else {

                }
                break;
            case 2:
                if ( opt == "higher" && Integer.valueOf(deck[cardNo-1][1]) < Integer.valueOf(deck[cardNo-2][1])) {

                    round = 3;
                    Button1.setText("Drinnen");
                    Button1.setText("Draußen");
                }
                else {

                }
                break;
            case 3:
                break;
            case 4:
                round = 1;
                break;
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
