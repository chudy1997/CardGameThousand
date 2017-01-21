package ThousandGame.Windows;

//KLASA KOMPLETNA

import ThousandGame.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by karol on 01.01.17.
 */

public class MenuWindow extends JFrame implements ActionListener {
    //DANE
    private final JButton exitButton; //przycisk wyjścia z gry
    private final JButton optionsButton; //przycisk wejścia do opcji
    private final JButton startButton; //przycisk rozpoczęcia gry
    private final JLabel titleLabel; //pole z tytułem gry

    //FUNKCJE
    public MenuWindow() {
        //ustawienie parametrow okienka
        Main.initJFrame(this,"Thousand",new Dimension(900,600),Color.ORANGE,true,true);

        //ustawienie parametrow przycisku exit
        exitButton=Main.initJButton(this,"Exit",new Rectangle(150,330,600,75),20,Color.GREEN,Color.BLUE);

        //ustawienie parametrow przycisku options
        optionsButton=Main.initJButton(this,"Options",new Rectangle(150, 240, 600, 75),20,Color.GREEN,Color.BLUE);

        //ustawienie parametrow przycisku start
        startButton=Main.initJButton(this,"Start",new Rectangle(150,150,600,75),20,Color.GREEN,Color.BLUE);

        //ustawienie parametrow paska z tytulem
        titleLabel=Main.initJLabel(this,"THOUSAND",JLabel.CENTER,
                new Rectangle(300,30,300,45),30,Color.BLUE);
    }

    //Funkcja tworząca tablice stringow z napisem, czy wygrales czy nie i z tablica wynikow wszystkich rund (html)
    static String[] generateFinalScores(int winner) {
        String[] scoreTable = new String[]{"", ""};

        if (winner == 0) scoreTable[0] = "YOU WON!";
        else scoreTable[0] = "YOU LOST!";

        scoreTable[1] = "<html>";
        for (int j = 0; j < 19; j++) scoreTable[1] += "&nbsp;";
        scoreTable[1] += "Scores table<br>";
        for (int i = 0; i < 3; i++) {
            scoreTable[1] += Main.players[i].getPlayerName();
            for (int j = 0; j < 15 - Main.players[i].getPlayerName().length(); j++) scoreTable[1] += "&nbsp;";
        }
        scoreTable[1] += "<br>";
        for (int[] t : Main.roundScores) {
            for (int j = 0; j < 3; j++) scoreTable[1] += "&nbsp;";
            scoreTable[1] += String.format("%04d", t[0]);
            for (int j = 0; j < 15; j++) scoreTable[1] += "&nbsp;";
            scoreTable[1] += String.format("%04d", t[1]);
            for (int j = 0; j < 15; j++) scoreTable[1] += "&nbsp;";
            scoreTable[1] += String.format("%04d", t[2]) + "<br>";
        }
        for (int j = 0; j < 22 - Main.roundScores.size(); j++) scoreTable[1] += "<br>";
        scoreTable[1] += "</html>";
        return scoreTable;
    }

    //W zaleznosci od kliknietego przycisku zaczynamy gre, wchodzimy w opcje lub wychodzimy z gry
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            Main.nameWindow.setFocusable(true);
            Main.nameWindow.setVisible(true);
            this.setVisible(false);
        } else if (e.getSource() == optionsButton) {
            this.setVisible(false);
            Main.optionsWindow.setFocusable(true);
            Main.optionsWindow.setVisible(true);
        } else if (e.getSource() == exitButton) {
            Main.exitWindow.setFocusable(true);
            Main.exitWindow.setVisible(true);
        }
    }
}