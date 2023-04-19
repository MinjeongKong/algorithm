package baekjoon.최소_스패닝_트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class P1197 {
    static class Edge implements Comparable<Edge>{
        int a, b, c;

        public Edge(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public int compareTo(Edge o) {
            return this.c-o.c;
        }
    }

    static int[] unf;
    static int Find(int v) {
        if(v==unf[v]) return v;
        return unf[v]=Find(unf[v]);
    }

    static void Union(int a, int b) {
        int fa = Find(a);
        int fb = Find(b);
        if(fa!=fb) unf[fa] = fb;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        unf = new int[V+1];
        ArrayList<Edge> list = new ArrayList<>();
        for(int i=1; i<=V; i++) unf[i]=i;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list.add(new Edge(a, b, c));
        }
        Collections.sort(list);
        int answer = 0;
        for (Edge o : list) {
            int fa = Find(o.a);
            int fb = Find(o.b);
            if(fa!=fb){
                answer+=o.c;
                Union(o.a, o.b);
            }
        }
        System.out.println(answer);
    }
}
