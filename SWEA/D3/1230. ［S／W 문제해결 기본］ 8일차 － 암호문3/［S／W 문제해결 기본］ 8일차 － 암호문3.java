import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		for ( int t = 1 ; t <= 10 ; ++t ) {
			int n = Integer.parseInt(br.readLine());
			List<Integer> list = new LinkedList<Integer>();
			
			st = new StringTokenizer(br.readLine());
			while ( st.hasMoreTokens() ) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			
			int commandCount = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			while ( st.hasMoreTokens() ) {
				String command = st.nextToken();
//				System.out.println(command);
				if ( command.equals("I") ) {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					List<Integer> tempList = new LinkedList<Integer>();
					for ( int i = 0 ; i < y ; ++i ) {
						tempList.add(Integer.parseInt(st.nextToken()));
					}
					list.addAll(x,tempList);
				}
				else if ( command.equals("D") ) {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					List<Integer> tempList = new LinkedList<Integer>();
					for ( int i = x-1 ; i < x+y-1 ; ++i ) {
						tempList.add(list.get(i));
					}
					list.removeAll(tempList);
				}
				else if ( command.equals("A") ) {
					int y = Integer.parseInt(st.nextToken());
					for ( int i = 0 ; i < y ; ++i ) {
						list.add(Integer.parseInt(st.nextToken()));
					}
				}
			}
			
			sb.append("#"+t+" ");
			for ( int i = 0 ; i < 10 ; ++i ) {
				sb.append(list.get(i)+" ");
			}
			sb.append("\n");
		}
		bw.write(String.valueOf(sb));
		bw.flush();
		bw.close();
	}
}
