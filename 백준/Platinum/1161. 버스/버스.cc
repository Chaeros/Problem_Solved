#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>

int K, N, C;
class Node {
public:
	int start;
	int end;
	int count;
	Node(int start, int end, int count)
		:start(start), end(end), count(count) {}
	bool operator<(const Node& other) {
		return this->end < other.end;
	}
};
int tree[200001];
int lazyTree[200001];

void makeTree(int node, int start, int end) {
	if (start == end) {
		tree[node] = 0;
		return;
	}
	int mid = (start + end) / 2;
	makeTree(node * 2, start, mid);
	makeTree(node * 2 + 1, mid + 1, end);
	tree[node] = std::max(tree[node * 2], tree[node * 2 + 1]);
}

void lazyUpdate(int node, int start, int end) {
	if (lazyTree[node] != 0) {
		tree[node * 2] += lazyTree[node];
		tree[node * 2 + 1] += lazyTree[node];
		lazyTree[node * 2] += lazyTree[node];
		lazyTree[node * 2 + 1] += lazyTree[node];
		lazyTree[node] = 0;
	}
}

void updateTree(int node, int start, int end, int left, int right, int val) {
	if (right < start || end < left) return;
	if (left <= start && end <= right) {
		tree[node] += val;
		lazyTree[node] += val;
		return;
	}
	lazyUpdate(node, start, end);

	int mid = (start + end) / 2;
	updateTree(node * 2, start, mid, left, right, val);
	updateTree(node * 2 + 1, mid + 1, end, left, right, val);
	tree[node] = std::max(tree[node * 2], tree[node * 2 + 1]);
}

int getMaxData(int node, int start, int end, int left, int right) {
	if (right < start || end < left) return 0;
	if (left <= start && end <= right) {
		return tree[node];
	}
	lazyUpdate(node, start, end);

	int mid = (start + end) / 2;
	return std::max(getMaxData(node * 2, start, mid, left, right), getMaxData(node * 2 + 1, mid + 1, end, left, right));
}

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	std::cin >> K >> N >> C;

	std::vector<Node> list;
	for (int k = 0;k < K;++k) {
		int start, end, count;
		std::cin >> start >> end >> count;
		list.push_back(Node(start, end, count));
	}
	std::sort(list.begin(), list.end());

	int result = 0;
	for (Node node : list) {
		int maxCount = getMaxData(1, 1, N, node.start, node.end-1);
		int minVal = std::min(node.count, C - maxCount);

		if (minVal > 0) {
			updateTree(1, 1, N, node.start, node.end-1, minVal);
			result += minVal;
		}
	}
	std::cout << result << "\n";

	return 0;
}