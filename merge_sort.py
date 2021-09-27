from enum import Enum


class Sort(Enum):
    ASC = 1
    DESC = 2


# def merge_sort(input, order=Sort.ASC):
#     comparisons = 0
#
#     if len(input) == 1:
#         return
#
#     left_array = input[:len(input) // 2]
#     right_array = input[len(input) // 2:]
#
#     merge_sort(left_array, order)
#     merge_sort(right_array, order)
#
#     def compare(first_numb, second_numb, order):
#         nonlocal comparisons
#         comparisons += 1
#
#         if order == Sort.ASC:
#             return first_numb > second_numb
#         else:
#             return second_numb > first_numb
#
#     def merge(input, first_array, second_array):
#         first_index = second_index = sorted_index = 0
#
#         while first_index < len(first_array) and second_index < len(second_array):
#             if compare(first_array[first_index], second_array[second_index], order):
#                 input[sorted_index] = second_array[second_index]
#                 second_index += 1
#             else:
#                 input[sorted_index] = first_array[first_index]
#                 first_index += 1
#
#             sorted_index += 1
#
#         if first_index == len(first_array):
#             for i in range(second_index, len(second_array)):
#                 input[sorted_index] = second_array[i]
#                 sorted_index += 1
#
#         else:
#             for i in range(first_index, len(first_array)):
#                 input[sorted_index] = first_array[i]
#                 sorted_index += 1
#
#     merge(input.copy(), left_array, right_array)
#     return comparisons


def merge_sort(arr, order, comparison):
    # The last array split
    if len(arr) <= 1:
        return arr
    mid = len(arr) // 2
    # Perform merge_sort recursively on both halves
    left, right = merge_sort(arr[:mid], order, comparison), merge_sort(arr[mid:], order, comparison)

    # Merge each side together
    return merge(left, right, arr.copy(), order, comparison)


def merge(first_array, second_array, merged, order, comparison):
    first_index, second_index = 0, 0
    if order == Sort.ASC:
        while first_index < len(first_array) and second_index < len(second_array):

            # Sort each one and place into the result
            comparison += 1
            if first_array[first_index] <= second_array[second_index]:
                merged[first_index + second_index] = first_array[first_index]
                first_index += 1
            else:
                merged[first_index + second_index] = second_array[second_index]
                second_index += 1

    else:
        while first_index < len(first_array) and second_index < len(second_array):

            # Sort each one and place into the result
            comparison += 1
            if first_array[first_index] >= second_array[second_index]:
                merged[first_index + second_index] = first_array[first_index]
                first_index += 1
            else:
                merged[first_index + second_index] = second_array[second_index]
                second_index += 1

    for first_index in range(first_index, len(first_array)):
        merged[first_index + second_index] = first_array[first_index]

    for second_index in range(second_index, len(second_array)):
        merged[first_index + second_index] = second_array[second_index]

    return merged
