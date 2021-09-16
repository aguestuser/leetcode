"""
Example 1:

Input: nums = [3,2,2,3], val = 3
Output: 2, nums = [2,2,_,_]
Explanation: Your function should return k = 2, with the first two elements of nums being 2.
It does not matter what you leave beyond the returned k (hence they are underscores).

Example 2:

Input: nums = [0,1,2,2,3,0,4,2], val = 2
Output: 5, nums = [0,1,4,0,3,_,_,_]
Explanation: Your function should return k = 5, with the first five elements of nums containing 0, 0, 1, 3, and 4.
Note that the five elements can be returned in any order.
It does not matter what you leave beyond the returned k (hence they are underscores).
"""
from typing import List


def remove(val: int, vals: List[int], ) -> (int, List[int]):
    """
    Removes `val` from `vals` in place by replaces all occurrences of `val` w/ -1, and shifting them to end of `vals`
    Time complexity:  O(N log N)
    Space complexity: O(1)
    """
    num_removed = 0
    for i in range(len(vals)):
        if vals[i] == val:
            vals[i] = -1
            num_removed += 1

    # we incur logarithmic time complexity with this sort (could we do better?)
    vals.sort(reverse=True)
    return len(vals) - num_removed, vals
