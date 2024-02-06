import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class Main {
	
	public static int N;
	public static int dx[] = {-1,0,1,0};
	public static int dy[] = {0,1,0,-1};
	public static int map[][];
	public static boolean isVisited[][];
	public static int count = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		isVisited = new boolean[N][N];
		
		for ( int r = 0 ; r < N ; ++r ) {
			String line = br.readLine();
			for ( int c = 0 ; c < N ; ++c ){
				map[r][c] = Integer.parseInt(String.valueOf(line.charAt(c)));
			}
		}
		
		List<Integer> resultList = new ArrayList<>();
		for ( int r = 0 ; r < N ; ++r ) {
			for ( int c = 0 ; c < N ; ++c ) {
				if ( !isVisited[r][c] && map[r][c] == 1 ) {
					isVisited[r][c] = true;
					count = 1;
					bfs(r,c);
					resultList.add(count);
				}
			}
		}

		bw.write(resultList.size()+"\n");
		Collections.sort(resultList);
		for ( int x : resultList ) {
			bw.write(x+"\n");
		}
		bw.flush();
		bw.close();
	}
	
	public static void bfs(int r, int c) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {r,c});
		
		while ( !q.isEmpty() ) {
			int[] cPos = q.poll();
			int row = cPos[0];
			int col = cPos[1];

			for ( int i = 0 ; i < 4 ; ++i ) {
				int mr = row + dx[i];
				int mc = col + dy[i];
				
				if ( mr < 0 || mr >= N || mc < 0 || mc >= N) continue;
				if ( isVisited[mr][mc] ) continue;
				if ( map[mr][mc] != 1 ) continue;
				isVisited[mr][mc] = true;
				count++;
				q.offer(new int[] {mr,mc});
			}
		}
	}
}
