import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {
	
	public static int cost[];
	public static int dp[];
	public static int rdp[];
	public static int rMax[];
	public static int N;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N=Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		cost=new int[N+1];
		dp=new int[N+1];
		rdp=new int[N+1];
		rMax=new int[N+1];
		
		for(int n=1;n<=N;++n) {
			cost[n]=Integer.parseInt(st.nextToken());
			dp[n]=1;
			rdp[n]=1;
		}
		
		rMax[N]=1;
		for(int x=N-1;x>=1;--x) {
			for(int a=N;a>x;--a) {
				if(cost[a]<cost[x]) {
					rdp[x]=Math.max(rdp[x], rdp[a]+1);
				}
			}
			rMax[x]=Math.max(rMax[x+1], rdp[x]);
		}
		
		int result=0;
		for(int x=1;x<=N;++x) {
			for(int a=1;a<x;++a) {
				if(cost[a]<cost[x]) {
					dp[x]=Math.max(dp[x], dp[a]+1);
				}
			}
			result=Math.max(result, dp[x]+getSmallerValue(x));
		}
		System.out.println(result);
	}
	
	public static int getSmallerValue(int x) {
		int result=0;
		for(int n=x+1;n<=N;++n) {
			if(cost[x]>cost[n]) {
				result=Math.max(result, rdp[n]);
			}
		}
		return result;
	}
}
