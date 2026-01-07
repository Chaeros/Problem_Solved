#include <iostream>
#include <vector>
#include <cmath>

int N, M;
std::vector<int> list;
int maxVal = 0;

bool isPass(int value) {
	int size = list.size();
	int count = 1;
	int current = value;
	for (int i = 0;i < size;++i) {
		if (value < list[i]) return false;
		if (current < list[i]) {
			current = value;
			++count;
		}
		current -= list[i];
	}
	if (count <= M) return true;
	return false;
}

int binarySearch() {
	int left = maxVal;
	int right = 100000*10000;
	int result = right + 1;

	while (left <= right) {
		int mid = left + (right - left) / 2;
		if (isPass(mid)) {
			result = mid;
			right = mid - 1;
		}
		else {
			left = mid + 1;
		}
	}
	return result;
}

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);
	std::cin >> N >> M;

	for (int n = 0;n < N;++n) {
		int x;
		std::cin >> x;
		list.push_back(x);
		maxVal = std::max(maxVal, x);
	}

	std::cout << binarySearch() << "\n";
	return 0;
}