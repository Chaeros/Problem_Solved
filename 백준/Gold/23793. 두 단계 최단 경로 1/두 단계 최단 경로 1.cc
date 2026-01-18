#include <iostream>
#include <vector>
#include <queue>

class Node {
public:
	int to;
	int weight;
	Node(int to, int weight)
		:to(to), weight(weight) {
	}
	bool operator<(const Node& other) const {
		return this->weight > other.weight;
	}
};

int N, M;
const int INF = 1876543210;
std::vector<Node> list[100001];

int dijkstra(int start, int end, int exception) {
	std::priority_queue<Node> pq;
	std::vector<int> dist(100001, INF);
	pq.push(Node(start, 0));
	dist[start] = 0;

	while (!pq.empty()) {
		Node now = pq.top(); pq.pop();
		if (now.weight > dist[now.to]) continue;
		for (Node next : list[now.to]) {
			if (next.to == exception) continue;
			if (dist[next.to] > dist[now.to] + next.weight) {
				dist[next.to] = dist[now.to] + next.weight;
				pq.push(Node(next.to, dist[next.to]));
			}
		}
	}
	return dist[end];
}

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	std::cin >> N >> M;
	for (int m = 0;m < M;++m) {
		int start, end, weight;
		std::cin >> start >> end >> weight;
		list[start].push_back(Node(end, weight));
	}

	int startPoint, middlePoint, endPoint;
	std::cin >> startPoint >> middlePoint >> endPoint;

	int result1 = dijkstra(startPoint, middlePoint, -1);
	int result2 = dijkstra(middlePoint, endPoint, -1);
	int result3 = dijkstra(startPoint, endPoint, middlePoint);

	if (result1 == INF || result2 == INF) {
		std::cout << -1 << " ";
	}
	else {
		std::cout << result1 + result2 << " ";
	}

	if (result3 == INF) {
		std::cout << -1 << "\n";
	}
	else {
		std::cout << result3 << "\n";
	}

	return 0;
}