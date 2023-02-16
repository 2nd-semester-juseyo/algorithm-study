#include <iostream>
#include <queue>

using namespace std;

const int MAX = 21;
const int dir[4][2] = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };
int n, m, k, ans;
int b[MAX][MAX];
bool visited[MAX][MAX];

class Dice {
public:
	int top, bottom, front, back, left, right, direction;
	int r, c;

	Dice();

	void roll_front();
	void roll_back();
	void roll_left();
	void roll_right();
	void rotate_clockwise();
	void rotate_counter_clockwise();
	void reverse_direction();
};

Dice::Dice() {
	r = c = 1;

	top = 1;
	front = 2;
	right = 3;
	left = 4;
	back = 5;
	bottom = 6;

	direction = 0;
}

void Dice::roll_front() {
	swap(top, back);
	swap(back, bottom);
	swap(bottom, front);
	--r;
}

void Dice::roll_back() {
	swap(top, front);
	swap(front, bottom);
	swap(bottom, back);
	++r;
}

void Dice::roll_left() {
	swap(top, right);
	swap(right, bottom);
	swap(bottom, left);
	--c;
}

void Dice::roll_right() {
	swap(top, left);
	swap(left, bottom);
	swap(bottom, right);
	++c;
}

void Dice::rotate_clockwise() {
	if (++direction == 4) {
		direction = 0;
	}
}

void Dice::rotate_counter_clockwise() {
	if (--direction == -1) {
		direction = 3;
	}
}

void Dice::reverse_direction() {
	direction += 2;

	if (direction > 3) {
		direction -= 4;
	}
}

int bfs(int sr, int sc, int val) {
	memset(visited, false, sizeof(visited));
	queue<pair<int, int>> q;
	q.push({ sr, sc });
	visited[sr][sc] = true;
	int ret = 0;

	while (!q.empty()) {
		int r = q.front().first;
		int c = q.front().second;
		ret++;
		q.pop();

		for (int i = 0; i < 4; i++) {
			int nr = r + dir[i][0];
			int nc = c + dir[i][1];

			if (nr >= 1 && nr <= n && nc >= 1 && nc <= m && !visited[nr][nc] && b[nr][nc] == val) {
				visited[nr][nc] = true;
				q.push({ nr, nc });
			}
		}
	}

	return ret;
}

void func(Dice& dice) {
	int nr = dice.r + dir[dice.direction][0];
	int nc = dice.c + dir[dice.direction][1];

	if (nr < 1 || nr > n || nc < 1 || nc > m) {
		dice.reverse_direction();
	}

	switch (dice.direction) {
	case 0:
		dice.roll_right();
		break;
	case 1:
		dice.roll_back();
		break;
	case 2:
		dice.roll_left();
		break;
	default:
		dice.roll_front();
	}

	ans += bfs(dice.r, dice.c, b[dice.r][dice.c]) * b[dice.r][dice.c];

	int num_a = dice.bottom, num_b = b[dice.r][dice.c];

	if (num_a > num_b) {
		dice.rotate_clockwise();
	}
	else if (num_a < num_b) {
		dice.rotate_counter_clockwise();
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	cin >> n >> m >> k;

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= m; j++) {
			cin >> b[i][j];
		}
	}

	Dice dice = Dice();

	while (k--) {
		func(dice);
	}

	cout << ans << '\n';
	return 0;
}