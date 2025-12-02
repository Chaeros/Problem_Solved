#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <cstring> 

int arrow[1000001];

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int N;
	scanf("%d", &N);

	memset(arrow, 0, sizeof(arrow));

	int result = 0;
	for (int n = 0;n < N;++n) {
		int h;
		scanf("%d", &h);

		if (arrow[h] > 0) {
			arrow[h]--;
		}
		else{
			++result;
		}

		if (h > 0) {
			arrow[h - 1]++;
		}
	}

	std::cout << result << std::endl;

	return 0;
}