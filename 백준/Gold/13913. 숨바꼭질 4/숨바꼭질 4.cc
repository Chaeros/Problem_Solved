#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#include <climits>

class Node {
public:
	int count;
	int position;

	Node(int c, int p) : count(c), position(p) {}

	bool operator<(const Node other) const {
		return count > other.count;
	}

};

bool visit[100001];
int distances[100001];
int pre[100001];
std::vector<int> result;

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int start, end;

	scanf("%d %d", &start, &end);
	std::fill(distances, distances + 100001, INT_MAX);

	std::priority_queue<Node> pq;
	pq.push(Node(0, start));
	distances[start] = 0;
	pre[start] = -1;

	int dx[3] = { -1,0,1 };
	while (!pq.empty()) {
		Node now = pq.top();
		pq.pop();

		if (visit[now.position]) continue;
		visit[now.position] = true;
		if (now.position == end) break;

		for (int d = 0;d < 3;++d) {
			int next = now.position + dx[d];
			int count = distances[now.position] + 1;
			if (next < 0 || next >= 100001) continue;
			if (distances[next] > count) {
				distances[next] = count;
				pre[next] = now.position;
				pq.push(Node(count, next));
			}
		}
		int next = now.position*2;
		int count = distances[now.position] + 1;
		if (next < 0 || next >= 100001) continue;
		if (distances[next] > count) {
			distances[next] = count;
			pre[next] = now.position;
			pq.push(Node(count, next));
		}
	}

	std::vector<int> result;
	for (int i = end;pre[i] != -1;i = pre[i]) {
		result.push_back(i);
	}
	result.push_back(start);

	std::cout << distances[end] << std::endl;
	for (int i = result.size()-1;i >= 0;--i) {
		printf("%d ", result[i]);
	}

	return 0;
}