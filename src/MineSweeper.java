import java.util.Scanner;

public class MineSweeper {

    int rowNumber;
    int colNumber;
    String[][] answerBoard;
    String[][] board;

    int mineNumber;
    int mineCount = 0;
    int correctAnswer = 0;
    int row;
    int column;
    int rowMaxLimit;
    int rowMinLimit;
    int columnMaxLimit;
    int columnMinLimit;

    MineSweeper(int rowNumber, int colNumber) {
        this.rowNumber = rowNumber;
        this.colNumber = colNumber;
        this.answerBoard = new String[rowNumber][colNumber];
        this.board = new String[rowNumber][colNumber];
        this.mineNumber = (rowNumber * colNumber) / 4;

    }

    void fillArr() {
        for (int i = 0; i < this.rowNumber; i++) {
            for (int j = 0; j < this.colNumber; j++) {
                this.answerBoard[i][j] = "-";
                this.board[i][j] = "-";
            }
        }
    }

    void placeMines() {

        for (int i = 0; i < this.mineNumber; i++) {
            double row = Math.random() * this.rowNumber;
            double column = Math.random() * this.colNumber;
            if (this.answerBoard[(int) row][(int) column].equals("*")) {
                i--;
            }
            this.answerBoard[(int) row][(int) column] = "*";
        }
    }

    void findMines() {
        this.rowMaxLimit = this.row + 1;
        this.rowMinLimit = this.row - 1;
        this.columnMaxLimit = this.column + 1;
        this.columnMinLimit = this.column - 1;

        if (this.rowMinLimit < 0) {
            this.rowMinLimit++;
        }
        if (this.rowMaxLimit > this.answerBoard[0].length - 1) {
            this.rowMaxLimit--;
        }
        if (this.columnMinLimit < 0) {
            this.columnMinLimit++;
        }
        if (this.columnMaxLimit > this.answerBoard.length - 1) {
            this.columnMaxLimit--;
        }

        for (int i = this.rowMinLimit; i <= this.rowMaxLimit; i++) {
            for (int j = this.columnMinLimit; j <= this.columnMaxLimit; j++) {
                if (this.answerBoard[i][j].equals("*")) {
                    this.mineCount++;
                }
            }
        }
        this.board[this.row][this.column] = Integer.toString(this.mineCount);
        this.mineCount = 0;

    }

    void showBoard() {
        for (int i = 0; i < this.rowNumber; i++) {
            for (int j = 0; j < this.colNumber; j++) {
                System.out.print(this.board[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    void showAnswerBoard() {
        System.out.println("Mayınların Konumu");
        for (int i = 0; i < this.rowNumber; i++) {
            for (int j = 0; j < this.colNumber; j++) {
                System.out.print(this.answerBoard[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    void run() {

        Scanner input = new Scanner(System.in);
        System.out.println("Mayın Tarlası Oyununa Hoşgeldiniz !");

        fillArr();
        placeMines();
        showAnswerBoard();


        while (this.correctAnswer < (this.rowNumber * this.colNumber) - this.mineNumber) {
            System.out.println("=================");
            showBoard();

            System.out.print("Satır Giriniz : ");
            this.row = input.nextInt();

            System.out.print("Sütun Giriniz : ");
            this.column = input.nextInt();


            if (this.row >= 0 && this.row < this.board[0].length) {
                if (this.column >= 0 && this.column < this.board.length) {
                    findMines();
                    this.correctAnswer++;
                    if (this.correctAnswer == (this.rowNumber * this.colNumber) - this.mineNumber) {
                        System.out.println("=====================================================");
                        System.out.println("Son Tablo");
                        showBoard();

                        String art = "!!! Tebrikler oyunu kazandınız !!!";

                        for (int i = 0; i < art.length(); i++) {
                            System.out.print(art.charAt(i));
                        }
                    }

                    if (this.answerBoard[this.row][this.column].equals("*")) {
                        System.out.println("=================");
                        showAnswerBoard();

                        String art = "Mayına Bastın";

                        for (int i = 0; i < art.length(); i++) {
                            System.out.print(art.charAt(i));
                        }
                        break;
                    }

                } else {
                    System.out.println("Lütfen geçerli bir indis değeri giriniz !!!");
                }
            } else {
                System.out.println("Lütfen geçerli bir indis değeri giriniz !!!");
            }
        }
    }
}