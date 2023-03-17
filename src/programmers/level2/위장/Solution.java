package programmers.level2.위장;

import java.util.HashMap;

class Solution {

    public int solution(String[][] clothes) {

        int answer = 1;
        HashMap<String, Integer> hp = new HashMap<>();
        for (String[] c : clothes) {
            hp.put(c[1], hp.getOrDefault(c[1], 0) + 1);
        }
        for (Integer value : hp.values()) {
            answer *= (value + 1);
        }

        return answer-1;
    }
}
