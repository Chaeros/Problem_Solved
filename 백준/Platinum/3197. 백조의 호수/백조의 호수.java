import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int R,C;
	static char[][] map;
	static int resultCount = 0;
	static int dr[] = {-1,0,1,0};
	static int dc[] = {0,1,0,-1};
	static boolean[][] visited;
	static boolean[][] routeVisited;
	static Queue<Position> water = new ArrayDeque<>();
	static Queue<Position> route = new ArrayDeque<>();
	static List<Position> swan = new ArrayList<>();
	static class Position{
		int row;
		int col;
		public Position(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new boolean[R][C];
		routeVisited = new boolean[R][C];
		
		for ( int r = 0 ; r < R ; ++r ) {
			String line = br.readLine();
			for ( int c = 0 ; c < C ; ++c ) {
				map[r][c] = line.charAt(c);
				if ( map[r][c] != 'X' ) {
					water.add(new Position(r,c));
					visited[r][c] = true;
				}
				if ( map[r][c] == 'L' ) {
					swan.add(new Position(r,c));
				}
			}
		}
		
		route.add(swan.get(0));
		routeVisited[swan.get(0).row][swan.get(0).col] = true;
		
		while ( !isMeetable() ) {
			int waterSize = water.size();
			for ( int i = 0 ; i < waterSize ; ++ i ) {
				Position now = water.poll();
				for ( int d = 0 ; d < 4 ; ++d ) {
					int mr = now.row + dr[d];
					int mc = now.col + dc[d];
					if ( mr < 0 || mr >= R || mc < 0 || mc >= C ) continue;
					if ( visited[mr][mc] ) continue;
					if ( map[mr][mc] != 'X' ) continue;
					visited[mr][mc] = true;
					map[mr][mc] = '.';
					water.offer(new Position(mr,mc));
				}
			}
			++resultCount;
		}
		
		bw.write(resultCount+"\n");
		bw.flush();
		bw.close();
	}

	private static boolean isMeetable() {
		Queue<Position> q = new ArrayDeque<>(route);
		route.clear();
		while ( !q.isEmpty() ) {
			Position now = q.poll();
			for ( int d = 0 ; d < 4 ; ++d ) {
				int mr = now.row + dr[d];
				int mc = now.col + dc[d];
				if ( mr < 0 || mr >= R || mc < 0 || mc >= C ) continue;
				if ( routeVisited[mr][mc] ) continue;
				if ( map[mr][mc] == 'X' ) {
					route.add(new Position(mr,mc));
					routeVisited[mr][mc] = true;
					continue;
				}
				routeVisited[mr][mc] = true;
				q.offer(new Position(mr,mc));
			}
		}
		
		Position destination = swan.get(1);
		if ( routeVisited[destination.row][destination.col] ) return true;
		return false;
	}
}
