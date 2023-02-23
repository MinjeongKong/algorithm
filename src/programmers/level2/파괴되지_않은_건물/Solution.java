package programmers.level2.파괴되지_않은_건물;

class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int colSize = board[0].length;
        int rowSize = board.length;

        int[][] save = new int[rowSize + 1][colSize + 1];
        for (int[] o : skill) {
            int x1 = o[1];
            int y1 = o[2];
            int x2 = o[3];
            int y2 = o[4];
            int c;
            if (o[0] == 1) {
                c = -o[5];
            } else {
                c = o[5];
            }
            save[x1][y1] +=c;
            save[x1][y2 + 1] -=c;
            save[x2 + 1][y1] -=c;
            save[x2 + 1][y2 + 1] +=c;
        }
        for (int j = 0; j < colSize; j++) {
            for (int i = 1; i < rowSize; i++) {
                save[i][j] += save[i - 1][j];
            }
        }

        for (int i = 0; i < rowSize; i++) {
            for (int j = 1; j < colSize; j++) {
                save[i][j] += save[i][j - 1];
            }
        }

        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                board[i][j] += save[i][j];
                if (board[i][j] > 0) answer++;
            }
        }

        return answer;
    }
}
