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
from leet_python.remove_element import remove


def test_1():
    (num_remaining, remaining) = remove(3, [3, 2, 2, 3])
    assert num_remaining == 2
    assert remaining[:num_remaining] == [2, 2]


def test_2():
    (num_remaining, remaining) = remove(2, [0, 1, 2, 2, 3, 0, 4, 2])
    assert num_remaining == 5
    assert remaining[:num_remaining] == [4, 3, 1, 0, 0]
