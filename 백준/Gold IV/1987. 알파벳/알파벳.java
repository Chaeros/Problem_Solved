import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	public static int R,C;
	public static char map[][];
	public static boolean isVisited[];
	public static int dr[] = {-1,0,1,0};
	public static int dc[] = {0,1,0,-1};
	public static int maxCount = 1;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		isVisited = new boolean['Z'+1];
		
		for ( int r = 0 ; r < R ; ++r ) {
			String line = br.readLine();
			for ( int c = 0 ; c < C ; ++c ) {
				map[r][c] = line.charAt(c);
			}
		}
		isVisited[map[0][0]] = true;
		dfs(0,0,1);
		bw.write(maxCount+"\n");
		bw.flush();
		bw.close();
	}
	
	public static void dfs(int r, int c, int depth) {
		maxCount = Math.max(maxCount, depth);
		
		for ( int i = 0 ; i < 4 ; ++i ) {
			int mr = r + dr[i];
			int mc = c + dc[i];
			
			if ( mr < 0 || mr >= R || mc < 0 || mc >= C ) continue;
			if ( isVisited[map[mr][mc]] ) continue;
			isVisited[map[mr][mc]] = true;
			dfs(mr,mc,depth+1);
			isVisited[map[mr][mc]] = false;
		}
		
	}
}
