#include <iostream>
#include <vector>
#include <queue>

class Node {
public:
	Node(int to, int weight) {
		this->to = to;
		this->weight = weight;
	}
	int to;
	int weight;
};

std::vector<Node> adj[10001];
std::vector<Node> radj[10001];
int indegree[10001];

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int N, M;
	scanf("%d", &N);
	scanf("%d", &M);

	for (int m = 0;m < M;++m) {
		int start, end, weight;
		scanf("%d %d %d", &start, &end, &weight);
		adj[start].push_back(Node(end, weight));
		radj[end].push_back(Node(start, weight));
		++indegree[end];
	}

	int hanStepCityNumber;
	int romaCityNumber;

	scanf("%d %d", &hanStepCityNumber, &romaCityNumber);

	std::queue<int> q;
	for (int i = 1;i <= N;++i) {
		if (indegree[i] == 0) {
			q.push(i);
		}
	}

	std::vector<int> dist(N+1,0);
	while (!q.empty()) {
		int now = q.front();q.pop();
		for (Node next : adj[now]) {
			if (dist[next.to] < dist[now] + next.weight) {
				dist[next.to] = dist[now] + next.weight;
			}
			--indegree[next.to];
			if (indegree[next.to] == 0) {
				q.push(next.to);
			}
		}
	}

	std::cout << dist[romaCityNumber] << std::endl;

	std::vector<bool> visit(N + 1, false);
	q.push(romaCityNumber);
	visit[romaCityNumber] = true;
	int resultCount = 0;
	while (!q.empty()) {
		int now = q.front(); q.pop();
		for (Node next : radj[now]) {
			if (dist[now] == dist[next.to] + next.weight) {
				++resultCount;
				if (!visit[next.to]) {
					visit[next.to] = true;
					q.push(next.to);
				}
			}
		}
	}

	std::cout << resultCount << std::endl;

	return 0;
}