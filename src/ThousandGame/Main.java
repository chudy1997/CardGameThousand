package ThousandGame;

import ThousandGame.Others.Distribution;
import ThousandGame.Players.Player;
import ThousandGame.Windows.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * Created by Karol on 2017-01-20.
 */
public class Main {
    //Obiekty wszystkich klas
    public static JFrame exitWindow;
    public static JFrame fightWindow;
    public static JFrame finishWindow;
    public static JFrame giveBackCardsWindow;
    public static JFrame levelWindow;
    public static JFrame menuWindow;
    public static JFrame mustWindow;
    public static JFrame nameWindow;
    public static JFrame optionsWindow;
    public static JFrame roundBidWindow;
    public static JFrame roundScoresWindow;
    public static JFrame roundStartWindow;
    public static JFrame showStockWindow;

    public static int actualTopBid; //aktualna najwyzsza kwota w licytacji
    public static int[] actualBids = new int[3]; //tablica kwot licytacji graczy w danej rundzie
    public static int cardsDealerIndex; //aktualnie rozdający
    public static Distribution distribution;
    public static String playerName; //nazwa gracza
    public static Player[] players = new Player[3];
    public static int roundNumber; //numer rundy
    public static LinkedList<int[]> roundScores; //lista wyników po poszczególnych rundach dla kazdego gracza
    public static int turnPlayerIndex; //gracz aktualnie wykonujący ruch
    public static int mustValue; //kwota musiku

    //inicjuje JButton
    public static JButton initJButton(JFrame frame,String text,Rectangle bounds,int fontSize,Color backColor,Color foreColor){
        JButton button = new JButton(text);
        button.setBounds(bounds);
        button.setFont(new Font("SansSerif", Font.PLAIN, fontSize));
        button.setBackground(backColor);
        button.setForeground(foreColor);
        frame.add(button);
        button.addActionListener((ActionListener)frame);
        return button;
    }

    //inicjuje okienko
    public static void initJFrame(JFrame frame,String title,Dimension bounds,Color color,boolean visible,boolean focusable){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds((int) (screenSize.getWidth() - bounds.getWidth()) / 2, (int) (screenSize.getHeight() - bounds.getHeight()) / 2,(int) bounds.getWidth(),(int) bounds.getHeight());
        frame.setTitle(title);
        frame.setLayout(null);
        frame.getContentPane().setBackground(color);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFocusable(focusable);
        frame.setVisible(visible);
    }

    //inicjuje JLabel
    public static JLabel initJLabel(JFrame frame, String text, int position, Rectangle bounds,int fontSize,Color color){
        JLabel label = new JLabel(text,position);
        label.setBounds(bounds);
        label.setFont(new Font("SansSerif",Font.PLAIN,fontSize));
        label.setForeground(color);
        frame.add(label);
        return label;
    }

    //inicjuje JTextField
    public static JTextField initJTextField(JFrame frame,Rectangle bounds,int fontSize,Color color){
        JTextField textField = new JTextField();
        textField.setBounds(bounds);
        textField.setFont(new Font("SansSerif", Font.PLAIN, 20));
        textField.setForeground(color);
        frame.add(textField);
        textField.addActionListener((ActionListener)frame);
        return textField;
    }

    //inicjujemy pierwsze okienka
    public static void main(String[] args) {
        Main.menuWindow = new MenuWindow();

        //wyzerowanie
        Main.actualTopBid = 0;
        Main.cardsDealerIndex = 0;
        Main.roundNumber = 0;

        //utworzenie okienek uzywanych na poczatku gry
        Main.exitWindow = new ExitWindow();
        Main.levelWindow = new LevelWindow();
        Main.optionsWindow = new OptionsWindow();
        Main.nameWindow = new NameWindow();
    }
}
