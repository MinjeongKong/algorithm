package programmers.level2.행렬_테두리_회전하기;

class Solution {
    static int[][] map;
    public int query(int x1, int y1, int x2, int y2) {
        int min = 10001;
        int tmp = map[x1][y1];
        for (int col = y1+1; col <= y2; col++) {
            int next = map[x1][col];
            map[x1][col] = tmp;
            min = Math.min(min, tmp);
            tmp = next;
        }
        for (int row = x1 + 1; row <= x2; row++) {
            int next = map[row][y2];
            map[row][y2] = tmp;
            min = Math.min(min, tmp);
            tmp = next;
        }
        for (int col = y2 - 1; col >= y1; col--) {
            int next = map[x2][col];
            map[x2][col] = tmp;
            min = Math.min(min, tmp);
            tmp = next;
        }
        for (int row = x2 - 1; row >= x1; row--) {
            int next = map[row][y1];
            map[row][y1] = tmp;
            min = Math.min(min, tmp);
            tmp = next;
        }
        return min;
    }
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        map = new int[rows+1][columns+1];
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                map[i][j] = (i - 1) * columns + j;
            }
        }
        for (int i = 0; i < answer.length; i++) {
            answer[i] = query(queries[i][0], queries[i][1], queries[i][2], queries[i][3]);
        }
        return answer;
    }
}
