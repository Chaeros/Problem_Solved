import java.io.*;
import java.util.Stack;

public class Main{
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int list[] = new int[N];
        for(int i=0;i<N;++i){
            list[i]=Integer.parseInt(br.readLine());
        }

        Stack<Integer> stack = new Stack<>();
        long ansSum=0;
        for(int i=0;i<N;++i){
            while(!stack.isEmpty() && stack.peek()<=list[i]){
                stack.pop();
            }
            ansSum+=stack.size();
            stack.push(list[i]);
        }
        bw.write(ansSum+"\n");
        bw.flush();
        bw.close();
    }
}