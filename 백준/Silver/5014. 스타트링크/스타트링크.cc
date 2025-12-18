#include <iostream>
#include <vector>
#include <queue>

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int F, S, G, U, D;
	scanf("%d %d %d %d %d", &F, &S, &G, &U, &D);

	int inf = 987654321;
	std::vector<int> dist(1000001, inf);

	std::queue<int> q;
	q.push(S);
	dist[S] = 0;

	while (!q.empty()) {
		int size = q.size();
		for (int i = 0;i < size;++i) {
			int now = q.front();q.pop();
			int mu = now + U;
			int md = now - D;
			if (mu <= F && dist[mu] == inf) {
				dist[mu] = dist[now] + 1;
				q.push(mu);
			}
			if (md > 0 && dist[md] == inf) {
				dist[md] = dist[now] + 1;
				q.push(md);
			}
		}
		if (dist[G] != inf) break;
	}

	if (dist[G] == inf) std::cout << "use the stairs" << std::endl;
	else std::cout << dist[G] << std::endl;
	return 0;
}