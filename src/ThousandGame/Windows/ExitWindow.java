package ThousandGame.Windows;

//KLASA KOMPLETNA

import ThousandGame.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by karol on 01.01.17.
 */
public class ExitWindow extends JFrame implements ActionListener{
    //DANE
    private final JButton noButton; //przycisk powrotu do menuWindow
    private final JButton yesButton; //przycisk potwierdzenia chęci wyjścia
    private final JLabel questionLabel; //pole z pytaniem

    //FUNKCJE
    public ExitWindow() {
        Main.initJFrame(this,"Thousand",new Dimension(300,200),Color.ORANGE,false,false);

        //ustawienie parametrów paska z pytaniem
        questionLabel=Main.initJLabel(this,"Are you sure to exit?",JLabel.CENTER,
                new Rectangle(25,25,250,20),15,Color.BLUE);

        //ustawienie parametrów przycisku yes
        yesButton=Main.initJButton(this,"Yes",new Rectangle(50,100,90,20),15,Color.GREEN,Color.BLUE);

        //ustawienie parametrów przycisku no
        noButton=Main.initJButton(this,"No",new Rectangle(160,100,90,20),15,Color.GREEN,Color.BLUE);
    }

    //jeli klikniesz yes to konczy grę, jeśli no wraca do menuWindow
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==yesButton){
            System.exit(0);
        }
        else if(e.getSource()==noButton){
            Main.exitWindow.setVisible(false);
        }
    }
}