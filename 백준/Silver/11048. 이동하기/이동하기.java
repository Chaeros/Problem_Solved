import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		int N=Integer.parseInt(st.nextToken());
		int M=Integer.parseInt(st.nextToken());
		
		int dp[][]=new int[N][M];
		int cost[][]=new int[N][M];
		
		for(int r=0;r<N;++r) {
			st=new StringTokenizer(br.readLine());
			for(int c=0;c<M;++c) {
				cost[r][c]=Integer.parseInt(st.nextToken());
			}
		}
		
		int dr[]= {0,1,1};
		int dc[]= {1,1,0};
		
		for(int r=0;r<N;++r) {
			for(int c=0;c<M;++c) {
				for(int d=0;d<3;++d) {
					int mr=r+dr[d];
					int mc=c+dc[d];
					if(mr>=N || mc>=M) continue;
					dp[mr][mc]=Math.max(dp[mr][mc], dp[r][c]+cost[r][c]);
				}
			}
		}
		System.out.println(dp[N-1][M-1]+cost[N-1][M-1]);
	}
}