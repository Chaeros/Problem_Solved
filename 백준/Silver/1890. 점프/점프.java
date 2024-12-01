import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N=Integer.parseInt(br.readLine());
		int cost[][]=new int[N][N];
		long dp[][]=new long[N][N];
		
		for(int n=0;n<N;++n) {
			st=new StringTokenizer(br.readLine());
			for(int i=0;i<N;++i) {
				cost[n][i]=Integer.parseInt(st.nextToken());
			}
		}
		
		dp[0][0]=1;
		for(int r=0;r<N;++r) {
			for(int c=0;c<N;++c) {
				int val=cost[r][c];
				
				if(val==0 || dp[r][c]==0) continue;
				if(r+val<N) {
					dp[r+val][c]+=dp[r][c];
				}
				if(c+val<N) {
					dp[r][c+val]+=dp[r][c];
				}
			}
		}
		System.out.println(dp[N-1][N-1]);
	}
}
