import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Queue<int[]> q = new ArrayDeque<int[]>();
		for ( int n = 0 ; n < N ; ++n ) {
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			for ( int i = K ; i > 0 ; i >>= 1 ) {
				int x = i - (i>>1);
				q.offer(new int[] {V*x,C*x});
			}
		}
		int d[][] = new int[q.size()+1][M+1];
		int size = q.size();
		
		for ( int n = 1 ; n <= size ; ++n ) {
			int[] now = q.poll();
			int weight = now[0];
			int value = now[1];
			for ( int w = 1 ; w <= M ; ++w ) {
				if ( w < weight ) d[n][w] = d[n-1][w];
				else {
					d[n][w] = Math.max(d[n-1][w], d[n-1][w-weight]+value);
					d[n][w] = Math.max(d[n][w], d[n][w-1]);
				}
			}
		}
		bw.write(d[size][M]+"\n");
		bw.flush();
		bw.close();
	}
}
