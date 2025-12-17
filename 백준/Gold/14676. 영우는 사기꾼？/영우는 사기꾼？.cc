#include <iostream>
#include <vector>

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int N, M, K;
	scanf("%d %d %d", &N, &M, &K);

	std::vector<std::vector<int>> list(N+1);
	std::vector<int> indegree(N + 1, 0);

	for (int m = 0;m < M;++m) {
		int start, to;
		scanf("%d %d", &start, &to);
		list[start].push_back(to);
		++indegree[to];
	}

	std::vector<int> preNeedBuilding(N + 1, 0);
	std::vector<int> buildingCount(N + 1, 0);
	for (int k = 0;k < K;++k) {
		int command, building;
		scanf("%d %d", &command, &building);
		if (command == 1) {
			if (preNeedBuilding[building] != indegree[building]) {
				std::cout << "Lier!" << std::endl;
				return 0;
			}
			++buildingCount[building];
			if (buildingCount[building] == 1) {
				for (int x : list[building]) {
					++preNeedBuilding[x];
				}
			}
		}
		else if (command == 2) {
			if (buildingCount[building] == 0) {
				std::cout << "Lier!" << std::endl;
				return 0;
			}
			--buildingCount[building];
			if (buildingCount[building] == 0) {
				for (int x : list[building]) {
					--preNeedBuilding[x];
				}
			}
		}
	}

	std::cout << "King-God-Emperor" << std::endl;
	return 0;
}