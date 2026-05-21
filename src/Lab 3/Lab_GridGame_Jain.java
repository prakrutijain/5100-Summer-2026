// Prakruti Jain

//Note: Test cases are hardcoded in main() to demonstrate the GridGame logic.
//The move() method works independently and can be called with any valid input.
// Scanner-based user input can be added if required.

public class Lab_GridGame_Jain {

    int[][] board;
    int n;

    public Lab_GridGame_Jain(int n) {
        this.n = n;
        board = new int[n][n];
    }

    public int move(int row, int col, int player) {

        // Place the player's mark
        board[row][col] = player;

        // Check the row
        int rowCount = 0;
        for (int i = 0; i < n; i++) {
            if (board[row][i] == player) rowCount++;
        }
        if (rowCount == n) return player;

        // Check the column
        int colCount = 0;
        for (int i = 0; i < n; i++) {
            if (board[i][col] == player) colCount++;
        }
        if (colCount == n) return player;

        // Check main diagonal (top-left to bottom-right)
        int diagCount = 0;
        for (int i = 0; i < n; i++) {
            if (board[i][i] == player) diagCount++;
        }
        if (diagCount == n) return player;

        // Check anti-diagonal (top-right to bottom-left)
        int antiDiagCount = 0;
        for (int i = 0; i < n; i++) {
            if (board[i][n - 1 - i] == player) antiDiagCount++;
        }
        if (antiDiagCount == n) return player;

        return 0;
    }

    public static void main(String[] args) {
        Lab_GridGame_Jain game = new Lab_GridGame_Jain(3);
        System.out.println(game.move(0, 0, 1)); // 0
        System.out.println(game.move(0, 2, 2)); // 0
        System.out.println(game.move(2, 2, 1)); // 0
        System.out.println(game.move(1, 1, 2)); // 0
        System.out.println(game.move(2, 0, 1)); // 0
        System.out.println(game.move(1, 0, 2)); // 0
        System.out.println(game.move(2, 1, 1)); // 1
    }
}