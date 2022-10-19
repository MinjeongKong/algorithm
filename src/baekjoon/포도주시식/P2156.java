package baekjoon.포도주시식;

import java.io.*;

public class P2156 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N+1];
        int[] dp = new int[N+1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp[1] = arr[1];
        if (N > 1) {
            dp[2] = arr[1] + arr[2];

            for (int i = 3; i <= N; i++) {
                dp[i] = Math.max(Math.max(dp[i - 3] + arr[i - 1], dp[i - 2]) + arr[i], dp[i - 1]);
            }
        }

        System.out.println(dp[N]);
    }

}
