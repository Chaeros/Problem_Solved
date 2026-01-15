#include <iostream>
#include <vector>
#include <cmath>
#include <queue>

int N, M, J, K;
std::vector<int> A;
std::vector<int> B;

class Node {
public:
	int to;
	int weight;
	Node(int to, int weight)
		:to(to), weight(weight) {
	}
	bool operator< (const Node& other) const {
		return this->weight > other.weight;
	}
};

const int INF = 987654321;
std::vector<Node> list[5001];
std::vector<int> dist(5001, INF);

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	std::cin >> N >> M;
	std::cin >> J;
	std::cin >> K;

	for (int k = 0;k < K;++k) {
		int x;
		std::cin >> x;
		A.push_back(x);
	}

	for (int k = 0;k < K;++k) {
		int x;
		std::cin >> x;
		B.push_back(x);
	}

	for (int m = 0;m < M;++m) {
		int start, end, weight;
		std::cin >> start >> end >> weight;
		list[start].push_back(Node(end, weight));
		list[end].push_back(Node(start, weight));
	}

	std::priority_queue<Node> pq;
	pq.push(Node(J, 0));
	dist[J] = 0;

	while (!pq.empty()) {
		Node now = pq.top(); pq.pop();
		if (now.weight > dist[now.to]) continue;
		for (Node next : list[now.to]) {
			if (dist[next.to] > dist[now.to] + next.weight) {
				dist[next.to] = dist[now.to] + next.weight;
				pq.push(Node(next.to, dist[next.to]));
			}
		}
	}

	int distA = INF;
	int distB = INF;
	for (int a : A) {
		distA = std::min(distA, dist[a]);
	}

	for (int b : B) {
		distB = std::min(distB, dist[b]);
	}

	if (distA == INF && distB == INF) {
		std::cout << "-1\n";
	}
	else if (distA <= distB) {
		std::cout << "A\n";
		std::cout << distA << "\n";
	}
	else if (distA > distB) {
		std::cout << "B\n";
		std::cout << distB << "\n";
	}

	return 0;
}