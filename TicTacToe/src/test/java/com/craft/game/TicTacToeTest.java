package com.craft.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Random;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TicTacToeTest {

    private Board mockBoard;

    @Mock
    private Human mockHuman = new Human();

    @Mock
    private Computer mockComputer = new Computer();

    @Mock
    private Random random;

    @InjectMocks
    private TicTacToe ticTacToe;

    @Mock
    private Scanner mockScanner;

    @BeforeEach
    public void setup() throws Exception {
        // Mock the singleton instance
        mockBoard = mock(Board.class);

        // Use reflection to set the singleton instance to the mock
        Field instanceField = Board.class.getDeclaredField("instance");
        instanceField.setAccessible(true);

        // Remove final modifier from the field
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(instanceField, instanceField.getModifiers() & ~Modifier.FINAL);

        // Set the mock Board instance
        instanceField.set(null, mockBoard);
        random = mock(Random.class);

        // Initialize the TicTacToe with mock dependencies
        ticTacToe = new TicTacToe(mockBoard, null, mockComputer, mockHuman, random);
    }

    @Test
    public void testConductToss_HumanWins() {
        when(random.nextInt()).thenReturn(2);
        ticTacToe.setCurrentPlayer(mockHuman);

        ticTacToe.conductToss();

        assertEquals(mockHuman, ticTacToe.getCurrentPlayer());
    }

    @Test
    public void testConductToss_ComputerWins() {

        when(random.nextInt()).thenReturn(3); // Odd number, computer wins the toss
        ticTacToe.setCurrentPlayer(mockComputer);

        ticTacToe.conductToss();

        assertEquals(mockComputer, ticTacToe.getCurrentPlayer());
    }

    @Test
    public void testSwitchPlayer() {
        ticTacToe.setCurrentPlayer(mockHuman);
        ticTacToe.switchPlayer();
        assertEquals(mockComputer, ticTacToe.getCurrentPlayer());

        ticTacToe.switchPlayer();
        assertEquals(mockHuman, ticTacToe.getCurrentPlayer());
    }
}
