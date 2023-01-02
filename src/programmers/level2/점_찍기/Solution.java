package programmers.level2.점_찍기;

class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        for (long x = 0; x <= d; x += k) {
            long tmp = (long) Math.sqrt((long) d*d - x*x);
            answer += (tmp / k + 1);
        }
        return answer;
    }
}
