import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	public static int N;
	public static int tree[];
	public static int MAX = 1000000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		tree = new int[4*MAX];
		
		for(int n=0;n<N;++n) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			if(A==1) {
				sb.append(findTree(1, 1, MAX, B)).append("\n");
			}
			else if(A==2) {
				int C = Integer.parseInt(st.nextToken());
				updateTree(1, 1, MAX, B, C);
			}
		}
		System.out.println(sb.toString());
	}
	
	public static void updateTree(int node, int left, int right, int index, int value) {
		if(left == index && right == index) {
			tree[node]+=value;
			return;
		}
		if(index<left || right<index) {
			return;
		}
		int mid=(left+right)/2;
		updateTree(node*2, left, mid, index, value);
		updateTree(node*2+1, mid+1, right, index, value);
		tree[node]=tree[node*2]+tree[node*2+1];
	}
	
	public static int findTree(int node, int left, int right, int count) {
		if(left==right) {
			--tree[node];
			return left;
		}
		
		int mid=(left+right)/2;
		--tree[node];
		if(tree[node*2]>=count) {
			return findTree(node*2, left, mid, count);
		}
		return findTree(node*2+1, mid+1, right, count-tree[node*2]);
	}

}
