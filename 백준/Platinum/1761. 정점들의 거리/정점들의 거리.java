import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	public static class Node{
		int index;
		int distance;
		public Node(int index, int distance) {
			this.index = index;
			this.distance = distance;
		}
	}
	
	public static List<Node> graph[];
	public static int ac[][];
	public static int depth[];
	public static int maxLevel;
	public static int distance[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N=Integer.parseInt(br.readLine());
		graph=new ArrayList[N+1];
		depth=new int[N+1];
		distance=new int[N+1];
		maxLevel = (int) Math.floor(Math.log(N) / Math.log(2));
		ac=new int[N+1][maxLevel+1];
		
		for(int n=0;n<=N;++n) {
			graph[n]=new ArrayList<>();
		}
		
		for(int n=0;n<N-1;++n) {
			st=new StringTokenizer(br.readLine());
			int start=Integer.parseInt(st.nextToken());
			int end=Integer.parseInt(st.nextToken());
			int distance=Integer.parseInt(st.nextToken());
			graph[start].add(new Node(end,distance));
			graph[end].add(new Node(start,distance));
		}
		
		depth[0]=-1;
		makeTree(1, 0);
		
		StringBuilder sb=new StringBuilder();
		int M=Integer.parseInt(br.readLine());
		for(int m=0;m<M;++m) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			int originA=a;
			int originB=b;
			
			if(depth[a]!=depth[b]) {
				if(depth[a]>depth[b]) {
					int tmp=a;
					a=b;
					b=tmp;
				}
				
				for(int i=maxLevel;i>=0;--i) {
					if(depth[a]<=depth[ac[b][i]]) {
						b=ac[b][i];
					}
				}
			}
			int lca=a;
			
			if(a!=b) {
				for(int i=maxLevel;i>=0;--i) {
					if(ac[a][i]!=ac[b][i]) {
						a=ac[a][i];
						b=ac[b][i];
					}
					lca=ac[a][i];
				}
			}
			
			int result=distance[originA]-distance[lca]+distance[originB]-distance[lca];
			sb.append(result).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	public static void makeTree(int current, int parent) {
		depth[current]=depth[parent]+1;
		ac[current][0]=parent;
		
		for(int i=1;i<=maxLevel;++i) {
			int tmp=ac[current][i-1];
			ac[current][i]=ac[tmp][i-1];
		}
		
		int currentLen=graph[current].size();
		for(int i=0;i<currentLen;++i) {
			Node next=graph[current].get(i);
			if(next.index!=parent) {
				distance[next.index]=distance[current]+next.distance;
				makeTree(next.index,current);
			}
		}
	}
}