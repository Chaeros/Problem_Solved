import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N=Integer.parseInt(br.readLine());
		double dp[]=new double[N];
		
		double maxVal=dp[0];
		for(int n=0;n<N;++n) {
			dp[n]=Double.parseDouble(br.readLine());
		}
		for(int n=1;n<N;++n) {
			dp[n]=Math.max(dp[n],dp[n-1]*dp[n]);
			maxVal=Math.max(maxVal, dp[n]);
		}
		System.out.print(String.format("%.3f",maxVal));;
	}
}
