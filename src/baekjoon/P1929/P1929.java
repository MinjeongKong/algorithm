package baekjoon.P1929;

import java.io.*;
import java.util.*;

public class P1929 {
    static boolean[] num = new boolean[1000001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine()," ");
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        // 소수 : 에라토스테네스의 체
        for (int i = 2; i <= N; i++) {
            if (!num[i]) {
                if (i >= M) sb.append(i).append("\n");
                for (int j = i; j <= N; j = j + i) {
                    num[j] = true;
                }
            }
        }
        System.out.print(sb);
    }
}
