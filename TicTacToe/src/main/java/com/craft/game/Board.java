package com.craft.game;

import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class Board {

    private char[] board;

    @Getter
    private static final Board instance = new Board();

    private Board() {
        board = new char[9];
        Arrays.fill(board, '-');
    }

    public void printBoardNumbers() {
        for (int i = 1; i <= 3; i++) {
            System.out.println("| -" + (i * 3 - 2) + "- | -" + (i * 3 - 1) + "- | -" + (i * 3) + "- |");
            System.out.println("-------------------");
        }
    }

    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            System.out.println("| " + board[i * 3] + " | " + board[i * 3 + 1] + " | " + board[i * 3 + 2] + " |");
            System.out.println("-------------");
        }
    }

    public boolean isFull() {
        for (char c : board) {
            if (c == '-')
                return false;
        }
        return true;
    }

    public boolean makeMove(int pos, char player) {
        if (board[pos] == '-') {
            board[pos] = player;
            return true;
        }
        return false;
    }

    public void revertMoveForComputer(int pos) {
        board[pos] = '-';
    }

    public boolean checkWinner(char player) {
        if((board[0] == player && board[4] == player && board[8] == player) ||
                (board[2] == player && board[4] == player && board[6] == player))
            return true;

        for (int i = 0; i < 3; i++) {
            if (board[i * 3] == player && board[i * 3 + 1] == player && board[i * 3 + 2] == player)
                return true;
            if (board[i] == player && board[i + 3] == player && board[i + 6] == player)
                return true;
        }
        return false;
    }

    public List<Integer> availableMoves() {
        List<Integer> moves = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            if (board[i] == '-') {
                moves.add(i);
            }
        }
        return moves;
    }
}
