package ThousandGame.Windows;

import ThousandGame.Main;
import ThousandGame.Others.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by Karol on 2017-01-18.
 */

public class FightWindow extends JFrame implements ActionListener {
    //DANE
    private final JLabel actualTrumpsLabel; //pole z informacją o trumfie
    private final JLabel badCardWarningLabel; //pole z ostrzeżeniem (pojawia się, gdy wybierzemy nieodpowiednią kartę)
    private final ButtonGroup cardsButtonsGroup; //grupa przycisków, która pozwala na to, aby tylko jeden był wybrany
    private JRadioButton[] cardsButtonsTable; //tablica przycisków do wybrania karty do walki
    private final JButton giveButton; //przycisk potwierdzający rzucenie karty
    private JLabel[] givenCardsLabel; //tablica pól na karty rzucone do potyczki
    private final JLabel informationLabel; //pole z instrukcją
    private JLabel[] playerCardsLabel; //tablica pól z kartami gracza
    private final JLabel previousFightWinnerNameDescriptionLabel;
    private final JLabel previousFightWinnerNameLabel; //pole z nazwą zwycięzcy poprzedniej potyczki
    private final Timer timer; //timer

    private int cardsOnTableAmmount=0; //ile kart jest na stole
    private int fightNumber=1; //numer potyczki
    private LinkedList<Card> givenCardsList; //lista kart rzuconych do potyczki
    private final int mustPlayerIndex; //gracz, który jest na musiku
    private int resultOfMustPlayer; //suma zdobytych punktów przez gracza na musiku w rundzie
    private String trumpsName="-"; //string z informacją o trumfie

    //FUNKCJE
    public FightWindow(){
        //ustawienie parametrów okienka
        Main.initJFrame(this,"Thousand",new Dimension(900,600),Color.ORANGE,true,true);

        //ustawienie parametrów paska z nazwą trumfa
        trumpsName = "-";
        actualTrumpsLabel=Main.initJLabel(this,"Trumps: "+trumpsName,JLabel.LEFT,
                new Rectangle(20,20,200,30),20,Color.BLUE);

        //ustawienie parametrów paska z ostrzeżeniem o zle wybranej karcie
        badCardWarningLabel=Main.initJLabel(this,"Choose a card in appropriate color",JLabel.CENTER,
                new Rectangle(275,50,350,30),20,Color.RED);
        badCardWarningLabel.setVisible(false);

        //tworzy nowa grupe przyciskow, tablice pasków na karty własne i karty na stole
        cardsButtonsTable =new JRadioButton[Main.players[0].getPlayerCardsList().size()];
        cardsButtonsGroup =new ButtonGroup();
        playerCardsLabel =new JLabel[Main.players[0].getPlayerCardsList().size()];
        givenCardsLabel =new JLabel[3];

        //tworzy listę kart na stole i ustawia odp. wartości
        givenCardsList =new LinkedList<>(); //lista kart na stole
        mustPlayerIndex = Main.turnPlayerIndex; //gracz po dealerze jest na musiku
        resultOfMustPlayer =0; //zerowanie sumy punktow w tej rundzie gracza na musiku

        //ustawienie parametrów przycisku give
        giveButton=Main.initJButton(this,"Give",new Rectangle(400,330,100,20),20,Color.GREEN,Color.BLUE);

        //ustawienie parametrów paska z informacją
        informationLabel=Main.initJLabel(this,"Choose a card to fight",JLabel.CENTER,
                new Rectangle(275,280,350,30),20,Color.BLUE);

        //ustawienie parametrów paska z napisem "Last round winner"
        previousFightWinnerNameDescriptionLabel=Main.initJLabel(this,"Last round winner:",JLabel.LEFT,
                new Rectangle(20,60,170,30),20,Color.BLUE);

        //ustawienie parametrów paska z nazwą zwycięzcy poprzedniej rundy
        previousFightWinnerNameLabel=Main.initJLabel(this,"-",JLabel.LEFT,
                new Rectangle(200,60,200,30),20,Color.BLUE);

        //ustawienie parametrów timera
        timer=new Timer(500,this);

        //wstawianie przycisków wyboru kart
        for(int k = 0, j = (952 - 90 * Main.players[0].getPlayerCardsList().size()) / 2; k< Main.players[0].getPlayerCardsList().size(); k++,j+=90){
            cardsButtonsTable[k] = new JRadioButton();
            cardsButtonsTable[k].setBounds(j, 520, 20, 20);
            cardsButtonsGroup.add(cardsButtonsTable[k]);
            this.add(cardsButtonsTable[k]);
            cardsButtonsTable[k].addActionListener(this);
        }

        //wstawianie kart gracza
        Collections.sort(Main.players[0].getPlayerCardsList()); //sortowanie kart do wypisania
        int k = (900 - 90 * Main.players[0].getPlayerCardsList().size()) / 2;
        for (int i = 0; i< Main.players[0].getPlayerCardsList().size(); i++) {
            playerCardsLabel[i] = new JLabel();
            playerCardsLabel[i].setBounds(k, 407, 73, 103);

            ImageIcon image = new ImageIcon(Main.players[0].getPlayerCardsList().get(i).getImagePath());
            playerCardsLabel[i].setIcon(image);

            this.add(playerCardsLabel[i]);
            k += 90;
        }

        timer.start();
    }

