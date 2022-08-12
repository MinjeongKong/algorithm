package baekjoon;

import java.io.*;
import java.util.*;
class Pos {
    int x,y;
    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
public class P14500 {
    static int N,M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, 1}; // 동서북남
    static int[] dy = {1, -1, 0, 0};
    static int answer = Integer.MIN_VALUE;

    public static void dfs(int L, int sum, int x, int y) {
        if (L == 4) {
            if (sum > answer) {
                answer = sum;
            }
        } else {
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny]) {
                    if(L==2){
                        visited[nx][ny] = true;
                        dfs(L + 1, sum + map[nx][ny], x, y);
                        visited[nx][ny] = false;
                    }
                    visited[nx][ny] = true;
                    dfs(L + 1, sum + map[nx][ny], nx, ny);
                    visited[nx][ny] = false;

                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(1, map[i][j], i, j);
                visited[i][j] = false;
            }
        }

        System.out.println(answer);
    }
}
