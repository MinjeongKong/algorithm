package programmers.level2.귤_고르기;

import java.util.Arrays;
import java.util.Collections;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        Integer[] save = new Integer[100001];

        for (int i = 0; i < save.length; i++) {
            save[i] = 0;
        }
        for (int x : tangerine) {
            save[x]++;
        }

        Arrays.sort(save,Collections.reverseOrder());

        int tmp = 0;
        for (int i = 0; i < save.length; i++) {
            if (tmp >= k) {
                break;
            }
            tmp += save[i];
            answer++;
        }
        return answer;
    }
}
