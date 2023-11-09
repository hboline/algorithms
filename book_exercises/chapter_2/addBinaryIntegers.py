import random

# function that adds the binary integers
def addBinaryIntegers(A: list[int], B: list[int], n: int) -> list[int]:
    C = [0]*(n+1)
    for i in range(n):
        C[i] += A[i] + B[i]
        C[i+1] += (C[i] - (C[i] % 2))//2
        C[i] = C[i] % 2
    return(C)

# function that converts the binary integers into base-10 integers
def getInt(A: list[int]) -> int:
    int_out = 0
    for i in range(len(A)):
        int_out += A[i]*(2**i)
    return(int_out)

if __name__=="__main__":
    n = 8
    A = random.sample([0,1], counts=[n,n], k=n)
    B = random.sample([0,1], counts=[n,n], k=n)

    C = addBinaryIntegers(A, B, n)
    
    print(A)
    print(B)
    print(str(C) + "\n")

    print("A = " + str(getInt(A)))
    print("B = " + str(getInt(B)))
    print("C = " + str(getInt(C)) + "\n")

    print("Equal?: " + str(getInt(A) + getInt(B) == getInt(C)))