import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N,M,K;
	static int map[][];
	
	public static class Node{
		int row;
		int col;
		int crushCount;
		int distance;
		public Node(int row, int col, int crushCount, int distance) {
			this.row = row;
			this.col = col;
			this.crushCount = crushCount;
			this.distance = distance;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for ( int r = 0 ; r < N ; ++r ) {
			String line = br.readLine();
			for ( int c = 0 ; c < M ; ++c ) {
				map[r][c] = Integer.parseInt(String.valueOf(line.charAt(c)));
			}
		}
		System.out.println(bfs(0,0));
	}
	
	public static int bfs(int startRow, int startCol) {
		Queue<Node> q = new ArrayDeque<>();
		q.offer(new Node(startRow,startCol,0,1));
		boolean visited[][][] = new boolean[N][M][K+1];
		visited[startRow][startCol][0] = true;
		
		int dr[] = {-1,0,1,0};
		int dc[] = {0,1,0,-1};
		
		while ( !q.isEmpty() ) {
			int qSize = q.size();
			for ( int s = 0 ; s < qSize ; ++s ) {
				Node now = q.poll();
				if ( now.row == N-1 && now.col == M-1 ) {
					return now.distance;
				}
				for ( int d = 0 ; d < 4 ; ++d ) {
					int mr = now.row + dr[d];
					int mc = now.col + dc[d];
					if ( mr < 0 || mr >= N || mc < 0 || mc >= M ) continue;
					if ( visited[mr][mc][now.crushCount] ) continue;
					visited[mr][mc][now.crushCount] = true;
					if ( map[mr][mc] == 1 ) {
						if ( now.crushCount == K ) continue;
						q.offer(new Node(mr,mc,now.crushCount+1,now.distance+1));
					}
					else {
						q.offer(new Node(mr,mc,now.crushCount,now.distance+1));
					}
				}
			}
		}
		return -1;
	}
}
