package com.craft;

import com.craft.game.TicTacToe;

public class GameMain {
    public static void main(String[] args) {
        System.out.println("Welcome to Tic Tac Toe!!!");

        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.playGame();
    }
}
