import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class Solution {
    static int N,L;
    static int map[][];

    static int result=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for ( int t = 1 ; t <= T; ++t ) {
        	st = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(st.nextToken());
        	L = Integer.parseInt(st.nextToken());
        	map = new int[N][N];
        	result = 0;
        	
        	for(int i=0;i<N;++i){
        		st = new StringTokenizer(br.readLine());
        		for(int j=0;j<N;++j){
        			map[i][j]=Integer.parseInt(st.nextToken());
        		}
        	}
        	
        	// 행 검사
        	for(int i=0;i<N;++i){
        		checkCanPass(map[i]);
        	}
        	
        	// 열 검사
        	for(int i=0;i<N;++i){
        		checkCanPass(transferColumnArray(i));
        	}
        	
        	bw.write("#"+t+" "+result+"\n");
        }
        bw.flush();
        bw.close();
    }

    static int[] transferColumnArray(int columnIndex){
        int resultArray[] = new int[N];
        for(int i=0;i<N;++i){
            resultArray[i]=map[i][columnIndex];
        }
        return resultArray;
    }

    static void checkCanPass(int arr[]){
        boolean isUsed[] = new boolean[N];

        for(int i=0;i<N-1;++i){
            if(arr[i]==arr[i+1]-1){ // 이전 블록이 이후 블록보다 1 작은 경우
                if(i-L+1>=0){
                    for(int j=i-L+1;j<i+1;++j){
                        if(isUsed[j] || arr[j]!=arr[i]) return;
                        isUsed[j]=true;
                    }
                }
                else return;
            }
            else if(arr[i]==arr[i+1]+1){ // 이전 블록이 이후 블록보다 1 큰 경우
                if(i+L<N){
                    for(int j=i+1;j<=i+L;++j){
                        if(isUsed[j] || arr[j]!=arr[i+1]) return;
                        isUsed[j]=true;
                    }
                }
                else return;
            }
            else if(arr[i]!=arr[i+1]) return;
        }
        ++result;
    }
}
