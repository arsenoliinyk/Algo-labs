MODULE = int(1e9 + 7)


def rabin_karp(text, pattern):
    found_pattern_in_text = []
    pattern_value = 0
    for char in pattern:
        pattern_value *= 10
        pattern_value += ord(char)
        pattern_value %= MODULE
    current_subtext_value = 0
    for i in range(len(pattern)):
        current_subtext_value *= 10
        current_subtext_value += ord(text[i])
        current_subtext_value %= MODULE

    if text[:len(pattern)] == pattern:
        found_pattern_in_text.append(0)
    for j in range(1, len(text) - len(pattern) + 1):
        current_subtext_value -= ord(text[j - 1]) * (10 ** (len(pattern) - 1))
        current_subtext_value *= 10
        current_subtext_value += ord(text[j + len(pattern) - 1])
        current_subtext_value %= MODULE
        if current_subtext_value == pattern_value and text[j:j + len(pattern)] == pattern:
            found_pattern_in_text.append(j)

    return tuple(found_pattern_in_text)
