package baekjoon.P15685;

import java.io.*;
import java.util.*;

class Curve {
   int x,y,d,g;
    public Curve(int x, int y, int d, int g) {
        this.x = x;
        this.y = y;
        this.d = d;
        this.g = g;
    }
}
public class P15685 {
    static int[] dx = {1, 0, -1, 0}; // 열
    static int[] dy = {0, -1, 0, 1}; // 행
    static boolean[][] map = new boolean[101][101];

    public static void draw(Curve curve) {
        int cnt = 0; // 세대
        ArrayList<Integer> list = new ArrayList<>();
        list.add(curve.d);
        map[curve.y][curve.x] = true;
        int cx = curve.x + dx[curve.d];
        int cy = curve.y + dy[curve.d];
        map[cy][cx] = true;

        while (cnt < curve.g) {
            for (int i = list.size() - 1; i >= 0; i--) {
                int dir = (list.get(i) + 1) % 4;
                list.add(dir);
                cx += dx[dir];
                cy += dy[dir];
                map[cy][cx] = true;
            }
            cnt++;
        }
    }

    public static int check() {
        int answer = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] && map[i + 1][j] && map[i][j + 1] && map[i + 1][j + 1]) {
                    answer++;
                }
            }
        }
        return answer;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        ArrayList<Curve> curves = new ArrayList<>();
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            curves.add(new Curve(x, y, d, g));
        }
        for (Curve o : curves) {
            draw(o);
        }
        System.out.println(check());

    }
}
