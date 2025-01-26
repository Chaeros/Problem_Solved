#include <iostream>
#include <algorithm>
#include <vector>
#include <sstream>

using namespace std;

const int MAX = 100001;
const int MAXLOG = 32;
int cityCount;
int queryCount;
int depth[MAX];
int ac[MAX][MAXLOG];
int length[MAX];
vector<pair<int, int>> graph[MAX];
bool visit[MAX];

void makeTree(int current, int dist) {
	depth[current] = dist;
	visit[current] = true;
	for (int i = 0; i < graph[current].size(); ++i) {
		pair<int, int> now = graph[current][i];
		int objective = now.first;
		int leng = now.second;
		if (visit[objective]) continue;
		visit[objective] = true;
		ac[objective][0] = current;
		length[objective] = length[current] + leng;
		makeTree(objective, dist + 1);
	}
}

void memorization() {
	for (int i = 1; i < MAXLOG; ++i) {
		for (int j = 1; j < MAX; ++j) {
			int temp = ac[j][i - 1];
			ac[j][i] = ac[temp][i - 1];
		}
	}
}

long long getResultByLCA(int a, int b) {
	long long result = 0;

	if (depth[a] < depth[b]) {
		int temp = a;
		a = b;
		b = temp;
	}
	int originA = a;
	for (int i = MAXLOG-1; i >= 0; --i) {
		if (ac[a][i] != 0 && depth[ac[a][i]] >= depth[b]) {
			result += length[a] - length[ac[a][i]];
			a = ac[a][i];
		}
	}
	if (a == b) return result;

	for (int i = MAXLOG - 1; i >= 0; --i) {
		if (ac[a][i]!=0 && ac[a][i] != ac[b][i]) {
			result += length[a] - length[ac[a][i]];
			result += length[b] - length[ac[b][i]];
			a = ac[a][i];
			b = ac[b][i];
		}
	}
	result += length[a] + length[b] - 2 * length[ac[a][0]];
	return result;
}

int main() {
	scanf("%d %d", &cityCount, &queryCount);
	for (int n = 0; n < cityCount-1; ++n) {
		int start, end, dist;
		scanf("%d %d %d", &start, &end, &dist);
		graph[start].push_back({ end,dist });
		graph[end].push_back({ start,dist });
	}

	depth[1] = 1;
	length[1] = 0;
	makeTree(1, 0);
	memorization();

	ostringstream output;

	for (int n = 0; n < queryCount; ++n) {
		int start, end;
		scanf("%d %d", &start, &end);
		output << getResultByLCA(start, end) << "\n";
	}
	cout << output.str();
	return 0;
}