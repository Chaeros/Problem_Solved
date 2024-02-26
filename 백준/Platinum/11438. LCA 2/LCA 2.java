import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static boolean visited[];
    static int depths[];
    static int parents[][];
    static List<List<Integer>> list = new ArrayList<>();
    static int N,K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        for ( int i = 0 ; i <= N ; ++i ) {
            list.add(new ArrayList<>());
        }

        K = 0;
        int temp = 1;
        while ( temp < N+1 ) {
            temp <<= 1;
            ++K;
        }

        depths = new int[N+1];
        parents = new int[N+1][K+1];
        visited = new boolean[N+1];

        for ( int i = 0 ; i < N-1 ; ++i ) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.get(a).add(b);
            list.get(b).add(a);
        }
        setParent(1, 0);
        settingAllParent();

        int M = Integer.parseInt(br.readLine());
        for ( int m = 0 ; m < M ; ++m ) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            bw.write(lca(a,b)+"\n");
        }
        bw.flush();
        bw.close();
    }

    private static void setParent(int x, int depth) {
        depths[x] = depth;
        visited[x] = true;
        for ( int val : list.get(x) ) {
            if ( !visited[val] ) {
                parents[val][0] = x;
                setParent(val, depth+1);
            }
        }
    }

    private static void settingAllParent() {
        for ( int i = 1 ; i < K ; ++i ) {
            for ( int j = 1 ; j <= N ; ++j ) {
                parents[j][i] = parents[parents[j][i-1]][i-1];
            }
        }
    }

    private static int lca(int a, int b) {
        if ( depths[a] < depths[b] ) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        for ( int k = K-1 ; k >= 0 ; --k ) {
            if ( Math.pow(2, k) <= depths[a] - depths[b] ) {
                a = parents[a][k];
            }
        }

        if ( a == b ) return a;

        for ( int k = K-1 ; k >= 0 ; --k ) {
            if ( parents[a][k] != parents[b][k] ) {
                a = parents[a][k];
                b = parents[b][k];
            }
        }
        return parents[a][0];
    }

}