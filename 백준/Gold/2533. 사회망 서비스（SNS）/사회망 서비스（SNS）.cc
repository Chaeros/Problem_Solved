#include <iostream>
#include <vector>
#include <cmath>

std::vector<int> adj[1000001];
std::vector<int> graph[1000001];
std::vector<bool> visit(1000001,false);
int dp[1000001][2];

void makeTree(int node) {
	for (int i = 0;i < adj[node].size();++i) {
		int next = adj[node][i];
		if (visit[next]) continue;
		visit[next] = true;
		graph[node].push_back(next);
		makeTree(next);
	}
}

void dfs(int node) {
	dp[node][0] = 0;
	dp[node][1] = 1;

	for (int i = 0;i < graph[node].size();++i) {
		int child = graph[node][i];

		dfs(child);

		dp[node][0] += dp[child][1];
		dp[node][1] += std::min(dp[child][0], dp[child][1]);
	}
}

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int N;
	scanf("%d", &N);

	for (int n = 0;n < N-1;++n) {
		int a, b;
		scanf("%d %d", &a, &b);
		adj[a].push_back(b);
		adj[b].push_back(a);
	}

	visit[1] = true;
	makeTree(1);
	dfs(1);
	std::cout << std::min(dp[1][0], dp[1][1]) << std::endl;

	return 0;
}