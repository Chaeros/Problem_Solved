import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int L = Integer.parseInt(br.readLine());
		String str = br.readLine();
		
		long hash = 0;
		long r = 1;
		for ( int i = 0 ; i < str.length() ; ++i ) {
			hash = (hash + ((str.charAt(i) - 'a' + 1) * r ) % 1234567891) % 1234567891;
			r = r * 31 % 1234567891;
		}
		bw.write(hash+"\n");
		bw.flush();
		bw.close();
	}
}
