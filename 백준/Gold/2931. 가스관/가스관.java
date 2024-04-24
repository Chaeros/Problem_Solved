import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Pipe{
		// top, bottom, left, right
		boolean direction[] = new boolean[4];
		public Pipe(boolean top, boolean bottom, boolean left, boolean right) {
			direction[0] = top;
			direction[1] = right;
			direction[2] = bottom;
			direction[3] = left;
		}
		@Override
		public String toString() {
			return "Pipe [direction=" + Arrays.toString(direction) + "]";
		}
	}
	
	static int R,C;
	static Pipe[][] map;
	static int startX, startY;
	static int endX, endY;
	static Queue<int[]> tempQ;
	static char[] text = {'+','|','-','1','2','3','4'};
	static int dx[] = {-1,0,1,0};
	static int dy[] = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new Pipe[R][C];
		tempQ = new ArrayDeque<int[]>();
		
		for ( int r = 0 ; r < R ; ++r ) {
			String line = br.readLine();
			for ( int c = 0 ; c < C ; ++c ) {
				char object = line.charAt(c);
				setMap(r,c,object);
			}
		}
		
		selectPosition();
		outter : while ( !tempQ.isEmpty() ) {
			int[] temp = tempQ.poll();
			for( int i = 0 ; i < 7 ; ++i ) {
				setMap(temp[0],temp[1],text[i]);
				boolean isContinue = false;
				for ( int d = 0 ; d < 4 ; ++d ) {
					int mx = temp[0] + dx[d];
					int my = temp[1] + dy[d];
					if ( mx < 0 || mx >= R || my < 0 || my >= C ) {
						if ( map[temp[0]][temp[1]].direction[d] ) {
							isContinue = true;
							break;
						}
						continue;
					}
					if ( map[temp[0]][temp[1]].direction[d] &&
						 !map[mx][my].direction[(d+2)%4] ) {
						isContinue = true;
						break;
					}
				}
				if ( isContinue ) continue;
				if ( getResult() ) {
					System.out.println((temp[0]+1)+" "+(temp[1]+1)+" "+text[i]);
					break outter;
				}
				map[temp[0]][temp[1]] = new Pipe(false,false,false,false);
			}
		}
		
	}
	
	public static void setMap(int row, int col, char object) {
		switch(object) {
		case '|': map[row][col] = new Pipe(true,true,false,false);
		break;
		case '-': map[row][col] = new Pipe(false,false,true,true);
		break;
		case '+': map[row][col] = new Pipe(true,true,true,true);
		break;
		case '1': map[row][col] = new Pipe(false,true,false,true);
		break;
		case '2': map[row][col] = new Pipe(true,false,false,true);
		break;
		case '3': map[row][col] = new Pipe(true,false,true,false);
		break;
		case '4': map[row][col] = new Pipe(false,true,true,false);
		break;
		case 'Z': map[row][col] = new Pipe(false,false,false,false);
				startX = row; startY = col; break;
		case 'M': map[row][col] = new Pipe(false,false,false,false);
				endX = row; endY = col; break;
		default : map[row][col] = new Pipe(false,false,false,false);
		break;
		}
	}
	
	public static void selectPosition() {
		boolean[][] visited = new boolean[R][C];
		Queue<int[]> q = new ArrayDeque<int[]>();
		for ( int d = 0 ; d < 4 ; ++d ) {
			int mx = startX + dx[d];
			int my = startY + dy[d];
			if ( mx < 0 || mx >= R || my < 0 || my >= C ) continue;
			boolean isSpace = true;
			for ( int i = 0 ; i < 4 ; ++i ) {
				if ( map[mx][my].direction[i] ) {
					isSpace = false;
				}
			}
			if ( isSpace ) {
				tempQ.offer(new int[] {mx,my});
				visited[mx][my] = true;
				continue;
			}
			if ( map[mx][my].direction[(d+2)%4] ) {
				q.offer(new int[] {mx,my});
			}
		}
		visited[startX][startY] = true;
		
		while ( !q.isEmpty() ) {
			int[] now = q.poll();
			visited[now[0]][now[1]] = true;
			for ( int d = 0 ; d < 4 ; ++d ) {
				int mx = now[0] + dx[d];
				int my = now[1] + dy[d];
				if ( mx < 0 || mx >= R || my < 0 || my >= C ) continue;
				if ( !map[now[0]][now[1]].direction[d] ) continue;
				if ( visited[mx][my] ) continue;
				boolean isSpace = true;
				for ( int i = 0 ; i < 4 ; ++i ) {
					if ( map[mx][my].direction[i] ) {
						isSpace = false;
					}
				}
				if ( isSpace ) {
					tempQ.offer(new int[] {mx,my});
					visited[mx][my] = true;
					continue;
				}
				if ( !map[mx][my].direction[(d+2)%4] ) continue;
				q.offer(new int[] {mx,my});
			}
		}
	}
	
	public static boolean getResult() {
		boolean[][] visited = new boolean[R][C];
		Queue<int[]> q = new ArrayDeque<int[]>();
		for ( int d = 0 ; d < 4 ; ++d ) {
			int mx = startX + dx[d];
			int my = startY + dy[d];
			if ( mx < 0 || mx >= R || my < 0 || my >= C ) continue;
			if ( map[mx][my].direction[(d+2)%4] ) {
				q.offer(new int[] {mx,my});
			}
		}
		visited[startX][startY] = true;		
		
		while ( !q.isEmpty() ) {
			int[] now = q.poll();
			visited[now[0]][now[1]] = true;
			for ( int d = 0 ; d < 4 ; ++d ) {
				int mx = now[0] + dx[d];
				int my = now[1] + dy[d];
				if ( mx < 0 || mx >= R || my < 0 || my >= C ) continue;
				if ( !map[now[0]][now[1]].direction[d] ) continue;
				if ( visited[mx][my] ) continue;
				if ( mx == endX && my == endY ) {
					return true;
				}
				if ( !map[mx][my].direction[(d+2)%4] ) continue;
				q.offer(new int[] {mx,my});
			}
		}
		return false;
	}
}
