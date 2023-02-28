#include <bits/stdc++.h>

using namespace std;

const int MAX = 21, INF = 1e9;
const int dir[4][2] = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };
int n, m, fuel;
pair<int, int> pos;
int b[MAX][MAX], dist[MAX][MAX];
bool visited[MAX][MAX];

class Path {
public:
	pair<int, int> start;
	pair<int, int> end;
	bool visited;
};

class Data {
public:
	pair<int, int> pos;
	int dist;
	int index;

	bool operator<(const Data& data);
};

vector<Path> path;

bool Data::operator<(const Data& data) {
	if (dist == data.dist) {
		if (pos.first == data.pos.first) {
			return pos.second < data.pos.second;
		}
		return pos.first < data.pos.first;
	}
	return dist < data.dist;
}

pair<int, int> bfs1(pair<int, int> from) {
	memset(visited, false, sizeof(visited));

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			dist[i][j] = INF;
		}
	}

	queue<pair<int, int>> q;
	q.push(from);
	visited[from.first][from.second] = true;
	dist[from.first][from.second] = 0;

	while (!q.empty()) {
		auto now = q.front();
		q.pop();

		for (int i = 0; i < 4; i++) {
			pair<int, int> next = { now.first + dir[i][0], now.second + dir[i][1] };

			if (next.first >= 1 && next.first <= n && next.second >= 1 && next.second <= n
				&& !visited[next.first][next.second] && !b[next.first][next.second]) {
				q.push(next);
				visited[next.first][next.second] = true;
				dist[next.first][next.second] = dist[now.first][now.second] + 1;
			}
		}
	}

	vector<Data> v;

	for (int i = 0; i < path.size(); i++) {
		if (!path[i].visited && dist[path[i].start.first][path[i].start.second] != INF) {
			v.push_back({ path[i].start, dist[path[i].start.first][path[i].start.second], i });
		}
	}

	if (v.empty()) {
		return { -1, -1 };
	}

	sort(v.begin(), v.end());
	return { v[0].index, v[0].dist };
}

int bfs2(pair<int, int> from, pair<int, int> to) {
	memset(visited, false, sizeof(visited));
	
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			dist[i][j] = INF;
		}
	}

	queue<pair<int, int>> q;
	q.push(from);
	visited[from.first][from.second] = true;
	dist[from.first][from.second] = 0;

	while (!q.empty()) {
		auto now = q.front();
		q.pop();

		if (now == to) {
			break;
		}

		for (int i = 0; i < 4; i++) {
			pair<int, int> next = { now.first + dir[i][0], now.second + dir[i][1] };

			if (next.first >= 1 && next.first <= n && next.second >= 1 && next.second <= n
				&& !visited[next.first][next.second] && !b[next.first][next.second]) {
				q.push(next);
				visited[next.first][next.second] = true;
				dist[next.first][next.second] = dist[now.first][now.second] + 1;
			}
		}
	}

	return dist[to.first][to.second];
}

bool simulate() {
	auto next = bfs1(pos);

	if (next.first == -1) {
		return false;
	}

	if (next.second > fuel) {
		return false;
	}

	fuel -= next.second;
	path[next.first].visited = true;

	auto to = path[next.first].end;
	pos = path[next.first].start;

	int used = bfs2(pos, to);

	if (used > fuel || used == INF) {
		return false;
	}

	fuel += used;
	pos = to;
	return true;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	cin >> n >> m >> fuel;

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			cin >> b[i][j];
		}
	}

	cin >> pos.first >> pos.second;

	for (int i = 0; i < m; i++) {
		int sr, sc, er, ec;
		cin >> sr >> sc >> er >> ec;
		path.push_back({ {sr, sc}, {er, ec}, false });
	}

	bool flag = false;
	for(int i = 0; i < m; i++) {
		if (!simulate()) {
			flag = true;
			break;
		}
	}

	cout << (flag ? -1 : fuel) << '\n';
	return 0;
}