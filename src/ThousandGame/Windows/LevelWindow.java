package ThousandGame.Windows;

//KLASA KOMPLETNA

import ThousandGame.Main;
import ThousandGame.Players.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;


/**
 * Created by karol on 01.01.17.
 */
public class LevelWindow extends JFrame implements ActionListener{
    //DANE
    private final JButton backButton; //przycisk powrotu do menuWindow nicku
    private final JButton easyButton; //przycisk łatwego poziomu
    private final JButton hardButton; //przycisk trudnego poziomu
    private final JButton mediumButton; //przycisk średnio poziomu
    private final JLabel informationLabel; //pole z tekstem informującym

    public LevelWindow() {
        //ustawienie parametrów okienka
        Main.initJFrame(this,"Thousand",new Dimension(900,600),Color.ORANGE,false,false);

        //ustawienie parametrów przycisku back
        backButton=Main.initJButton(this,"Back",new Rectangle(150,420,600,75),20,Color.GREEN,Color.BLUE);

        //ustawienie parametrów przycisku easy
        easyButton=Main.initJButton(this,"Easy",new Rectangle(150,150,600,75),20,Color.GREEN,Color.BLUE);

        //ustawienie parametrów przycisku hard
        hardButton=Main.initJButton(this,"Hard",new Rectangle(150,330,600,75),20,Color.GREEN,Color.BLUE);

        //ustawienie parametrów przycisku medium
        mediumButton=Main.initJButton(this,"Medium",new Rectangle(150,240,600,75),20,Color.GREEN,Color.BLUE);

        //ustawienie parametrów paska informującego
        informationLabel=Main.initJLabel(this,"Choose level",JLabel.CENTER,
                new Rectangle(300,30,300,45),20,Color.BLUE);
    }

    //funkcja inicjująca gracza i botów
    private static void initPlayers(char s){
        //inicjalizacja gracza
        Main.players[0] = new Player(Main.playerName.equals("") ? "Player 1" : Main.playerName);

        //inicjalizacja botów
        switch (s) {
            case 'e': //easy
                Main.players[1] = new ComputerPlayerEasy();
                Main.players[2] = new ComputerPlayerEasy();
                break;
            case 'm': //medium
                Main.players[1] = new ComputerPlayerMedium();
                Main.players[2] = new ComputerPlayerMedium();
                break;
            case 'h': //hard
                Main.players[1] = new ComputerPlayerHard();
                Main.players[2] = new ComputerPlayerHard();
                break;
        }

        //stworzenie listy na wyniki
        Main.roundScores =new LinkedList<>();
    }

    //Wybierając back wracamy do okienka z nazwą, a pozostałe rozpoczynają gre na odpowiednim poziomie
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==backButton){
            Main.levelWindow.setVisible(false);
            Main.nameWindow.setFocusable(true);
            Main.nameWindow.setVisible(true);
        }
        else if(e.getSource()==easyButton){
            initPlayers('e');
            Main.levelWindow.setVisible(false);
            Main.roundStartWindow =new RoundStartWindow();
        }
        else if(e.getSource()==hardButton){
            initPlayers('h');
            Main.levelWindow.setVisible(false);
            Main.roundStartWindow =new RoundStartWindow();
        }
        else if(e.getSource()==mediumButton){
            initPlayers('m');
            Main.levelWindow.setVisible(false);
            Main.roundStartWindow =new RoundStartWindow();
        }
    }
}
