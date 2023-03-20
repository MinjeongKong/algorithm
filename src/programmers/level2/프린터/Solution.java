package programmers.level2.프린터;

import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class Print implements Comparable<Print> {
    int location, priority;

    public Print(int location, int priority) {
        this.location = location;
        this.priority = priority;
    }

    @Override
    public int compareTo(Print o) {
        return o.priority - this.priority;
    }
}

class Solution {
    /*
    일반 큐, 우선순위 큐 생성
    우선순위 큐 pop, 일반 큐 pop해서 우선순위값이 같지 않으면 다시 일반큐에 push 반복
    우선순위 값이 같은 경우 location 값 비교 동일 시 break, 아닐 시 time++ 하고 continue
     */
    public int solution(int[] priorities, int location) {
        int answer = 0;
        PriorityQueue<Integer> pQ = new PriorityQueue<>(Collections.reverseOrder());
        Queue<Print> nQ = new LinkedList<>();

        for (int i = 0; i < priorities.length; i++) {
            nQ.add(new Print(i, priorities[i]));
            pQ.add(priorities[i]);
        }
        while (!pQ.isEmpty()) {
            answer++;
            int qPriority = pQ.poll();
            Print nPrint;
            while (true) {
                nPrint = nQ.poll();
                if (qPriority == nPrint.priority) {
                    break;
                }
                nQ.add(nPrint);
            }
            if (location == nPrint.location) {
                break;
            }
        }
        return answer;
    }
}

