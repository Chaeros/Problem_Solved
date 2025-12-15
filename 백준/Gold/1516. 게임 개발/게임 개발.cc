#include <iostream>
#include <vector>
#include <queue>
#include <cmath>

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int N;
	scanf("%d", &N);

	std::vector<int> weight(N + 1, 0);
	std::vector<int> graph[501];
	std::vector<int> indegree(N + 1, 0);

	for (int n = 1;n <= N;++n) {
		bool isEnd = false;
		
		scanf("%d", &weight[n]);
		while (!isEnd) {
			int x;
			scanf("%d", &x);
			if (x == -1) break;
			graph[x].push_back(n);
			++indegree[n];
		}
	}

	std::queue<int> q;
	std::vector<int> result(N + 1, 0);
	for (int n = 1;n<= N;++n) {
		if (indegree[n] == 0) {
			q.push(n);
			result[n] = weight[n];
		}
	}

	while (!q.empty()) {
		int now = q.front(); q.pop();
		for (int x : graph[now]) {
			result[x] = std::max(result[x], result[now] + weight[x]);
			--indegree[x];
			if (indegree[x] == 0) {
				q.push(x);
			}
		}
	}

	for (int n = 1;n <= N;++n) {
		std::cout << result[n] << std::endl;
	}
	return 0;
}