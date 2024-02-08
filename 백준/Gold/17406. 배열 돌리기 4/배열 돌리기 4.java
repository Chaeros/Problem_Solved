import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static int N,M,K;
	public static int map[][];
	
	public static class Node{
		int luX;
		int luY;
		int rdX;
		int rdY;
		
		public Node(int luX, int luY, int rdX, int rdY) {
			super();
			this.luX = luX;
			this.luY = luY;
			this.rdX = rdX;
			this.rdY = rdY;
		}

		@Override
		public String toString() {
			return "Node [luX=" + luX + ", luY=" + luY + ", rdX=" + rdX + ", rdY=" + rdY + "]";
		}
		
		
	}
	
	public static Node[] nodes;
	public static Node[] selected;
	public static boolean[] isSelected;
	public static int minSum = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		nodes = new Node[K];
		selected = new Node[K];
		isSelected = new boolean[K];
		
		for ( int r = 0 ; r < N ; ++r ) {
			st = new StringTokenizer(br.readLine());
			for ( int c = 0 ; c < M ; ++c ) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		for ( int t = 0 ; t < K ; ++t ) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int d = Integer.parseInt(st.nextToken());
			
			nodes[t] = new Node(r-d,c-d,r+d,c+d);
		}
		dfs(0);
		System.out.println(minSum);
	}
	
	public static void rotate(int startRow, int startCol, int endRow, int endCol) {
		int dr[] = {0,1,0,-1};
		int dc[] = {1,0,-1,0};
		int number = Math.min((endRow-startRow+1)/2, (endCol-startCol+1)/2 );
		for ( int t = 0 ; t < number; ++t ) {
			int r = startRow + t;
			int c = startCol + t;
			int temp = map[r][c];
			for ( int d = 0 ; d < 4 ; ++d ) {
				while ( true ) {
					int mr = r + dr[d];
					int mc = c + dc[d];
					if ( mr < startRow+t || mr > endRow - t || mc < startCol+t || mc > endCol-t ) break;
					int swap = map[mr][mc];
					map[mr][mc] = temp;
					temp = swap;
					r = mr;
					c = mc;
				}
			}
		}
	}
	
	public static void dfs(int depth) {
		if ( depth == K ) {
			int sum = calculate();
			minSum = Math.min(minSum, sum);
			return;
		}
		
		for ( int i = 0 ; i < K ; ++i ) {
			if ( !isSelected[i] ) {
				isSelected[i] = true;
				selected[depth] = nodes[i];
				dfs(depth+1);
				isSelected[i] = false;
			}
		}
	}
	
	private static int calculate() {
		int tempMap[][] = new int[N][M];
		for ( int i = 0 ; i < N ; ++i ) {
			tempMap[i] = map[i].clone();
		}
		
		for (Node node : selected) {
			rotate(node.luX,node.luY,node.rdX,node.rdY);
		}
		
		int minSum = Integer.MAX_VALUE;
		for ( int r = 0 ; r < N ; ++r ) {
			minSum = Math.min(minSum, Arrays.stream(map[r]).sum());
		}
		map = tempMap;
		return minSum;
	}

	public static void printMap() {
		System.out.println("===============");
		for ( int r = 0 ; r < N ; ++r ) {
			for ( int c = 0 ; c< M ; ++c ) {
				System.out.print(map[r][c]+" ");
			}
			System.out.println();
		}
	}
}
