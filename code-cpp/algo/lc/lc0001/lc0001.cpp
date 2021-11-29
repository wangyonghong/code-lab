#include <iostream>
#include <vector>
#include "../solution.h"

using namespace std;

class Lc0001 : public Solution {
public:
    vector<int> twoSum(vector<int> &nums, int target) {
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (nums[i] + nums[j] == target) {
                    return {i, j};
                }
            }
        }
        return {};
    }

    void test() override {
        vector<int> nums = {2, 7, 11, 15};
        vector<int> res = twoSum(nums, 9);
        cout << integerVectorToString(res) << endl;
    }
};

int main() {
    Lc0001().test();
}