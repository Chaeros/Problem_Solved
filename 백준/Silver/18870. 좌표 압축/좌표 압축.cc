#include <iostream>
#include <vector>
#include <algorithm>

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int N;
	std::cin >> N;
	std::vector<int> A(N,0);
	std::vector<int> temp;
	for (int n = 0;n < N;++n) {
		std::cin >> A[n];
		temp.push_back(A[n]);
	}

	std::sort(temp.begin(), temp.end());
	temp.erase(std::unique(temp.begin(), temp.end()), temp.end());
	for (int n = 0;n < N;++n) {
		A[n] = std::lower_bound(temp.begin(), temp.end(), A[n]) - temp.begin();
	}

	for (int n = 0;n < N;++n) {
		std::cout << A[n];
		if (n != N - 1) std::cout << " ";
	}

	return 0;
}