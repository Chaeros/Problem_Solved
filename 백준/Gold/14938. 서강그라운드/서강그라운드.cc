#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
#include <climits>
#include <cmath>

class Node {
public:
	int vertex;
	int weight;

	Node(int v, int w) :vertex(v), weight(w) {}

	bool operator<(const Node& other) const {
		return weight > other.weight;
	}
};

std::vector<std::pair<int, int>> list[101];
int vertexNum, range, routeNum;
int itemCount[101];
int result = 0;

void dikjstra(int start) {
	std::priority_queue<Node> pq;
	bool visit[101];
	int distance[101];
	std::fill(visit, visit + 100, false);
	std::fill(distance, distance + 100, INT_MAX);

	pq.push(Node(start,0));
	distance[start] = 0;
	int tempResultSum = 0;

	while (!pq.empty()) {
		Node now = pq.top();
		pq.pop();

		if (visit[now.vertex]) continue;
		visit[now.vertex] = true;
		tempResultSum += itemCount[now.vertex];
		for (auto x : list[now.vertex]) {
			int next = x.first;
			int cost = x.second;
			int newDistance = distance[now.vertex] + cost;
			if (distance[next] > newDistance) {
				if (newDistance > range) continue;
				distance[next] = newDistance;
				pq.push(Node(next, distance[next]));
			}
		}
	}
	result = std::max(result, tempResultSum);
}

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);
	scanf("%d %d %d", &vertexNum, &range, &routeNum);

	int x;
	for (int i = 1;i <= vertexNum;++i) {
		scanf("%d", &x);
		itemCount[i] = x;
	}

	int a, b, cost;
	for (int i = 0;i < routeNum;++i) {
		scanf("%d %d %d", &a, &b, &cost);
		list[a].push_back({ b,cost });
		list[b].push_back({ a,cost });
	}

	for (int n = 1;n <= vertexNum;++n) {
		dikjstra(n);
	}
	std::cout << result << std::endl;

	return 0;
}