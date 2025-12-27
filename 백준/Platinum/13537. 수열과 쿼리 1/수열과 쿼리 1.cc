#include <iostream>
#include <vector>
#include <algorithm>

int A[100001];
std::vector<int> segTree[400001];

void makeTree(int node, int start, int end) {
	if (start == end) {
		segTree[node].push_back(A[start]);
		return;
	}
	int mid = (start + end)/2;
	makeTree(node * 2, start, mid);
	makeTree(node * 2 + 1, mid + 1, end);
	std::merge(segTree[node * 2].begin(), segTree[node * 2].end(), segTree[node * 2 + 1].begin(), segTree[node * 2 + 1].end(), std::back_inserter(segTree[node]));
}

int query(int node, int start, int end, int left, int right, int val) {
	if (right < start || end < left) return 0;
	if (left <= start && end <= right) {
		return segTree[node].end() - std::upper_bound(segTree[node].begin(), segTree[node].end(), val);
	}
	int mid = (start + end) / 2;
	int leftVal = query(node * 2, start, mid, left, right, val);
	int rightVal = query(node * 2 + 1, mid + 1, end, left, right, val);
	return leftVal + rightVal;
}

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int N;
	std::cin >> N;
	for (int n = 1;n <= N;++n) {
		std::cin >> A[n];
	}
	makeTree(1, 1, N);

	int M;
	std::cin >> M;
	for (int m = 0;m < M;++m) {
		int left, right, val;
		std::cin >> left >> right >> val;
		std::cout << query(1, 1, N, left, right, val) << "\n";
	}

	return 0;
}