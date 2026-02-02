#include <iostream>
#include <string>

const std::string L1 = "Messi";
const std::string L2 = "Messi Gimossi";
long long L[100];

void answer(int N, long long M) {
	if (N == 1) {
		if (L1[M - 1] == ' ') std::cout << "Messi Messi Gimossi\n";
		else std::cout << L1[M - 1] << "\n";
		return;
	}
	else if (N == 2) {
		if (L2[M - 1] == ' ') std::cout << "Messi Messi Gimossi\n";
		else std::cout << L2[M - 1] << "\n";
		return;
	}

	if (L[N-1] >= M) {
		answer(N - 1, M);
	}
	else if (L[N-1] + 1 == M) {
		std::cout << "Messi Messi Gimossi\n";
	}
	else {
		answer(N - 2, M - (L[N - 1] + 1));
	}
}

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	long long M;
	std::cin >> M;
	L[1] = 5;
	L[2] = 13;

	int i = 3;
	while (true) {
		L[i] = L[i - 1] + L[i - 2] + 1;
		if (L[i] >= M) break;
		++i;
	}
	answer(i, M);
	return 0;
}