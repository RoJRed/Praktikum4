package cardgame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Random;

public class Mainform extends JPanel {
    private JPanel mainPanel;
    private JLabel labDeck;
    private JLabel labPos1;
    private JButton neueKarte;
    private JButton naechste;
    private JButton Button1;
    private JButton Button2;
    private JLabel pos1;
    private JLabel pos2;
    private JLabel pos3;
    private JLabel pos4;
    private JLabel taskLabel;
    private JButton newGame;
    private JLabel resultLabel;
    private JLabel value1;
    private JLabel value2;
    private JLabel value3;
    private JLabel value4;
    private JLabel color1;
    private JLabel color2;
    private JLabel color3;
    private JLabel color4;

    private JDialog blackRed;

    private String[][] deck = new String[52][3];
    private String[][] discard = new String[3][3];
    private int cardNo = 0;
    private int round = 1;


    public Mainform() {
        neueKarte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                karteLegen();
            }
        });

        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deckAnlegen();
            }
        });

        Button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                naechsteKarte("opt1");
            }
        });

        Button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                naechsteKarte("opt2");
            }
        });


    }


    public void deckAnlegen() {
        for (int i = 0; i <= 51; i++) {
            String path = "./res/card" + i + ".png";

            deck[i][0] = path;

            if (i <= 12) {
                deck[i][1] = String.valueOf(i + 2);
                deck[i][2] = "red";
            } else if (i > 12 && i <= 25) {
                deck[i][1] = String.valueOf((i - 11));
                deck[i][2] = "red";
            } else if (i > 25 && i <= 38) {
                deck[i][1] = String.valueOf((i - 24));
                deck[i][2] = "black";
            } else if (i > 39 && i <= 51) {
                deck[i][1] = String.valueOf((i - 37));
                deck[i][2] = "black";
            }
        }

        //mixDeck();
    }

    public void mixDeck() {
        for (int i = 1; i <= 100; i++) {
            Random r = new Random();
            int card1 = r.nextInt(51);
            int card2 = r.nextInt(51);

            String path = "./res/card" + card1 + ".png";
            String number = deck[card1][1];
            String color = deck[card1][2];

            deck[card1][0] = "./res/card" + card2 + ".png";
            deck[card1][1] = deck[card2][1];
            deck[card1][2] = deck[card2][2];

            deck[card2][0] = path;
            deck[card2][1] = number;
            deck[card2][2] = color;
        }
    }

    public void naechsteKarte(String opt) {
        String resultText = "Start";

        URL iconURL = getClass().getResource(deck[cardNo][0]);
        ImageIcon icon = new ImageIcon(iconURL);

        ImageIcon icon1 = new ImageIcon(getClass().getResource("./res/pos1.png"));
        ImageIcon icon2 = new ImageIcon(getClass().getResource("./res/pos2.png"));
        ImageIcon icon3 = new ImageIcon(getClass().getResource("./res/pos3.png"));
        ImageIcon icon4 = new ImageIcon(getClass().getResource("./res/pos4.png"));

        if (round == 1) {
            pos1.setIcon(icon1);
            pos2.setIcon(icon2);
            pos3.setIcon(icon3);
            pos4.setIcon(icon4);
        }

        switch (round) {
            case 1:
                //Welche Farbe wird die nächste Karte haben?
                pos1.setIcon(icon);
                value1.setText(deck[cardNo][1]);
                color1.setText(deck[cardNo][2]);

                if ((opt == "opt1" && deck[cardNo][2] == "red") | (opt == "opt2" && deck[cardNo][2] == "black")) {
                    round = 2;
                    resultLabel.setText("Richtig!");
                    taskLabel.setText("Ist die nächste Karte vom Wert her höher oder tiefer?");
                    Button1.setText("Höher");
                    Button2.setText("Tiefer");
                } else {
                    round = 1;
                    resultLabel.setText("Falsch!");
                    resetRound();
                }

                break;
            case 2:
                //Ist die nächste Karte vom Wert her höher oder tiefer?
                pos2.setIcon(icon);
                value2.setText(deck[cardNo][1]);
                color2.setText(deck[cardNo][2]);

                if ((opt == "opt1" && Integer.valueOf(deck[cardNo][1]) < Integer.valueOf(deck[cardNo - 1][1])) | (opt == "opt2" && Integer.valueOf(deck[cardNo][1]) > Integer.valueOf(deck[cardNo - 1][1]))) {
                    round = 3;
                    resultLabel.setText("Richtig!");
                    taskLabel.setText("Ist die nächste Karte vom Wert her innehalb oder außerhalb der beiden vorangeganenen Karten?");
                    Button1.setText("Drinnen");
                    Button2.setText("Draußen");
                } else {
                    round = 1;
                    resultLabel.setText("Falsch!");
                    resetRound();
                }

                break;
            case 3:
                //Ist die nächste Karte vom Wert her innehalb oder außerhalb der beiden vorangegangenen Karten?

                int low = 0;
                int high = 0;

                if (Integer.valueOf(deck[cardNo - 1][1]) <= Integer.valueOf(deck[cardNo - 2][1])) {
                    low = Integer.valueOf(deck[cardNo - 1][1]);
                    high = Integer.valueOf(deck[cardNo - 2][1]);
                } else {
                    high = Integer.valueOf(deck[cardNo - 1][1]);
                    low = Integer.valueOf(deck[cardNo - 2][1]);
                }

                pos3.setIcon(icon);
                value3.setText(deck[cardNo][1]);
                color3.setText(deck[cardNo][2]);

                if ((opt == "opt1" && Integer.valueOf(deck[cardNo][1]) <= high && Integer.valueOf(deck[cardNo][1]) >= low) | (opt == "opt2" && Integer.valueOf(deck[cardNo][1]) >= high | Integer.valueOf(deck[cardNo][1]) <= low)) {
                    round = 4;
                    resultLabel.setText("Richtig!");
                    taskLabel.setText("Welches Symbol hat die nächste Karte?");
                    Button1.setText("Drinnen");
                    Button2.setText("Draußen");
                } else {
                    round = 1;
                    resultLabel.setText("Falsch!");
                    resetRound();
                }

                break;
            case 4:
                //Ist die nächste Karte vom Wert her höher oder tiefer?
                pos4.setIcon(icon);
                value4.setText(deck[cardNo][1]);
                color4.setText(deck[cardNo][2]);

                if ((opt == "opt1" && Integer.valueOf(deck[cardNo][1]) < Integer.valueOf(deck[cardNo - 1][1])) | (opt == "opt2" && Integer.valueOf(deck[cardNo][1]) > Integer.valueOf(deck[cardNo - 1][1]))) {

                    round = 1;
                    resultLabel.setText("Richtig!");
                } else {
                    round = 1;
                    resultLabel.setText("Falsch!");
                    resetRound();
                }

                break;
        }

        cardNo++;
    }

    public void resetRound() {
        taskLabel.setText("Welche Farbe wird die nächste Karte haben?");
        Button1.setText("Rot");
        Button2.setText("Schwarz");
    }

    public void karteLegen() {
        Random r = new Random();
        String path = "./res/card" + r.nextInt(52) + ".png";
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