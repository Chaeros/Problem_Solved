#include <iostream>
#include <cmath>

int getResult(int x) {
	int result = 0;

	int divide = 5;
	while (x >= divide) {
		result += x / divide;
		divide *= 5;
	}
	return result;
}

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int T;
	std::cin >> T;
	for (int t = 0;t < T;++t) {
		int x;
		std::cin >> x;

		std::cout<< getResult(x) <<"\n";
	}

	return 0;
}