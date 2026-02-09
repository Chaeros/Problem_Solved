#include <iostream>
#include <vector>

int N;
int stones[100001];
bool visit[100001];
int result = 0;

void dfs(int position) {
	if (position - stones[position] >=0 && visit[position - stones[position]] == false) {
		visit[position - stones[position]] = true;
		++result;
		dfs(position - stones[position]);
	}

	if (position + stones[position] < N && visit[position + stones[position]] == false) {
		visit[position + stones[position]] = true;
		++result;
		dfs(position + stones[position]);
	}
}

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	std::cin >> N;
	for (int n = 0;n < N;++n) {
		std::cin >> stones[n];
	}

	int start;
	std::cin >> start;
	visit[start-1] = true;
	++result;
	dfs(start-1);
	std::cout << result << "\n";

	return 0;
}