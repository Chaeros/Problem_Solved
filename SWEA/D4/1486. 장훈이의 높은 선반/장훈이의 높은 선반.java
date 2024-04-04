import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	
	public static int N,B;
	public static int arr[];
	public static int result;
	public static int minResult;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for ( int t = 1 ; t <= T ; ++t ) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			arr = new int[N];
			minResult = Integer.MAX_VALUE;
			result = 0;
			st = new StringTokenizer(br.readLine());
			for ( int i = 0 ; i < N ; ++i ) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			dfs(0);
			bw.write("#"+t+" "+(minResult-B)+"\n");
		}
		bw.flush();
		bw.close();
	}
	
	public static void dfs(int depth) {
		if ( depth == N ) {
			if ( result - B >= 0 && minResult > result) {
				minResult = result;
			}
			return;
		}
		
		result += arr[depth];
		dfs(depth+1);
		result -= arr[depth];
		dfs(depth+1);
	}
}
