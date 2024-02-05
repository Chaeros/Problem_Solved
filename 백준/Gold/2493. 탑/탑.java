import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static class Building{
		int index;
		int height;
		
		public Building(int index, int height) {
			super();
			this.index = index;
			this.height = height;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int building[] = new int[N];
		List<Building> list = new ArrayList<>();
		int result[] = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for ( int i = 0 ; i < N ; ++i ) {
			building[i] = Integer.parseInt(st.nextToken());
		}
		
		list.add(new Building(N-1,building[N-1]));
		for ( int i = N - 2 ; i >= 0 ; --i ) {
			int size = list.size();
			for ( int j = size-1 ; j >= 0 ; --j ) {
				if ( list.get(j).height <= building[i] ) {
//					System.out.println(building[i]+"진입");
					result[list.get(j).index] = i+1;
					list.remove(j);
				}
				else {
//					System.out.println(building[i]+"진입2");
					break;
				}
			}
			list.add(new Building(i,building[i]));
		}
		
		for ( int x : result ) {
			bw.write(x+" ");
		}
		bw.write("\n");
		bw.flush();
		bw.close();
	}
}
