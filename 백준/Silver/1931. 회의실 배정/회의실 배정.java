import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	public static class Meeting implements Comparable<Meeting>{
		int start;
		int end;
		
		public Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Meeting o) {
			return this.end != o.end ? this.end - o.end : this.start - o.start;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		Meeting meetings[] = new Meeting[N];
		for ( int i = 0 ; i < N ; ++i ) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			meetings[i] = new Meeting(start,end);
		}
		Arrays.sort(meetings);
		
		
		List<Meeting> list = new ArrayList<>();
		list.add(meetings[0]);
		
		for ( int j = 1 ; j < N ; ++j ) {
			if ( list.get(list.size()-1).end <= meetings[j].start ) {
				list.add(meetings[j]);
			}
		}
		
		bw.write(list.size()+"\n");
		bw.flush();
		bw.close();
	}
}
