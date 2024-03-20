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
		
		int d[][] = new int[N+2][M+2];
		int arr[][] = new int[N+1][M+1];
		
		for ( int r = 1 ; r <= N ; ++r ) {
			st = new StringTokenizer(br.readLine());
			for ( int c = 1 ; c <= M ; ++c ) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		d[1][1] = arr[1][1];
		for ( int c = 2; c <= M ; ++c ) {
			d[1][c] = d[1][c-1] + arr[1][c];
		}
		
		for ( int r = 2 ; r <= N ; ++r ) {
			int temp1[] = new int[M+2];
			int temp2[] = new int[M+2];
			
			temp1[1] = d[r-1][1] + arr[r][1];
			for ( int c = 2 ; c <= M ; ++c ) {
				temp1[c] = Math.max(d[r-1][c]+arr[r][c],
						temp1[c-1]+arr[r][c]);
			}
			
			temp2[M] = d[r-1][M] + arr[r][M];
			for ( int c  = M-1; c >= 1 ; --c ) {
				temp2[c] = Math.max(d[r-1][c]+arr[r][c],
						temp2[c+1]+arr[r][c]);
			}
			
			for ( int c = 1 ; c <= M ; ++c ) {
				d[r][c] = Math.max(temp1[c], temp2[c]);
			}
		}
		bw.write(d[N][M]+"\n");
		bw.flush();
		bw.close();
	}
}
