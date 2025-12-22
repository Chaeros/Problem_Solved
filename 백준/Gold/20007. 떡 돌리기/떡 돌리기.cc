#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

class Node {
public:
	int to;
	int weight;
	Node(int to, int weight) {
		this->to = to;
		this->weight = weight;
	}
	bool operator<(const Node& other) const {
		return weight > other.weight;
	}
};
std::vector<Node> list[1001];

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int N, M, X, Y;
	std::cin >> N >> M >> X >> Y;

	for (int m = 0;m < M;++m) {
		int start, end, weight;
		std::cin >> start >> end >> weight;

		list[start].push_back(Node(end, weight));
		list[end].push_back(Node(start, weight));
	}

	std::priority_queue<Node> pq;
	pq.push(Node(Y, 0));
	std::vector<long long> dist(N + 1, 999999999);
	dist[Y] = 0;

	while (!pq.empty()) {
		Node now = pq.top(); pq.pop();
		for (Node next : list[now.to]) {
			if (dist[next.to] > dist[now.to] + next.weight) {
				dist[next.to] = dist[now.to] + next.weight;
				pq.push(Node(next.to, dist[next.to]));
			}
		}
	}
	
	std::vector<int> v;
	for (int n = 0;n < N;++n) {
		v.push_back(dist[n]);
	}

	std::sort(v.begin(), v.end());

	int dayCount = 1;
	int sum = 0;
	for (int n = 0;n < N;++n) {
		if (v[n] * 2 > X) {
			std::cout << -1;
			return 0;
		}
		if ((sum + v[n]) * 2 <= X) {
			sum += v[n];
		}
		else {
			sum = v[n];
			++dayCount;
		}
	}
	
	std::cout << dayCount;
	return 0;
}