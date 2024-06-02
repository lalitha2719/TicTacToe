package com.craft.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BoardTest {

    private Board board;

    @BeforeEach
    public void setup() {
        board = Board.getInstance();
        Arrays.fill(board.getBoard(), '-');
    }

    @Test
    public void testPrintBoardNumbers() {
        assertDoesNotThrow(()->board.printBoardNumbers());
    }

    @Test
    public void testPrintBoard() {
        assertDoesNotThrow(()->board.printBoard());
    }

    @Test
    public void testIsFull() {
        assertFalse(board.isFull());
        Arrays.fill(board.getBoard(), 'X');
        assertTrue(board.isFull());
    }

    @Test
    public void testMakeMove_validMove() {
        assertTrue(board.makeMove(0, 'X'));
        assertEquals('X', board.getBoard()[0]);
    }

    @Test
    public void testMakeMove_invalidMove() {
        Arrays.fill(board.getBoard(), 'X');
        assertFalse(board.makeMove(0, 'O'));
        assertNotEquals('O',board.getBoard()[0]);
        assertEquals('X', board.getBoard()[0]);
    }

    @Test
    public void testRevertMoveForComputer() {
        board.makeMove(0, 'X');
        board.revertMoveForComputer(0);
        assertEquals('-', board.getBoard()[0]);
    }

    @Test
    public void testCheckWinner_noWinner() {
        assertFalse(board.checkWinner('X'));
    }

    @Test
    public void testCheckWinner_horizontal_win() {
        board.makeMove(0, 'X');
        board.makeMove(1, 'X');
        board.makeMove(2, 'X');
        assertTrue(board.checkWinner('X'));
    }

    @Test
    public void testCheckWinner_vertical_win() {
        board.makeMove(0, 'X');
        board.makeMove(3, 'X');
        board.makeMove(6, 'X');
        assertTrue(board.checkWinner('X'));
    }

    @Test
    public void testCheckWinner_diagonal_win() {
        board.makeMove(0, 'X');
        board.makeMove(4, 'X');
        board.makeMove(8, 'X');
        assertTrue(board.checkWinner('X'));
    }

    @Test
    public void testAvailableMoves_emptyBoard() {
        List<Integer> expectedMoves = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8);
        assertEquals(expectedMoves, board.availableMoves());
    }

    @Test
    public void testAvailableMoves_halfFilledBoard() {
        board.makeMove(0, 'X');
        board.makeMove(1, 'O');
        List<Integer> expectedMoves = Arrays.asList(2, 3, 4, 5, 6, 7, 8);
        assertEquals(expectedMoves, board.availableMoves());
    }
}
