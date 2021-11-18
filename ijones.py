def find_ways_count(matrix, w, h):
    flow_arr = [[1 for _ in range(w)] for _ in range(h)]

    counter_for_letter = dict()
    letter_in_column = dict()

    for i in range(w):
        for j in range(h):
            letter = matrix[j][i]
            if i+1 < w:
                if matrix[j][i] == matrix[j][i+1]:
                    flow_arr[j][i+1] -= 1


            if letter in counter_for_letter:
                flow_arr[j][i] += counter_for_letter[letter]

            if letter in letter_in_column:
                letter_in_column[letter] += flow_arr[j][i]
            else:
                letter_in_column[letter] = flow_arr[j][i]

        for (letter, count) in letter_in_column.items():
            if letter in counter_for_letter:
                counter_for_letter[letter] += letter_in_column[letter]
            else:
                counter_for_letter[letter] = letter_in_column[letter]
        letter_in_column = dict()

    return flow_arr