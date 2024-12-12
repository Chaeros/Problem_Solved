import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static int N,M,K;
	public static int parent[];
	public static int cost[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		
		parent=new int[N+1];
		cost=new int[N+1];
		st=new StringTokenizer(br.readLine());
		for(int n=1;n<=N;++n) {
			cost[n]=Integer.parseInt(st.nextToken());
			parent[n]=n;
		}
		
		for(int m=0;m<M;++m) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			union(a,b);
		}
		
		int minCost[]=new int[N+1];
		for(int n=1;n<=N;++n) {
			int root=find(n);
			if(minCost[root]==0 || minCost[root]>cost[n]) {
				minCost[root]=cost[n];
			}
		}
		
		int result=0;
		for(int n=1;n<=N;++n) {
			int findNum=find(n);
			if(n==findNum) {
				result+=minCost[n];
			}
		}
		
		if(result>K) System.out.println("Oh no");
		else System.out.println(result);
	}
	
	public static int find(int x) {
		if(parent[x]==x) return x;
		return parent[x]=find(parent[x]);
	}
	
	public static void union(int a, int b) {
		int aParent=find(a);
		int bParent=find(b);
		
		if(aParent<bParent) {
			parent[bParent]=aParent;
		}
		else {
			parent[aParent]=bParent;
		}
	}
}