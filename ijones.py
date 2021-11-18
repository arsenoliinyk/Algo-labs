def find_ways_count(matrix, w, h):
    ways_arr = [[1 for _ in range(w)] for _ in range(h)]

    incrementer_for_letter = dict()
    letter_was_in_column = dict()

    for i in range(w):
        for j in range(h):
            letter = matrix[j][i]
            if i+1 < w:
                if matrix[j][i] == matrix[j][i+1]:
                    ways_arr[j][i+1] -= 1

            if letter in incrementer_for_letter:
                ways_arr[j][i] += incrementer_for_letter[letter]

            if letter in letter_was_in_column:
                letter_was_in_column[letter] += ways_arr[j][i]
            else:
                letter_was_in_column[letter] = ways_arr[j][i]

        for (letter, count) in letter_was_in_column.items():
            if letter in incrementer_for_letter:
                incrementer_for_letter[letter] += letter_was_in_column[letter]
            else:
                incrementer_for_letter[letter] = letter_was_in_column[letter]
        letter_was_in_column = dict()

    return ways_arr