    //zwraca tablicę z indeksem i sumą punktow gracza, ktory ma ich najwiecej
    private int[] getMaxOfPoints(){
        return Main.players[0].getPlayerPoints() >= Main.players[1].getPlayerPoints() && Main.players[0].getPlayerPoints() >= Main.players[2].getPlayerPoints() ?
                new int[]{0, Main.players[0].getPlayerPoints()} : (Main.players[1].getPlayerPoints() >= Main.players[2].getPlayerPoints() ?
                new int[]{1, Main.players[1].getPlayerPoints()} :  new int[]{2, Main.players[2].getPlayerPoints()});
    }

    //pokazuje kartę rzuconą na stół
    private void showCardToFight(Card c){
        givenCardsLabel[Main.turnPlayerIndex]=new JLabel();
        givenCardsLabel[Main.turnPlayerIndex].setBounds(330+80* Main.turnPlayerIndex,150,73,103);

        ImageIcon image = new ImageIcon(c.getImagePath());
        givenCardsLabel[Main.turnPlayerIndex].setIcon(image);

        Main.fightWindow.add(givenCardsLabel[Main.turnPlayerIndex]);
    }

    //liczy sumę wartosci kart z talii
    private int sumValueOfCards(Card[] cards){
        int result=0;
        for (Card c:cards) {result+=c.getValue();}
        return result;
    }

    //zwraca indeks gracza, który wygrał potyczkę
    private int getFightWinnerIndex(Card[] cards){
        String start=cards[0].getColor();
        int winner=0;

        if(cards[1].getColor().equals(start) && cards[1].getValue()>
                cards[winner].getValue())winner=1;
        if(cards[2].getColor().equals(start) && cards[2].getValue()>
                cards[winner].getValue())winner=2;

        if(!start.equals(trumpsName)) {
            if (cards[1].getColor().equals(trumpsName) && cards[2].getColor().equals(trumpsName))
                winner = (cards[1]).getValue() > (cards[2]).getValue() ? 1 : 2;
            else if (cards[1].getColor().equals(trumpsName)) winner = 1;
            else if (cards[2].getColor().equals(trumpsName)) winner = 2;
        }
        return (Main.turnPlayerIndex +winner)%3;
    }


