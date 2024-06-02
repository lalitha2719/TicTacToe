package com.craft.game;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Scanner;

@Getter
@Setter
@NoArgsConstructor
public class Human implements Player{

    private String name;

    private Board board = Board.getInstance();

    private Scanner scanner = new Scanner(System.in);

    public Human(String name) {
        this.name = name;
    }

    @Override
    public char getSymbol() {
        return 'O';
    }

    @Override
    public void makeMove() {
        int move;
        while (true) {
            System.out.println("Please enter a position, your symbol is "+ getSymbol());
            move = scanner.nextInt()-1;
            if (move >= 0 && move < 9 && board.makeMove(move, getSymbol())) {
                break;
            }
            System.out.println("Invalid move. Try again.");
        }
        System.out.println("Player "+getName()+" filled the position "+move);
    }

}
