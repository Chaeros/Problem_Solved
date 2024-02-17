import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	public static List<Star> stars = new ArrayList<>();
	public static List<Line> lines = new ArrayList<>();
	public static int parent[];
	
	public static class Star {
		int number;
		double x;
		double y;
		
		public Star(int number, double x, double y) {
			this.number = number;
			this.x = x;
			this.y = y;
		}
	}
	
	public static class Line implements Comparable<Line>{
		int start;
		int end;
		double distance;

		public Line(int start, int end, double distance) {
			this.start = start;
			this.end = end;
			this.distance = distance;
		}
		
		@Override
		public int compareTo(Line o) {
			if ( this.distance < o.distance ) return -1;
			return 1;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		parent = new int[N+1];
		for ( int i = 1 ; i <= N ; ++i ) {
			st = new StringTokenizer(br.readLine());
			
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			stars.add(new Star(i,x,y));
			parent[i] = i;
		}
		
		dfs(0,0);
		Collections.sort(lines);
		
		double resultSum = 0;
		int lineCount = 0;
		int index = 0;
		while ( lineCount < N-1 ) {
			if ( findParent(lines.get(index).start) != findParent(lines.get(index).end )  ) {
				union(lines.get(index).start,lines.get(index).end);
				resultSum += lines.get(index).distance;
				++lineCount;
			}
			++index;
		}
		bw.write(resultSum+"\n");
		bw.flush();
		bw.close();
	}
	
	public static Star tempStar[] = new Star[2];
	public static void dfs(int index, int depth) {
		if ( depth == 2 ) {
			double distance = Math.sqrt(Math.pow(tempStar[0].x - tempStar[1].x,2) + Math.pow(tempStar[0].y - tempStar[1].y,2));
			lines.add(new Line(tempStar[0].number,tempStar[1].number,distance));
			return;
		}
		
		for ( int i = index ; i < stars.size() ; ++i ) {
			tempStar[depth] = stars.get(i);
			dfs(i+1,depth+1);
		}
	}
	
	public static int findParent(int x) {
		if ( parent[x] == x ) return x;
		return parent[x] = findParent(parent[x]);
	}
	
	public static void union(int a, int b) {
		int aAncestor = findParent(a);
		int bAncestor = findParent(b);
		
		if ( aAncestor < bAncestor ) {
			parent[bAncestor] = aAncestor; 
		}
		else {
			parent[aAncestor] = bAncestor;
		}
	}
}
