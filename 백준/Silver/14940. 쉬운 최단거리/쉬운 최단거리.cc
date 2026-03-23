#include <iostream>
#include <queue>
#include <cstring>
#include <cstdio>

const int INF = 987654321;

struct Node {
	int row;
	int col;
	int dist;

	Node(int row, int col, int dist) : row(row), col(col), dist(dist) {}

	bool operator< (const Node& other) const {
		return dist > other.dist;
	}
};

int N, M;
int arr[1001][1001];
int destRow, destCol;

int dr[4] = { -1, 0, 1, 0 };
int dc[4] = { 0, 1, 0, -1 };
int dist[1001][1001];

void bfs() {
	int startRow = destRow;
	int startCol = destCol;

	std::priority_queue<Node> pq;
	
	for (int n = 0;n < N;++n) {
		for (int m = 0;m < M;++m) {
			if (arr[n][m] == 1) dist[n][m] = INF;
			else if (arr[n][m] == 0) dist[n][m] = 0;
		}
	}
	pq.push(Node(startRow, startCol, 0));

	while (!pq.empty()) {
		Node now = pq.top(); pq.pop();
		for (int d = 0;d < 4;++d) {
			int mr = now.row + dr[d];
			int mc = now.col + dc[d];

			if (mr < 0 || mr >= N || mc < 0 || mc >= M) continue;
			if (arr[mr][mc] == 0) continue;

			if (dist[mr][mc] > dist[now.row][now.col] + 1) {
				dist[mr][mc] = dist[now.row][now.col] + 1;
				pq.push(Node(mr, mc, dist[mr][mc]));
			}
		}
	}
}

void printResult() {
	for (int n = 0;n < N;++n) {
		for (int m = 0;m < M;++m) {
			if (dist[n][m] == INF) dist[n][m] = -1;
			printf("%d ", dist[n][m]);
		}
		printf("\n");
	}
}

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	std::memset(dist, 987654321, sizeof(dist));
	std::cin >> N >> M;
	for (int n = 0;n < N;++n) {
		for (int m = 0;m < M;++m) {
			std::cin >> arr[n][m];
			if(arr[n][m]==2) {
				destRow = n;
				destCol = m;
			}
		}
	}

	dist[destRow][destCol] = 0;

	bfs();
	printResult();

	return 0;
}