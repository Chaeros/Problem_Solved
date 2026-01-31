#include <iostream>
#include <stack>
#include <string>

using namespace std;

struct Node {
	string command;
	char c;
	int val;
	int time;
	Node(string command, char c, int time)
		:command(command), c(c), time(time) {
	};
	Node(string command, int val, int time)
		:command(command), val(val), time(time) {
	};
};

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int N;
	std::cin >> N;

	string command;
	char c;
	int val;
	int time;
	stack<Node> stack;
	for (int n = 0;n < N;++n) {
		cin >> command;
		if (command == "type") {
			cin >> c >> time;
			stack.push(Node(command, c, time));
		}
		else if(command=="undo") {
			cin >> val >> time;
			stack.push(Node(command, val, time));
		}
	}

	string answer = "";
	while (!stack.empty()) {
		Node now = stack.top(); stack.pop();
		if (now.command == "type") {
			answer = now.c + answer;
		}
		else if (now.command == "undo") {
			while (!stack.empty()) {
				Node next = stack.top();
				if (next.time >= now.time - now.val) {
					stack.pop();
				}
				else {
					break;
				}
			}
		}
	}
	std::cout << answer << "\n";
	return 0;
}