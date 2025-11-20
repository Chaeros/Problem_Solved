#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <vector>
#include <queue>
#include <climits>

std::vector<std::pair<long long, int>> list[100000];
bool isPossibleToGo[100000];
long long dist[100000];

int main() 
{
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int N, M;
	scanf("%d %d", &N, &M);
	std::fill(dist, dist + N, LLONG_MAX);

	int x;
	for (int n = 0;n < N;++n) {
		scanf("%d", &x);
		if (x == 0) isPossibleToGo[n] = true;
		else isPossibleToGo[n] = false;
	}

	int start, end, cost;
	for (int m = 0;m < M;++m) {
		scanf("%d %d %d", &start, &end, &cost);
		list[start].push_back({cost,end});
		list[end].push_back({ cost,start });
	}

	struct Compare {
		bool operator()(
			const std::pair<long long, int>& a,
			const std::pair<long long, int>& b) const 
		{
			return a.first > b.first;
		}
	};

	std::priority_queue<
		std::pair<long long, int>,
		std::vector<std::pair<long long, int>>,
		Compare> pq;

	pq.push({ 0,0 });
	dist[0] = 0;
	while (!pq.empty()) {
		std::pair<long long, int> now = pq.top(); pq.pop();
		if (now.first > dist[now.second]) continue;
		for (std::pair<long long, int> next : list[now.second]) {
			if (isPossibleToGo[next.second] == false && next.second != N-1) continue;
			long long cost = dist[now.second] + next.first;
			if(dist[next.second] > cost){
				dist[next.second] = cost;
				pq.push({dist[next.second],next.second});
			}
		}
	}

	if (dist[N-1] == LLONG_MAX) printf("%d", -1);
	else printf("%lld", dist[N-1]);

	return 0;
}