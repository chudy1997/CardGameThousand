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
public class FinishWindow extends JFrame implements ActionListener {
    private final JButton menuButton; //przycisk powrotu do menuWindow w celu rozpoczęcia nowej gry
    private final JLabel winnerLabel; //pole z rezultatem (won/lost)
    private final JLabel scoresTableLabel; //tabela wyników poszczególnych rund całej gry

    public FinishWindow(String score, String scoresTable) {
        //ustawienie parametrów okienka
        Main.initJFrame(this,"Thousand",new Dimension(900,600),Color.ORANGE,true,true);

        //ustawienie parametrów przycisku powrotu do menuWindow
        menuButton=Main.initJButton(this,"Menu",new Rectangle(400,500,100,20),20,Color.GREEN,Color.BLUE);

        //ustawienie parametrów paska z napisem o rezultacie
        winnerLabel=Main.initJLabel(this,score,JLabel.CENTER,
                new Rectangle(350,20,200,40),20,Color.BLUE);

        ////ustawienie parametrów paska z wynikami rund
        scoresTableLabel =new JLabel(scoresTable,JLabel.CENTER);
        JScrollPane scoreTableScrollPane= JScrollPaneInit(scoresTableLabel);
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

    //jeśli klikniesz na menuWindow, powrócisz do menuWindow
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==menuButton){
            Main.finishWindow.setVisible(false);
            Main.menuWindow =new MenuWindow();
        }
    }
}
