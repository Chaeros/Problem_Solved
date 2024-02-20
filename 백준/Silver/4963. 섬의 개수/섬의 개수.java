import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	static int w,h;
	static int map[][];
	
	static int dr[] = {-1,-1,-1,0,1,1,1,0};
	static int dc[] = {-1,0,1,1,1,0,-1,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		while ( true ) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			if ( w == 0 && h == 0 ) break;
			
			map = new int[h][w];
			for ( int r = 0 ; r < h ; ++r ) {
				st = new StringTokenizer(br.readLine());
				for ( int c = 0 ; c < w ; ++c ) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			int count = 0;
			for ( int r = 0 ; r < h ; ++r ) {
				for ( int c = 0 ; c < w ; ++c ) {
					if ( map[r][c] == 1 ) {
						map[r][c] = 0;
						dfs(r,c);
						++count;
					}
				}
			}
			bw.write(count+"\n");
		}
		bw.flush();
		bw.close();	
	}
	
	private static void dfs(int row, int col) {
		
		for ( int d = 0 ; d < 8 ; ++d ) {
			int mr = row + dr[d];
			int mc = col + dc[d];
			if ( mr < 0 || mr >= h || mc < 0 || mc >= w ) continue;
			if ( map[mr][mc] == 0 ) continue;
			map[mr][mc] = 0;
			dfs(mr,mc);
		}
	}
	
}
