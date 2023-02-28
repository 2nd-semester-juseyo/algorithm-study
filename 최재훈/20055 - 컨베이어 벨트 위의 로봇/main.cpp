#include <bits/stdc++.h>

using namespace std;

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	int n, k, cnt = 0;
	cin >> n >> k;

	vector<pair<int, bool>> up, down;
	for (int i = 0; i < n; i++) {
		int buffer;
		cin >> buffer;
		up.push_back({ buffer, false });
	}

	for (int i = 0; i < n; i++) {
		int buffer;
		cin >> buffer;
		down.push_back({ buffer, false });
	}

	int zero_cnt = 0;
	while (1) {
		if (zero_cnt >= k) {
			break;
    }

		cnt++;

		if (up.back().second) {
			up.back().second = false;
    }

		down.insert(down.begin(), up.back());
		up.erase(up.end() - 1);
		up.insert(up.begin(), down.back());
		down.erase(down.end() - 1);

		if (up.back().second) {
			up.back().second = false;
    }

		for (int i = n - 2; i >= 0; i--) {
			if (up[i].second && !up[i + 1].second && up[i + 1].first >= 1) {
				up[i].second = false;
				up[i + 1].second = true;
				up[i + 1].first--;

				if (up[i + 1].first == 0) {
					zero_cnt++;
        }
			}
		}

		if (up[0].first > 0) {
			up[0].first--;
			up[0].second = true;
			if (up[0].first == 0)
				zero_cnt++;
		}
	}

	cout << cnt;
	return 0;
}