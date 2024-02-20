import java.io.*;
import java.util.*;

public class Main {
    static int N,M;
    static int inbound[];
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static List<Integer> resultList = new ArrayList<>();
    static int topological_sort() throws IOException{
        Queue<Integer> q = new LinkedList<>();
        int count=0;
        for(int i=1;i<=N;++i){
            if(inbound[i]==0){
                q.offer(i);
                ++count;
                resultList.add(i);
            }
        }

        while(!q.isEmpty()){
            int now = q.poll();

            for(int x:list.get(now)){
                --inbound[x];
                if(inbound[x]==0){
                    resultList.add(x);
                    ++count;
                    q.offer(x);
                }
            }
        }
        return count;
    }
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        inbound = new int[N+1];

        for(int i=0;i<=N;++i){
            list.add(new ArrayList<>());
        }

        for(int i=0;i<M;++i){
            st = new StringTokenizer(br.readLine());
            int director = Integer.parseInt(st.nextToken());
            int preSinger = Integer.parseInt(st.nextToken());
            while(st.hasMoreTokens()){
                int currentSinger = Integer.parseInt(st.nextToken());
                list.get(preSinger).add(currentSinger);
                ++inbound[currentSinger];
                preSinger=currentSinger;
            }
        }
        if(topological_sort()==N){
            for(int i=0;i<N;++i){
                bw.write(resultList.get(i)+"\n");
            }
        }
        else{
            bw.write(0+"\n");
        }
        bw.flush();
        bw.close();
    }
}
