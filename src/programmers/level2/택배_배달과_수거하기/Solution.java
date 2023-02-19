package programmers.level2.택배_배달과_수거하기;


class Solution {

    public int setIndex(int[] arr, int n) {
        for (int i = n; i >= 0; i--) {
            if (arr[i] > 0) return i;
        }
        return -1;
    }

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int delP = setIndex(deliveries, deliveries.length - 1);
        int pickP = setIndex(pickups, pickups.length - 1);

        while (delP >= 0 || pickP >= 0) {
            answer += (Math.max(delP, pickP) + 1);
            delP = doTurn(deliveries, delP, cap);
            pickP = doTurn(pickups, pickP, cap);
        }

        return answer * 2;
    }

    private int doTurn(int[] arr, int head, int cap) {
        for (int i = head; i >= 0; i--) {
            if (cap < arr[i]) {
                arr[i] -= cap;
                head = i;
                break;
            } else {
                cap -= arr[i];
                arr[i] = 0;
                head = setIndex(arr, i);
                if (head == -1) {
                    break;
                }
            }
        }
        return head;
    }

}
