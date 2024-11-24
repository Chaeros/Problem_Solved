import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N=Integer.parseInt(br.readLine());
		long[] dp = new long[101];
		dp[1]=dp[2]=dp[3]=1L;
		
		for(int n=4;n<=100;++n) {
			dp[n]=dp[n-2]+dp[n-3];
		}
		
		for(int n=0;n<N;++n) {
			int x=Integer.parseInt(br.readLine());
			sb.append(dp[x]).append("\n");
		}
		System.out.println(sb.toString());
	}
}
