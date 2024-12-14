import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static int parent[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int G=Integer.parseInt(br.readLine());
		
		parent=new int[G+1];
		
		for(int g=1;g<=G;++g) {
			parent[g]=g;
		}
		
		int P=Integer.parseInt(br.readLine());
		int result=0;
		for(int p=0;p<P;++p) {
			int plane=Integer.parseInt(br.readLine());
			if(find(plane)!=0) {
				++result;
				union(plane,find(plane)-1);
			} else {
				break;
			}
		}
		System.out.println(result);
	}
	
	public static void union(int a, int b) {
		int aParent=find(a);
		int bParent=find(b);
		parent[aParent]=bParent;
	}
	
	public static int find(int x) {
		if(parent[x]==x) return x;
		return parent[x]=find(parent[x]);
	}
}
