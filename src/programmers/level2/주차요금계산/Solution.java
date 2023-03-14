package programmers.level2.주차요금계산;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Car {
    List<Integer> timeList = new ArrayList<>();

    public Car() {
    }
}

class Solution {
    public int useTime(List<Integer> timeList) {
        int sum = 0;
        int len = timeList.size();
        for (int i = 1; i < len; i = i + 2) {
            sum += timeList.get(i) - timeList.get(i - 1);
        }
        if (len % 2 != 0) {
            sum += 1439 - timeList.get(len - 1);
        }
        return sum;
    }

    public int[] solution(int[] fees, String[] records) {
        int[] answer;
        Car[] cars = new Car[10000];
        StringTokenizer st;
        for (String s : records) {
            st = new StringTokenizer(s, " ");
            String[] timeStamp = st.nextToken().split(":");
            int time = 60 * Integer.parseInt(timeStamp[0]) + Integer.parseInt(timeStamp[1]);
            int num = Integer.parseInt(st.nextToken());

            if (cars[num] == null) {
                cars[num] = new Car();
            }

            cars[num].timeList.add(time);
            System.out.println("num: " + num + " / time: " + time);
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (Car c : cars) {
            if (c == null) {
                continue;
            }
            int totalTime = useTime(c.timeList);
            int totalFee = fees[1];
            if (totalTime > fees[0]) {
                int extraFee = ((int) Math.ceil((double) (totalTime - fees[0]) / fees[2])) * fees[3];
                totalFee += extraFee;
            }
            result.add(totalFee);
        }
        answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }

}
