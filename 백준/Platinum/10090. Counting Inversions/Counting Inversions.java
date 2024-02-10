import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static int N;
	public static int arr[];
	public static int tree[];
	public static long answer = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		tree = new int[4*N+1];
		
		st = new StringTokenizer(br.readLine());
		for ( int i = 0 ; i < N ; ++i ) {
			arr[i] = Integer.parseInt(st.nextToken())-1;
		}
		
		PriorityQueue<int[]> q = new PriorityQueue<>(
				(o1,o2) -> {
					if ( o1[0] < o2[0] ) return -1;
					return 1;
					}
				);
		
		for ( int i = 0 ; i < N ; ++i ) {
			q.offer(new int[] {arr[i],i});
		}
        
		for ( int i = 0 ; i < N ; ++i ) {
			int[] temp = q.poll();
			answer += getSumTree(1, 0, N-1, temp[1]+1, N-1);
			updateTree(1, 0, N-1, temp[1]);
		}
		
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();		
	}
	
	public static int getSumTree(int node, int start, int end, int nodeLeft, int nodeRight) {
		if ( nodeLeft <= start && end <= nodeRight ) {
			return tree[node];
		}
		if ( nodeRight < start || end < nodeLeft ) {
			return 0;
		}
		
		int mid = ( start + end ) / 2;
		int left = getSumTree(node*2, start, mid, nodeLeft, nodeRight);
		int right = getSumTree(node*2+1, mid+1, end, nodeLeft, nodeRight);
		return tree[node] = left + right;
	}
	
	public static void updateTree(int node, int start, int end, int index) {
		if ( start == index && end == index) {
			tree[node] = 1;
			return;
		}
		
		if ( index < start || end < index ) {
			return;
		}
		
		int mid = ( start + end ) /2;
		updateTree(node*2,start,mid,index);
		updateTree(node*2+1,mid+1,end,index);
		tree[node] = tree[node*2] + tree[node*2+1];
	}
}
