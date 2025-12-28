#include <iostream>
#include <vector>
#include <algorithm>
#include <cstring>

int N, M;
int A[1000001];

struct Node {
	int left;
	int right;
	int index;
	Node(int left, int right, int index) :left(left), right(right), index(index) {};
};

int tree[100001];
std::vector<Node> querys;
int lastPosition[1000001];

void updateTree(int index, int val){
	while (index <= N) {
		tree[index] += val;
		index += index & (-index);
	}
}

int query(int index) {
	int result = 0;
	while (index > 0) {
		result += tree[index];
		index -= index & (-index);
	}
	return result;
}

int getResult(int left, int right) {
	return query(right) - query(left - 1);
}

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	std::cin >> N;
	for (int n = 1;n <= N;++n) {
		std::cin >> A[n];
	}

	std::cin >> M;
	for (int m = 1; m <= M;++m) {
		int left, right;
		std::cin >> left >> right;
		querys.push_back(Node(left, right, m));
	}

	std::sort(querys.begin(), querys.end(),
		[](const Node& a, const Node& b) {
			return a.right < b.right;
		});
	
	std::memset(lastPosition, 0, sizeof(lastPosition));

	std::vector<int> result(M + 1, 0);
	int queryIndex = 0;
	for (int i = 1;i <= N;++i) {
		int val = A[i];

		if (lastPosition[val] != 0) {
			updateTree(lastPosition[val], -1);
		}

		updateTree(i, 1);
		lastPosition[val] = i;

		while (queryIndex<M && querys[queryIndex].right==i) {
			result[querys[queryIndex].index] = getResult(querys[queryIndex].left,querys[queryIndex].right);
			++queryIndex;
		}
	}

	for (int i = 1;i <= M;++i) {
		std::cout << result[i] << "\n";
	}

	return 0;
}