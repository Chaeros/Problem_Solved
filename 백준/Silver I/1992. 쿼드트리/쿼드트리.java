import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	public static int N;
	public static int map[][];
	public static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for ( int i = 0 ; i < N ; ++i ) {
			String line = br.readLine();
			for ( int j = 0 ; j < N ; ++j ) {
				map[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
			}
		}
		
		merge_sort(0, N-1, 0, N-1);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	public static void merge_sort(int rStart, int rEnd, int cStart, int cEnd) {
		
		if ( rStart == rEnd && cStart == cEnd ) {
			if ( isBlack(rStart, rEnd, cStart, cEnd) ) {
				sb.append("1");
				return;
			}
			if ( isWhite(rStart, rEnd, cStart, cEnd)) {
				sb.append("0");
				return;
			}
		}
		int rMid = ( rStart + rEnd ) / 2;
		int cMid = ( cStart + cEnd ) / 2;
		
		if ( isBlack(rStart, rEnd, cStart, cEnd)) {
			sb.append("1");
			return;
		}
		
		if ( isWhite(rStart, rEnd, cStart, cEnd)) {
			sb.append("0");
			return;
		}
		sb.append("(");
		merge_sort(rStart, rMid, cStart, cMid);
		merge_sort(rStart, rMid, cMid+1, cEnd);
		merge_sort(rMid+1, rEnd, cStart, cMid);
		merge_sort(rMid+1, rEnd, cMid+1, cEnd);
		sb.append(")");
	}
	
	public static boolean isBlack(int rStart, int rEnd, int cStart, int cEnd) {
		for ( int r = rStart ; r <= rEnd ; ++r ) {
			for ( int c = cStart ; c <= cEnd ; ++c ) {
				if ( map[r][c] == 0 ) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static boolean isWhite(int rStart, int rEnd, int cStart, int cEnd) {
		for ( int r = rStart ; r <= rEnd ; ++r ) {
			for ( int c = cStart ; c <= cEnd ; ++c ) {
				if ( map[r][c] == 1 ) {
					return false;
				}
			}
		}
		return true;
	}
}
