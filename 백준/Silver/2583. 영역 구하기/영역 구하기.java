import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static int N,M,K;
	public static boolean[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		map=new boolean[N][M];
		
		for(int k=0;k<K;++k) {
			st=new StringTokenizer(br.readLine());
			int leftX=Integer.parseInt(st.nextToken());
			int leftY=Integer.parseInt(st.nextToken());
			int rightX=Integer.parseInt(st.nextToken())-1;
			int rightY=Integer.parseInt(st.nextToken())-1;
			
			for(int r=leftY;r<=rightY;++r) {
				for(int c=leftX;c<=rightX;++c) {
					map[r][c]=true;
				}
			}
		}
		
		PriorityQueue<Integer> pQ=new PriorityQueue<Integer>();
		for(int r=0;r<N;++r) {
			for(int c=0;c<M;++c) {
				if(!map[r][c]) {
					int result=bfs(r,c);
					pQ.offer(result);
				}
			}
		}
		System.out.println(pQ.size());
		StringBuilder sb=new StringBuilder();
		while(!pQ.isEmpty()) {
			sb.append(pQ.poll()).append(" ");
		}
		System.out.println(sb.toString());
	}
	
	public static int bfs(int row, int col) {
		int result=0;
		int[] dr= {-1,0,1,0};
		int[] dc= {0,1,0,-1};
		
		Queue<int[]> q=new ArrayDeque<int[]>();
		q.offer(new int[] {row,col});
		while(!q.isEmpty()) {
			int[] now=q.poll();
			for(int d=0;d<4;++d) {
				int mr=now[0]+dr[d];
				int mc=now[1]+dc[d];
				if(mr<0 || mr>=N || mc<0 || mc>=M) continue;
				if(map[mr][mc]) continue;
				map[mr][mc]=true;
				q.offer(new int[] {mr,mc});
				++result;
			}
		}
		if(result==0) return 1;
		return result;
	}
}