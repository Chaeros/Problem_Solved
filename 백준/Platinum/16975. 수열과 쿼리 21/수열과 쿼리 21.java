import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int arr[];
    static long tree[];
    static long lazy[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        tree = new long[N*4];
        lazy = new long[N*4];

        st = new StringTokenizer(br.readLine());
        for ( int n = 0 ; n < N ; ++n ) {
            arr[n] = Integer.parseInt(st.nextToken());
        }

        initTree(0, N-1, 1);
        int T = Integer.parseInt(br.readLine());
        for ( int t = 0 ; t < T ; ++t ) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            if ( command == 1 ) {
                int start = Integer.parseInt(st.nextToken())-1;
                int end = Integer.parseInt(st.nextToken())-1;
                int diff = Integer.parseInt(st.nextToken());
                updateTree(0, N-1, 1, start, end, diff);
            }
            else if ( command == 2 ) {
                int index = Integer.parseInt(st.nextToken())-1;
                sb.append(getValue(0,N-1,1,index)+"\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void initTree(int start, int end, int node) {
        if ( start == end ) {
            tree[node] = arr[start];
            return;
        }
        int mid = ( start + end ) / 2;
        initTree(start, mid, node*2);
        initTree(mid+1, end, node*2+1);
        tree[node] = tree[node*2]+tree[node*2+1];
    }

    private static void updateLazy(int start, int end, int node) {
        tree[node] += (end-start+1)*lazy[node];
        if ( start != end ) {
            lazy[node*2] += lazy[node];
            lazy[node*2+1] += lazy[node];
        }
        lazy[node] = 0;
    }

    private static void updateTree(int start, int end, int node, int nodeLeft, int nodeRight, int diff) {
        updateLazy(start,end,node);
        if ( nodeRight < start || end < nodeLeft ) {
            return;
        }
        if ( nodeLeft <= start && end <= nodeRight ) {
            tree[node] += (end-start+1)*diff;
            if ( start != end ) {
                lazy[node*2] += diff;
                lazy[node*2+1] += diff;
            }
            return;
        }

        int mid = ( start + end ) / 2;
        updateTree(start, mid, node*2, nodeLeft, nodeRight, diff);
        updateTree(mid+1, end, node*2+1, nodeLeft, nodeRight, diff);
//        tree[node] = tree[node*2] + tree[node*2+1];
    }

    private static long getValue(int start, int end, int node, int index) {
        updateLazy(start,end,node);
        if ( index < start || end < index ) {
            return 0;
        }
        if ( start == index && index == end ) {
            return tree[node];
        }
        int mid = ( start + end ) / 2;
        long left = getValue(start, mid, node*2, index);
        long right = getValue(mid+1, end, node*2+1, index);
        return left + right;
    }
}