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
public class RoundScoresWindow extends JFrame implements ActionListener {
    //DANE
    private final JLabel scoreTableLabel; //pole z tabela wyników po konkretnych rundach
    private final Timer timer; //timer

    //FUNKCJE
    public RoundScoresWindow(String scoresTable) {
        //ustawienie parametrów okienka
        Main.initJFrame(this,"Thousand",new Dimension(900,600),Color.ORANGE,true,true);

        //ustawienie parametrów pola na wynik
        scoreTableLabel=new JLabel(scoresTable,JLabel.CENTER);
        JScrollPane scoreTableScrollPane= JScrollPaneInit(scoreTableLabel);

        //ustawienie parametrów timera
        timer=new Timer(5000,this);
        timer.start();
    }

    //inicjuje scrollowany pasek
    private JScrollPane JScrollPaneInit(JLabel label){
        JScrollPane scrollPane= new JScrollPane(label);
        scrollPane.setBounds(200,80,500,400);
        scrollPane.setFont(new Font("SansSerif",Font.PLAIN,20));
        scrollPane.setBackground(Color.ORANGE);
        scrollPane.setForeground(Color.BLUE);
        this.add(scrollPane);
        return scrollPane;
    }

    //wypisuje wyniki i po 5 sekundach rozpoczyna nową rundę
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==timer){
            this.setVisible(false);
            timer.stop();
            Main.roundStartWindow =new RoundStartWindow();
        }
    }
}
