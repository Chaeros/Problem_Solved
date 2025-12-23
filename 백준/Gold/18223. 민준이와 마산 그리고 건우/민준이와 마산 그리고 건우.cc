#include <iostream>
#include <vector>
#include <queue>

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

int V, E, P;
std::vector<Node> list[5001];

int getResult(int start, int to) {
	std::priority_queue<Node> pq;
	pq.push(Node(start, 0));
	std::vector<int> dist(V + 1, 999999999);
	dist[start] = 0;

	while (!pq.empty()) {
		Node now = pq.top();pq.pop();
		if (dist[now.to] < now.weight) continue;
		for (Node next : list[now.to]) {
			if (dist[next.to] > dist[now.to] + next.weight) {
				dist[next.to] = dist[now.to] + next.weight;
				pq.push(Node(next.to, dist[next.to]));
			}
		}
	}
	return dist[to];
}

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	std::cin >> V >> E >> P;

	for (int e = 0;e < E;++e) {
		int start, to, weight;
		std::cin >> start >> to >> weight;
		list[start].push_back(Node(to, weight));
		list[to].push_back(Node(start, weight));
	}

	int iToDest = getResult(1, V);
	int iToFriend = getResult(1, P);
	int friendToDest = getResult(P, V);

	if (iToFriend + friendToDest == iToDest) {
		std::cout << "SAVE HIM";
	}
	else {
		std::cout << "GOOD BYE";
	}

	return 0;
}