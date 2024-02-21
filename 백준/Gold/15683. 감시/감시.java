import java.io.*;
import java.util.*;

public class Main {
    static int N,M;
    static int map[][];

    // 1번, 1방향, 4개의 경우의 수
    static int cctv1X[][] = {{0},{1},{0},{-1}};
    static int cctv1Y[][] = {{1},{0},{-1},{0}};

    // 2번, 2방향, 2개의 경우의 수
    static int cctv2X[][] = {{0, 0},{1,-1}};
    static int cctv2Y[][] = {{1,-1},{0,0}};

    // 3번, 2방향, 4개의 경우의 수
    static int cctv3X[][] = {{0,-1},{1,0},{0,1},{-1,0}};
    static int cctv3Y[][] = {{1,0},{0,1},{-1,0},{0,-1}};

    // 4번, 3방향 ,4개의 경우의 수
    static int cctv4X[][] = {{0,-1,0},{1,0,-1},{0,1,0},{-1,0,1}};
    static int cctv4Y[][] = {{1,0,-1},{0,1,0},{-1,0,1},{0,-1,0}};

    // 5번, 4방향, 1개의 경우의 수
    static int cctv5X[][] = {{0,1,0,-1}};
    static int cctv5Y[][] = {{1,0,-1,0}};

    static HashMap<Integer,int[][]> cctvXs = new HashMap<>(){{
        put(1,cctv1X);
        put(2,cctv2X);
        put(3,cctv3X);
        put(4,cctv4X);
        put(5,cctv5X);
    }};
    static HashMap<Integer,int[][]> cctvYs = new HashMap<>(){{
        put(1,cctv1Y);
        put(2,cctv2Y);
        put(3,cctv3Y);
        put(4,cctv4Y);
        put(5,cctv5Y);
    }};

    static int resultMinVal = Integer.MAX_VALUE;
    static class Node{
        int row;
        int col;
        int number;

        public Node(int row, int col, int number) {
            this.row = row;
            this.col = col;
            this.number = number;
        }
    }

    static List<Node> originCctvList = new ArrayList<>();
    static int subsetArray[];
    static int cctvX[][][];
    static int cctvY[][][];

    static void dfs(int depth){
        if(depth==originCctvList.size()){
            func();
            return;
        }

        Node node = originCctvList.get(depth);

        cctvX[depth] = cctvXs.get(node.number);
        cctvY[depth] = cctvYs.get(node.number);

        for(int i=0;i<cctvX[depth].length;++i){
            subsetArray[depth]=i;
            dfs(depth+1);
        }
    }

    static void func(){

        int tempMap[][];
        // 기존 맵 깊은 복사
        tempMap = new int[N][M];
        for(int j=0;j<N;++j){
            for(int k=0;k<M;++k){
                tempMap[j][k]=map[j][k];
            }
        }

        for(int i=0;i<originCctvList.size();++i){
            int row = originCctvList.get(i).row;
            int col = originCctvList.get(i).col;
            int direction = subsetArray[i];
            for(int j=0;j<cctvX[i][direction].length;++j){
                for(int k=1;k<8;++k){
                    int mx = row+cctvX[i][direction][j]*k;
                    int my = col+cctvY[i][direction][j]*k;

                    if(mx<0 || mx>=N || my<0 || my>=M) break;
                    if(tempMap[mx][my]==6) break;

                    if(tempMap[mx][my]==0) tempMap[mx][my]=7;
                }
            }
        }
        calBlindSpot(tempMap);
    }


    static void calBlindSpot(int tempMap[][]){
        int sum=0;
        for(int i=0;i<N;++i){
            for(int j=0;j<M;++j){
                if(tempMap[i][j]==0) ++sum;
            }
        }
        resultMinVal = Math.min(resultMinVal,sum);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i=0;i<N;++i){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;++j){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<N;++i){
            for(int j=0;j<M;++j){
                if(map[i][j]!=0 && map[i][j]!=6){
                    originCctvList.add(new Node(i,j,map[i][j]));
                }
            }
        }

        cctvX = new int[originCctvList.size()][N][M];
        cctvY = new int[originCctvList.size()][N][M];
        subsetArray = new int[originCctvList.size()];

        dfs(0);

        bw.write(resultMinVal+"\n");
        bw.close();
        bw.close();
    }
}
