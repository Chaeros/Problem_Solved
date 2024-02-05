import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		List<Integer> list = new ArrayList<>();
		for ( int i = 1 ; i <= N ; ++i ) {
			list.add(i);
		}
		
		StringJoiner sj = new StringJoiner(", ","<",">");
		int index = 0;
		while ( list.size() > 0 ) {
			index = (index + K - 1) % list.size();
			sj.add(String.valueOf(list.get(index)));
			list.remove(index);
		}
		bw.write(sj.toString());
		bw.flush();
		bw.close();
	}
}
