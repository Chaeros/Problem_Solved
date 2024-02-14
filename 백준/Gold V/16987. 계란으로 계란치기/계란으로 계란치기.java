import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static int N;

    public static class Egg{
        int durability;
        int weight;

    public Egg(int durability, int weight) {
        this.durability = durability;
        this.weight = weight;
    }
}

public static Egg eggs[];
public static int maxCount = 0;

public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;
    
    N = Integer.parseInt(br.readLine());
    eggs = new Egg[N];
    for ( int i = 0 ; i < N ; ++i ) {
        st = new StringTokenizer(br.readLine());
        int durability = Integer.parseInt(st.nextToken());
        int weight = Integer.parseInt(st.nextToken());
        eggs[i] = new Egg(durability,weight);
    }
    dfs(0);
    bw.write(maxCount+"\n");
    bw.flush();
    bw.close();
}

public static void dfs(int depth) {
    if ( depth == N ) {
        maxCount = Math.max(maxCount, getCountCrackedEggs());
        return;
    }
    
    Egg egg = eggs[depth];
    if ( egg.durability <= 0 ) {
        dfs(depth+1);
        return;
    }
    for ( int i = 0 ; i < N ; ++i ) {
        if ( depth == i ) continue;
        if ( eggs[i].durability > 0 ) {
            eggs[i].durability -= egg.weight;
            egg.durability -= eggs[i].weight;
            dfs(depth+1);
            eggs[i].durability += egg.weight;
            egg.durability += eggs[i].weight;
        }
    }
    maxCount = Math.max(maxCount, getCountCrackedEggs());
}

public static int getCountCrackedEggs() {
    int count = 0;
    for ( Egg egg : eggs ) {
        if ( egg.durability <= 0 ) ++count;
    }
    return count;
}
}