    //Obsluga przycisków (z racji na podział na kilka ifów - każdy opiszę osobno)
    @Override
    public void actionPerformed(ActionEvent e) {
        //Jeśli była kolej gracza i wcisnął giveButton, to:
        if(e.getSource()==giveButton && !timer.isRunning()){
            //szukamy wcisnietej karty, jesli nie ma to pomijamy ten sygnał
            for(int i = 0; i< Main.players[0].getPlayerCardsList().size(); i++)
                if(cardsButtonsTable[i].isSelected()) {
                    //jeśli karta jest w zlym kolorze to wypisujemy warning
                    if (cardsOnTableAmmount != 0 && Main.players[0].hasCardInColor(givenCardsList.get(0).getColor()) &&
                            !Main.players[0].getPlayerCardsList().get(i).getColor().equals(givenCardsList.get(0).getColor()))
                        badCardWarningLabel.setVisible(true);
                    //jesli wybrano kartę zgodnie z zasadami dodaje ją do listy i maluje ją na ekranie
                    else{
                        badCardWarningLabel.setVisible(false);
                        Card c= Main.players[0].getCard(i);
                        givenCardsList.addLast(c);
                        showCardToFight(c);
                        this.validate();
                        this.repaint();
                        cardsOnTableAmmount++;
                        Main.turnPlayerIndex =(Main.turnPlayerIndex +1)%3;
                        timer.start();
                    }
                    break; //przerywa pętle , bo tylko 1 guzik może być zaznaczony (grupa przycisków)
                }
        }
        //Jeśli timer daje sygnał, to:
        else if(e.getSource()==timer){
            //jeśli trwa jeszcze potyczka to zależnie od tego czyja kolej wykonujemy odpowiednio:
            if(cardsOnTableAmmount !=3){
                //dla gracza wstrzymujemy zegar i czekamy na jego ruch
                if(Main.turnPlayerIndex ==0)timer.stop();
                //dla botów rzucamy kartę i pokazujemy ją na ekranie
                else{
                    Card c;
                    if(cardsOnTableAmmount ==0)c= Main.players[Main.turnPlayerIndex].giveCardToFight();
                    else c= Main.players[Main.turnPlayerIndex].giveCardToFight(givenCardsList.get(0).getColor());

                    givenCardsList.addLast(c);
                    showCardToFight(c);
                    this.validate();
                    this.repaint();
                    cardsOnTableAmmount++;
                    Main.turnPlayerIndex =(Main.turnPlayerIndex +1)%3;
                }
            }
            //jeśli skończyła się potyczka to:
            else{
                //sprawdzamy obecność meldunku w rzuconych kartach: jeśli wystąpi to dajemy punkty graczowi i aktualizujemy trumfy
                if (givenCardsList.get(0).getShape().equals("Queen")) {
                    if (Main.players[Main.turnPlayerIndex].hasCard(new Card(givenCardsList.get(0).getColor(), "King"))) {
                        if (Main.turnPlayerIndex == mustPlayerIndex) resultOfMustPlayer += givenCardsList.get(0).getReport();
                        else Main.players[Main.turnPlayerIndex].addPoints(givenCardsList.get(0).getReport());
                        trumpsName = givenCardsList.get(0).getColor();
                    }
                } else if (givenCardsList.get(0).getShape().equals("King")) {
                    if (Main.players[Main.turnPlayerIndex].hasCard(new Card(givenCardsList.get(0).getColor(), "Queen"))) {
                        if (Main.turnPlayerIndex == mustPlayerIndex) resultOfMustPlayer += givenCardsList.get(0).getReport();
                        else Main.players[Main.turnPlayerIndex].addPoints(givenCardsList.get(0).getReport());
                        trumpsName = givenCardsList.get(0).getColor();
                    }
                }
                actualTrumpsLabel.setText("Trumps: "+ trumpsName);

                //sprawdzamy kto wygrał i dopisujemy punkty (także dla gracza na musiku)
                Main.turnPlayerIndex = getFightWinnerIndex(givenCardsList.toArray(new Card[0]));
                int result= sumValueOfCards(givenCardsList.toArray(new Card[0]));
                if(Main.turnPlayerIndex == mustPlayerIndex) resultOfMustPlayer +=result; // musikowy gracz
                else Main.players[Main.turnPlayerIndex].addPoints(result); // niemusikowy gracz

                //ustawienie odpowiednich pól na ekranie
                previousFightWinnerNameLabel.setText(Main.players[Main.turnPlayerIndex].getPlayerName());
                cardsOnTableAmmount=0;

                //jeśli była to ostatnia runda to:
                if(fightNumber==8){
                    //dodanie/odjęcie musika(w zaleznosci czy ugral) i wyrównanie wyników
                    if(resultOfMustPlayer >= Main.mustValue) Main.players[mustPlayerIndex].addPoints(Main.mustValue);
                    else Main.players[mustPlayerIndex].addPoints((-1)* Main.mustValue);
                    Main.roundScores.add(new int[]{(Main.players[0].getPlayerPoints()+5)/10*10, (Main.players[1].getPlayerPoints()-(Main.players[1].getPlayerPoints()%10)+5)/10*10, (Main.players[2].getPlayerPoints()-(Main.players[2].getPlayerPoints()%10)+5)/10*10});

                    fightNumber=1;
                    Main.fightWindow.setVisible(false);
                    timer.stop();

                    //sprawdzenie czy koniec gry - jeśli tak wypisujemy wyniki, jeśli nie zaczynamy nową rundę
                    if(getMaxOfPoints()[1]>=1000){
                        Main.finishWindow = new FinishWindow(MenuWindow.generateFinalScores(getMaxOfPoints()[0])[0], MenuWindow.generateFinalScores(getMaxOfPoints()[0])[1]);
                    }
                    else {
                        Main.roundScoresWindow = new RoundScoresWindow(MenuWindow.generateFinalScores(0)[1]); //wypisanie wynikow rundy i stworzenie nowej
                        Main.cardsDealerIndex = (Main.cardsDealerIndex + 1) % 3;
                    }
                }
                //kiedy runda nadal trwa
                else {
                    //aktualizujemy karty i przyciski
                    fightNumber++;
                    givenCardsList = new LinkedList<>();
                    for (int i = 0; i < Main.players[0].getPlayerCardsList().size() + 1; i++){
                        cardsButtonsTable[i].setVisible(false); playerCardsLabel[i].setVisible(false);}
                    for (int i = 0; i < 3; i++) givenCardsLabel[i].setVisible(false);
                    cardsButtonsTable = new JRadioButton[Main.players[0].getPlayerCardsList().size()];
                    playerCardsLabel = new JLabel[Main.players[0].getPlayerCardsList().size()];
                    givenCardsLabel = new JLabel[3];

                    //wstawianie przycisków
                    for (int k = 0, j = (952 - 90 * Main.players[0].getPlayerCardsList().size()) / 2; k < Main.players[0].getPlayerCardsList().size(); k++, j += 90) {
                        cardsButtonsTable[k] = new JRadioButton();
                        cardsButtonsTable[k].setBounds(j, 520, 20, 20);
                        cardsButtonsGroup.add(cardsButtonsTable[k]);
                        this.add(cardsButtonsTable[k]);
                        cardsButtonsTable[k].addActionListener(this);
                    }

                    //wstawianie kart
                    int k = (900 - 90 * Main.players[0].getPlayerCardsList().size()) / 2;
                    for (int i = 0; i < Main.players[0].getPlayerCardsList().size(); i++) {
                        playerCardsLabel[i] = new JLabel();
                        playerCardsLabel[i].setBounds(k, 407, 73, 103);

                        ImageIcon image = new ImageIcon(Main.players[0].getPlayerCardsList().get(i).getImagePath());
                        playerCardsLabel[i].setIcon(image);

                        Main.fightWindow.add(playerCardsLabel[i]);
                        k += 90;
                    }
                }
            }
        }
    }
}
