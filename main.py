
from ijones import find_ways_count


if __name__ == '__main__':

    w, h = map(int, input().split(" "))

    matrix = [list(input())[:w] for i in range(h)]
    ways_count = find_ways_count(matrix, w, h)

    # print sum of ways counts to top right and bottom left corners of corridor
    print(ways_count[0][w-1] + ways_count[h-1][w-1])