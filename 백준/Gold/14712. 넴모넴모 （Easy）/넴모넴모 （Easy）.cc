#include <iostream>

int N, M;
bool map[25][25];
int result = 0;

bool isImpossible(int row, int col) {
	bool result = false;
	if (row > 0 && col > 0) {
		if (map[row - 1][col - 1] && map[row - 1][col] && map[row][col - 1]) result = true;
	}
	if (row > 0 && col + 1 < M) {
		if (map[row - 1][col] && map[row - 1][col + 1] && map[row][col + 1]) result = true;
	}
	if (row + 1 < N && col > 0) {
		if (map[row][col - 1] && map[row + 1][col - 1] && map[row + 1][col]) result = true;
	}
	if (row + 1 < N && col + 1 < M) {
		if (map[row + 1][col] && map[row + 1][col + 1] && map[row][col + 1]) result = true;
	}
	
	return result;
}

void dfs(int val) {
	if (val == N * M) {
		++result;
		return;
	}
	int row = val / M;
	int col = val % M;

	map[row][col] = false;
	dfs(val+1);

	map[row][col] = true;
	if (!isImpossible(row, col)) {
		dfs(val + 1);
	}
	map[row][col] = false;
}

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	std::cin >> N >> M;
	dfs(0);

	std::cout << result;
	return 0;
}