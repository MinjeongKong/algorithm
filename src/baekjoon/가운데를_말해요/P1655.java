package baekjoon.가운데를_말해요;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class P1655 {
    static PriorityQueue<Integer> leftQ = new PriorityQueue<>(Collections.reverseOrder());
    static PriorityQueue<Integer> rightQ = new PriorityQueue<>();

    public static void solution(int input) {
        if (leftQ.size() == rightQ.size()) {
            leftQ.add(input);
        } else {
            rightQ.add(input);
        }
        if (!rightQ.isEmpty()) {
            swap();
        }
    }

    private static void swap() {
        if (leftQ.peek() > rightQ.peek()) {
            int left = leftQ.poll();
            int right = rightQ.poll();
            leftQ.add(right);
            rightQ.add(left);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            solution(Integer.parseInt(br.readLine()));
            sb.append(leftQ.peek()).append("\n");
        }
        System.out.print(sb);
    }
}
