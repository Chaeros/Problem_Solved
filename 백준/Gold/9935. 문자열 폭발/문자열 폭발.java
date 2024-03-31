import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static class Node{
		Node pre;
		Node next;
		char character;
		
		public Node() {
		}

		public Node(char character) {
			this.character = character;
		}

		@Override
		public String toString() {
			return "Node [pre=" + pre + ", next=" + next + ", character=" + character + "]";
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String plaintext = br.readLine();
		String pattern = br.readLine();
		
		Node nodes[] = new Node[plaintext.length()];
		nodes[0] = new Node(plaintext.charAt(0));
		for ( int i = 1 ; i < plaintext.length() ; ++i ) {
			nodes[i] = new Node(plaintext.charAt(i));
			nodes[i].pre = nodes[i-1];
			nodes[i-1].next = nodes[i];
		}
		
		Node leftNode = new Node();
		leftNode.next = nodes[0];
		nodes[0].pre = leftNode;
		
		Node rightNode = new Node();
		rightNode.pre = nodes[plaintext.length()-1];
		nodes[plaintext.length()-1].next = rightNode;
		
		for ( Node i = rightNode.pre ; i != leftNode ; i = i.pre ) {
			int index = 0;
			for ( Node j = i ; j != rightNode ; j = j.next ) {
				if ( j.character != pattern.charAt(index++) ) {
					break;
				}
				if ( index == pattern.length() ) {
					i.pre.next = j.next;
					j.next.pre = i.pre;
					break;
				}
			}
		}
		
		if ( leftNode.next == rightNode ) {
			bw.write("FRULA\n");
		}
		else {
			Node node = leftNode.next;
			while ( node!= rightNode ) {
				bw.write(node.character);
				node = node.next;
			}
			bw.write("\n");
		}
		bw.flush();
		bw.close();
	}
}
