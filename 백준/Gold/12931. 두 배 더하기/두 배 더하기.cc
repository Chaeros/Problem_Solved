#include <iostream>

int N;
int arr[50];

bool isEnd() {
	for (int n = 0;n < N;++n) {
		if (arr[n] > 0) return false;
	}
	return true;
}

void calculate() {
	bool isOddArr = true;
	for (int n = 0;n < N;++n) {
		if (arr[n] % 2 == 1) {
			isOddArr = false;
			break;
		}
	}

	if (isOddArr == true) {
		for (int n = 0;n < N;++n) {
			arr[n] /= 2;
		}
	}
	else if (isOddArr == false) {
		for (int n = 0;n < N;++n) {
			if (arr[n] % 2 == 1) {
				arr[n] -= 1;
				break;
			}
		}
	}
}

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	std::cin >> N;
	for (int n = 0;n < N;++n) {
		std::cin >> arr[n];
	}

	int result = 0;
	while (!isEnd()) {
		calculate();
		++result;
	}
	std::cout << result << "\n";
	return 0;
}