#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>
#include <utility>
#include <sstream>
#define _CRT_SECURE_NO_WARNINGS
using namespace std;

const int MAX = 100001;
const int MAXLOG = 17;
int N;
int ac[MAX][MAXLOG];
int minLength[MAX][MAXLOG];
int maxLength[MAX][MAXLOG];
int depth[MAX];
bool visit[MAX];
vector<pair<int,int>> adjList[MAX];

void makeTree(int current, int depthValue) {
	depth[current] = depthValue;
	visit[current] = true;

	for (int i = 0; i < adjList[current].size(); ++i) {
		int target = adjList[current][i].first;
		int length = adjList[current][i].second;

		if (visit[target]) continue;
		ac[target][0] = current;

		minLength[target][0] = length;
		maxLength[target][0] = length;
		makeTree(target, depthValue + 1);
	}
}

void makeMemorization() {
	for (int i = 1; i < MAXLOG; ++i) {
		for (int j = 1; j <= N; ++j) {
			int preAc = ac[j][i - 1];
			if (preAc == 0) continue;
			ac[j][i] = ac[preAc][i - 1];

			if (ac[preAc][i - 1] == 0) continue;

			int preMin = minLength[preAc][i - 1];
			int preMax = maxLength[preAc][i - 1];

			int cMin = minLength[j][i - 1];
			int cMax = maxLength[j][i - 1];

			minLength[j][i] = min(preMin, cMin);
			maxLength[j][i] = max(preMax, cMax);
		}
	}
}

pair<int, int> lca(int a, int b) {
	if (depth[a] > depth[b]) {
		int tmp = a;
		a = b;
		b = tmp;
	}
	int maxValue = -1;
	int minValue = 1000001;

	for (int i = MAXLOG - 1; i >= 0; --i) {
		int powJump = 1 << i;
		if (depth[b] - depth[a] >= powJump) {
			minValue = min(minValue, minLength[b][i]);
			maxValue = max(maxValue, maxLength[b][i]);
			b = ac[b][i];
		}
	}

	if (a == b) return { minValue,maxValue };

	for (int i = MAXLOG - 1; i >= 0; --i) {
		if (ac[a][i] == ac[b][i]) continue;
		minValue = min(minValue, min(minLength[a][i], minLength[b][i]));
		maxValue = max(maxValue, max(maxLength[a][i], maxLength[b][i]));
		a = ac[a][i];
		b = ac[b][i];
	}

	minValue = min(minValue, min(minLength[a][0], minLength[b][0]));
	maxValue = max(maxValue, max(maxLength[a][0], maxLength[b][0]));
	return { minValue,maxValue };
}

int main() {
	scanf("%d", &N);
	fill(visit, visit + MAX, false);
	int a, b, length;
	for (int n = 0; n < N - 1; ++n) {
		scanf("%d %d %d", &a, &b, &length);
		adjList[a].push_back({ b,length });
		adjList[b].push_back({ a,length });
	}

	makeTree(1, 0);
	makeMemorization();

	int K;
	scanf("%d", &K);
	ostringstream output;
	for (int k = 0; k < K; ++k) {
		scanf("%d %d", &a, &b);
		pair<int, int> result = lca(a, b);
		output << result.first << " " << result.second << "\n";
	}
	cout << output.str();
	return 0;
}