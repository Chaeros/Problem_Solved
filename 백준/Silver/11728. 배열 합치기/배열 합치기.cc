#include <iostream>
#include <vector>

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int A, B;
	scanf("%d %d", &A, &B);

	std::vector<int> aVector(A, 0);
	std::vector<int> bVector(B, 0);
	for (int a = 0;a < A;++a) {
		scanf("%d", &aVector[a]);
	}
	for (int b = 0;b < B;++b) {
		scanf("%d", &bVector[b]);
	}

	int i = 0, j = 0;
	bool isStart = true;
	while (i < A && j < B) {
		if (isStart) {
			isStart = false;
		}
		else {
			std::cout << " ";
		}
		if (aVector[i] <= bVector[j]) {
			std::cout << aVector[i++];
		}
		else {
			std::cout << bVector[j++];
		}
	}

	while (i < A) std::cout << " " << aVector[i++];
	while (j < B) std::cout << " " << bVector[j++];

	return 0;
}