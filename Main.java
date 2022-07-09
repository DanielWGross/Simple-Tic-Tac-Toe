package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static boolean checkUnbalancedGame(String[] symbolArr) {
        int xCount = 0;
        int oCount = 0;
        int difference = 0;

        for (String symbol: symbolArr ) {
            if (symbol.equals("X")) {
                xCount++;
            } else if (symbol.equals("O")) {
                oCount++;
            }
        }

        difference = xCount - oCount;
        if (difference < 0) {
            difference = difference * -1;
        }
        if (difference >= 2) {
            System.out.println("Impossible");
            return true;
        }
        return false;
    }

    public static boolean checkRows(String[] symbolArr) {
        boolean hasXWon = false;
        boolean hasOWon = false;

        for (int i = 0; i <= 6; i = i + 3) {
            if (symbolArr[i].equals(symbolArr[i + 1]) && symbolArr[i].equals(symbolArr[i + 2]) && !symbolArr[i].equals(" ")) {
                if(symbolArr[i].equals("X")) {
                    hasXWon = true;
                } else {
                    hasOWon = true;
                }
            }
        }

        if (hasXWon && hasOWon) {
            System.out.println("Impossible");
            return true;
        } else if (hasXWon) {
            System.out.println("X wins");
            return true;
        } else if (hasOWon) {
            System.out.println("O wins");
            return true;
        }
        return false;
    }

    public static boolean checkColumns(String[] symbolArr) {
        boolean hasXWon = false;
        boolean hasOWon = false;

        for (int i = 0; i <= 2; i++) {
            if (symbolArr[i].equals(symbolArr[i + 3]) && symbolArr[i].equals(symbolArr[i + 6]) && !symbolArr[i].equals(" ")) {
                if(symbolArr[i].equals("X")) {
                    hasXWon = true;
                } else {
                    hasOWon = true;
                }
            }
        }

        if (hasXWon && hasOWon) {
            System.out.println("Impossible");
            return true;
        } else if (hasXWon) {
            System.out.println("X wins");
            return true;
        } else if (hasOWon) {
            System.out.println("O wins");
            return true;
        }
        return false;
    }

    public static boolean checkDiagonal(String[] symbolArr) {
        boolean hasXWon = false;
        boolean hasOWon = false;

        if (symbolArr[0].equals(symbolArr[4]) && symbolArr[0].equals(symbolArr[8]) && !symbolArr[0].equals(" ")) {
            if(symbolArr[0].equals("X")) {
                hasXWon = true;
            } else {
                hasOWon = true;
            }
        }

        if (symbolArr[2].equals(symbolArr[4]) && symbolArr[2].equals(symbolArr[6]) && !symbolArr[2].equals(" ")) {
            if(symbolArr[2].equals("X")) {
                hasXWon = true;
            } else {
                hasOWon = true;
            }
        }

        if (hasXWon && hasOWon) {
            System.out.println("Impossible");
            return true;
        } else if (hasXWon) {
            System.out.println("X wins");
            return true;
        } else if (hasOWon) {
            System.out.println("O wins");
            return true;
        }
        return false;
    }

    public static boolean checkEmptyCells(String[] symbolArr) {
        for (String symbol : symbolArr) {
            if (!"X".equals(symbol) && !"O".equals(symbol)) {
                return false;
            }
        }
        System.out.println("Draw");
        return true;
    }

    public static boolean analyzeGameState(String[] gameState) {
        boolean isUnbalancedGame = checkUnbalancedGame(gameState);

        if (isUnbalancedGame) {
            return false;
        }

        boolean isRowWinner = checkRows(gameState);
        if (isRowWinner) {
            return false;
        }

        boolean isColumnWinner = checkColumns(gameState);
        if (isColumnWinner) {
            return false;
        }

        boolean isDiagonalWinner = checkDiagonal(gameState);
        if (isDiagonalWinner) {
            return false;
        }

        boolean isGameFinished = checkEmptyCells(gameState);
        if (isGameFinished) {
            return false;
        }

        return true;
    }

    public static boolean getCoords(String[] gameState, boolean isXCurrentPlayer) {
        Scanner scanner = new Scanner(System.in);
        String marker = "X";

        if (!isXCurrentPlayer) {
            marker = "O";
        }

        System.out.print("Enter the coordinates: ");

        int row;
        int column;

        if (scanner.hasNextInt()) {
            row = scanner.nextInt();
        } else {
            System.out.println("You should enter numbers!");
            return false;
        }

        if (scanner.hasNextInt()) {
            column = scanner.nextInt();
        } else {
            System.out.println("You should enter numbers!");
            return false;
        }

        if (row < 1 || column < 1 || row > 3 || column > 3) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }

        int location = 0;

        if (row == 1) {
            location += 0;
        } else if (row == 2) {
            location += 3;
        } else if (row == 3) {
            location += 6;
        }

        if (column == 1) {
            location += 0;
        } else if (column == 2) {
            location += 1;
        } else if (column == 3) {
            location += 2;
        }

        if (!" ".equals(gameState[location])) {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        } else {
            gameState[location] = marker;
            return true;
        }
    }

    public static void printGameState(String[] gameState) {
        System.out.println("---------");

        for (int i = 0; i < gameState.length; i++) {
            if (i == 0 || i == 3 || i == 6) {
                System.out.print("| ");
            }
            System.out.print(gameState[i] + " ");
            if (i == 2 || i == 5 || i == 8 ) {
                System.out.println("| ");
            }
        }

        System.out.println("---------");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean isXCurrentPlayer = true;
        boolean validGameState = true;
        boolean isValidCoords = false;


        String[] gameState = new String[9];
        Arrays.fill(gameState, " ");
        printGameState(gameState);

        do {
            do {
                isValidCoords = getCoords(gameState, isXCurrentPlayer);
            } while (!isValidCoords);
        isXCurrentPlayer = !isXCurrentPlayer;
        printGameState(gameState);
        validGameState = analyzeGameState(gameState);
        } while (validGameState);
    }
}
