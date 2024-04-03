import java.beans.Visibility;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	
	public static int N,M;
	public static int table[][] = new int[504][504];
	public static boolean visited[] = new boolean[504];
	public static int smallCount;
	public static int bigCount;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for ( int t = 1 ; t <= T; ++t ) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			
			for ( int i = 0 ; i <= N ; ++i ) {
				Arrays.fill(table[i], 0);
			}
			
			for ( int m = 0 ; m < M ; ++m ) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				table[from][to] = 1;
			}
			
			int resultCount = 0;
			for ( int n = 1 ; n <= N ; ++n ) {
				Arrays.fill(visited,false);
				smallCount = 0;
				bigCount = 0;
				getSmallCount(n);
				getBigCount(n);
				if ( smallCount + bigCount == N-1 ) ++resultCount;
			}
			bw.write("#"+t+" "+resultCount+"\n");
		}
		bw.flush();
		bw.close();
	}
	
	public static void getSmallCount(int n) {
		visited[n] = true;
		for ( int i = 1 ; i <= N ; ++i ) {
			if ( !visited[i] && table[i][n] == 1 ) {
				visited[i] = true;
				++smallCount;
				getSmallCount(i);
			}
		}
	}
	
	public static void getBigCount(int n) {
		visited[n] = true;
		for ( int i = 1 ; i <= N ; ++i ) {
			if ( !visited[i] && table[n][i] == 1 ) {
				visited[i] = true;
				++bigCount;
				getBigCount(i);
			}
		}
	}
}