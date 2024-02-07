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
		
		st = new StringTokenizer(br.readLine());
		for ( int i = 0 ; i < R ; ++i ) {
			int command = Integer.parseInt(st.nextToken());
			
			switch(command) {
			case 1:
				upsideDownInversion();
				break;
			case 2:
				leftRightInversion();
				break;
			case 3:
				rotateClockwise();
				break;
			case 4:
				rotateCounterClockWise();
				break;
			case 5:
				func5();
				break;
			case 6:
				func6();
				break;
			}
		}
		
		printMap();
		bw.write(sb.toString());
		bw.flush();
		bw.close();		
	}
	
	public static void printMap() {
		for ( int r = 0 ; r < N ; ++r ) {
			for ( int c = 0 ; c < M ; ++c ) {
				sb.append(map[r][c]).append(" ");
			}
			sb.append("\n");
		}
	}
	
	public static void upsideDownInversion() {
		int tempMap[][] = new int[N][M];
		for ( int i = 0 ; i < N ; ++i ) {
			tempMap[i] = map[N-1-i].clone();
		}
		map = tempMap;
	}
	
	public static void leftRightInversion() {
		int tempMap[][] = new int[N][M];
		for ( int c = 0 ; c < M ; ++c ) {
			for ( int r = 0 ; r < N ; ++r ) {
				tempMap[r][c] = map[r][M-1-c];
			}
		}
		map = tempMap;
	}
	
	public static void rotateClockwise() {
		int tempMap[][] = new int[M][N];
		for ( int r = 0 ; r < N ; ++r ) {
			int tempLine[] = map[r].clone();
			for ( int c = 0 ; c < M ; ++c ) {
				tempMap[c][N-1-r] = tempLine[c];
			}
		}
		int temp = N;
		N = M;
		M = temp;
		map = tempMap;
	}
	
	public static void rotateCounterClockWise() {
		int tempMap[][] = new int[M][N];
		for ( int r = 0 ; r < N ; ++r ) {
			int tempLine[] = map[r].clone();
			for ( int c = 0 ; c < M ; ++c ) {
				tempMap[M-1-c][r] = tempLine[c];
			}
		}
		int temp = N;
		N = M;
		M = temp;
		map = tempMap;
	}
	
	public static void func5() {
		int tempMap[][] = new int[N][M];
		int dr[] = {0,N/2,0,-N/2};
		int dc[] = {M/2,0,-M/2,0};
		
		for( int t = 0 ; t < 4 ; ++t ) {
			int row[][] = {{0,N/2},{0,N/2},{N/2,N},{N/2,N}};
			int col[][] = {{0,M/2},{M/2,M},{M/2,M},{0,M/2}};
			for ( int r = row[t][0] ; r < row[t][1] ; ++r ) {
				for ( int c = col[t][0] ; c < col[t][1] ; ++c ) {
					int mr = r + dr[t];
					int mc = c + dc[t];
					tempMap[mr][mc] = map[r][c];
				}
			}
		}
		map = tempMap;
	}
	
	public static void func6() {
		int tempMap[][] = new int[N][M];
		int dr[] = {N/2,0,-N/2,0};
		int dc[] = {0,-M/2,0,M/2};
		
		for( int t = 0 ; t < 4 ; ++t ) {
			int row[][] = {{0,N/2},{0,N/2},{N/2,N},{N/2,N}};
			int col[][] = {{0,M/2},{M/2,M},{M/2,M},{0,M/2}};
			for ( int r = row[t][0] ; r < row[t][1] ; ++r ) {
				for ( int c = col[t][0] ; c < col[t][1] ; ++c ) {
					int mr = r + dr[t];
					int mc = c + dc[t];
					tempMap[mr][mc] = map[r][c];
				}
			}
		}
		map = tempMap;
	}
	
}
