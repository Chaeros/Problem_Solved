#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

typedef pair<int, int> pi;

bool compare(pi a, pi b) {
	if (a.first == b.first) return a.second < b.second;
	return a.first > b.first;
}

int main() {
	int A, B;
	cin >> A;
	vector<pi> sequence1, sequence2;
	int value;
	for (int a = 0; a < A; ++a) {
		cin >> value;
		sequence1.push_back({value,a});
	}
	cin >> B;
	for (int b = 0; b < B; ++b) {
		cin >> value;
		sequence2.push_back({value,b});
	}
	sort(sequence1.begin(), sequence1.end(), compare);
	sort(sequence2.begin(), sequence2.end(), compare);

	vector<int> result;
	int indexA=0, indexB=0, maxA=0, maxB=0;
	while (indexA < A && indexB < B) {
		if (sequence1[indexA].first == sequence2[indexB].first) {
			if (maxA > sequence1[indexA].second) ++indexA;
			else if (maxB > sequence2[indexB].second) ++indexB;
			else {
				maxA = sequence1[indexA].second;
				maxB = sequence2[indexB].second;
				result.push_back(sequence1[indexA].first);
				++indexA;
				++indexB;
			}
		}
		else if (sequence1[indexA].first > sequence2[indexB].first) ++indexA;
		else ++indexB;
	}

	cout << result.size() << endl;
	for (int x : result) {
		cout << x << " ";
	}
	return 0;
}