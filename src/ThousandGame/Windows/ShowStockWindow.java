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
public class ShowStockWindow extends JFrame implements ActionListener {
    private final JLabel[] cardsLabelsTable; //tablica pasków wyświetlających karty
    private final Timer timer; //timer
    private final JLabel winnerLabel; //pole z nazwą zwycięzcy licytacji

    public ShowStockWindow(){
        //ustawienie parametrów okienka
        Main.initJFrame(this,"Thousand",new Dimension(300,200),Color.ORANGE,true,true);

        //inicjalizacja tablicy i wstawienie kart w odpowiednie miejsca
        cardsLabelsTable =new JLabel[3];
        for(int i=0;i<3;i++){
            cardsLabelsTable[i]=new JLabel();
            cardsLabelsTable[i].setBounds(20+80*i,40,75,110);

            ImageIcon image = new ImageIcon(Main.distribution.getStock().get(i).getImagePath());
            cardsLabelsTable[i].setIcon(image);

            this.add(cardsLabelsTable[i]);
        }

        timer = new Timer(3000,this);
        timer.start();

        //ustawienie parametrów paska zwycięzcy licytacji
        winnerLabel=Main.initJLabel(this,"Top bid by "+ Main.players[Main.turnPlayerIndex].getPlayerName()+" : "+ Main.actualTopBid,
                JLabel.CENTER, new Rectangle(20,10,240,20),20,Color.BLUE);
    }

    //Wyświetla musik i zwycięzce licytacji przez 3 sekundy i przechodzi do rozdania kart
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==timer){
            this.setVisible(false);
            timer.stop();
            Main.roundBidWindow.setVisible(false);
            if(Main.turnPlayerIndex ==0) Main.giveBackCardsWindow =new GiveBackCardsWindow();
            else {
                Main.fightWindow = new FightWindow();
                Main.fightWindow.setFocusable(true);
                Main.fightWindow.setVisible(true);
            }
        }
    }
}
