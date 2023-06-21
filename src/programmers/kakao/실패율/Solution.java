package programmers.kakao.실패율;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Double.compare() 숙지, int<->double 형변환 주의
 */
class Solution {
    class StageInfo implements Comparable<StageInfo> {
        int stage;
        double fail;

        public StageInfo(int stage, double fail) {
            this.stage = stage;
            this.fail = fail;
        }

        @Override
        public int compareTo(StageInfo o) {
            if (o.fail == this.fail) {
                return this.stage - o.stage;
            }
            return Double.compare(o.fail, this.fail);
        }
    }

    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        int[] now = new int[N + 2];
        int[] total = new int[N + 2];
        for (int stage : stages) {
            for (int i = 1; i <= stage; i++) {
                total[i]++;
            }
            now[stage]++;
        }

        List<StageInfo> list = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (total[i] == 0) {
                list.add(new StageInfo(i, 0));
                continue;
            }
            if (now[i] == total[i]) {
                list.add(new StageInfo(i, 1));
                continue;
            }
            list.add(new StageInfo(i, (double)now[i] / total[i]));
        }
        Collections.sort(list);
        for (int i = 0; i < N; i++) {
            answer[i] = list.get(i).stage;
        }
        return answer;
    }
}
