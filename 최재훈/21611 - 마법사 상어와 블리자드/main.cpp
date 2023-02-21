#include <bits/stdc++.h>

using namespace std;

const int MAX = 50;
const int dir[4][2] = { {0, -1}, {1, 0}, {0, 1}, {-1, 0} };
const int sdir[5][2] = { {0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
int n, m, ans;
int b[MAX][MAX], mapper[MAX][MAX];
pair<int, int> shark;
list<int> ls;

void rotate(int& d) {
	if (++d == 4) {
		d = 0;
	}
}

void simulate() {
	int d, s;
	cin >> d >> s;

	unordered_set<int> us;

	for (int i = 1; i <= s; i++) {
		int nr = shark.first + sdir[d][0] * i;
		int nc = shark.second + sdir[d][1] * i;
		us.insert(mapper[nr][nc]);
	}

	vector<list<int>::iterator> pos;

	int idx = 0;
	for (list<int>::iterator it = ls.begin(); it != ls.end(); it++, idx++) {
		if (us.find(idx) != us.end()) {
			pos.push_back(it);
		}
	}

	for (auto& i : pos) {
		ls.erase(i);
	}

	while (1) {
		bool flag = false;

		vector<list<int>::iterator> v, st;

		for (list<int>::iterator it = ls.begin(); it != ls.end(); it++) {
			if (!*it) {
				break;
			}

			if (st.empty()) {
				st.push_back(it);
				continue;
			}

			if (*st.back() == *it) {
				st.push_back(it);
			}
			else {
				if (st.size() >= 4) {
					for (auto& i : st) {
						v.push_back(i);
					}

					flag = true;
				}

				st.clear();
				st.push_back(it);
			}
		}

		if (st.size() >= 4) {
			for (auto& i : st) {
				v.push_back(i);
			}

			flag = true;
		}

		for (auto& i : v) {
			ans += *i;
			ls.erase(i);
		}

		if (!flag) {
			break;
		}
	}

	vector<int> v, st;

	for (list<int>::iterator it = ls.begin(); it != ls.end(); it++) {
		if (!*it) {
			break;
		}

		if (st.empty()) {
			st.push_back(*it);
			continue;
		}

		if (st.back() == *it) {
			st.push_back(*it);
		}
		else {
			v.push_back(st.size());
			v.push_back(st.back());
			st.clear();
			st.push_back(*it);
		}
	}

	if (!st.empty()) {
		v.push_back(st.size());
		v.push_back(st.back());
	}

	ls.clear();
	for (auto& i : v) {
		ls.push_back(i);
	}

	while (ls.size() > n * n - 2) {
		ls.pop_back();
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	cin >> n >> m;

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			cin >> b[i][j];
		}
	}

	int r = shark.first = (n + 1) / 2;
	int c = shark.second = (n + 1) / 2;
	int cnt1 = 0, cnt2 = 0, len = 1, d = 0, idx = 0;;

	while (1) {
		r += dir[d][0];
		c += dir[d][1];

		mapper[r][c] = idx++;

		if (++cnt1 == len) {
			cnt1 = 0;
			cnt2++;
			rotate(d);
		}

		if (cnt2 == 2) {
			cnt2 = 0;
			len++;
		}

		if (r == 1 && c == 1) {
			break;
		}

		ls.push_back(b[r][c]);
	}

	while (m--) {
		simulate();
	}

	cout << ans << '\n';
	return 0;
}