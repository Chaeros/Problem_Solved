import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int cost[][] = new int[N+1][3];
		for ( int i = 1 ; i <= N ; ++i ) {
			st = new StringTokenizer(br.readLine());
			cost[i][0] = Integer.parseInt(st.nextToken());
			cost[i][1] = Integer.parseInt(st.nextToken());
			cost[i][2] = Integer.parseInt(st.nextToken());
		}
		
		int d[][] = new int[1001][3];
		for ( int i = 1 ; i <= N ; ++i ) {
			d[i][0] = Math.min(d[i-1][1]+cost[i][0], d[i-1][2]+cost[i][0]);
			d[i][1] = Math.min(d[i-1][0]+cost[i][1], d[i-1][2]+cost[i][1]);
			d[i][2] = Math.min(d[i-1][0]+cost[i][2], d[i-1][1]+cost[i][2]);
		}
		
		int minVal = Math.min(d[N][0], d[N][1]);
		minVal = Math.min(minVal, d[N][2]);
		bw.write(minVal+"\n");
		bw.flush();
		bw.close();
	}
}
