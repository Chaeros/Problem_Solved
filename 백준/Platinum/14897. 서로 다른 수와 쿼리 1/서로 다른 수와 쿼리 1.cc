#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>

int A[1000001];
int count[1000001];
int answer[1000001];
int rootN;
class Node {
public:
	int left;
	int right;
	int index;
	Node(int left, int right, int index)
		:left(left), right(right), index(index) {}
	bool operator<(const Node &other) {
		if (left / rootN != other.left / rootN) return left / rootN < other.left / rootN;
		return right < other.right;
	}
};
std::vector<Node> list;
int maxVal=0;

void add(int x) {
	int curCount = count[x];
	if (curCount == 0) ++maxVal;
	++count[x];
}

void remove(int x) {
	int curCount = count[x];
	if (curCount == 1) --maxVal;
	--count[x];
}

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int N;
	std::cin >> N;
	rootN = std::sqrt(N);
	std::vector<int> temp;
	for (int n = 1;n <= N;++n) {
		std::cin >> A[n];
		temp.push_back(A[n]);
	}

	std::sort(temp.begin(), temp.end());
	temp.erase(std::unique(temp.begin(),temp.end()),temp.end());

	for (int i = 1;i <= N;++i) {
		A[i] = std::lower_bound(temp.begin(), temp.end(), A[i]) - temp.begin();
	}

	int Q;
	std::cin >> Q;
	for (int q = 0;q < Q;++q) {
		int left, right;
		std::cin >> left >> right;
		list.push_back(Node(left,right,q));
	}
	std::sort(list.begin(), list.end());

	int curLeft = 1;
	int curRight = 0;

	for (Node query : list) {
		while (curLeft < query.left) remove(A[curLeft++]);
		while (query.left < curLeft) add(A[--curLeft]);
		while (curRight < query.right) add(A[++curRight]);
		while (query.right < curRight) remove(A[curRight--]);
		answer[query.index] = maxVal;
	}

	for (int i = 0;i < Q;++i) {
		std::cout << answer[i] << "\n";
	}

	return 0;
}