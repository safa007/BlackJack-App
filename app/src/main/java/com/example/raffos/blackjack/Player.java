package com.example.raffos.blackjack;

import java.util.ArrayList;

/**
 * Created by raffos on 7/28/2017.
 */

//Object for the player, can be dealer or regular player
public class Player {

    private int playerNum; //If num is 0, player is the dealer
    private ArrayList<Card> cards; //The cards the player holds in hand

    //Player constructor
    public Player(int playerNum){
        this.playerNum = playerNum;
        this.cards = new ArrayList<>();
    }

    //Getter for the player number
    public int getPlayerNum(){
        return playerNum;
    }

    //Adds a card to the arraylist of cards in the player's hand
    public void addCard(Card card){
        cards.add(card);
    }

    //Gets the card at a certain index in the arraylist
    public Card getCard(int i){
        return cards.get(i);
    }

    //Gets the number of cards in the player's hand
    public int getNumCards(){ return cards.size(); }

    //Get rid of all cards in the player's hand
    public void resetCards(){
        cards.clear();
    }

    //Get the total of the cards in the hand
    public int getTotal(){

        int sum = 0; //Total of the cards
        int numAces = 0; //To find how many aces there are
        int currentNum;


        //Loop through the cards in the hand
        for(int i = 0; i < cards.size(); ++i){
            currentNum = cards.get(i).getNumber();
            //If the current card is an ace
            if(currentNum == 11) {
                ++numAces;
            }
            sum += currentNum; //Increment sum by current number
        }


        /* The resolution strategy to deal with deciding whether an ace counts as a
        1 or 11 is to count it initially as an 11, and while the sum exceeds 21,
        decrease the total by 10 (11 - 1) to count it as a 1 until the sum is
        below 22 or we run out of aces */

        while(numAces > 0 && sum > 21){
            sum -= 10;
            --numAces;
        }

        return sum;
    }
}
