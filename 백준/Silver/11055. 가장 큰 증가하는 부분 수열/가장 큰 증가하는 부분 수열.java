import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N=Integer.parseInt(br.readLine());

		int cost[]=new int[N];
		int dp[]=new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int n=0;n<N;++n) {
			cost[n]=Integer.parseInt(st.nextToken());
		}
		
		int result=Integer.MIN_VALUE;
		for(int i=0;i<N;++i) {
			dp[i]=cost[i];
			for(int j=0;j<i;++j) {
				if(cost[i]>cost[j]) {
					dp[i]=Math.max(dp[i], dp[j]+cost[i]);
				}
			}
			result=Math.max(result, dp[i]);
		}
		System.out.println(result);
	}
}
