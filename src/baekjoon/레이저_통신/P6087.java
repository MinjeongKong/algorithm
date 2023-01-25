package baekjoon.레이저_통신;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Pos implements Comparable<Pos> {
    int x, y, dir, cost;

    public Pos(int x, int y, int dir, int cost) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.cost = cost;
    }

    @Override
    public int compareTo(Pos o) {
        return this.cost - o.cost;
    }
}

public class P6087 {
    static char[][] arr;
    static int[][] cost;
    static int[] dirX = {-1, 0, 1, 0}; //북, 서, 남, 동
    static int[] dirY = {0, -1, 0, 1};
    static int H, W;

    public static int bfs(int x, int y) {
        PriorityQueue<Pos> q = new PriorityQueue<>();
        q.add(new Pos(x, y, -1, 0));
        cost[x][y] = 0;

        int cnt = 0;

        while (!q.isEmpty()) {
            Pos tmp = q.poll();

            if (arr[tmp.x][tmp.y] == 'C') {
                cnt++;
                if (cnt == 2) return cost[tmp.x][tmp.y];
            }
            if (tmp.cost > cost[tmp.x][tmp.y]) continue;

            for (int i = 0; i < 4; i++) {
                int nx = tmp.x + dirX[i];
                int ny = tmp.y + dirY[i];

                if (nx < 0 || ny < 0 || nx >= H || ny >= W) continue;
                if (arr[nx][ny] == '*') continue;

                if (i != tmp.dir && tmp.dir != -1) {
                    if (cost[nx][ny] >= tmp.cost + 1) {
                        cost[nx][ny] = tmp.cost + 1;
                        q.add(new Pos(nx, ny, i, tmp.cost + 1));
                    }
                } else {
                    if (cost[nx][ny] >= tmp.cost) {
                        cost[nx][ny] = tmp.cost;
                        q.add(new Pos(nx, ny, i, tmp.cost));
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        arr = new char[H][W];
        cost = new int[H][W];

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                cost[i][j] = Integer.MAX_VALUE;
            }
        }

        int startX = 0;
        int startY = 0;

        for (int i = 0; i < H; i++) {
            String input = br.readLine();
            for (int j = 0; j < W; j++) {
                arr[i][j] = input.charAt(j);
                if (arr[i][j] == 'C') {
                    startX = i;
                    startY = j;
                }
            }
        }
        System.out.println(bfs(startX, startY));
    }
}
