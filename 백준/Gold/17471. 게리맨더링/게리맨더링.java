import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static int population[];
    static boolean ischecked[];
    static int minVal=Integer.MAX_VALUE;

    static void diff(){
        int aListSum=0;
        int bListSum=0;
        for(int i=0;i<N;++i){
            if(ischecked[i]) aListSum+=population[i];
            else bListSum+=population[i];
        }
        int diff=Math.abs(aListSum-bListSum);
        minVal=Math.min(minVal,diff);
    }

    static boolean bfs(ArrayList<Integer> list){
        Queue<Integer> q = new LinkedList<>();
        boolean visited[] = new boolean[N+1];
        visited[list.get(0)]=true;
        q.offer(list.get(0));

        int cnt=1;
        while(!q.isEmpty()){
            int now = q.poll();
            for(int x:graph.get(now)){
                if(list.contains(x) && !visited[x]){
                    visited[x]=true;
                    ++cnt;
                    q.offer(x);
                }
            }
        }

        if(cnt==list.size()) return true;
        else return false;
    }

    static void subset(int index){

        if(index==N){
            ArrayList<Integer> aList=new ArrayList<>();
            ArrayList<Integer> bList=new ArrayList<>();

            for(int i=0;i<N;++i){
                if(ischecked[i]) aList.add(i);
                else bList.add(i);
            }

            if(aList.size()==0 || bList.size()==0) return;

            if(bfs(aList) && bfs(bList)){
                diff();
            }
            return;
        }

        ischecked[index]=true;
        subset(index+1);
        ischecked[index]=false;
        subset(index+1);
    }
    public static void main(String agrs[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        population = new int[N];
        ischecked = new boolean[N];

        st=new StringTokenizer(br.readLine());
        for(int i=0;i<N;++i){
            population[i]=Integer.parseInt(st.nextToken());
        }

        graph.add(new ArrayList<>());
        for(int i=0;i<N;++i){
            graph.add(new ArrayList<>());

            st=new StringTokenizer(br.readLine());
            int count=Integer.parseInt(st.nextToken());
            for(int j=0;j<count;++j){
                int custum=Integer.parseInt(st.nextToken())-1;
                graph.get(i).add(custum);
            }
        }

        subset(0);

        if(minVal==Integer.MAX_VALUE) bw.write(-1+"\n");
        else bw.write(minVal+"\n");
        bw.flush();
        bw.close();
    }
}
