import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int weights[] = new int[N+1];
		int values[] = new int[N+1];
		int dp[][] = new int[101][100001];
		
		for ( int n = 1 ; n <= N ; ++n ) {
			st = new StringTokenizer(br.readLine());
			weights[n] = Integer.parseInt(st.nextToken());
			values[n] = Integer.parseInt(st.nextToken());
		}
		
		for ( int n = 1 ; n <= N ; ++n ) {
			for ( int w = 1 ; w <= K ; ++w ) {
				if ( w < weights[n] ) dp[n][w] = dp[n-1][w];
				else dp[n][w] = Math.max(dp[n-1][w], dp[n-1][w-weights[n]]+values[n]);
			}
		}
		
		bw.write(dp[N][K]+"\n");
		bw.flush();
		bw.close();
	}
}
