package TicTacToe.src;
import java.util.Scanner; // Import the Scanner class to read user input

public class TicTacToeGame { // Our main class

    // 1. Game Board Representation
    // A 2D array to represent the 3x3 Tic-Tac-Toe board
    static char[][] board = new char[3][3];

    // 2. Initialize the Board
    // Fill the board with empty spaces (' ') at the start
    public static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // 3. Display the Board
    // Print the current state of the board to the console
    public static void displayBoard() {
        System.out.println("-------------"); // Top border
        for (int i = 0; i < 3; i++) {
            System.out.print("| "); // Left border
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | "); // Cell content and separator
            }
            System.out.println(); // New line after each row
            System.out.println("-------------"); // Row separator
        }
    }

    // 4. Check if a move is valid
    // Checks if the row and column are within bounds and the cell is empty
    public static boolean isValidMove(int row, int col) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3) {
            System.out.println("Invalid move: Row/column out of bounds. Try again.");
            return false;
        }
        if (board[row][col] != ' ') {
            System.out.println("Invalid move: Cell already taken. Try again.");
            return false;
        }
        return true;
    }

    // 5. Check for a Win Condition
    // Checks all rows, columns, and diagonals for a win
    public static boolean checkWin(char player) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
        }
        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == player && board[1][j] == player && board[2][j] == player) {
                return true;
            }
        }
        // Check diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        return false;
    }

    // 6. Check for a Draw Condition
    // If all cells are filled and no one has won, it's a draw
    public static boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false; // Found an empty cell, so not a draw yet
                }
            }
        }
        return true; // All cells are filled, and no win (checked by game loop), so it's a draw
    }

    // 7. Main Game Logic
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Create a Scanner object for input
        char currentPlayer = 'X'; // Player X starts
        boolean gameWon = false;
        boolean gameDraw = false;
        int moves = 0; // Keep track of moves to detect draw faster (max 9 moves)

        initializeBoard(); // Set up the empty board

        System.out.println("Welcome to Tic-Tac-Toe!");

        // Main game loop
        while (!gameWon && !gameDraw) {
            displayBoard(); // Show the current board

            int row, col;
            boolean moveMade = false;

            // Loop until a valid move is entered
            while (!moveMade) {
                System.out.println("Player " + currentPlayer + ", enter your move (row [0-2] column [0-2]): ");
                // Ensure the next input is an integer for row
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number for row: ");
                    scanner.next(); // consume the non-integer input
                }
                row = scanner.nextInt();

                // Ensure the next input is an integer for column
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number for column: ");
                    scanner.next(); // consume the non-integer input
                }
                col = scanner.nextInt();

                if (isValidMove(row, col)) {
                    board[row][col] = currentPlayer; // Place the player's marker
                    moveMade = true;
                    moves++; // Increment move count
                }
            }

            // Check game status after each valid move
            if (checkWin(currentPlayer)) {
                gameWon = true;
                displayBoard();
                System.out.println("Player " + currentPlayer + " wins! Congratulations!");
            } else if (moves == 9 && !gameWon) { // Only check for draw after 9 moves if no one won
                gameDraw = true;
                displayBoard();
                System.out.println("It's a draw!");
            } else {
                // Switch players for the next turn
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }

        scanner.close(); // Close the scanner to prevent resource leaks
        System.out.println("Game Over. Thanks for playing!");
    }
}