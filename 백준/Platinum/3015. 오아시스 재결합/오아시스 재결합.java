import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {
	
	public static class Node{
		int height;
		int count;

		public Node(int height, int count) {
			this.height = height;
			this.count = count;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		Stack<Node> stack = new Stack<>();
		long resultCount = 0;
		
		for ( int i = 0 ; i < N ; ++i ) {
			int newVal = Integer.parseInt(br.readLine());
			int size = stack.size() - 1;
			
			while ( !stack.isEmpty() ) {
				if ( size < 0 ) break;
				if ( stack.get(size).height == newVal ) {
					resultCount += stack.get(size).count;
					--size;
				}
				else if ( stack.get(size).height < newVal ) {
					resultCount += stack.get(size).count;
					stack.pop();
					--size;
				}
				else if ( stack.get(size).height > newVal ) {
					++resultCount;
					break;
				}
			}
			
			if ( !stack.isEmpty() && stack.peek().height == newVal ) {
				++stack.get(stack.size()-1).count;
			}
			else {
				stack.push(new Node(newVal,1));
			}
			
		}
		bw.write(resultCount+"\n");
		bw.flush();
		bw.close();
	}
}
