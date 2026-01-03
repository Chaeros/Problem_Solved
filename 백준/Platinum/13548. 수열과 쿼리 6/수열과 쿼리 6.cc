#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>

int A[100001];
int freq[100001];
int count[100001];
int answer[100001];
int rootA;
int maxVal;

class Node {
public:
	int left;
	int right;
	int index;
	bool operator<(Node &o) {
		if (left / rootA != o.left / rootA) {
			return left / rootA < o.left / rootA;
		}
		return right < o.right;
	}
	Node(int left, int right, int index)
	:left(left),right(right),index(index){}
};

void add(int x) {
	int curCount = count[x];
	if (curCount > 0) --freq[curCount];
	++count[x];
	++freq[curCount+1];
	maxVal = std::max(maxVal, curCount+1);
}

void remove(int x) {
	int curCount = count[x];
	--count[x];
	--freq[curCount];
	if (curCount > 1) ++freq[curCount-1];
	if (freq[maxVal] == 0) {
		--maxVal;
	}
}

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int N;
	std::cin >> N;
	for (int i = 1;i <= N;++i) {
		std::cin >> A[i];
	}
	rootA = std::sqrt(N);
	
	int M;
	std::cin >> M;
	std::vector<Node> queries;
	for (int m = 0;m < M;++m) {
		int start, end;
		std::cin >> start >> end;
		queries.push_back(Node(start, end, m));
	}
	std::sort(queries.begin(),queries.end());

	int left = 1;
	int right = 0;

	for (Node node : queries) {
		while (left < node.left) remove(A[left++]);
		while (node.left < left) add(A[--left]);
		while (right < node.right) add(A[++right]);
		while (node.right < right) remove(A[right--]);
		
		answer[node.index] = maxVal;
	}
	
	for (int m = 0; m < M; ++m) {
		std::cout << answer[m] << "\n";
	}
	return 0;
}