#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <vector>
#include <cmath>

std::vector<int> v[10001];
bool isCut[10001];
int distance[10001];
int edgeCount = 0;

int dfs(int now, bool isRoot) {
	distance[now] = ++edgeCount;
	int childCount = 0;
	int num = distance[now];
	for (int i = 0;i < v[now].size();++i) {
		int next = v[now][i];
		if (distance[next] == 0) {
			++childCount;
			int low = dfs(next, false);
			num = std::min(num, low);
			if (!isRoot && low >= distance[now]) {
				isCut[now] = true;
			}
		}
		else {
			num = std::min(num, distance[next]);
		}
	}
	if (isRoot && childCount > 1) {
		isCut[now] = true;
	}
	return num;
}

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int vertex, edge;
	scanf("%d %d", &vertex, &edge);
	int a, b;
	for (int i = 0;i < edge;++i) {
		scanf("%d %d", &a, &b);
		v[a].push_back(b);
		v[b].push_back(a);
	}

	for (int i = 1;i <= vertex;++i) {
		if (distance[i] == 0) {
			dfs(i, true);
		}
	}

	int resultSum = 0;
	for (int i = 1;i <= vertex;++i) {
		if (isCut[i]) {
			++resultSum;
		}
	}
	std::cout << resultSum << std::endl;

	for (int i = 1;i <= vertex;++i) {
		if (isCut[i]) {
			printf("%d ", i);
		}
	}

	return 0;
}