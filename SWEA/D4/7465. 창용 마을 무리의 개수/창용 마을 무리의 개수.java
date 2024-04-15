import java.io.*;
import java.util.*;

class Solution
{
    public static int N,M;
    public static boolean visited[];
    public static ArrayList<ArrayList<Integer>> list;
    
    public static void dfs(int x){
        for(int val:list.get(x)){
            if(!visited[val]){
                visited[val]=true;
                dfs(val);
            }
        }
    }
    
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
               
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
            list = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
            N =Integer.parseInt(st.nextToken());
            M =Integer.parseInt(st.nextToken());
            visited = new boolean[N+1];
            
            for(int i=0;i<=N;++i){
             	list.add(new ArrayList<>());   
            }
            
            for(int i=0;i<M;++i){
                list.add(new ArrayList<>());
             	st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                list.get(a).add(b);
                list.get(b).add(a);
            }
            
            int result=0;
            for(int i=1;i<=N;++i){
                if(!visited[i]){
                    dfs(i);
                    result++;
                }
            }
            
            bw.write("#"+test_case+" "+result+"\n");            
		}
        bw.flush();
        bw.close();
	}
}