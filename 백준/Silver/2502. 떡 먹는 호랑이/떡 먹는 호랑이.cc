#include <iostream>

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int D, K;
	std::cin >> D >> K;

	int A[31], B[31];
	A[1] = 1;
	A[2] = 0;

	B[1] = 0;
	B[2] = 1;

	for (int i = 3;i <= D;++i) {
		A[i] = A[i - 1] + A[i - 2];
		B[i] = B[i - 1] + B[i - 2];
	}

	int aLast = A[D];
	int bLast = B[D];

	for (int aVal = 1;aVal <= K;++aVal) {
		int val = K - aLast * aVal;
		
		if (val < 0) break;

		if (val % bLast == 0) {
			int bVal = val / bLast;

			if (aVal <= bVal) {
				std::cout << aVal << "\n" << bVal;
				break;
			}
		}
	}

	return 0;
}