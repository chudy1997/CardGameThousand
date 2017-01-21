package ThousandGame.Windows;

//KLASA KOMPLETNA

import ThousandGame.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Karol on 2017-01-18.
 */
public class MustWindow extends JFrame implements ActionListener {
    //DANE
    private final JButton confirmButton; //przycisk do potwierdzenia
    private final JTextField mustTextField; //pole do wprowadzenia liczby
    private final JLabel rangeLabel; //pole z zakresem możliwych wartości
    private final JLabel informationLabel; //pole z instrukcją

    //FUNKCJE
    public MustWindow(){
        //ustawienie parametrów okienka
        Main.initJFrame(this,"Thousand",new Dimension(300,200),Color.ORANGE,true,true);

        //ustawienie parametrów przycisku confirm
        confirmButton=Main.initJButton(this,"Confirm",new Rectangle(60,120,150,30),20,Color.GREEN,Color.BLUE);

        //ustawienie parametrów paska z instrukcją
        informationLabel=Main.initJLabel(this,"Give a must or leave blank",JLabel.CENTER,
                new Rectangle(10,10,260,20),20,Color.BLUE);

        //ustawienie parametrów pola do wprowadzenia musiku
        mustTextField = Main.initJTextField(this,new Rectangle(85,80,100,30),20,Color.BLUE);

        //ustawienie parametrów paska z zakresem możliwych wartości
        rangeLabel=Main.initJLabel(this,"Range: "+Integer.toString(Main.actualTopBid)+" - "+Integer.toString(Main.players[0].getMaxSumOfCards()),
                JLabel.CENTER, new Rectangle(20,40,240,20),20,Color.BLUE);
    }

    //jeśli pole jest puste lub podana jest odpowiednia wartość przechodzimy dalej.
    // Jeśli nie wyświetlamy warning i czekamy na ponowny impuls
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==confirmButton || e.getSource()==mustTextField){
            if(mustTextField.getText().equals("")){
                Main.giveBackCardsWindow.setVisible(false);
                this.setVisible(false);
                Main.fightWindow = new FightWindow();
                Main.fightWindow.setFocusable(true);
                Main.fightWindow.setVisible(true);
            }
            else if(!mustTextField.getText().equals("") && Integer.parseInt(mustTextField.getText())/10*10 >= Main.mustValue &&
                    Integer.parseInt(mustTextField.getText()) <= Main.players[0].getMaxSumOfCards()) {
                Main.mustValue = Integer.parseInt(mustTextField.getText()) / 10 * 10;
                Main.giveBackCardsWindow.setVisible(false);
                this.setVisible(false);
                Main.fightWindow = new FightWindow();
                Main.fightWindow.setFocusable(true);
                Main.fightWindow.setVisible(true);
            }
            else informationLabel.setText("You gave bad mustValue!");
        }
    }

}