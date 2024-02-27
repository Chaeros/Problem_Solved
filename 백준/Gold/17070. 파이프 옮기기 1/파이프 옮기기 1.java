import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int d[][][] = new int[17][17][3];
		int map[][] = new int[17][17];
		d[1][2][0] = 1;
		
		for ( int r = 1 ; r <= N ; ++r ) {
			st = new StringTokenizer(br.readLine());
			for ( int c = 1 ; c <= N ; ++c ) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		for ( int r = 1 ; r <= N ; ++r ) {
			for ( int c = 2 ; c <= N ; ++c ) {
				if ( r == 1 && c == 2 ) continue;
				if ( map[r][c] == 0 ) {
					d[r][c][0] = d[r][c-1][0] + d[r][c-1][2];
					d[r][c][1] = d[r-1][c][1] + d[r-1][c][2];
				}
				if ( map[r][c] == 0 && map[r-1][c] == 0 && map[r][c-1] == 0) {
					d[r][c][2] = d[r-1][c-1][0] + d[r-1][c-1][1] + d[r-1][c-1][2];
				}
			}
		}
		bw.write((d[N][N][0] + d[N][N][1] + d[N][N][2]) + "\n");
		bw.flush();
		bw.close();
	}
}
