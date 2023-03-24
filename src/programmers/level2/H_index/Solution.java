package programmers.level2.H_index;

import java.util.Arrays;
import java.util.Collections;

class Solution {
    public int solution(int[] citations) {
        Integer[] arr = Arrays.stream(citations).boxed().toArray(Integer[]::new);
        Arrays.sort(arr, Collections.reverseOrder());
        for (int i = 0; i < citations.length; i++) {
            if (arr[i] <= (i + 1)) {
                if (arr[i] == (i + 1)) return arr[i];
                return i;
            }
        }
        return citations.length;
    }
}
