"""
https://leetcode.com/problems/longest-substring-without-repeating-characters/

Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.

Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.

Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.

Example 4:

Input: s = ""
Output: 0



Constraints:

    0 <= s.length <= 5 * 104
    s consists of English letters, digits, symbols and spaces.
"""
from collections import defaultdict
from typing import Optional


class Occurrence:
    def __init__(self, last: Optional[int], dist: int):
        self.last = last
        self.dist = dist

    @classmethod
    def empty(cls):
        return cls(None, 0)


def old_longest_in(s: str) -> int:
    if s == "":
        return 0

    longest = 1

    for n in range(0, len(s)):
        last = s[n]
        for ch in s[n + 1:]:
            if ch not in last:
                last += ch
                if len(last) > longest:
                    longest = len(last)
            else:
                last = ch

    return longest


def longest_in(s: str) -> int:
    """
    find longest substring that contains no repeating characters
    time complexity: O(n) (where n is number of characters in string)
    space complexity: O(n) (")
    """
    substr_start = -1
    occurrences = defaultdict(lambda: -1)
    longest = 0

    for idx in range(len(s)):
        char = s[idx]
        last_occurrence = occurrences[char]
        occurrences[char] = idx

        if substr_start < last_occurrence:
            substr_start = last_occurrence

        if idx - substr_start > longest:
            longest = idx - substr_start

    return longest
