#include <iostream>
#include <vector>
#include <set>
#include <climits>
#include <queue>

using namespace std;

class Node {
public:
	int index;
	int cost;
	Node(int index, int cost)
		:index(index), cost(cost) {}
	bool operator<(const Node other) const {
		return this->cost > other.cost;
	}
};

vector<Node>* graph;
int N=0, M=0;
int start, destination;
int minVal = INT_MAX;
int nextMinVal = INT_MAX;
int dist[500];
vector<int> parent[500];

void remove();
int dijkstra(int s);

int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);

	do {
		scanf("%d %d", &N, &M);
		if (N == 0 && M == 0) break;
		scanf("%d %d", &start, &destination);
		graph = new vector<Node>[N];
		for (int n = 0;n < N;++n) {
			parent[n].clear();
		}

		for(int m=0;m<M;++m){
			int s, e, cost;
			scanf("%d %d %d", &s, &e, &cost);
			graph[s].push_back(Node(e, cost));
		}
		dijkstra(start);
		remove();
		int answer = dijkstra(start);
		if (answer == INT_MAX) printf("-1\n");
		else printf("%d\n", answer);
	} while (true);

	delete[] graph;
	return 0;
}

int dijkstra(int s) {
	priority_queue<Node> pq;
	vector<bool> visit(N,false);
	visit[s] = true;
	pq.push(Node(s, 0));
	fill(dist, dist + N, INT_MAX);
	dist[s] = 0;
	while (!pq.empty()) {
		Node now = pq.top(); pq.pop();
		for (Node next : graph[now.index]) {
			if (next.cost == -1) continue;
			if (visit[next.index]) continue;
			if (dist[next.index] > dist[now.index] + next.cost) {
				dist[next.index] = dist[now.index] + next.cost;
				parent[next.index].clear();
				parent[next.index].push_back(now.index);
				pq.push(Node(next.index, dist[next.index]));
			}
			else if (dist[next.index] == dist[now.index] + next.cost) {
				parent[next.index].push_back(now.index);
			}
		}
	}
	return dist[destination];
}

void remove() {
	bool visit[500] = { false };
	queue<int> q;
	visit[destination] = true;
	q.push(destination);
	while (!q.empty()) {
		int now = q.front(); q.pop();
		for (int pre : parent[now]) {
			for (auto& edge : graph[pre]) {
				if (edge.index == now && dist[now] == dist[pre] + edge.cost) {
					edge.cost = -1;
				}
			}
			if (!visit[pre]) {
				visit[pre] = true;
				q.push(pre);
			}
		}
	}
}