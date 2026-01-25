#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <cstring>

int N, Q;
int A[100001];
int rootN;
int count[200001];
int maxCount;
int freq[100001];
int answer[100001];

struct Node {
	int left;
	int right;
	int index;
	Node(int left, int right, int index)
		:left(left), right(right), index(index) {
	}
	bool operator<(const Node& other) const {
		if (this->left / rootN != other.left / rootN) {
			return this->left < other.left;
		}
		return this->right < other.right;
	}
};

void add(int x) {
	if (freq[count[x]] > 0) --freq[count[x]];
	++count[x];
	++freq[count[x]];
	maxCount = std::max(maxCount, count[x]);
}

void remove(int x) {
	if (count[x] == maxCount && freq[count[x]]==1) {
		--maxCount;
	}
	++freq[count[x] - 1];
	--freq[count[x]];
	--count[x];
}

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	while (std::cin >> N && N != 0) {
		std::cin >> Q;
		rootN = std::sqrt(N);
		for (int n = 1;n <= N;++n) {
			int x;
			std::cin >> x;
			A[n] = x + 100001;
		}

		std::memset(count, 0, sizeof(count));
		std::memset(freq, 0, sizeof(freq));
		std::memset(answer, 0, sizeof(answer));
		maxCount = 0;

		std::vector<Node> queries;
		int left, right;
		for (int q = 0;q < Q;++q) {
			std::cin >> left >> right;
			queries.push_back(Node(left, right, q));
		}
		std::sort(queries.begin(), queries.end());

		int leftIndex = 1;
		int rightIndex = 0;

		for (Node query : queries) {
			while (leftIndex < query.left) remove(A[leftIndex++]);
			while (query.left < leftIndex) add(A[--leftIndex]);
			while (rightIndex < query.right) add(A[++rightIndex]);
			while (query.right < rightIndex) remove(A[rightIndex--]);

			answer[query.index] = maxCount;
		}

		for (int q = 0;q < Q;++q) {
			std::cout << answer[q] << "\n";
		}
	}

	return 0;
}