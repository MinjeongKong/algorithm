package baekjoon.특정한_최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P1504 {
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
    static int[] distV1;
    static int[] distV2;
    static int V, E;

    public static int[] dst(int start) {
        int[] dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
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
        return dist;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[V + 1];


        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Edge(v, w));
            graph[v].add(new Edge(u, w));
        }
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        distV1 = dst(v1);
        distV2 = dst(v2);

        int answer;
        if (distV1[1] == Integer.MAX_VALUE || distV2[v1] == Integer.MAX_VALUE || distV2[V] == Integer.MAX_VALUE) {
            if (distV2[1] == Integer.MAX_VALUE || distV1[v2] == Integer.MAX_VALUE || distV1[V] == Integer.MAX_VALUE) {
                answer = -1;
            } else {
                answer = distV2[1] + distV1[v2] + distV1[V];
            }
        }else if (distV2[1] == Integer.MAX_VALUE || distV1[v2] == Integer.MAX_VALUE || distV1[V] == Integer.MAX_VALUE) {
            answer = distV1[1] + distV2[v1] + distV2[V];
        } else {
            answer = Math.min(distV2[1] + distV1[v2] + distV1[V], distV1[1] + distV2[v1] + distV2[V]);
        }

        System.out.println(answer);
    }
}
