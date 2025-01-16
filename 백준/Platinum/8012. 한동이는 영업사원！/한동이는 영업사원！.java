import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static int N;
	public static List<Integer> graph[];
	public static int[] depth;
	public static int[][] ac;
	public static int maxLevel;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N=Integer.parseInt(br.readLine());
		graph=new ArrayList[N+1];
		depth=new int[N+1];
		maxLevel=(int) (Math.log(N)/Math.log(2));
		ac=new int[N+1][maxLevel+1];
		
		for(int n=0;n<=N;++n) {
			graph[n]=new ArrayList<>();
		}
		
		for(int n=0;n<N-1;++n) {
			st=new StringTokenizer(br.readLine());
			int start=Integer.parseInt(st.nextToken());
			int end=Integer.parseInt(st.nextToken());
			graph[start].add(end);
			graph[end].add(start);
		}
		depth[0]=-1;
		makeTree(1,0);
		
		int M=Integer.parseInt(br.readLine());
		int prePosition=Integer.parseInt(br.readLine());
		int result=0;
		for(int m=1;m<M;++m) {
			int x=Integer.parseInt(br.readLine());
			int a=prePosition;
			int b=x;
			if(depth[a]>depth[b]) {
				int temp=a;
				a=b;
				b=temp;
			}
			
			for(int i=maxLevel;i>=0;--i) {
				if(depth[a]<=depth[ac[b][i]]) {
					b=ac[b][i];
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
			result+=depth[prePosition]+depth[x]-depth[lca]*2;
			prePosition=x;
		}
		System.out.println(result);
	}
	
	public static void makeTree(int current, int parent) {
		depth[current]=depth[parent]+1;
		ac[current][0]=parent;
		
		for(int i=1;i<=maxLevel;++i) {
			int tmp=ac[current][i-1];
			ac[current][i]=ac[tmp][i-1];
		}
		
		int graphSize=graph[current].size();
		for(int i=0;i<graphSize;++i) {
			if(parent!=graph[current].get(i)) {
				makeTree(graph[current].get(i),current);
			}
		}
	}
}
