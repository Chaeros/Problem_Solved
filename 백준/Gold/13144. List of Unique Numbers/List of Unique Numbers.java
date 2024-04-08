import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int arr[] = new int[N];
		st = new StringTokenizer(br.readLine());
		for ( int n = 0 ; n < N ; ++n ) {
			arr[n] = Integer.parseInt(st.nextToken());
		}
		
		long result = 0;
		int lp = 0;
		int rp = 0;
		Set<Integer> set = new HashSet<>();
		while ( rp < N) {
			if ( set.contains(arr[rp]) ) {
				result += rp - lp;
				set.remove(arr[lp++]);
				continue;
			}
			set.add(arr[rp++]);
		}
		while ( lp < N ) {
			result += rp - lp;
			++lp;
		}
		bw.write(result+"\n");
		bw.flush();
		bw.close();
	}
}
