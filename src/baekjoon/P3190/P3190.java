package baekjoon.P3190;

import java.util.*;
import java.io.*;

class Pos {
    int sec;
    char x;
    public Pos(int sec, char x) {
        this.sec=sec;
        this.x=x;
    }
}
class Snake{
    int r,c;
    public Snake(int r, int c) {
        this.r=r;
        this.c=c;
    }
}
public class P3190
{
    static int[] dx= {0,1,0,-1};
    static int[] dy= {1,0,-1,0}; //시계방향
    public static int roll(char x, int dir) {
        if(x=='L') dir--;
        else dir++;
        if(dir==-1) dir=3;
        if(dir==4) dir=0;
        return dir;
    }
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N=Integer.parseInt(br.readLine());
        int K=Integer.parseInt(br.readLine());

        int[][] map=new int[N+1][N+1];
        for(int i=0;i<K;i++) {
            st=new StringTokenizer(br.readLine()," ");
            int r=Integer.parseInt(st.nextToken());
            int c=Integer.parseInt(st.nextToken());
            map[r][c]=1;
        }
        int L=Integer.parseInt(br.readLine());
        Queue<Pos> q =new LinkedList<>();
        for(int i=0;i<L;i++) {
            st=new StringTokenizer(br.readLine()," ");
            int sec=Integer.parseInt(st.nextToken());
            char x=st.nextToken().charAt(0);
            q.add(new Pos(sec, x));
        }

        map[1][1]=2; //1은 사과 2는 뱀
        Queue<Snake> snake=new LinkedList<>();
        snake.add(new Snake(1,1));
        int dir=0;
        int cnt=0;
        int nr=1, nc=1;
        Pos next=q.poll();
        while(true) {
            if(cnt==next.sec) {
                dir=roll(next.x, dir);
                if(!q.isEmpty()) next=q.poll();
            }
            nr=nr+dx[dir];
            nc=nc+dy[dir];
            //System.out.println("nr:"+nr+", nc:"+nc);
            if(nr>0&&nc>0&&nr<=N&&nc<=N&&map[nr][nc]!=2) {
                if(map[nr][nc]!=1) {
                    Snake tmp=snake.poll();
                    map[tmp.r][tmp.c]=0;
                }
                map[nr][nc]=2;
                snake.add(new Snake(nr,nc));
            }else
                break;
            cnt++;
        }

        System.out.print(cnt+1);
    }
}
