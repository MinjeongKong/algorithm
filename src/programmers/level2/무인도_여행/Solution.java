package programmers.level2.무인도_여행;

import java.util.*;

public class Solution {
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int N, M;

    class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int bfs(int x, int y) {
        int sum = 0;
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(x, y));
        visited[x][y] = true;
        while (!q.isEmpty()) {
            Pos tmp = q.poll();
            sum += map[tmp.x][tmp.y];
            for (int i = 0; i < 4; i++) {
                int nx = tmp.x + dx[i];
                int ny = tmp.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (map[nx][ny] == 0 || visited[nx][ny]) continue;
                q.add(new Pos(nx, ny));
                visited[nx][ny] = true;
            }
        }
        return sum;
    }

    public int[] solution(String[] maps) {
        N = maps.length;
        M = maps[0].length();
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (maps[i].charAt(j) != 'X') {
                    map[i][j] = maps[i].charAt(j) - '0';
                }
            }
        }
        List<Integer> answers = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0 && !visited[i][j]) {
                    answers.add(bfs(i, j));
                }
            }
        }
        if (answers.size() == 0) {
            answers.add(-1);
        } else {
            Collections.sort(answers);
        }

        int[] answer = new int[answers.size()];
        for (int i = 0; i < answers.size(); i++) {
            answer[i] = answers.get(i);
        }
        return answer;
    }
}
