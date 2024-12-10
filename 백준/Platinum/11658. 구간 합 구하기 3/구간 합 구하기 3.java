import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int N,M;
	public static long tree[][];
	public static int cost[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		StringBuilder sb=new StringBuilder();
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		tree=new long[N+1][N+1];
		cost=new int[N+1][N+1];
		
		for(int n=1;n<=N;++n) {
			st=new StringTokenizer(br.readLine());
			for(int i=1;i<=N;++i) {
				int value=Integer.parseInt(st.nextToken());
				cost[n][i]=value;
				update(n,i,value);
			}
		}
		for(int m=0;m<M;++m) {
			st=new StringTokenizer(br.readLine());
			int command=Integer.parseInt(st.nextToken());
			if(command==0) {
				int x=Integer.parseInt(st.nextToken());
				int y=Integer.parseInt(st.nextToken());
				int value=Integer.parseInt(st.nextToken());
				int diff=value-cost[x][y];
				cost[x][y]=value;
				update(x,y,diff);
			} else if(command==1) {
				int x1=Integer.parseInt(st.nextToken());
				int y1=Integer.parseInt(st.nextToken());
				int x2=Integer.parseInt(st.nextToken());
				int y2=Integer.parseInt(st.nextToken());
				
				long resultSum=search(x2,y2)-search(x1-1,y2)-search(x2,y1-1)+search(x1-1,y1-1);
				sb.append(resultSum).append("\n");
			}
		}
		System.out.println(sb.toString());
	}
	
	public static void update(int x, int y, int value) {
		for(int i=x;i<=N;i=i+(i & -i)) {
			for(int j=y;j<=N;j=j+(j & -j)) {
				tree[i][j]+=value;
			}
		}
	}
	
	public static long search(int x, int y) {
		long result=0;
		for(int i=x;i>0;i=i-(i & -i)) {
			for(int j=y;j>0;j=j-(j & -j)) {
				result+=tree[i][j];
			}
		}
		return result;
	}
}