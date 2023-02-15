#include <bits/stdc++.h>

using namespace std;

union Value {
    char op;
    int num;
};

struct Item {
    bool isoperator;
    Value val;

    Item();

    Item(char c);
};

Item::Item(char c) {
    if (isdigit(c)) {
        isoperator = false;
        val.num = c - '0';
    } else {
        isoperator = true;
        val.op = c;
    }
}

Item::Item() {}

int n, ans = INT_MIN;
vector<Item> v;
unordered_set<int> pos;

int cal(int num1, int num2, char oper) {
    switch (oper) {
        case '+':
            return num1 + num2;
        case '-':
            return num1 - num2;
        default:
            return num1 * num2;
    }
}

void solution() {
    vector<Item> s;

    for (int i = 0; i < v.size(); i++) {
        if (pos.find(i) == pos.end()) {
            s.push_back(v[i]);
        } else {
            Item item1 = s.back();
            s.pop_back();
            Item item2 = v[i + 1];

            Item item;
            item.isoperator = false;
            item.val.num = cal(item1.val.num, item2.val.num, v[i].val.op);
            s.push_back(item);
            i++;
        }
    }

    int res = s[0].val.num;
    for (int i = 1; i < s.size(); i += 2) {
        res = cal(res, s[i + 1].val.num, s[i].val.op);
    }

    ans = max(ans, res);
}

void func(int idx) {
    if (idx >= v.size()) {
        solution();
        return;
    }

    for (int i = idx; i < v.size(); i += 2) {
        pos.insert(i);
        func(i + 4);
        pos.erase(i);
    }

    func(v.size());
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    cin >> n;

    for (int i = 0; i < n; i++) {
        char c;
        cin >> c;
        v.emplace_back(c);
    }

    func(1);
    cout << ans << '\n';
    return 0;
}