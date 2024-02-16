import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	public static int N;
	public static Planet planets[];
	public static List<Tunnel> tunnels;
	public static int parent[];
	
	public static class Planet {
		int number;
		int x;
		int y;
		int z;

		public Planet() {
		}

		public Planet(int number, int x, int y, int z) {
			this.number = number;
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
	
	public static class Tunnel implements Comparable<Tunnel>{
		int start;
		int end;
		int distance;
		
		public Tunnel() {
		}

		public Tunnel(int start, int end, int distance) {
			this.start = start;
			this.end = end;
			this.distance = distance;
		}

		@Override
		public int compareTo(Tunnel o) {
			if ( this.distance < o.distance ) return -1;
			return 1;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		planets = new Planet[N];
		tunnels = new ArrayList<>();
		parent = new int[N];
		
		for ( int i = 0 ; i < N ; ++i ) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			planets[i] = new Planet(i,x,y,z);
			parent[i] = i;
		}
		
		Arrays.sort(planets, 
			(o1, o2) -> {
				if ( o1.x < o2.x ) return -1;
				return 1;
			}
		);
		for ( int i = 1 ; i < N ; ++i ) {
			int x = Math.abs(planets[i].x - planets[i-1].x);
			tunnels.add(new Tunnel(planets[i-1].number,planets[i].number,x));
		}
		
		Arrays.sort(planets, 
			(o1, o2) -> {
				if ( o1.y < o2.y ) return -1;
				return 1;
			}
		);
		for ( int i = 1 ; i < N ; ++i ) {
			int y = Math.abs(planets[i].y - planets[i-1].y);
			tunnels.add(new Tunnel(planets[i-1].number,planets[i].number,y));		
		}
			
		Arrays.sort(planets, 
			(o1, o2) -> {
				if ( o1.z < o2.z ) return -1;
				return 1;
			}
		);
		for ( int i = 1 ; i < N ; ++i ) {
			int z = Math.abs(planets[i].z - planets[i-1].z);
			tunnels.add(new Tunnel(planets[i-1].number,planets[i].number,z));
		}
		Collections.sort(tunnels);
		
		int ans = 0;
		for (int i = 0; i < tunnels.size(); i++) {
			Tunnel tunnel = tunnels.get(i);

			if (findParent(tunnel.start) != findParent(tunnel.end)) {
				ans += tunnel.distance;
				union(tunnel.start, tunnel.end);
			}
		}
		
		bw.write(ans+"\n");
		bw.flush();
		bw.close();
	}
	
	public static void union(int a, int b) {
		int parentA = findParent(a);
		int parentB = findParent(b);
		if ( parentA < parentB ) {
			parent[parentB] = a;
		}
		else {
			parent[parentA] = b;
		}
	}
	
	public static int findParent(int x) {
		if ( parent[x] == x ) return x;
		return parent[x] = findParent(parent[x]);
	}
}
