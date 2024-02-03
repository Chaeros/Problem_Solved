import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for ( int t = 1 ; t <= T ; ++t ) {
			st = new StringTokenizer(br.readLine());
			int count = Integer.parseInt(st.nextToken());
			int number = Integer.parseInt(st.nextToken());
			
			int compareNumber = (1 << count)-1;
			if ( (number & compareNumber) == compareNumber ) {
				bw.write("#" + t + " ON\n");
			}
			else {
				bw.write("#" + t + " OFF\n");
			}
		}
		bw.flush();
		bw.close();
	}
}
