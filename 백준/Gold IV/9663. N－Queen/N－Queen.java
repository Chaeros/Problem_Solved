import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	public static int N;
	public static int count = 0;
	public static boolean isSelected[];
	public static Queen queens[];
	public static int size = 0;
	
	static class Queen{
		int x;
		int y;

		public Queen(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		isSelected = new boolean[N];
		queens = new Queen[N];
		for ( int i = 0 ; i < N ; ++i ) {
			queens[i] = new Queen(0,0);
		}
		
		dfs(0);
		bw.write(count+"\n");
		bw.flush();
		bw.close();
	}
	
	public static void dfs(int depth) {
		if ( depth == N) {
			++count;
			return;
		}
		
		for ( int i = 0 ; i < N ; ++i ) {
			if ( !isSafe(depth,i) || isSelected[i] ) continue;
			isSelected[i] = true;
			++size;
			queens[depth].x = depth;
			queens[depth].y = i;
			dfs(depth+1);
			isSelected[i] = false;
			--size;
		}
	}

	private static boolean isSafe(int x, int y) {
		for ( int i = 0 ; i < size ; ++i ) {
			if ( (int)Math.abs(queens[i].x - x) == (int)Math.abs(queens[i].y - y) ) {
				return false;
			}
		}
		return true;
	}
}
