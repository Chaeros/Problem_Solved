import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	static int N,M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int arr[][] = new int[N+2][N+2];
		int prefix_sum[][] = new int[N+2][N+2];
		for ( int r = 1 ; r <= N ; ++r ) {
			st = new StringTokenizer(br.readLine());
			for ( int c = 1 ; c <= N ; ++c ) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		for ( int r = 1 ; r <= N ; ++r ) {
			for ( int c = 1 ; c <= N ; ++c ) {
				prefix_sum[r][c] = prefix_sum[r-1][c]+prefix_sum[r][c-1]-prefix_sum[r-1][c-1] +arr[r][c];
			}
		}
		
		for ( int m = 0 ; m < M ; ++m ) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			bw.write((prefix_sum[x2][y2] - prefix_sum[x1-1][y2] - prefix_sum[x2][y1-1] + prefix_sum[x1-1][y1-1])+"\n");
		}
		bw.flush();
		bw.close();
	}
}