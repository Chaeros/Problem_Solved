import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		int d[] = new int[5001];
		Arrays.fill(d, 5001);
		
		d[3] = 1;
		d[5] = 1;
		for ( int i = 6 ; i <= N; ++i ) {
			d[i] = Math.min(d[i-3]+1, d[i-5]+1);
		}
		
		if ( d[N] <= 5000 ) bw.write(d[N]+"\n");
		else bw.write("-1\n");
		bw.flush();
	}
}
