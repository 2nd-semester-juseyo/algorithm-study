#include <bits/stdc++.h>

using namespace std;

struct Info {
	int p, f, s, v, c;
};

int ans = INT_MAX;
int mp, mf, ms, mv;
vector<int> ls;
vector<vector<int>> result;
vector<Info> ing;

void func(int idx, int sp, int sf, int ss, int sv, int sc) {
	if (sp >= mp && sf >= mf && ss >= ms && sv >= mv) {
		if (sc < ans) {
			ans = sc;
			result.clear();
			result.push_back(ls);
		}
		else if (sc == ans) {
			result.push_back(ls);
		}
	}

	if (idx == ing.size()) {
		return;
	}
	
	func(idx + 1, sp, sf, ss, sv, sc);
	ls.push_back(idx);
	func(idx + 1, sp + ing[idx].p, sf + ing[idx].f, ss + ing[idx].s, sv + ing[idx].v, sc + ing[idx].c);
	ls.pop_back();
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	int n;
	cin >> n >> mp >> mf >> ms >> mv;

	while (n--) {
		int p, f, s, v, c;
		cin >> p >> f >> s >> v >> c;
		ing.push_back({ p, f, s, v, c });
	}

	func(0, 0, 0, 0, 0, 0);

	sort(result.begin(), result.end(), [](const vector<int>& a, const vector<int>& b) {
		int len = min(a.size(), b.size());
		for (int i = 0; i < len; i++) {
			if (a[i] != b[i]) {
				return a[i] < b[i];
			}
		}

		return a.size() < b.size();
	});

	if (ans == INT_MAX) {
		cout << -1 << '\n';
	}
	else {
		cout << ans << '\n';
		for (auto& i : result[0]) {
			cout << i + 1 << ' ';
		}
		cout << '\n';
	}

	return 0;
}