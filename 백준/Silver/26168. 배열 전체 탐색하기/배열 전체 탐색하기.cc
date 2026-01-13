#include <iostream>
#include <vector>
#include <algorithm>

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int N, M;
	std::cin >> N >> M;

	std::vector<long long> list(N);
	for (int n = 0;n < N;++n) {
		std::cin >> list[n];
	}
	std::sort(list.begin(), list.end());

	for (int m = 0;m < M;++m) {
		int command;
		long long val1, val2;
		std::cin >> command;
		if (command == 1) {
			std::cin >> val1;
			auto it = std::lower_bound(list.begin(), list.end(), val1);
			std::cout << list.end() - it << "\n";
		}
		else if (command == 2) {
			std::cin >> val1;
			auto it = std::upper_bound(list.begin(), list.end(), val1);
			std::cout << list.end() - it << "\n";
		}
		else if (command == 3) {
			std::cin >> val1 >> val2;
			auto left = std::lower_bound(list.begin(), list.end(), val1);
			auto right = std::upper_bound(list.begin(), list.end(), val2);
			std::cout << right - left << "\n";
		}
	}
	return 0;
}