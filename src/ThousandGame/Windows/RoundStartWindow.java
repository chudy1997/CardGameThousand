package ThousandGame.Windows;

//KLASA KOMPLETNA

import ThousandGame.Main;
import ThousandGame.Others.Distribution;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Karol on 2017-01-17.
 */
public class RoundStartWindow extends JFrame implements ActionListener {
    //DANE
    private final JLabel roundNumberLabel; //pole z numerem rundy
    private final JLabel gameNameLabel; //pole z nazwą gry
    private final Timer timer; //timer

    //FUNKCJE
    public RoundStartWindow(){
        //ustawienie parametrów okienka
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Main.initJFrame(this,"Thousand",new Dimension(300,200),Color.ORANGE,true,true);

        //rozdanie kart i zwiększenie indeksu rundy
        Main.distribution=new Distribution();
        Main.roundNumber++;

        //ustawienie parametrów paska z numerem rundy
        roundNumberLabel=Main.initJLabel(this,"Round no. "+Integer.toString(Main.roundNumber),
                JLabel.CENTER, new Rectangle(20,80,230,50),32,Color.BLUE);

        //ustawienie parametrów paska z nazwą gry
        gameNameLabel=Main.initJLabel(this,"THOUSAND",
                JLabel.CENTER, new Rectangle(20,20,230,50),32,Color.BLUE);

        //ustawienie parametrów timera
        timer = new Timer(2000,this);
        timer.start();
    }

    //wypisuje numer rundy i po 2 sekundach ją rozpoczyna
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==timer){
            timer.stop();
            this.setVisible(false);
            Main.roundBidWindow =new RoundBidWindow();
            Main.roundBidWindow.setFocusable(true);
            Main.roundBidWindow.setVisible(true);
        }
    }
}
