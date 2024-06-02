package com.craft.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Random;
import java.util.Scanner;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicTacToe {

    private Board board = Board.getInstance();
    private Player currentPlayer;
    private Player computer = new Computer();
    private Player human;
    private Random random = new Random();

    public void playGame() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello Human Player. Please Enter your name");
        human = new Human(sc.next());
        conductToss();
        board.printBoardNumbers();

        while (true) {
            System.out.println("Player "+ currentPlayer.getName()+"'s turn");
            currentPlayer.makeMove();
            board.printBoard();

            if (board.checkWinner(currentPlayer.getSymbol())) {
                System.out.println(currentPlayer.getName()+" -("+currentPlayer.getSymbol()+")" + " wins!");
                break;
            } else if (board.isFull()) {
                System.out.println("It is a draw \uD83D\uDE00");
                break;
            }
            switchPlayer();
        }
    }

    public void conductToss() {
        System.out.println("Conducting Toss between 2 players");
        int tossNumber = random.nextInt();
        this.currentPlayer = tossNumber % 2 == 0 ? human : computer;
        Player secondPlayer = tossNumber % 2 != 0 ? human : computer;
        System.out.println(currentPlayer.getName()+" won the toss");
        System.out.println("First Player is "+ currentPlayer.getName() + " - ("+currentPlayer.getSymbol()+")");
        System.out.println("Second Player is "+ secondPlayer.getName() + " - ("+secondPlayer.getSymbol()+")");
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == human) ? computer : human;
    }
}