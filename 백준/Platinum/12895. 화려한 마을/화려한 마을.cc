#include <iostream>
#include <vector>

int N, T, Q;
int segTree[400001];
int lazyTree[400001];

void makeTree(int node, int start, int end) {
	if (start == end) {
		segTree[node] = 1;
		return;
	}
	int mid = (start + end) / 2;
	makeTree(node * 2, start, mid);
	makeTree(node * 2 + 1, mid + 1, end);
	segTree[node] = segTree[node * 2] | segTree[node * 2 + 1];
}

void lazyUpdate(int node, int start, int end) {
	if (lazyTree[node] != 0) {
		segTree[node] = 1 << (lazyTree[node] - 1);
		if (start != end) {
			lazyTree[node * 2] = lazyTree[node];
			lazyTree[node * 2 + 1] = lazyTree[node];
		}
		lazyTree[node] = 0;
	}
}

void updateTree(int node, int start, int end, int left, int right, int val) {
	lazyUpdate(node, start, end);
	if (right < start || end < left) return;
	if (left <= start && end <= right) {
		segTree[node] = 1 << (val - 1);
		lazyTree[node] = val;
		return;
	}
	int mid = (start + end) / 2;
	updateTree(node * 2, start, mid, left, right, val);
	updateTree(node * 2 + 1, mid + 1, end, left, right, val);
	segTree[node] = segTree[node * 2] | segTree[node * 2 + 1];
}

int getResult(int node, int start, int end, int left, int right) {
	lazyUpdate(node, start, end);
	if (right < start || end < left) return 0;
	if (left <= start && end <= right) {

		return segTree[node];
	}
	int mid = (start + end) / 2;
	return getResult(node * 2, start, mid, left, right) | getResult(node * 2 + 1, mid + 1, end, left, right);
}

int getCount(int bit) {
	int result = 0;
	for (int i = 0;i < 32;++i) {
		int val = 1 << i;
		if ((bit & val) == val) ++result;
	}
	return result;
}

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	std::cin >> N >> T >> Q;
	makeTree(1, 1, N);
	
	for (int q = 0;q < Q;++q) {
		char command;
		int left, right, val;
		std::cin >> command;

		if (command == 'C') {
			std::cin >> left >> right >> val;
			if (left > right) std::swap(left, right);
			updateTree(1, 1, N, left, right, val);
		}
		else if (command == 'Q') {
			std::cin >> left >> right;
			if (left > right) std::swap(left, right);
			int mask = getResult(1, 1, N, left, right);
			std::cout << getCount(mask) << "\n";
		}
	}

	return 0;
}