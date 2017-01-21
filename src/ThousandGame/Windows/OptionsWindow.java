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
public class OptionsWindow extends JFrame implements ActionListener{
    //DANE
    private final JButton backButton; //przycisk powrotu do menuWindow

    //FUNKCJE
    public OptionsWindow(){
        //ustawienie parametrów okienka
        Main.initJFrame(this,"Thousand",new Dimension(900,600),Color.ORANGE,false,false);

        //ustawienie parametrów przycisku back
        backButton=Main.initJButton(this,"Back",new Rectangle(150,420,600,75),20,Color.GREEN,Color.BLUE);
    }

    //jesli wcisniemy back to wracamy do menuWindow
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==backButton){
            Main.optionsWindow.setVisible(false);
            Main.menuWindow.setFocusable(true);
            Main.menuWindow.setVisible(true);
        }
    }
}
