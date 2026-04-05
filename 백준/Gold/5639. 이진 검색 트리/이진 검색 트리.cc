#include <iostream>
#include <vector>

std::vector<int> preorder;

void getResult(int start, int end) {
	if (start > end) return;

	int root = preorder[start];
	int devide = end + 1;

	for (int i = start + 1;i <= end;++i) {
		if (root < preorder[i]) {
			devide = i;
			break;
		}
	}

	getResult(start + 1, devide - 1);
	getResult(devide, end);

	std::cout << root << "\n";
}

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int x;
	while (std::cin >> x) {
		preorder.push_back(x);
	}

	getResult(0, preorder.size()-1);
	return 0;
}