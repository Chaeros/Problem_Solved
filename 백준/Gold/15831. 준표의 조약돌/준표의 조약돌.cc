#include <iostream>
#include <string>
#include <vector>
#include <cmath>

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int N, B, W;
	std::cin >> N >> B >> W;

	std::string str;
	std::cin >> str;

	int wCount = 0;
	int bCount = 0;
	int result = 0;
	int left = 0;
	for (int right = 0;right < N;++right) {
		if (str[right] == 'W') ++wCount;
		else if (str[right] == 'B') ++bCount;

		while (bCount > B) {
			if (str[left] == 'W') --wCount;
			else if (str[left] == 'B') --bCount;
			++left;
		}
		if (wCount >= W) {
			result = std::max(result, right - left + 1);
		}
	}

	std::cout << result << std::endl;
	return 0;
}