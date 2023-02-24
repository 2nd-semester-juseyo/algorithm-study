#include <bits/stdc++.h>

using namespace std;

const int MAX = 51;
const int dir[4][2] = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };
int n, m, t;
deque<int> dq[MAX];
bool visited[MAX][MAX];

void rotate_clockwise(deque<int>& dq, int cnt) {
	while (cnt--) {
		dq.push_front(dq.back());
		dq.pop_back();
	}
}

void rotate_counter_clockwise(deque<int>& dq, int cnt) {
	while (cnt--) {
		dq.push_back(dq.front());
		dq.pop_front();
	}
}

bool bfs(int sr, int sc, int num) {
	queue<pair<int, int>> q;
	vector<pair<int, int>> adj;
	q.push({ sr, sc });
	visited[sr][sc] = true;

	while (!q.empty()) {
		int r = q.front().first;
		int c = q.front().second;
		q.pop();
		adj.push_back({ r, c });

		for (int i = 0; i < 4; i++) {
			int nr = r + dir[i][0];
			int nc = c + dir[i][1];

			if (nc >= m) {
				nc = 0;
			}

			if (nc < 0) {
				nc = m - 1;
			}

			if (nr >= 1 && nr <= n && !visited[nr][nc] && dq[nr][nc] == num) {
				visited[nr][nc] = true;
				q.push({ nr, nc });
			}
		}
	}

	if (adj.size() >= 2) {
		for (auto& i : adj) {
			dq[i.first][i.second] = 0;
		}
	}

	return adj.size() >= 2;
}

void simulate() {
	memset(visited, false, sizeof(visited));

	int x, d, k;
	cin >> x >> d >> k;

	for (int i = x; i <= n; i += x) {
		if (!d) {
			rotate_clockwise(dq[i], k);
		} else {
			rotate_counter_clockwise(dq[i], k);
		}
	}

	bool flag = false;
	for (int i = 1; i <= n; i++) {
		for (int j = 0; j < m; j++) {
			if (!visited[i][j] && dq[i][j]) {
				if (bfs(i, j, dq[i][j])) {
					flag = true;
				}
			}
		}
	}

	if (flag) {
		return;
	}

	double sum = 0;
	int cnt = 0;

	for (int i = 1; i <= n; i++) {
		for (auto& j : dq[i]) {
			if (j) {
				sum += j;
				cnt++;
			}
		}
	}

	double avg = sum / (double)cnt;

	for (int i = 1; i <= n; i++) {
		for (auto& j : dq[i]) {
			if (j) {
				if ((double)j > avg) {
					j--;
				}
				else if ((double)j < avg) {
					j++;
				}
			}
		}
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	cin >> n >> m >> t;

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= m; j++) {
			int num;
			cin >> num;
			dq[i].push_back(num);
		}
	}

	while (t--) {
		simulate();
	}

	int ans = 0;

	for (int i = 1; i <= n; i++) {
		for (auto& j : dq[i]) {
			if (j) {
				ans += j;
			}
		}
	}

	cout << ans << '\n';
	return 0;
}
