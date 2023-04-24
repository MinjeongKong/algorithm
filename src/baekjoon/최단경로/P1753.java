package baekjoon.최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P1753 {
    static class Edge implements Comparable<Edge> {
        int v, cost;

        public Edge(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    static ArrayList<Edge>[] graph;
    static int[] dist;

    public static void dst(int start) {
        PriorityQueue<Edge> pQ = new PriorityQueue<>();
        pQ.add(new Edge(start, 0));

        while (!pQ.isEmpty()) {
            Edge tmp = pQ.poll();
            if (tmp.cost > dist[tmp.v]) continue;
            for (Edge o : graph[tmp.v]) {
                if (dist[o.v] > o.cost + tmp.cost) {
                    dist[o.v] = o.cost + tmp.cost;
                    pQ.add(new Edge(o.v, dist[o.v]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());
        dist = new int[V + 1];
        graph = new ArrayList[V + 1];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Edge(v, w));
        }
        dst(start);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                sb.append("INF\n");
            } else {
                sb.append(dist[i] + "\n");
            }
        }
        System.out.print(sb);
    }

}
