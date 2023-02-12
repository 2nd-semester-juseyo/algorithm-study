#include <bits/stdc++.h>

using namespace std;

const int MAX = 11;
int n, ans = INT_MAX;
int people[MAX];
bool area[MAX], visited[MAX];
vector<int> adj[MAX];

int bfs(int s, bool color) {
    queue<int> q;
    q.push(s);
    visited[s] = true;
    int cnt = 0;

    while (!q.empty()) {
        int now = q.front();
        q.pop();
        cnt += people[now];

        for (auto &next: adj[now]) {
            if (!visited[next] && area[next] == color) {
                q.push(next);
                visited[next] = true;
            }
        }
    }

    return cnt;
}

void calc() {
    memset(visited, false, sizeof(visited));

    vector<int> v;

    for (int i = 1; i <= n; i++) {
        if (!visited[i]) {
            v.push_back(bfs(i, area[i]));
        }
    }

    if (v.size() == 2) {
        ans = min(ans, abs(v[0] - v[1]));
    }
}

void func(int idx) {
    if (idx > n) {
        calc();
        return;
    }

    for (int i = 0; i <= 1; i++) {
        area[idx] = i;
        func(idx + 1);
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    cin >> n;

    for (int i = 1; i <= n; i++) {
        cin >> people[i];
    }

    for (int i = 1; i <= n; i++) {
        int num;
        cin >> num;

        while (num--) {
            int vertex;
            cin >> vertex;
            adj[i].push_back(vertex);
        }
    }

    func(1);
    cout << (ans != INT_MAX ? ans : -1) << '\n';
    return 0;
}