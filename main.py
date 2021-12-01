from rabin_karp import rabin_karp

if __name__ == '__main__':

    print("Input string:")
    text = input()
    print("Input pattern:")
    pattern = input()
    found_pattern_in_text = rabin_karp(text, pattern)
    print("Pattern found at:")
    for index in found_pattern_in_text:
        print(f"{index}-{index + len(pattern) - 1}", end=" ")
