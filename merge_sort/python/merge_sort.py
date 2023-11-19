import sys
import time

def merge(A,p,q,r):
    nL = q-p+1
    nR = r-q
    L = []
    R = []
    for i in range(nL):
        L.append(A[p+i])
    for j in range(nR):
        R.append(A[q+1+j])
    i = j = 0
    k = p
    while i < nL and j < nR:
        if L[i] <= R[j]:
            A[k] = L[i]
            i += 1
        else:
            A[k] = R[j]
            j += 1
        k += 1
    while i < nL:
        A[k] = L[i]
        i += 1
        k += 1
    while j < nR:
        A[k] = R[j]
        j += 1
        k += 1

def merge_sort(A,p=None,r=None):
    if (p == None) and (r == None):
        p = 0
        r = len(A)-1
    elif (p == None) ^ (r == None):
        print("error: either specify 1 or 3 arguments")
        return
    if p != r:
        q = (p + r)//2
        merge_sort(A,p,q)
        merge_sort(A,q+1,r)
        merge(A,p,q,r)

def timeit(func, *args, **kwargs):
    start = time.time()
    func(*args, **kwargs)
    end = time.time()
    return(end - start)

def main():
    A = [int(line) for line in open(sys.argv[1],'r').readlines()]
    A_correct = [int(line) for line in open(sys.argv[2],'r').readlines()]

    runtime = timeit(merge_sort, **{'A':A})

    print("Array \"A\" was{}sorted correctly".format(" " if A == A_correct else " not "))
    print("Runtime: {:.3f}s\n".format(runtime))

if __name__=="__main__":
    main()
