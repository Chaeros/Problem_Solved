import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int N,M;
	public static char map[][];
	public static List<int[]> list;
	public static List<List<Node>> distances;
	public static Map<Integer,Integer> mapping;
	
	public static class Node implements Comparable<Node>{
		int position;
		int cost;
		public Node(int position, int cost) {
			this.position = position;
			this.cost = cost;
		}
		@Override
		public String toString() {
			return "Node [position=" + position + ", cost=" + cost + "]";
		}
		@Override
		public int compareTo(Node o) {
			return this.cost-o.cost;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		map=new char[N][N];
		list=new ArrayList<int[]>();
		distances=new ArrayList<List<Node>>();
		mapping=new HashMap<Integer, Integer>();
		int startPosition=0;
		int count=0;
		for(int r=0;r<N;++r) {
			String str=br.readLine();
			for(int c=0;c<N;++c) {
				char x=str.charAt(c);
				int position=r*N+c;
				if(x=='S') {
					startPosition=position;
				}
				if(x=='S' || x=='K') {
					list.add(new int[]{count,position});
					mapping.put(position, count);
					++count;
				}
				map[r][c]=x;
			}
		}
		for(int n=0;n<=M+1;++n) {
			distances.add(new ArrayList<>());
		}
		for(int[] now:list) {
			int row=now[1]/N;
			int col=now[1]%N;
			bfs(row,col);
		}
		boolean visit[]=new boolean[count+1];
		PriorityQueue<Node> q=new PriorityQueue();
		q.offer(new Node(startPosition,0));
		
		int total=0;
		while(!q.isEmpty()) {
			Node now=q.poll();
			if(visit[mapping.get(now.position)]) continue;
			visit[mapping.get(now.position)]=true;
			total+=now.cost;
			for(Node x:distances.get(mapping.get(now.position))){
				q.offer(x);
			}
		}
		
		boolean isPass=true;
		for(int n=0;n<count;++n) {
			if(!visit[n]) {
				isPass=false;
				break;
			}
		}
		if(isPass) System.out.println(total);
		else System.out.println(-1);
	}
	
	public static void bfs(int row, int col){
		int[][] result=new int[N][N];
		
		int dr[]= {-1,0,1,0};
		int dc[]= {0,1,0,-1};
		
		Queue<int[]> q=new ArrayDeque<int[]>();
		q.offer(new int[] {row,col});
		boolean visit[][]=new boolean[N][N];
		visit[row][col]=true;
		
		int originPosition=row*N+col;
		while(!q.isEmpty()) {
			int[] now=q.poll();
			
			for(int d=0;d<4;++d) {
				int mr=now[0]+dr[d];
				int mc=now[1]+dc[d];
				if(mr<0 || mr>=N || mc<0 || mc>=N) continue;
				if(visit[mr][mc]) continue;
				if(map[mr][mc]=='1') continue;
				result[mr][mc]=result[now[0]][now[1]]+1;
				visit[mr][mc]=true;
				q.offer(new int[] {mr,mc});
				
				int position=mr*N+mc;
				if(map[mr][mc]=='S' || map[mr][mc]=='K') {
					distances.get(mapping.get(originPosition)).add(new Node(position,result[mr][mc]));
				}
			}
		}
	}
}
