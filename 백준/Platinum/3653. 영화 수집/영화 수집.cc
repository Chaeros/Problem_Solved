#include <iostream>
#include <vector>
#include <cstring>

int N, M;
int segTree[800001];
int position[200001];

void makeTree(int node, int start, int end) {
	if (start == end) {
		if (start <= N) {
			segTree[node] = 1;
		}
		return;
	}
	int mid = (start + end) / 2;
	makeTree(node * 2, start, mid);
	makeTree(node * 2 + 1, mid + 1, end);
	segTree[node] = segTree[node * 2] + segTree[node * 2 + 1];
}

void updateTree(int node, int start, int end, int index, int val) {
	if (start == index && end == index) {
		segTree[node] = val;
		return;
	}
	int mid = (start + end) / 2;
	if (index <= mid) {
		updateTree(node * 2, start, mid, index, val);
	}
	else {
		updateTree(node * 2 + 1, mid + 1, end, index, val);
	}
	segTree[node] = segTree[node * 2] + segTree[node * 2 + 1];
}

int getValue(int node, int start, int end, int left, int right) {
	if (right < start || end < left) return 0;
	if (left <= start && end <= right) {
		return segTree[node];
	}
	int mid = (start + end) / 2;
	int leftVal = getValue(node * 2, start, mid, left, right);
	int rightVal = getValue(node * 2 + 1, mid + 1, end, left, right);
	return leftVal + rightVal;
}

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);
	int T;
	std::cin >> T;
	for (int t = 0;t < T;++t) {
		std::cin >> N >> M;
		std::memset(segTree, 0, sizeof(segTree));
		std::memset(position, 0, sizeof(position));
		makeTree(1, 1, N + M);

		for (int i = 1;i <= N;++i) {
			position[i] = N - i + 1;
		}

		std::string result;
		for (int m = 1;m <= M;++m) {
			int x;
			std::cin >> x;
			std::cout << getValue(1, 1, N + M, position[x] + 1, N + M);
			
			if (m != M) {
				std::cout << " ";
			}
			updateTree(1, 1, N + M, position[x], 0);
			updateTree(1, 1, N + M, N + m, 1);
			position[x] = N + m;
		}
		std::cout << "\n";
	}
	return 0;
}