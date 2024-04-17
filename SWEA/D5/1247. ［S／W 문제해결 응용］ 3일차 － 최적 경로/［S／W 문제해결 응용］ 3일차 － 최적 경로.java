import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	
	public static int N;
	
	public static class Position{
		int x;
		int y;
		
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static Position office, house;
	public static Position[] positions;
	public static Position[] result;
	public static boolean isVisited[];
	
	public static int minDistanceSum = Integer.MAX_VALUE;
	
	public static void dfs(int depth) {
		if ( depth == N ) {
			calculateSum();
			return;
		}
		
		for ( int i = 0 ; i < N ; ++i ) {
			if ( isVisited[i] ) continue;
			isVisited[i] = true;
			result[depth] = positions[i+2];
			dfs(depth+1);
			isVisited[i] = false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for ( int t = 1 ; t <= T ; ++t ) {
			N = Integer.parseInt(br.readLine());
			positions = new Position[N+2];
			result = new Position[N];
			isVisited = new boolean[N+2];
			minDistanceSum = Integer.MAX_VALUE;
			
			st = new StringTokenizer(br.readLine());
			for ( int i = 0 ; i < N +2 ; ++i ) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				positions[i] = new Position(x,y);
			}
			office = positions[0];
			house = positions[1];
			
			dfs(0);
			
			bw.write("#"+t+" "+minDistanceSum+"\n");
		}
		bw.flush();
		bw.close();
	}
	
	public static void calculateSum() {
		int sum = 0;
		sum += Math.abs(office.x - result[0].x) + Math.abs(office.y - result[0].y);
		
		for ( int i = 0 ; i < N-1 ; ++i ) {
			int xDistance = Math.abs(result[i].x - result[i+1].x);
			int yDistance = Math.abs(result[i].y - result[i+1].y);
			sum += xDistance + yDistance;
		}
		sum += Math.abs(result[N-1].x - house.x ) + Math.abs(result[N-1].y - house.y );
		minDistanceSum = Math.min(minDistanceSum, sum);
	}
	
	
}
