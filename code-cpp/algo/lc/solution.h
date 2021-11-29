#include <sstream>

using namespace std;

class Solution {
protected:
    struct ListNode {
        int val;
        ListNode *next;

        ListNode() : val(0), next(nullptr) {}

        ListNode(int x) : val(x), next(nullptr) {}

        ListNode(int x, ListNode *next) : val(x), next(next) {}
    };

public:
    virtual void test() {
        cout << "构造函数 Derived." << endl;
    }

    void trimLeftTrailingSpaces(string &input) {
        input.erase(input.begin(), find_if(input.begin(), input.end(), [](int ch) {
            return !isspace(ch);
        }));
    }

    void trimRightTrailingSpaces(string &input) {
        input.erase(find_if(input.rbegin(), input.rend(), [](int ch) {
            return !isspace(ch);
        }).base(), input.end());
    }

    int stringToInteger(string input) {
        return stoi(input);
    }

    vector<int> stringToIntegerVector(string input) {
        vector<int> output;
        trimLeftTrailingSpaces(input);
        trimRightTrailingSpaces(input);
        input = input.substr(1, input.length() - 2);
        stringstream ss;
        ss.str(input);
        string item;
        char delim = ',';
        while (getline(ss, item, delim)) {
            output.push_back(stoi(item));
        }
        return output;
    }

    string integerVectorToString(vector<int> list, int length = -1) {
        if (length == -1) {
            length = list.size();
        }

        if (length == 0) {
            return "[]";
        }

        string result;
        for (int index = 0; index < length; index++) {
            int number = list[index];
            result += to_string(number) + ", ";
        }
        return "[" + result.substr(0, result.length() - 2) + "]";
    }

    ListNode *stringToListNode(string input) {
        // Generate list from the input
        vector<int> list = stringToIntegerVector(input);

        // Now convert that list into linked list
        ListNode *dummyRoot = new ListNode(0);
        ListNode *ptr = dummyRoot;
        for (int item: list) {
            ptr->next = new ListNode(item);
            ptr = ptr->next;
        }
        ptr = dummyRoot->next;
        delete dummyRoot;
        return ptr;
    }

    string listNodeToString(ListNode *node) {
        if (node == nullptr) {
            return "[]";
        }

        string result;
        while (node) {
            result += to_string(node->val) + ", ";
            node = node->next;
        }
        return "[" + result.substr(0, result.length() - 2) + "]";
    }
};

