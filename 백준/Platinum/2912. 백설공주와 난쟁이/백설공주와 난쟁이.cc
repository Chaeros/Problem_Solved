#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>

int N, C, M;
int A[300001];
int count[10001];
bool isPretty;
int curSize;
int biggestNumber;
int rootN;
int answer[10001];

class Node {
public:
	int left;
	int right;
	int index;
	Node(int left, int right, int index)
		:left(left), right(right), index(index) {
	}
	bool operator<(const Node& other) {
		if (left / rootN != other.left / rootN) return left / rootN < other.left / rootN;
		return right < other.right;
	}
};

void add(int x) {
	++count[x];
	++curSize;
	if (count[biggestNumber] <= curSize/2) {
		isPretty = false;
	}
	if (curSize / 2 < count[x]) {
		isPretty = true;
		biggestNumber = x;
	}
}

void remove(int x) {
	--count[x];
	--curSize;
	if (count[biggestNumber] <= curSize/2) {
		isPretty = false;
	}
}

void recompute() {
	isPretty = false;
	biggestNumber = 0;
	for (int c = 1; c <= C; ++c) {
		if (count[c] > curSize / 2) {
			isPretty = true;
			biggestNumber = c;
			return;
		}
	}
}

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	std::cin >> N >> C;
	rootN = std::sqrt(N);

	for (int n = 1;n <= N;++n) {
		std::cin >> A[n];
	}

	std::cin >> M;

	std::vector<Node> queries;
	for (int m = 0;m < M;++m) {
		int left, right;
		std::cin >> left >> right;
		queries.push_back(Node(left, right, m));
	}
	std::sort(queries.begin(), queries.end());

	int leftVal = 1; int rightVal = 0;
	for (Node query : queries) {
		while (leftVal < query.left) remove(A[leftVal++]);
		while (query.left < leftVal) add(A[--leftVal]);
		while (rightVal < query.right) add(A[++rightVal]);
		while (query.right < rightVal) remove(A[rightVal--]);

		if (!isPretty) recompute();
		if(isPretty) answer[query.index] = biggestNumber;
	}

	for (int m = 0;m < M;++m) {
		if (answer[m] > 0) {
			std::cout << "yes " << answer[m] << "\n";
		}
		else {
			std::cout << "no\n";
		}
	}

	return 0;
}