import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	
	public static long tree[];
	public static Map<Integer, Integer> map;
	public static int mapCount = 0;
	public static List<Island> islands = new ArrayList<>();
	public static class Island implements Comparable<Island>{
		int x;
		int y;

		public Island(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Island o) {
			if ( this.y == o.y ) {
				if ( this.x < o.x ) return 1;
				return -1;
			}
			else if ( this.y < o.y ) return -1;
			return 1;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for ( int t = 1 ; t <= T ; ++t ) {
			int n = Integer.parseInt(br.readLine());
			islands = new ArrayList<>();
			map = new HashMap<>();
			mapCount = 0;
			for ( int i = 0 ; i < n ; ++i ) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				islands.add(new Island(x, y));
			}
			
			Collections.sort(islands,(o1,o2)->{
				if ( o1.x < o2.x ) return -1;
				return 1;
			});
			for ( int i = 0 ; i < n ; ++i ) {
				Island island = islands.get(i);
				if ( !map.containsKey(island.x) ) {
					map.put(island.x, mapCount++);
				}
			}
			
			Collections.sort(islands);
			tree = new long[4*n];
			
			long countSum = 0;
			for ( int i = 0 ; i < n ; ++i ) {
				Island island = islands.get(i);
				countSum += getCount(1, 0, mapCount-1, map.get(island.x), mapCount-1);
				updateTree(1, 0, mapCount-1, map.get(island.x));
			}
			bw.write(countSum+"\n");
		}
		bw.flush();
		bw.close();
	}
	
	public static void updateTree(int node, int start, int end, int index) {
		if ( index < start || end < index ) {
			return;
		}
		if ( start == index && end == index ) {
			++tree[node];
			return;
		}
		
		int mid = ( start + end ) / 2;
		updateTree(node*2, start, mid, index);
		updateTree(node*2+1, mid+1, end, index);
		tree[node] = tree[node*2] + tree[node*2+1];
	}
	
	public static long getCount(int node, int start, int end, int nodeLeft, int nodeRight ) {
		if ( nodeRight < start || end < nodeLeft ) {
			return 0;
		}
		if ( nodeLeft <= start && end <= nodeRight ) {
			return tree[node];
		}
		
		int mid = ( start + end ) / 2;
		long left = getCount(node*2, start, mid, nodeLeft, nodeRight);
		long right = getCount(node*2+1, mid+1, end, nodeLeft, nodeRight);
		return tree[node] = left + right;
	}
	
}
