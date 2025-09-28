import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static int waterPositionR=0;
    public static int waterPositionC=0;

    public static int animalPositionR=0;
    public static int animalPositionC=0;

    public static int destinationPositionR=0;
    public static int destinationPositionC=0;

    public static int R,C;
    public static char[][] map;

    public static int[][] waterMap=new int[50][50];
    public static int[][] moveMap=new int[50][50];

    public static Queue<Point> waterQueue=new LinkedList<Point>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());
        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                waterMap[i][j] = Integer.MAX_VALUE;
            }
        }

        for(int r=0;r<R;r++){
            String line=br.readLine();
            for(int c=0;c<C;c++){
                map[r][c] = line.charAt(c);

                if(map[r][c]=='*'){
                    waterQueue.offer(new Point(r,c,1));
                }
                else if(map[r][c]=='S'){
                    animalPositionR=r;
                    animalPositionC=c;
                }
                else if(map[r][c]=='D'){
                    destinationPositionR=r;
                    destinationPositionC=c;
                }
            }
        }

        waterBfs();
        animalBfs(animalPositionR,animalPositionC);

        int result = moveMap[destinationPositionR][destinationPositionC];
        if(result==0){
            System.out.println("KAKTUS");
        }
        else{
            System.out.println(result-1);
        }
    }

    public static class Point{
        int row,col,dist;
        Point(int row,int col, int dist){
            this.row=row;
            this.col=col;
            this.dist=dist;
        }
    }

    public static void waterBfs(){
        int[] dr={-1,0,1,0};
        int[] dc={0,1,0,-1};
        boolean[][] visited=new boolean[R][C];

        Queue<Point> q=new LinkedList<>();

        for(Point p:waterQueue){
            q.offer(p);
            visited[p.row][p.col]=true;
            waterMap[p.row][p.col]=1;
        }

        while(!q.isEmpty()){
            Point p=q.poll();
            for(int d=0;d<4;d++){
                int mR=p.row+dr[d];
                int mC=p.col+dc[d];
                int mDist=p.dist+1;
                if(mR<0 || mR>=R || mC<0 || mC>=C) continue;
                if(visited[mR][mC]) continue;
                if(map[mR][mC]=='X') continue;
                if(map[mR][mC]=='D') continue;
                visited[mR][mC]=true;
                waterMap[mR][mC]=mDist;
                q.offer(new Point(mR,mC,mDist));
            }
        }
    }

    public static void animalBfs(int r,int c){
        int[] dr={-1,0,1,0};
        int[] dc={0,1,0,-1};
        boolean[][] visited=new boolean[R][C];

        Queue<Point> q=new LinkedList<>();
        q.offer(new Point(r,c,1));
        visited[r][c]=true;
        moveMap[r][c]=1;

        while(!q.isEmpty()){
            Point p=q.poll();
            for(int d=0;d<4;d++){
                int mR=p.row+dr[d];
                int mC=p.col+dc[d];
                int mDist=p.dist+1;
                if(mR<0 || mR>=R || mC<0 || mC>=C) continue;
                if(visited[mR][mC]) continue;
                if(map[mR][mC]=='X') continue;
                if(map[mR][mC]!='D' && waterMap[mR][mC]<=mDist) continue;
                visited[mR][mC]=true;
                moveMap[mR][mC]=mDist;
                q.offer(new Point(mR,mC,mDist));
            }
        }
    }
}
