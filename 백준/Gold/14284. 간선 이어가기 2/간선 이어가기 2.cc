#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
#include <climits>

class Node {
public:
	int vertex;
	int weight;

	Node(int v, int w) :vertex(v), weight(w) {}

	bool operator<(const Node& other) const {
		return weight > other.weight;
	}
};

std::vector<std::pair<int, int>> list[5001];
bool visit[5001];
int distance[5001];

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int N, M;
	scanf("%d %d", &N, &M);

	int vertex1, vertex2, weight;
	for (int n = 0;n < M;++n) {
		scanf("%d %d %d", &vertex1, &vertex2, &weight);
		list[vertex1].push_back({ vertex2,weight });
		list[vertex2].push_back({ vertex1,weight });
	}

	int start, end;
	scanf("%d %d", &start, &end);
	std::priority_queue<Node> pq;
	pq.push(Node(start, 0));

	std::fill(distance, distance + N + 1, INT_MAX);
	distance[start] = 0;
	while (!pq.empty()) {
		Node now = pq.top();
		pq.pop();

		if (visit[now.vertex]) continue;
		visit[now.vertex] = true;

		for (std::pair<int, int> x : list[now.vertex]) {
			int next = x.first;
			int cost = x.second;
			if (distance[next] > distance[now.vertex] + cost) {
				distance[next] = distance[now.vertex] + cost;
				pq.push(Node(next, distance[next]));
			}
		}
	}

	std::cout << distance[end] << std::endl;

	return 0;
}