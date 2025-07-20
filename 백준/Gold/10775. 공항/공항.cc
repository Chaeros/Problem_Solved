#include <iostream>
#include <vector>

using namespace std;

vector<int> parent;

int find(int x) {
    if (parent[x] == x) return x;
    return parent[x] = find(parent[x]);
}

void unionSet(int a, int b) {
    a = find(a);
    b = find(b);
    parent[a] = b;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int G, P;
    cin >> G >> P;

    parent.resize(G + 1);
    for (int i = 1; i <= G; ++i) {
        parent[i] = i;
    }

    int result = 0;
    for (int i = 0; i < P; ++i) {
        int plane;
        cin >> plane;

        int availableGate = find(plane);
        if (availableGate == 0) {
            break;
        }

        unionSet(availableGate, availableGate - 1);
        result++;
    }

    cout << result << "\n";
    return 0;
}