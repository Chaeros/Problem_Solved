#include <iostream>
#include <queue>
#include <vector>

bool isPossible() {
	int N, M;
	std::cin >> N >> M;

	std::vector<std::vector<int>> list(N);
	std::vector<int> color(N, 0);

	for (int m = 0;m < M;++m) {
		int x, y;
		std::cin >> x >> y;
		list[x-1].push_back(y-1);
		list[y-1].push_back(x-1);
	}

	for (int n = 0;n < N;++n) {
		if (color[n] != 0) continue;

		std::queue<int> q;
		color[n] = 1;
		q.push(n);

		while (!q.empty()) {
			int now = q.front();q.pop();
			for (int next : list[now]) {
				if (color[next] == 0) {
					if (color[now] == 1) {
						color[next] = 2;
					}
					else {
						color[next] = 1;
					}
					q.push(next);
				}
				else if (color[now] == color[next]) {
					return false;
				}
			}
		}
	}
	return true;
}

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int T;
	std::cin >> T;

	while (T--) {
		if (isPossible()) {
			std::cout << "YES\n";
		}
		else {
			std::cout << "NO\n";
		}
	}

	return 0;
}