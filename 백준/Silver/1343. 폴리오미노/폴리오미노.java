import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
	      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	      char[] board = br.readLine().toCharArray();
	 
	      int next;
	      for(int i=0;i<board.length;i++){
	         if(board[i]=='.') continue;
	 
	         next = 1;
	         while(i+next<board.length && next<4 && board[i+next]=='X'){
	            next++;
	         }
	
	         if(next==2) for(int j=i;j<i+next;j++) board[j] = 'B';
	         else if(next==4) for(int j=i;j<i+next;j++) board[j] = 'A';
	         else {
	            System.out.println(-1);
	            return;
	         }
	         i += next - 1;
	      }
	      StringBuilder sb = new StringBuilder();
	      for(char character:board) {
              sb.append(character);
          }
	      System.out.println(sb);
	   }
}