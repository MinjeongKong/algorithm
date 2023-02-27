package programmers.level3.합승_택시_요금;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class Edge implements Comparable<Edge> {
    int vex, cost;

    public Edge(int vex, int cost) {
        this.vex = vex;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return this.cost - o.cost;
    }
}

class Solution {
    ArrayList<ArrayList<Edge>> graph = new ArrayList<>();

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] o : fares) {
            graph.get(o[0]-1).add(new Edge(o[1]-1, o[2]));
            graph.get(o[1]-1).add(new Edge(o[0]-1, o[2]));
        }
        int[] together = dijkstra(s - 1, new int[n]);
        int[] aloneA = dijkstra(a - 1, new int[n]);
        int[] aloneB = dijkstra(b - 1, new int[n]);

        for (int i = 0; i < n; i++) {
            if (together[i] == Integer.MAX_VALUE || aloneA[i] == Integer.MAX_VALUE || aloneB[i] == Integer.MAX_VALUE)
                continue;
            answer = Math.min(answer, together[i] + aloneA[i] + aloneB[i]);
        }

        return answer;
    }

    public int[] dijkstra(int start, int[] dis) {
        PriorityQueue<Edge> pQ = new PriorityQueue<>();
        pQ.offer(new Edge(start, 0));
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[start] = 0;
        while (!pQ.isEmpty()) {
            Edge tmp = pQ.poll();
            int now = tmp.vex;
            int nowCost = tmp.cost;
            if (nowCost > dis[now]) continue;
            for (Edge o : graph.get(now)) {
                if (dis[o.vex] > nowCost + o.cost) {
                    dis[o.vex] = nowCost + o.cost;
                    pQ.offer(new Edge(o.vex, dis[o.vex]));
                }
            }
        }
        return dis;
    }

}
