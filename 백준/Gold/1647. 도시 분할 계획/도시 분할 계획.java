import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static int parent[];
    static int find_parent(int x){
        if(x==parent[x]) return x;
        return find_parent(parent[x]);
    }

    static void union_node(int a,int b){
        int A = find_parent(a);
        int B = find_parent(b);
        if(A>B) parent[A]=B;
        else parent[B]=A;
    }

    static class Node implements Comparable<Node>{
        int cost;
        int houseA;
        int houseB;

        public Node(int cost,int houseA,int houseB){
            this.cost=cost;
            this.houseA=houseA;
            this.houseB=houseB;
        }

        @Override
        public int compareTo(Node o) {
            if(this.cost<o.cost) return -1;
            else if(this.cost>o.cost) return 1;
            else return 0;
        }
    }
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N];

        for(int i=0;i<N;++i){
            parent[i]=i;
        }

        ArrayList<Node> list = new ArrayList<>();

        for(int i=0;i<M;++i){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            Node node = new Node(C,A-1,B-1);
            list.add(node);
        }
        Collections.sort(list);

        int result=0;
        int count=0;
        for(Node node:list){
            if(N==2) break;
            if(find_parent(node.houseA)!=find_parent(node.houseB)){
                union_node(node.houseA,node.houseB);
                result+=node.cost;

                ++count;
                if(count==N-2) break;

            }
        }
        bw.write(result+"\n");
        bw.flush();
        bw.close();
    }
}