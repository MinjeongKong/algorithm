package programmers.level2.기능개발;

import java.util.ArrayList;
import java.util.Stack;

class Solution {
    /*
    progress 순회하며 걸리는 작업 시간 계산
    스택이 비어있으면 push, 걸리는 최대 시간 초기화
    스택이 비어있지 않으면 최대시간과 현재 시간 비교
    최대시간 보다 더 오래걸릴 때 스택 pop, answer에 추가
    최대시간 갱신, 스택에 push
    순회 완료 후 스택이 비어있지 않으면 pop, answer에 추가
     */
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> list = new ArrayList<>();
        Stack<Integer> stack = new Stack<Integer>();
        int maxTime = 0;
        for (int i = 0; i < progresses.length; i++) {
            int leftTime = 100 - progresses[i];
            int needDay = leftTime / speeds[i];
            if (leftTime % speeds[i] != 0) {
                needDay++;
            }
            if (stack.isEmpty()) {
                stack.push(i);
                maxTime = needDay;
                continue;
            }
            if (needDay > maxTime) {
                list.add(stack.size());
                stack.clear();
                maxTime = needDay;
            }
            stack.push(i);
        }
        if (!stack.isEmpty()) {
            list.add(stack.size());
        }
        int[] answer = new int[list.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}
