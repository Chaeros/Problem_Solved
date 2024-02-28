import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	static List<Point> points = new ArrayList<>();
	static class Point {
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static List<Line> lines = new ArrayList<>();
	static class Line {
		int start;
		int end;

		public Line(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	
	static List<Result> results = new ArrayList<>();
	static class Result {
		int x;
		boolean isStart;

		public Result(int x, boolean isStart) {
			this.x = x;
			this.isStart = isStart;
		}

		@Override
		public String toString() {
			return "Result [x=" + x + ", isStart=" + isStart + "]";
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int preX = Integer.MAX_VALUE;
		int preY = 0;
		int startIndex = 0;
		for ( int n = 0 ; n < N ; ++n ) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			points.add(new Point(x,y));
			
			if ( y < 0 && x < preX ) {
				preX = x;
				preY = y;
				startIndex = n;
			}
		}
		
		Line line = null;
		for ( int i = 0 ; i < points.size() ; ++i ) {
			Point current = points.get((i+startIndex)%N);
			
			int cX = current.x;
			int cY = current.y;
			
			if ( preY < 0 && cY > 0 ) {
				line = new Line(preX,-1);
			}
			
			else if ( preY > 0 && cY < 0 ) {
				line.end = cX;
				results.add(new Result(Math.min(line.start, line.end),true));
				results.add(new Result(Math.max(line.start, line.end),false));
			}
			
			preX = cX;
			preY = cY;
		}

		Collections.sort(results,(o1,o2) ->{
			if ( o1.x < o2.x ) return -1;
			return 1;
		});
		
		int unContainedCount = 0;
		int unContainCount = 0;
		Stack<Integer> stack = new Stack<>();
		int count = 0;
		
		for ( Result result : results ) {
			if ( result.isStart == true ) {
				stack.push(count);
			}
			else {
				int top = stack.pop();
				
				if ( stack.isEmpty() ) ++unContainedCount;
				
				if ( top == count ) ++unContainCount;
				
				++count;
			}
		}
		
		bw.write(unContainedCount+" "+unContainCount+"\n");
		bw.flush();
		bw.close();
	}
	
	
}