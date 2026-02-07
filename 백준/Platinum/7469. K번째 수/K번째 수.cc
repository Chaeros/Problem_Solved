#include <iostream>
#include <vector>
#include <algorithm>

int n, m;
const int MAX_N = 100000;
const int MAX_NODE = MAX_N * 40;

struct Node {
	int left;
	int right;
	int sum;
};

int root[MAX_N+1];
int nodeCount = 1;
Node segTree[MAX_NODE];

int getCloneNode(int pre) {
	segTree[nodeCount] = segTree[pre];
	return nodeCount++;
}

int updateTree(int pre, int start, int end, int index) {
	int current = getCloneNode(pre);
	++segTree[current].sum;

	if (start == end) return current;
	int mid = start + (end - start) / 2;
	if (index <= mid) {
		segTree[current].left = updateTree(segTree[pre].left, start, mid, index);
	}
	else {
		segTree[current].right = updateTree(segTree[pre].right, mid + 1, end, index);
	}
	return current;
}

int query(int left, int right, int start, int end, int order) {
	if (start == end) return start;

	int mid = start + (end - start) / 2;
	int count = segTree[segTree[right].left].sum - segTree[segTree[left].left].sum;

	if (order <= count) {
		return query(segTree[left].left, segTree[right].left, start, mid, order);
	}
	else {
		return query(segTree[left].right, segTree[right].right, mid + 1, end, order - count);
	}
}

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	std::cin >> n >> m;
	std::vector<int> a(n+1), v;
	for (int i = 1;i <= n;++i) {
		std::cin >> a[i];
		v.push_back(a[i]);
	}

	std::sort(v.begin(), v.end());
	v.erase(std::unique(v.begin(), v.end()), v.end());

	int M = v.size();
	std::vector<int> compression(n+1);
	for (int i = 1;i <= M;++i) {
		compression[i] = std::lower_bound(v.begin(), v.end(), a[i]) - v.begin()+1;
	}

	root[0] = 0;
	for (int i = 1;i <= n;++i) {
		root[i] = updateTree(root[i - 1], 1, M, compression[i]);
	}

	for (int i = 0;i < m;++i) {
		int left, right, order;
		std::cin >> left >> right >> order;
		if (left > right) {
			std::swap(left, right);
		}
		std::cout << v[query(root[left-1], root[right], 1, M, order) - 1] << "\n";
	}
	return 0;
}