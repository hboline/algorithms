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

if __name__=="__main__":
    import random
    A = random.sample(range(100),20)
    
    print("Original:", A)
    print("  Sorted:", insertion_sort(A))