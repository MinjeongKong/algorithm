package baekjoon.소문난_칠공주;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1941 {
    static char[][] map = new char[5][5];
    static boolean[][] visited = new boolean[5][5];
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int answer = 0;

    static void dfs(int L, int x, int y, int cnt) {
        if (L == 7) {
            if (cnt >= 4) {
                answer++;
            }
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5 || visited[nx][ny]) continue;
            visited[nx][ny] = true;
            if (map[nx][ny] == 'S') {
                dfs(L + 1, nx, ny, cnt + 1);
            } else {
                dfs(L + 1, nx, ny, cnt);
            }
            visited[nx][ny] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            String input = br.readLine();
            for (int j = 0; j < 5; j++) {
                map[i][j] = input.charAt(j);
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (!visited[i][j]) {
                    visited[i][j] = true;
                    if (map[i][j] == 'S') {
                        dfs(1, i, j, 1);
                    } else {
                        dfs(1, i, j, 0);
                    }
                }
            }
        }
        System.out.println(answer);
    }
}
