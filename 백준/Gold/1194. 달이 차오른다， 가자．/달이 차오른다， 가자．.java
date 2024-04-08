import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    static int N, M;
    static char[][] map;
    static boolean[][][] visited; // 방문 여부 및 열쇠 보유 상태 체크

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class State {
        int row;
        int col;
        int keys; // 열쇠 보유 상태
        int moves; // 이동 횟수

        public State(int row, int col, int keys, int moves) {
            this.row = row;
            this.col = col;
            this.keys = keys;
            this.moves = moves;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        map = new char[N][M];
        visited = new boolean[N][M][64]; // 2^6 = 64 (a~f 열쇠 비트마스크)

        int startRow = 0, startCol = 0;

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == '0') {
                    startRow = i;
                    startCol = j;
                }
            }
        }

        int result = bfs(startRow, startCol);
        System.out.println(result);
    }

    static int bfs(int sr, int sc) {
        Queue<State> queue = new ArrayDeque<>();
        queue.offer(new State(sr, sc, 0, 0));
        visited[sr][sc][0] = true;

        while (!queue.isEmpty()) {
            State current = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nr = current.row + dr[d];
                int nc = current.col + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if (map[nr][nc] == '#') continue;

                char nextCell = map[nr][nc];
                int nextKeys = current.keys;
                int nextMoves = current.moves + 1;

                if ('a' <= nextCell && nextCell <= 'f') { // 열쇠인 경우
                    nextKeys |= (1 << (nextCell - 'a')); // 해당 열쇠 획득
                }

                if ('A' <= nextCell && nextCell <= 'F') { // 문인 경우
                    if ((nextKeys & (1 << (nextCell - 'A'))) == 0) // 해당 문을 열 수 있는 열쇠가 없는 경우
                        continue;
                }

                if (nextCell == '1') { // 출구에 도착한 경우
                    return nextMoves;
                }

                if (!visited[nr][nc][nextKeys]) {
                    visited[nr][nc][nextKeys] = true;
                    queue.offer(new State(nr, nc, nextKeys, nextMoves));
                }
            }
        }

        return -1; // 탈출 불가능한 경우
    }
}
