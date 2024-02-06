import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	public static int N,M,R;
	public static int map[][];
	public static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for ( int r = 0 ; r < N ; ++r ) {
			st = new StringTokenizer(br.readLine());
			for ( int c = 0 ; c < M ; ++c ) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
//		R %= 2*(N-1)+2*(M-1);
		
		int minVal = Math.min(N/2, M/2);
		for ( int t = 0 ; t < R ; ++t ) {
			for ( int i = 0 ; i < minVal ; ++i ) {
				rotate(i,i);
			}
		}
		printMap();
		bw.write(String.valueOf(sb));
		bw.flush();
		bw.close();
		
	}
	
	public static void rotate(int row, int col) {
		int tempVal = map[N-row-1][col];
		for ( int r = N-row-1 ; r >= row + 1 ; --r ) {
			map[r][col] = map[r-1][col];
		}
		
		int tempVal2 = map[N-row-1][M-col-1];
		for ( int c = M-col-1 ; c >= col + 2 ; --c ) {
			map[N-row-1][c] = map[N-row-1][c-1];
		}
		map[N-row-1][col+1] = tempVal;
		
		
		int tempVal3 = map[row][M-col-1];
		for ( int r = row ; r <= N-3-row ; ++r ) {
			map[r][M-col-1] = map[r+1][M-col-1];
		}
		map[N-row-2][M-col-1] = tempVal2;
		
		
		for ( int c = col ; c <= M-3-col ; ++c ) {
			map[row][c] = map[row][c+1];
		}
		map[row][M-col-2] = tempVal3;
	}
	
	public static void printMap() {
		for ( int r = 0 ; r < N; ++r ) {
			for ( int c = 0 ; c < M ; ++c ) {
				sb.append(map[r][c]+" ");
			}
			sb.append("\n");
		}
	}
}
