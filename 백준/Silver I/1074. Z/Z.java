import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static int N,r,c;
	public static long count = 0;
	public static long result = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		N = (int)Math.pow(2, N);
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		merge_sort(0, N-1, 0, N-1);
		bw.write(count-1+"\n");
		bw.flush();
		bw.close();		
	}
	
	public static void merge_sort(int rStart, int rEnd, int cStart, int cEnd) {
		if ( rStart == rEnd && cStart == cEnd ) {
			count++;
			return;
		}
		int rMid = ( rStart + rEnd ) / 2;
		int cMid = ( cStart + cEnd ) / 2;
		
		if ( rStart <= r && r <= rMid && cStart <= c && c <= cMid ) {
			merge_sort(rStart, rMid, cStart, cMid);
			return;
		}
		count += (long)Math.pow(rMid-rStart+1,2);
		
		if ( rStart <= r && r <= rMid && cMid+1 <= c && c <= cEnd ) {
			merge_sort(rStart, rMid, cMid+1, cEnd);
			return;
		}
		count += (long)Math.pow(rMid-rStart+1,2);
		
		if ( rMid+1 <= r && r <= rEnd && cStart <= c && c <= cMid ) {
			merge_sort(rMid+1, rEnd, cStart, cMid);
			return;
		}
		count += (long)Math.pow(rEnd-(rMid+1)+1,2);
		
		if ( rMid+1 <= r && r <= rEnd && cMid+1 <= c && c <= cEnd ) {
			merge_sort(rMid+1, rEnd, cMid+1, cEnd);
			return;
		}
		count += (long)Math.pow(rEnd-(rMid+1)+1,2);
	}
}
