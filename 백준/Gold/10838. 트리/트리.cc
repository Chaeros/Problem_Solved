#include <iostream>
#include <algorithm>
#include <unordered_set>
#include <vector>

using namespace std;

int parent[100001];
int color[100001];

int getLca(int a, int b);
void paint(int a, int b, int c);
int getCount(int a, int b);
int n, k;

int main() {
	cin.tie(NULL); cout.tie(NULL);
	ios_base::sync_with_stdio(false);
	cin >> n >> k;

	int r, a, b, c;
	for (int i = 0; i < k; ++i) {
		cin >> r >> a >> b;

		if (r == 1) {
			cin >> c;
			paint(a, b, c);
		}
		else if (r == 2) {
			parent[a] = b;
		}
		else if (r == 3) {
			cout << getCount(a, b) << '\n';
		}
	}

	return 0;
}

int getLca(int a, int b) {
	int temp = 1000;
	vector<bool> visit(n, false);
	while (--temp) {
		visit[a] = true;
		if (a == 0) break;
		a = parent[a];
	}

	temp = 1000;
	while (--temp) {
		if (b == 0) break;
		if (visit[b]) return b;
		b = parent[b];
	}
	return 0;
}

void paint(int a, int b, int c) {
	int lca = getLca(a, b);
	while (a != lca) {
		color[a] = c;
		a = parent[a];
	}

	while (b != lca) {
		color[b] = c;
		b = parent[b];
	}
}

int getCount(int a, int b) {
	unordered_set<int> resultSet;
	int lca = getLca(a, b);
	while (a != lca) {
		resultSet.insert(color[a]);
		a = parent[a];
	}

	while (b != lca) {
		resultSet.insert(color[b]);
		b = parent[b];
	}
	return resultSet.size();
}