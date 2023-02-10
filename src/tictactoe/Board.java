package tictactoe;

import java.util.Scanner;

public class Board {

    private final FieldValues[][] field;
    private boolean finished;
    private boolean xWins;
    private boolean oWins;

    public Board() {
        field = new FieldValues[3][3];
        fillBoard();
        finished = false;
    }

    private void fillBoard() {
        String[] split = "_________".split("");
        int index = 0;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                FieldValues fieldValue = FieldValues.EMPTY;
                if (split[index].equals("O")) {
                    fieldValue = FieldValues.ZERO;
                } else if (split[index].equals("X")) {
                    fieldValue = FieldValues.CROSS;
                }
                field[i][j] = fieldValue;
                index++;
            }
        }
    }

    public void gameLogic(Scanner scanner) {
        int i = 0;
        System.out.println(this);
        while (!finished) {
            askMove(scanner, i);
            checkRows();
            checkCols();
            checkDiagonals();
            if (xWins) {
                System.out.println("X wins");
            } else if (oWins) {
                System.out.println("O wins");
            }

            if (isImpossibleNum() || (xWins && oWins)) {
                System.out.println("Impossible");
            } else if (draw()) {
                System.out.println("Draw");
            }
            i++;
        }


    }

    private void askMove(Scanner scanner, int i) {
        while (true) {
            String location = scanner.nextLine();
            String[] nums = location.split(" ");
            if (isNotNumeric(nums[0]) || isNotNumeric(nums[1])) {
                System.out.println("You should enter numbers!");
                continue;
            }
            int row = Integer.parseInt(nums[0]);
            int col = Integer.parseInt(nums[1]);
            if (!isValidInput(row, col)) {
                System.out.println("Coordinate should be from 1 to 3!");
                continue;
            }
            if (!(field[row - 1][col - 1] == FieldValues.EMPTY)) {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            FieldValues symbol;
            if (i % 2 == 0) {
                symbol = FieldValues.CROSS;
            } else {
                symbol = FieldValues.ZERO;
            }
            field[row - 1][col - 1] = symbol;
            System.out.println(this);
            break;
        }
    }

    private boolean isNotNumeric(String str) {
        return !str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    private boolean isValidInput(int row, int col) {
        return (row >= 1) && (row <= 3) && (col >= 1) && (col <= 3);
    }

    private boolean draw() {
        if (!finished) {
            for (FieldValues[] fieldValues : field) {
                for (FieldValues fieldValue : fieldValues) {
                    if (fieldValue == FieldValues.EMPTY) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    private void checkRows() {
        for (FieldValues[] fieldValues : field) {
            if (fieldValues[0] == fieldValues[1] && fieldValues[0] == fieldValues[2] && fieldValues[0] == FieldValues.CROSS) {
                finished = true;
                xWins = true;
            }
            if (fieldValues[0] == fieldValues[1] && fieldValues[0] == fieldValues[2] && fieldValues[0] == FieldValues.ZERO) {
                finished = true;
                oWins = true;
            }
        }
    }

    private void checkCols() {
        for (int i = 0; i < field.length; i++) {
            if (field[0][i] == field[1][i] && field[0][i] == field[2][i] && field[0][i] == FieldValues.CROSS) {
                finished = true;
                xWins = true;
            }
            if (field[0][i] == field[1][i] && field[0][i] == field[2][i] && field[0][i] == FieldValues.ZERO) {
                finished = true;
                oWins = true;
            }
        }
    }

    private void checkDiagonals() {
        if ((field[0][0] == field[1][1] && field[1][1] == field[2][2] && field[0][0] == FieldValues.CROSS)
                || (field[0][2] == field[1][1] && field[1][1] == field[2][0] && field[1][1] == FieldValues.CROSS)) {

            finished = true;
            xWins = true;
        }
        if ((field[0][0] == field[1][1] && field[1][1] == field[2][2] && field[0][0] == FieldValues.ZERO)
                || (field[0][2] == field[1][1] && field[1][1] == field[2][0] && field[1][1] == FieldValues.ZERO)) {
            finished = true;
            oWins = true;
        }
    }


    private boolean isImpossibleNum() {
        int numX = 0;
        int numO = 0;
        for (FieldValues[] fieldValues : field) {
            for (FieldValues fieldValue : fieldValues) {
                if (fieldValue == FieldValues.ZERO) {
                    numO++;
                } else if (fieldValue == FieldValues.CROSS) {
                    numX++;
                }
            }
        }
        return Math.abs(numX - numO) > 1;

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("---------\n");
        for (FieldValues[] row : field) {
            sb.append("| ");
            for (FieldValues col : row) {
                sb.append(col.getValue()).append(" ");
            }
            sb.append("|\n");

        }
        sb.append("---------");
        return sb.toString();
    }
}