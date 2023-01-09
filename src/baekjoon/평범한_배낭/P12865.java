package baekjoon.평범한_배낭;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Item implements Comparable<Item>{
    int w, v;
    public Item(int w, int v) {
        this.w = w;
        this.v = v;
    }

    @Override
    public int compareTo(Item o) {
        if (o.w == this.w) {
            return o.v-this.v;
        }
        return this.w - o.w;
    }
}

public class P12865 {
    /**
     * dy[i] 는 i 무게에서 최대의 가치
     * dy[i-item.w]+item.v 는 item을 확정적으로 배낭에 넣을 경우 가치합
     * 중복해서 담을 수 없는 경우 j를 뒤부터 탐색
     */
    static int[] dy;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<Item> items = new ArrayList<>();
        dy = new int[K + 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            items.add(new Item(w, v));
        }
        //Collections.sort(items);
        for (Item item : items) {
            for (int j = K; j >= item.w; j--) {
                dy[j] = Math.max(dy[j], dy[j - item.w] + item.v);
            }
        }

        System.out.println(dy[K]);
    }
}
