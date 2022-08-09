package baekjoon;

import java.io.*;
import java.util.*;

class Dice{
    int U,D,T,B,L,R;
    public Dice(){
        this.U=0;
        this.D=0;
        this.T=0;
        this.B=0;
        this.L=0;
        this.R=0;
    }
}
public class P14499 {
    static int N,M,K; //행,열,명령
    static int[][] map;
    static int[] dx = {0, 0, -1, 1}; // 동서북남
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine()," ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int now_x = Integer.parseInt(st.nextToken());
        int now_y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine()," ");
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Dice dice = new Dice();
        st = new StringTokenizer(br.readLine()," ");
        while(st.hasMoreElements()){
            int dir = Integer.parseInt(st.nextToken());
            int next_x = now_x + dx[dir - 1];
            int next_y = now_y + dy[dir - 1];

            if (next_x < 0 || next_y < 0 || next_x >= N || next_y >= M)
                continue;

            int tmp_U = dice.U;
            int tmp_D = dice.D;
            int tmp_T = dice.T;
            int tmp_B = dice.B;
            int tmp_L = dice.L;
            int tmp_R = dice.R;

            if(dir==1){
                dice.T = tmp_L;
                dice.B = tmp_R;
                dice.L = tmp_B;
                dice.R = tmp_T;

            }else if(dir==2){
                dice.T = tmp_R;
                dice.B = tmp_L;
                dice.L = tmp_T;
                dice.R = tmp_B;

            }else if(dir==3){
                dice.U = tmp_T;
                dice.D = tmp_B;
                dice.T = tmp_D;
                dice.B = tmp_U;

            }else{
                dice.U = tmp_B;
                dice.D = tmp_T;
                dice.T = tmp_U;
                dice.B = tmp_D;

            }

            if(map[next_x][next_y]!=0){
                dice.B = map[next_x][next_y];
                map[next_x][next_y]=0;
            }else{
                map[next_x][next_y] = dice.B;
            }

            sb.append(dice.T + "\n");
            now_x = next_x;
            now_y = next_y;
        }
        System.out.print(sb);
    }
}
