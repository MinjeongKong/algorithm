package baekjoon.P14502;

import java.io.*;
import java.util.*;

class Pos {
    int x,y;
    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
public class P14502 {
    static int N,M;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int answer = Integer.MIN_VALUE;

    public static void bfs(int x, int y) {
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(x, y));
        while (!q.isEmpty()) {
            int len = q.size();
            for (int i = 0; i < len; i++) {
                Pos tmp=q.poll();
                for (int j = 0; j < 4; j++) {
                    int nx = tmp.x + dx[j];
                    int ny = tmp.y + dy[j];
                    if(nx<0||ny<0||nx>=N||ny>=M) continue;
                    if (map[nx][ny] == 0) {
                        map[nx][ny] = 3; // 바이러스가 퍼져나간 곳
                        q.add(new Pos(nx, ny));
                    }
                }
            }
        }

    }

    public static void setMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j]==3) map[i][j]=0;
            }
        }
    }

    public static void printMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
    public static void dfs(int L, int s) {
        if (L == 3) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(map[i][j]==2) bfs(i, j);
                }
            }
            int cnt=0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(map[i][j]==0) cnt++;
                }
            }
            answer = Math.max(answer, cnt);

            //printMap();
            //System.out.println("answer : "+answer);
            setMap();
        } else {
            for (int i = s; i < N * M; i++) {
                if (map[i / M][i % M] == 0) {
                    map[i / M][i % M] = 1;
                    dfs(L + 1, s + 1);
                    map[i / M][i % M] = 0;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0, 0);
        System.out.println(answer);
    }
}
