from leet_python.longest_nonrepeating_substring import longest_in


def test_example_1() -> ():
    assert longest_in("abcabcbb") == 3


def test_example_2() -> ():
    assert longest_in("bbbb") == 1


def test_example_3() -> ():
    assert longest_in("pwwkew") == 3


def test_example_4() -> ():
    assert longest_in("") == 0
