package com.craft.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Scanner;

import static org.mockito.Mockito.*;

public class HumanTest {

    private Board mockBoard;
    private Human human;

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

        // Initialize the Human with the mock Board
        human = new Human("TestPlayer");

        // Set a custom Scanner for the human
        String input = "5\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        human.setScanner(new Scanner(in));
    }

    @Test
    public void testMakeMove() {

        when(mockBoard.makeMove(4, 'O')).thenReturn(true);
        human.makeMove();
        verify(mockBoard).makeMove(4, 'O');
    }

    @Test
    public void testInvalidMove() {
        String input = "10\n5\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        human.setScanner(new Scanner(in));
        when(mockBoard.makeMove(9, 'O')).thenReturn(false); // Position 10 (index 9) is invalid
        when(mockBoard.makeMove(4, 'O')).thenReturn(true);  // Position 5 (index 4) is valid and empty

        human.makeMove();

        verify(mockBoard, never()).makeMove(9, 'O');
        verify(mockBoard).makeMove(4, 'O');
    }
}
