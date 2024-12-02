import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n=Integer.parseInt(br.readLine());
		int dp[]=new int[n+1];
		Arrays.fill(dp, 99999999);
		dp[0]=0;
		dp[1]=1;
		for(int i=2;i<=n;++i) {
			int x=(int)(Math.pow(i, 0.5));
			for(int j=1;j<=x;++j) {
				int pow=j*j;
				dp[i]=Math.min(dp[i], 1+dp[i-pow]);
			}
		}
		System.out.println(dp[n]);
	}
}
