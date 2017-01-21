package ThousandGame.Windows;

//KLASA KOMPLETNA

import ThousandGame.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Karol on 2017-01-07.
 */
public class NameWindow extends JFrame implements ActionListener {
    //DANE
    private final JLabel informationLabel; //pole z informacją
    private final JButton backButton; //przycisk powrotu do menuWindow
    private final JButton confirmButton; //przycisk potwierdzenia
    private final JTextField nameTextField; //pole na nazwe

    //FUNKCJE
    public NameWindow(){
        //ustawienie parametrów okienka
        Main.initJFrame(this,"Thousand",new Dimension(600,400),Color.ORANGE,false,false);

        //ustawienie parametrów paska z informacją
        informationLabel=Main.initJLabel(this,"Give your name",
                JLabel.CENTER, new Rectangle(200,50,200,50),20,Color.BLUE);

        //ustawienie parametrów przycisku back
        backButton=Main.initJButton(this,"Back",new Rectangle(200,260,200,50),20,Color.GREEN,Color.BLUE);

        //ustawienie parametrów przycisku confirm
        confirmButton=Main.initJButton(this,"Confirm",new Rectangle(200,190,200,50),20,Color.GREEN,Color.BLUE);

        //ustawienie parametrów pola do wprowadzenia imienia
        nameTextField = Main.initJTextField(this,new Rectangle(200,120,200,50),20,Color.BLUE);
        nameTextField.setBackground(Color.GREEN);
    }

    //Jeśli podamy nick to tak będzie nazywał się gracz, a jeśli nie toPlayer 1
    //Jeśli wciśniemy back to wracamy do menu
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==nameTextField || e.getSource()==confirmButton){
            Main.playerName = nameTextField.getText().equals("") ? "" : nameTextField.getText();
            Main.nameWindow.setVisible(false);
            Main.levelWindow.setFocusable(true);
            Main.levelWindow.setVisible(true);
        }
        else if(e.getSource()==backButton){
            Main.nameWindow.setVisible(false);
            Main.menuWindow.setFocusable(true);
            Main.menuWindow.setVisible(true);
        }
    }
}
