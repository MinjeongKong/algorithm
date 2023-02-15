package programmers.level2.호텔_대실;

import java.time.LocalTime;
import java.util.PriorityQueue;

class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        PriorityQueue<Integer> startQ = new PriorityQueue<>();
        PriorityQueue<Integer> endQ = new PriorityQueue<>();

        for (String[] o : book_time) {
            LocalTime start = LocalTime.parse(o[0]);
            LocalTime end = LocalTime.parse(o[1]);
            startQ.add(start.getHour() * 60 + start.getMinute());
            endQ.add(end.getHour() * 60 + end.getMinute() + 10);
        }

        while (!startQ.isEmpty()) {
            Integer tmp = startQ.poll();
            Integer valid = endQ.peek();
            if (tmp >= valid) {
                endQ.poll();
                continue;
            }
            answer++;
        }

        return answer;
    }
}
