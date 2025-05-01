#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <vector>
#include <cmath>
#include <algorithm>

typedef std::pair<int, int> Pair;
std::vector<int> edges[100001];
std::vector<Pair> result;
int distance[100001];
int vertexCount = 0;

int dfs(int now, int parent) {
	distance[now] = ++vertexCount;

	int num = distance[now];
	for (int i = 0;i < edges[now].size();++i) {
		int next = edges[now][i];
		if (next == parent) continue;

		if (distance[next] == 0) {
			int low = dfs(next, now);
			num = std::min(num, low);
			if (low > distance[now]) {
				result.push_back({ std::min(now,next), std::max(now, next) });
			}
		}
		else {
			num = std::min(num, distance[next]);
		}
	}
	return num;
}

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int vertexCount, edgeCount;
	scanf("%d %d", &vertexCount, &edgeCount);
	int start, end;
	for (int i = 0;i < edgeCount;++i) {
		scanf("%d %d", &start, &end);
		edges[start].push_back(end);
		edges[end].push_back(start);
	}
	dfs(1, 0);

	std::sort(result.begin(), result.end());
	std::cout << result.size() << std::endl;
	for (Pair x : result) {
		printf("%d %d\n", x.first, x.second);
	}

	return 0;
}