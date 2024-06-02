package com.craft.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyChar;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class ComputerTest {

    private Board mockBoard;
  //  private Board originalBoard;

    private Computer computer;

    @BeforeEach
    public void setup() throws Exception {
        // Mock the singleton instance
        mockBoard = mock(Board.class);

        // Save the original instance
       // originalBoard = Board.getInstance();

        // Use reflection to set the singleton instance to the mock
        Field instanceField = Board.class.getDeclaredField("instance");
        instanceField.setAccessible(true);

        // Remove final modifier from the field
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(instanceField, instanceField.getModifiers() & ~Modifier.FINAL);

        // Set the mock Board instance
        instanceField.set(null, mockBoard);

        // Initialize the Computer with the mock Board
        computer = new Computer();
    }

    @Test
    public void testMakeMove() {

        when(mockBoard.availableMoves()).thenReturn(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8));
        when(mockBoard.checkWinner(anyChar())).thenReturn(false);
        when(mockBoard.makeMove(4, ' ')).thenReturn(true);

        computer.makeMove();

        verify(mockBoard,times(10)).makeMove(anyInt(), eq('X'));
    }

   /* @AfterEach
    public void tearDown() throws Exception {
        // Reset the singleton instance to its original value after each test
        Field instanceField = Board.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, originalBoard);
    }*/
}
