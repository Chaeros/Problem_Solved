import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	public static int MIN_VALUE = -1000000000;
	public static int MAX_VALUE = 1000000000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for ( int t = 1 ; t <= T; ++t ) {
			int N = Integer.parseInt(br.readLine());
			int maxValue = -1;
			int evenNumberCount = 0;
			for ( int n = 0 ; n < N ; ++n ) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int value = (Math.abs(x) + Math.abs(y));
				if ( value % 2 == 0 ) {
					++evenNumberCount;
				}
				maxValue = Math.max(maxValue, value);
			}
			if ( N - evenNumberCount == 0 || N - evenNumberCount == N ) {
				int sum = 0;
				for ( int i = 0 ; i < MAX_VALUE ; ++i ) {
					sum += i;
					if ( sum >= maxValue ) {
						if ( (sum%2) == (maxValue%2) ) {
							bw.write("#"+t+" "+i+"\n");
							break;
						}
					}
				}
			}
			else {
				bw.write("#"+t+" -1\n");
			}
		}
		bw.flush();
		bw.close();
	}
}