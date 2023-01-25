package baekjoon.가장_긴_증가하는_부분수열4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class P14002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] arr = new int[N];
        int[] cnt = new int[N];
        int[] index = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        cnt[0] = 1;
        index[0] = -1;

        for (int i = 0; i < N; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (cnt[i] <= cnt[j] && arr[i] > arr[j]) {
                    cnt[i] = cnt[j] + 1;
                    index[i] = j;
                }
                if (j == 0 && cnt[i] == 0) {
                    cnt[i] = 1;
                    index[i] = -1;
                }
            }
        }
        int cntMax = 0;
        int indexMax = -1;
        for (int i = 0; i < N; i++) {
            if (cnt[i] > cntMax) {
                cntMax = cnt[i];
                indexMax = i;
            }
        }
        Stack<Integer> stack = new Stack<>();
        while (indexMax != -1) {
            stack.add(arr[indexMax]);
            indexMax = index[indexMax];
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }

        System.out.println(cntMax);
        System.out.println(sb);
    }
}
