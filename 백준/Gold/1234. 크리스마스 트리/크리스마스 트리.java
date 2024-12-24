import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static long dp[][][][]=new long[11][101][101][101];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		int N=Integer.parseInt(st.nextToken());
		int red=Integer.parseInt(st.nextToken());
		int green=Integer.parseInt(st.nextToken());
		int blue=Integer.parseInt(st.nextToken());
		
		System.out.println(solve(N,red,green,blue));
	}
	
	public static long solve(int n, int r, int g, int b) {
		if(n<0) {
			return 0;
		}
		
		if(r<0 || g<0 || b<0) {
			return 0;
		}
		
		if(n==0) {
			return dp[n][r][g][b]=1;
		}
		
		if(dp[n][r][g][b]!=0) {
			return dp[n][r][g][b];
		}
		
		if(r-n>=0) dp[n][r][g][b]+=solve(n-1,r-n,g,b);
		if(g-n>=0) dp[n][r][g][b]+=solve(n-1,r,g-n,b);
		if(b-n>=0) dp[n][r][g][b]+=solve(n-1,r,g,b-n);
		
		if(n%2==0) {
			int cost=n/2;
			dp[n][r][g][b]+=solve(n-1,r-cost,g-cost,b)*combination(n,cost);
			dp[n][r][g][b]+=solve(n-1,r-cost,g,b-cost)*combination(n,cost);
			dp[n][r][g][b]+=solve(n-1,r,g-cost,b-cost)*combination(n,cost);
		}
		
		if(n%3==0) {
			int cost=n/3;
			dp[n][r][g][b]+=solve(n-1,r-cost,g-cost,b-cost)*combination(n,cost)*combination(n-cost,cost);
		}
		
		return dp[n][r][g][b];
	}
	
	public static long factorial(int x) {
		if(x==0 || x==1) return 1;
		return factorial(x-1)*x;
	}
	
	public static long combination(int n, int x) {
		return factorial(n)/(factorial(n-x)*factorial(x));
	}
}
