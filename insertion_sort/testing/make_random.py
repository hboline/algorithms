import random
import os
import sys

# HOW TO USE: pass argument to choose how large array A should be
# e.g. "python "

def main():
    try:
        fileNames = [sys.argv[2] + x for x in ["A.txt","A_sorted.txt"]]
    except:
        fileNames = ["A.txt","A_sorted.txt"]

    # remove A.txt and A_sorted.txt if they exist
    if os.path.exists(fileNames[0]):
        os.remove(fileNames[0])

    if os.path.exists(fileNames[1]):
        os.remove(fileNames[1])

    # open A.txt
    file = open(fileNames[0], "w")

    # create A, populate, and write to A.txt
    A = []
    try:
        for i in range(int(sys.argv[1])):
            A.append(random.randint(0,99999))
            file.write(str(A[i]) + "\n")
    except IndexError as e:
        print("error: " + str(e) + ". please provide an argument.")

    file.close()

    # sort A with built-in method
    A.sort()

    # open A_sorted.txt
    file = open((fileNames[1]), "w")

    # write A (now sorted) to A_sorted.txt
    for i in range(len(A)):
        file.write(str(A[i]) + "\n")

    file.close()

if __name__=="__main__":
    main()

