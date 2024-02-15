import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static int R,C;
    public static char map[][];
    public static int count = 0;
    public static int dr[] = {-1,0,1};
    public static boolean isEnd = false;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];

        for ( int r = 0 ; r < R ; ++r ) {
            String line = br.readLine();
            for ( int c = 0 ; c < C ; ++c ) {
                map[r][c] = line.charAt(c);
            }
        }
        
        for ( int i = 0 ; i < R ; ++i ) {
        	isEnd = false;
            dfs(i,0,0);
        }
        bw.write(count+"\n");
        bw.flush();
        bw.close();
    }

    public static void dfs(int row, int col, int depth) {
        if ( isEnd ) return;
        map[row][col] = 'x';
    	if ( depth == C - 1 ) {
            ++count;
            isEnd = true;
            return;
        }

        for ( int i = 0 ; i < 3 ; ++i ) {
            int mr = row + dr[i];
            int mc = col + 1;

            if ( mr < 0 || mr >= R || mc < 0 || mc >= C ) continue;
            if ( map[mr][mc] == '.' ) {
                dfs(mr,mc,depth+1);
            }
        }
    }
}