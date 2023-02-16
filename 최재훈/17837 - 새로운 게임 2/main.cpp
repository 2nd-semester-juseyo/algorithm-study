#include <iostream>
#include <vector>
#include <unordered_map>
#include <algorithm>

using namespace std;

const int MAX = 13;
const int dir[5][2] = { {0, 0}, {0, 1}, {0, -1}, {-1, 0}, {1, 0} };
int n, k, ans;
int b[MAX][MAX];
bool finished;
vector<pair<int, int>> pos[MAX][MAX]; // {item, dir}

void reverse_direction(int& d) {
	if (d == 1 || d == 3) {
		d++;
	}
	else {
		d--;
	}
}

void simulate() {
	for (int i = 1; i <= k; i++) {
		int r, c;
		vector<pair<int, int>> tmp;
		pair<int, int> p;

		for (int j = 1; tmp.empty() && j <= n; j++) {
			for (int k = 1; tmp.empty() && k <= n; k++) {
				for (auto& l : pos[j][k]) {
					if (l.first == i) {
						tmp.push_back(l);
						r = j;
						c = k;
					}
					else if (!tmp.empty()) {
						tmp.push_back(l);
					}
				}

				if (!tmp.empty()) {
					for (int l = 0, sz = tmp.size(); l < sz; l++) {
						pos[j][k].pop_back();
					}
				}
			}
		}

		int& d = tmp[0].second;
		int nr = r + dir[d][0];
		int nc = c + dir[d][1];

		if (nr < 1 || nr > n || nc < 1 || nc > n || b[nr][nc] == 2) {
			reverse_direction(d);
			nr = r + dir[d][0];
			nc = c + dir[d][1];
		}

		if (nr < 1 || nr > n || nc < 1 || nc > n || b[nr][nc] == 2) {
			for (auto& j : tmp) {
				pos[r][c].push_back(j);
			}
		}
		else {
			vector<pair<int, int>>& v = pos[nr][nc];

			if (b[nr][nc] == 1) {
				reverse(tmp.begin(), tmp.end());
			}

			for (auto& j : tmp) {
				v.push_back(j);
			}

			if (pos[nr][nc].size() >= 4) {
				finished = true;
				break;
			}
		}

		if (pos[r][c].size() >= 4) {
			finished = true;
			break;
		}
	}
}

void func() {
	while (1) {
		if (ans > 1000 || finished) {
			break;
		}

		ans++;
		simulate();
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);
	
	cin >> n >> k;

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			cin >> b[i][j];
		}
	}

	for (int i = 1; i <= k; i++) {
		int r, c, d;
		cin >> r >> c >> d;
		pos[r][c].push_back({i, d});
	}

	func();
	cout << (ans != 1001 ? ans : -1) << '\n';
	return 0;
}