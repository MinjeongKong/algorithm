package programmers.level2.두_큐_합_같게_만들기;

import java.util.*;

class Solution {

    public int solution(int[] queue1, int[] queue2) {
        int answer = -2;
        long sum1 = 0;
        long sum2 = 0;

        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        for (int i = 0; i < queue1.length; i++) {
            sum1 += queue1[i];
            sum2 += queue2[i];

            q1.add(queue1[i]);
            q2.add(queue2[i]);
        }

        if ((sum1 + sum2) % 2 != 0) {
            answer = -1;
        }

        int len = queue1.length * 3;
        for (int i = 0; i < len; i++) {
            if (sum1 == sum2) {
                answer = i;
                break;
            }
            if (sum1 > sum2) {
                int head = q1.poll();
                q2.add(head);
                sum1 -= head;
                sum2 += head;
            } else {
                int head = q2.poll();
                q1.add(head);
                sum1 += head;
                sum2 -= head;
            }
        }

        if (answer == -2) {
            answer = -1;
        }

        return answer;
    }
}