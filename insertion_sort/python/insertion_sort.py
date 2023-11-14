import time
import random
import sys

# implementation of insertion sort
def insertion_sort(A):
    n = len(A)
    for i in range(1,n):
        key = A[i]
        j = i - 1
        while (j >= 0 and A[j] > key):
            A[j+1] = A[j]
            j -= 1
        A[j+1] = key
    return(A)

def main():
    if len(sys.argv) > 1:
        A = [int(line) for line in open(sys.argv[1],'r').readlines()]
        A_correct = [int(line) for line in open(sys.argv[2],'r').readlines()]

        start = time.time()
        insertion_sort(A)
        end = time.time()
        time_elapsed = end-start

        print("Array \"A\" was{}sorted correctly".format(" " if A == A_correct else " not "))
        print("Runtime: {:.3f}s".format(time_elapsed))
    else:
        A = random.sample(range(100),20)

        print("Original:", A)
        print("  Sorted:", insertion_sort(A))

if __name__=="__main__":
    main()