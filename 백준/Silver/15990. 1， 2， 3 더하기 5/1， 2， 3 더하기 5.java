import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		long d[][] = new long[100001][3];
		d[1][0] = 1;
		d[2][1] = 1;
		d[3][0] = 1;
		d[3][1] = 1;
		d[3][2] = 1;
		for ( int i = 4 ; i <= 100000 ; ++i ) {
			d[i][0] = (d[i-1][1] + d[i-1][2]) % 1000000009;
			d[i][1] = (d[i-2][0] + d[i-2][2]) % 1000000009;
			d[i][2] = (d[i-3][0] + d[i-3][1]) % 1000000009;
		}
		
		for ( int t = 0 ; t< T; ++t ) {
			int n = Integer.parseInt(br.readLine());
			long result = ( d[n][0] + d[n][1] + d[n][2] ) % 1000000009;
			bw.write(result+"\n");
		}
		bw.flush();
		bw.close();
	}
}

