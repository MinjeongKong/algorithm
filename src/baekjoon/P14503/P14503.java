package baekjoon.P14503;
import java.io.*;
import java.util.*;

public class P14503 {
    static int N,M;
    static int[][] map;
    static int[] dy= {-1,0,1,0};
    static int[] dx= {0,1,0,-1};
    static int answer=0;

    public static void dfs(int row, int col, int dir) {
        map[row][col]=2; //청소

        for(int i=0;i<4;i++) {
            dir-=1;
            if(dir==-1) dir=3;

            int nCol=col+dx[dir];
            int nRow=row+dy[dir];
            if(nCol>=0&&nRow>=0&&nCol<M&&nRow<N) {
                if(map[nRow][nCol]==0) {
                    answer++;
                    dfs(nRow,nCol,dir);
                    return;
                }
            }
        }
        int d=(dir+2)%4;
        int bRow=row+dy[d];
        int bCol=col+dx[d];
        if(bCol>=0&&bRow>=0&&bCol<M&&bRow<N&&map[bRow][bCol]!=1)
            dfs(bRow, bCol, dir);
    }
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        map=new int[N][M];

        st = new StringTokenizer(br.readLine(), " ");
        int row=Integer.parseInt(st.nextToken());
        int col=Integer.parseInt(st.nextToken());
        int dir=Integer.parseInt(st.nextToken());

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<M;j++) {
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        answer++;
        dfs(row, col, dir);
        System.out.print(answer);


    }

}

