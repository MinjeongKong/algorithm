package programmers.level2.이모티콘_할인행사;

import java.util.ArrayList;
import java.util.Collections;

class Result implements Comparable<Result>{
    int cnt, sum;

    public Result(int cnt, int sum) {
        this.cnt = cnt;
        this.sum = sum;
    }

    @Override
    public int compareTo(Result o) {
        if (this.cnt == o.cnt) {
            return o.sum-this.sum;
        }
        return o.cnt-this.cnt;
    }
}
class Solution {
    int[] discount = {10, 20, 30, 40};
    int userLen, emoLen;
    ArrayList<Result> list = new ArrayList<>();

    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        /**
         * user 마다 emoticon 을 살지 탐색 : 100*7
         * emoticon 마다 할인율 조합 : 4^7
         * 완전 탐색 시 : 100*7*4^7 < 1억회 이므로 가능
         */
        userLen = users.length;
        emoLen = emoticons.length;
        dfs(0, users, emoticons, new int[emoLen]);
        Collections.sort(list);
        answer[0] = list.get(0).cnt;
        answer[1] = list.get(0).sum;
        return answer;
    }

    public void dfs(int level, int[][] users, int[] emoticons, int[] saveDiscount) {
        if (level == emoLen) {
            int buyCnt = 0;
            int buySum = 0;

            for (int i = 0; i < userLen; i++) {
                int sum = 0;
                for (int j = 0; j < emoLen; j++) {
                    if (users[i][0] <= saveDiscount[j]) {
                        sum += emoticons[j] * (100 - saveDiscount[j]) / 100;
                    }
                }
                if (users[i][1] <= sum) {
                    buyCnt++;
                } else {
                    buySum+=sum;
                }
                list.add(new Result(buyCnt, buySum));
            }
            return;
        }
        for (int i = 0; i < 4; i++) {
            saveDiscount[level] = discount[i];
            dfs(level + 1, users, emoticons, saveDiscount);
        }
    }
}