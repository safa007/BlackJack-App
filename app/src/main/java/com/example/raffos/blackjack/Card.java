package com.example.raffos.blackjack;

/**
 * Created by raffos on 7/20/2017.
 */

//Each card object will reference a card in a deck of 52
public class Card {

    //Properties that the card holds
    private int number;
    private char faceCard; //Indicates whether card is a 10 (T), Jack(J), King(K) or Queen(Q)
    private char suit;
    private boolean isFaceDown; //Indicates whether card is face up or face down
    private int cardID;

    //Constructor for a card object
    public Card(int number, char suit, char faceCard, int cardID){
        this.number = number;
        this.faceCard = faceCard;
        this.suit = suit;
        this.isFaceDown = false;
        this.cardID = cardID;
    }

    //Getter for number variable
    public int getNumber(){
        return number;
    }

    //Getter for suit variable
    public char getSuit(){
        return suit;
    }

    public int getCardID(){
        return cardID;
    }

    //Get to know if card is face up or down
    public boolean getIsFaceDown(){
        return isFaceDown;
    }

    //Getter for the face card
    public char getFaceCard(){
        return faceCard;
    }

    //Set card face down or up property
    public void setIsFaceDown(boolean isFaceDown){
        this.isFaceDown = isFaceDown;
    }
}
