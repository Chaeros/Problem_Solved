#include <iostream>
#include <map>
#include <algorithm>
#include <vector>
#define _CRT_SECURE_NO_WARNINGS

using namespace std;

int N, M;
int* tree;
map<int,int> hashmap;
typedef pair<int, int> pi;
vector<pi> lineList;
vector<int> edges;
int nodeCount = 0;

bool compare(pi p1, pi p2) {
	if (p1.first == p2.first) return p1.second < p2.second;
	return p1.first < p2.first;
}

bool compareEdges(int a, int b) {
	return a < b;
}

void updateTree(int node, int start, int end, int index) {
	if (start == index && end == index) {
		++tree[node];
		return;
	}
	if (index < start || end < index) {
		return;
	}
	int mid = (start + end) / 2;
	updateTree(node * 2, start, mid, index);
	updateTree(node * 2 + 1, mid + 1, end, index);
	tree[node] = tree[node * 2] + tree[node * 2 + 1];
}

int findTree(int node, int start, int end, int leftNode, int rightNode) {
	if (leftNode <= start && end <= rightNode) {
		return tree[node];
	}
	if (rightNode < start || end < leftNode) {
		return 0;
	}
	int mid = (start + end) / 2;
	int left = findTree(node * 2, start, mid, leftNode, rightNode);
	int right = findTree(node * 2 + 1, mid + 1, end, leftNode, rightNode);
	return left + right;
}

int main() {
	scanf("%d %d", &N, &M);
	
	tree = new int[4 * N]();
	int start;
	int end;
	for (int m = 0; m < M; ++m) {
		scanf("%d %d", &start, &end);
		lineList.push_back({ start,end });
		edges.push_back(start);
		edges.push_back(end);
	}
	sort(lineList.begin(), lineList.end(), compare);
	sort(edges.begin(), edges.end(), compareEdges);
	for (int m = 0; m < edges.size(); ++m) {
		int x = edges[m];
		if (hashmap.find(x) == hashmap.end()) {
			hashmap.insert(pi(x, ++nodeCount));
		}
	}


	long long result = 0;
	for (int i = 0; i < lineList.size(); ++i) {
		pi now = lineList[i];
		int mapIndex = now.second;
		result += findTree(1,1,N,mapIndex+1, N);
		updateTree(1,1,N, mapIndex);
	}
	cout << result << endl;

	delete[] tree;
	return 0;
}