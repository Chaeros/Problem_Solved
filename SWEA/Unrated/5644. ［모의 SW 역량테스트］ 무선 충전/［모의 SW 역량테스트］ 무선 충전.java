import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	
	public static class BC implements Comparable<BC> {
		int number;
		int performance;

		public BC(int number, int performance) {
			this.number = number;
			this.performance = performance;
		}

		@Override
		public int compareTo(BC o) {
			if ( this.performance < o.performance ) return 1;
			return -1;
		}
	}
	
	public static List<List<List<BC>>> bcList;
	public static int direction[][];
	public static int count = 0;
	public static int M,A;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for ( int t = 1 ; t <= T ; ++t ) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			
			bcList = new ArrayList<>();
			for ( int i = 0 ; i < 10 ; ++i ) {
				bcList.add(new ArrayList<>());
				for ( int j = 0 ; j < 10 ; ++j ) {
					bcList.get(i).add(new ArrayList<>());
				}
			}
			
			direction = new int[2][M];
			for ( int i = 0 ; i < 2 ; ++i ) {
				st = new StringTokenizer(br.readLine());
				for ( int j = 0 ; j < M ; ++j ) {
					direction[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for ( int i = 0 ; i < A ; ++i ) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken()) - 1;
				int x = Integer.parseInt(st.nextToken()) - 1;
				int range = Integer.parseInt(st.nextToken());
				int performance = Integer.parseInt(st.nextToken());
				initMap(x, y, i+1, range, performance);
			}
			sortingMap();
			count = 0;
			getCount();
			sb.append("#"+t+" "+count+"\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	public static void initMap(int x, int y, int number, int range, int performance) {
		int diff = 0;
		for ( int r = x - range ; r <= x ; ++r ) {
			for ( int c = y - diff ; c <= y + diff ; ++c ) {
				if ( r >= 0 && r < 10 && c >= 0 && c < 10) {
					bcList.get(r).get(c).add(new BC(number,performance));
				}
			}
			++diff;
		}
		
		diff-=2;
		for ( int r = x + 1 ; r <= x + range ; ++r ) {
			for ( int c = y - diff ; c <= y + diff ; ++c ) {
				if ( r >= 0 && r < 10 && c >= 0 && c < 10) {
					bcList.get(r).get(c).add(new BC(number,performance));
				}
			}
			--diff;
		}
		
	}
	
	public static void sortingMap() {
		for ( int r = 0 ; r < 10 ; ++r ) {
			for ( int c = 0 ; c < 10 ; ++c ) {
				Collections.sort(bcList.get(r).get(c));
			}
		}
	}
	
	public static void getCount() {
		int dx[] = {0,-1,0,1,0};
		int dy[] = {0,0,1,0,-1};
		
		int userA[] = {0,0};
		int userB[] = {9,9};
		
		int mxA = userA[0];
		int myA = userA[1];
		int mxB = userB[0];
		int myB = userB[1];
		
		if ( bcList.get(mxA).get(myA).size() > 0 ) {
			count += bcList.get(mxA).get(myA).get(0).performance;
		}
		if ( bcList.get(mxB).get(myB).size() > 0 ) {
			count += bcList.get(mxB).get(myB).get(0).performance;
		}
		
		for ( int m = 0 ; m < M ; ++m ) {
			mxA += dx[direction[0][m]];
			myA += dy[direction[0][m]];
			mxB += dx[direction[1][m]];
			myB += dy[direction[1][m]];
			
			
			if ( bcList.get(mxA).get(myA).size() > 0 && bcList.get(mxB).get(myB).size() > 0 ) {
				if ( bcList.get(mxA).get(myA).get(0).number == bcList.get(mxB).get(myB).get(0).number ) {
					
					if ( bcList.get(mxA).get(myA).size() == 1 && bcList.get(mxB).get(myB).size() == 1 ) {
						count += bcList.get(mxA).get(myA).get(0).performance;
					}
					else {
						if ( bcList.get(mxA).get(myA).size() > 1 && bcList.get(mxB).get(myB).size() > 1 ) {
							if ( bcList.get(mxA).get(myA).get(1).performance > bcList.get(mxB).get(myB).get(1).performance ) {
								count += bcList.get(mxA).get(myA).get(1).performance;
								count += bcList.get(mxB).get(myB).get(0).performance;
							}
							else {
								count += bcList.get(mxA).get(myA).get(0).performance;
								count += bcList.get(mxB).get(myB).get(1).performance;
							}
						}
						else if ( bcList.get(mxA).get(myA).size() > 1 ) {
							count += bcList.get(mxA).get(myA).get(1).performance;
							count += bcList.get(mxB).get(myB).get(0).performance;
						}
						else if ( bcList.get(mxB).get(myB).size() > 1 ) {
							count += bcList.get(mxA).get(myA).get(0).performance;
							count += bcList.get(mxB).get(myB).get(1).performance;
						}
					}
				}
				else {
					count += bcList.get(mxA).get(myA).get(0).performance;
					count += bcList.get(mxB).get(myB).get(0).performance;
				}
			}
			else {
				if ( bcList.get(mxA).get(myA).size() > 0 ) {
					count += bcList.get(mxA).get(myA).get(0).performance;
				}
				if ( bcList.get(mxB).get(myB).size() > 0 ) {
					count += bcList.get(mxB).get(myB).get(0).performance;
				}
			}
		}
	}
}
