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
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		Set<Integer> set = new HashSet<>();
		for ( int n = 0 ; n < N ; ++n ) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			int val;
			switch ( command ) {
			case "add":
				val = Integer.parseInt(st.nextToken());
				set.add(val);
				break;
			case "remove":
				val = Integer.parseInt(st.nextToken());
				set.remove(val);
				break;
			case "check":
				val = Integer.parseInt(st.nextToken());
				if ( set.contains(val) ) {
					sb.append("1\n");
				}
				else {
					sb.append("0\n");
				}
				break;
			case "toggle":
				val = Integer.parseInt(st.nextToken());
				if ( set.contains(val) ) {
					set.remove(val);
				}
				else {
					set.add(val);
				}
				break;
			case "all":
				set.clear();
				for ( int i = 1 ; i <= 20 ; ++i ) {
					set.add(i);
				}
				break;
			case "empty":
				set.clear();
				break;
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
