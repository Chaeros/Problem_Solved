#include <iostream>

long long getGCD(long long a, long long b) {
	while (b != 0) {
		a = a % b;
		std::swap(a, b);
	}
	return a;
}

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int x, y;
	std::cin >> x >> y;
	std::cout << x + y - getGCD(x, y) << "\n";

	return 0;
}