package baekjoon.P16234;

import java.io.*;
import java.util.*;

/**
 * 1. for문으로 map[0][0]부터 visited[][] = false 면 bfs -> 국경선 열리면 visited[][] = true, 인구 sum, list에 국가 추가
 * 2. list 크기가 1이 넘으면 list 지역 sum/list.size 값으로 변경하며 bfs 종료, 이동여부 true.
 * 3. 1번 for문 종료시 이동여부 false 라면 종료, true 시 visited[][] 초기화 후 1번으로
 */
class Pos {
    int x,y;
    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
public class P16234 {
    static int[][] map;
    static boolean[][] visited;
    static int N,L,R;
    static boolean isMove;
    static int answer = 0;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void bfs(Pos now) {
        Queue<Pos> q = new LinkedList<>();
        ArrayList<Pos> list = new ArrayList<>();
        int sum = 0;

        q.add(now);
        visited[now.x][now.y] = true;

        while (!q.isEmpty()) {
            int len = q.size();
            for (int i = 0; i < len; i++) {
                Pos tmp = q.poll();
                list.add(tmp);
                sum += map[tmp.x][tmp.y];
                for (int j = 0; j < 4; j++) {
                    int nx = tmp.x + dx[j];
                    int ny = tmp.y + dy[j];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                    int dif = Math.abs(map[tmp.x][tmp.y]-map[nx][ny]);
                    if (dif >= L && dif <= R && !visited[nx][ny]) {
                        q.add(new Pos(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
            }
        }

        if (list.size() > 1) {
            int people = sum/list.size();
            for (Pos tmp : list) {
                map[tmp.x][tmp.y] = people;
                isMove = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        isMove = true;
        while (isMove) {
            isMove = false;
            answer++;
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        bfs(new Pos(i, j));
                    }
                }
            }
        }
        System.out.println(answer-1);
    }
}
