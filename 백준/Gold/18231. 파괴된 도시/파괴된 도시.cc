#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <vector>
#include <unordered_set>
#include <queue>
#include <algorithm>

int N, M, K;
std::vector<int> list[2001];
std::vector<int> destroyedCIty;
std::vector<int> validDestroyedCIty;
std::unordered_set<int> destroyedCItySet;
std::vector<int> resultCityNumber;
bool visit[2001];

bool isDestroyed(int cityNumber) {
	for (int x : list[cityNumber]) {
		if (destroyedCItySet.find(x) == destroyedCItySet.end()) return false; 
	} 
	return true; 
}

void bfs(int cityNumber) {
	std::queue<int> q;
	q.push(cityNumber);
	visit[cityNumber] = true;

	while (!q.empty()) {
		int now = q.front(); q.pop();
		for (int x : list[now]) {
			if (visit[x]) continue;
			if (destroyedCItySet.find(x) == destroyedCItySet.end()) continue;
			if (!isDestroyed(x)) continue;
			visit[x] = true;
			q.push(x);
		}
	}
}

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	scanf("%d %d", &N, &M);

	for (int m = 0;m < M;++m) {
		int start, end;
		scanf("%d %d", &start, &end);
		list[start].push_back(end);
		list[end].push_back(start);
	}

	scanf("%d", &K);
	for (int k = 0;k < K;++k) {
		int cityNumber;
		scanf("%d", &cityNumber);
		destroyedCIty.push_back(cityNumber);
		destroyedCItySet.insert(cityNumber);
	}

	for (int x : destroyedCIty) {
		if (isDestroyed(x))
			validDestroyedCIty.push_back(x);
	}

	std::vector<bool> destroyedByBomb(N + 1, false);

	for (int city : validDestroyedCIty) {
		destroyedByBomb[city] = true;
		for (int x : list[city]) {
			destroyedByBomb[x] = true;
		}
	}

	bool ok = true;

	for (int city : destroyedCIty) {
		if (!destroyedByBomb[city]) {
			ok = false;
			break;
		}
	}

	if (!ok) {
		std::cout << -1 << "\n";
	}
	else {
		std::sort(validDestroyedCIty.begin(), validDestroyedCIty.end());
		std::cout << validDestroyedCIty.size() << "\n";
		for (int i = 0; i < validDestroyedCIty.size(); i++) {
			std::cout << validDestroyedCIty[i];
			if (i + 1 < validDestroyedCIty.size()) std::cout << " ";
		}
	}

	return 0;
}