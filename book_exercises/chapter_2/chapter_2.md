# Chapter 2: Getting Started

**2.1**: [2.1-2](#21-2), [2.1-3](#21-3), [2.1-5](21-5) \
**2.2**: [2.2-2](#22-2), [2.2-3](#22-3)  
**2.3**: [2.3-3](#23-3), [2.3-4](#22-3), [2.3-5](#23-5), [2.3-6](#23-6), [2.3-8](#23-8)  
**Chapter Problems**: [2-1](#2-1)

## Chapter 2.1: Insertion Sort

### 2.1-2
**Initialization:** Here, prior to the first iteration of the loop, `sum` represents the summation of the first `i-1` values in the array `A`, or `A[1:0]`, which in this case is 0. \
**Maintenance:** Prior to each successive iteration of the loop, the value of `sum` will now hold the summation of the previous `i-1` values in `A`, or the sum of `A[1:i-1]`, as each time the loop executes on the `i`th iteration, the `A[i]` is added to `sum`. \
**Termination:** When `i = n+1`, `sum` will hold the summation of the values of `A[1:n]`.
***
### 2.1-3
Here, we are asked to re-write insertion sort so that it sorts in the reverse order. The pseudocode for this is:

```
Insertion-Sort(A,n)
for i = n-1 to 1
    key = A[i]
    j = i + 1
    while j < n+1 and A[j] < key
        A[j-1] =  A[j]
        j = j + 1
    A[j-1] = key
```

And in Python:

```python
def insertion_sort(A):
    n = len(A)
    for i in range(n-1)[::-1]:
        key = A[i]
        j = i + 1
        while (j < n and A[j] > key):
            A[j - 1] = A[j]
            j += 1
        A[j-1] = key
    return(A)
```
***
### 2.1-5
The pseudocode for this program is as follows:
```
Add-Binary-Integers(A,B,n)
C = [0; n+1] 
for i = 1 to n
    C[i] = C[i] + A[i] + B[i]
    C[i+1] = C[i + 1] + (C[i] - (C[i] mod 2))/2
    C[i] = C[i] mod 2
return C
```

Here, we can show that this algorithm will work using the loop invariant that is the base-10 value of `c` for all of `C` between iterations always being equal to `a + b` for `A[1:i]` and `B[1:i]`. Initially, `c = 0`, and prior to each iteration `i`, `C` will properly represent the sum of `A[1:i-1]` and `B[1:i-1]`. When `i = n + 1`, the code terminates such that `c = a + b`.

*Note*: I'm not very confident that I'm wording this correctly, but I think I'm getting the idea of "loop invariance" that is talked about in this chapter.

The Python code for this algorithm can be tested using [addBinaryIntegers.py](addBinaryIntegers.py), with the implemented method as follows:

```python
def addBinaryIntegers(A: list[int], B: list[int], n: int) -> list[int]:
    C = [0]*(n+1)
    for i in range(n):
        C[i] += A[i] + B[i]
        C[i+1] += (C[i] - (C[i] % 2))//2
        C[i] = C[i] % 2
    return(C)
```

## Chapter 2.2: Analyzing Algorithms

### 2.2-2

```
Selection-Sort(A,n)             | times
for i = 1 to n-1                | n
    index = i                   | n-1
    for j = i+1 to n            | S[i:1,n-1](n-i+1)
        if A[j] < A[index]      | S[i:1,n-1](n-i)
            index = j           | S[i:1,n-1](n-i)
    switched_value = A[i]       | n-1
    A[i] = A[index]             | n-1
    A[index] = switched_value   | n-1
    return A
```

Here, the loop invariant is that `A[1:i]` is always contains the smallest numbers in order.

This algorithm only needs to run for the first `n-1` elements (i.e. the outer for-loop) because, by the loop invariant stated previously, if `A[1:n-1]` is correctly sorted, then so too must `A[1:n]` because its been established via the previous loops that everything in `A[1:n-1]` is $\leq$ `A[n]`.

The worst-case running time of selection sort can be determined by first determining $T(n)$:

$$
\begin{align*}
    T(n) &= c_1n + (c_2 + c_6 + c_7 + c_8)(n-1) + c_3\sum_{i=1}^{n-1}(n-i+1) + (c_4 + c_5)\sum_{i=1}^{n-1}(n-i) \\
    &= c_1n + (c_2 + c_6 + c_7 + c_8)(n-1) + c_3\left(\frac{n^2 - n}{2} - 1\right) + (c_4 + c_5)\left(\frac{n^2 - n}{2}\right) \\
    &= \frac12(c_3 + c_4 + c_5)n^2 + (c_1 + c_2 + \frac{c_3}{2} + \frac{c_4}{2} + \frac{c_5}{2} + c_6 + c_7 + c_8)n - (c_2 + c_6 + c_7 + c_8) \\
    &= an^2 + bn - c
\end{align*}
$$

And thus the worst-case running time of selection sort is $\Theta(n^2)$. Because for every step of `i` we must search through all of `A[i+1:n]`, the best-case and worst-case scenarios are no different.
***
### 2.2-3

```
Linear-Search(A,n,x)    | time
for i = 1:n             | t_i
    if x == A[i]        | t_i 
    return i            | 
return NIL              | 1 (for-loop condition will check once more if x not in A)
```
If we let $t_i$ be the number elements that need to be searched to find a match for `x` in `A`, and the probability for `x` to be any element `i` in `A[i]` is uniform, i.e. $P(t_i = i \mid x) = 1/n$, using the time in general $T(n) = c_1t_i + c_2t_i$ when `x` is in `A`, we can take the expectation value of $T(n)$:

$$
\begin{align*}
    \mathrm{E}\left[T(n)\right] &= \mathrm{E}\left[(c_1 + c_2)t_i\right] \\
    &= (c_1+c_2)\sum_{i=1}^niP(t_i = i \mid x) \\
    &= (c_1+c_2)\frac1n\left(\frac{n(n+1)}{2}\right) \\
    &= \boxed{a\left(\frac{n+1}{2}\right)}\quad \textrm{where }a = c_1 + c_2
\end{align*}
$$

Thus, the average-case running time is $\Theta(n)$. For the worst-case running time (where `x` is not in `A`) we get:

$$
    T(n) = c_1(n+1) + c_2n = \boxed{an + b}
$$

Or $\Theta(n)$, which is the same as the average-case. This reflects what was said in the chapter in regards to the average and the worst-case times being the same.

## Chapter 2.3: Designing Algorithms

### 2.3-3
The loop invariant of the while-loop in lines 12-18 of the `Merge` algorithm is that `A[p:k]` is always sorted. Initially, `A[p:k]` for `k = p` is simply a single element, which is obviously sorted. After the first iteration of the while-loop, the invariant `A[p:k]` for `k = p + 1`, will now have a first element with the smallest element between `A[p:q]` and `A[q+1:r]`. Regardless of whatever the second element of `A[p:k]` is, we can be sure that `A[p] <= A[p+1:r]` based on the algorithm. For each iteration of the while-loop until the conditions are satisfied, this invariant holds true.

Following this while-loop, there are two other while-loops, and this loop invariant holds true using the same logic as before. Regardless of which while-loop (20-23 or 24-27) runs, it will only be a single step, and `A[p:r]` is sorted prior to this, and must be sorted after using the same argument for the "maintenance" step for the first while-loop.
***
### 2.3-4
First, we show the base case, with $n = 2^i$:

$$
    S_1\colon T(2^1) = 2 = 2\lg2
$$

Next, we assume that the case $i = k$ is true, noting that $2^k/2 = 2^{k-1}$:

$$
    S_k\colon T(2^k) = 2T(2^{k-1}) + 2^k = 2^kk
$$

Now, for the case $i = k + 1$, we have:

$$
\begin{align*}
    S_{k+1}\colon T(2^{k+1}) = 2T(2^k) + 2^{k+1} &=2^{k+1}(k+1) \\
    2\left(2^kk\right) + 2^{k+1} = 2^{k+1}(k+1) \\
    2^{k+1}(k + 1) = 2^{k+1}(k+1) 
\end{align*}
$$

And so the claim $T(n) = n\lg n$ is proved. 🪶
***
### 2.3-5

```
INSERTION-SORT(A,n)
1   if n > 1
2       A[1:n-1] = INSERTION-SORT(A[1:n-1],n-1)
3   j = n - 1
4   key = A[n]
5   while j > 0 and A[j] > key
6       A[j + 1] = A[j]
7       j = j - 1
8   A[j + 1] = key
```
This recursive algorithm is correct because the loop invariant `A[1:n-1]` is always correctly sorted, the proof of which is omitted here.

For the worst case running time, when $n=1$, the steps involved are all constant time $\Theta(1)$ with constant $c_1$, or $T(n) = c_1$. When $n > 1$, we have both the call of `Insertion-Sort` of time $T(n-1)$, and the while-loop taking time $c_2n$, along with the same constant time steps. Therefore, the recurrence is given by

$$
    T(n) = \left\{\begin{matrix}
        c_1 & \textrm{if } n = 1, \\
        T(n-1) + c_2n & \textrm{if } n > 1
    \end{matrix}\right.
$$

To find the worst-case running time, we can analyze the following:

$$
\begin{align*}
    T(n) &= T(n-1) + c_2n \\
    T(n-1) &= T(n-2) + c_2(n-1) \\
    \vdots \\
    T(2) &= T(1) + 2c_2 \\
    T(1) &= c_1
\end{align*}
$$

Thus, we have:

$$
\begin{align*}
    T(n) &= c_2\sum_{i=2}^ni + c_1 \\
    &= \frac{c_2}{2}(n^2+n) - c_1 - 1
\end{align*}
$$

Thus, the worst-case runing time is $\Theta(n^2)$, the same as the original insertion sort.
***
### 2.3-6

```
BINARY-SEARCH(A,n,x)
 1  q = ceil(n/2)
 2  if x == A[q]
 3      return q
 4  else if n == 1
 5      return NIL
 6  else if x < A[q]
 7      return BINARY-SEARCH(A[1:q-1],q-1,x)
 8  else if x > A[q]
 9      return BINARY-SEARCH(A[q+1:n],n-q,x)
```

The only two outcomes for `index` in this function are "`NIL`" or an index in `A` where there is a match for `x`. The loop invariant is that the array `A \ A_i` where `A_i` is the subarry being passed to `BINARY-SEARCH` at the `i`th recursive call of `BINARY-SEARCH`. In otherwords, its the part of `A` that isn't the subarray at any given step of recursion. 

To determine the worst-case running time, we can use the following recurrence:

$$
    T(n) = T(n/2) + c_1
$$

The calculation of `q` and the checks of each if-else condition take constant time, which we represent as $c_1$. The sub-call of `BINARY-SEARCH` takes $T(n/2)$ time, as the array passed is half (+/- 1) the length of the input array. Thus, we have:

$$
\begin{align*}
    T(n) &= T(n/2) + c_1 \\
    T(n/2) &= T(n/4) + c_1 \\
    \vdots \\
    T(1) &= c_1
\end{align*}
$$

Therefore, we get $T(n) = c_1\lg n$, and thus binary search has a worst-case running time of $\Theta(\lg n)$.
***

### 2.3-8

First, we define a method `MOD-LINEAR-SEARCH`, which is just linear search, but modified so that (1) the input is assumed to already be sorted and (2) we return the largest index in `S` where `S[index] + S[1] <= x`. This allows us to restrict our set to only a subset such that the sum of the minimum and maximum elements can at most add to `x` and not more. There will still be pairs in the set that add to more than `x`, but this isn't an issue as will be demonstrated in a later procedure.

```
MODIFIED-LINEAR-SEARCH(S,n,x)
1   index = 2
2   while S[1] + S[index] < x and index < n
3       index = index + 1
4   return index
```

Now, using `MERGE-SORT` to first sort `S`, we can write our algorithm to determine if *any* members of `S` sum to `x`.

```
CLOSING-SUM-SEARCH(S,n,x)
 1  MERGE-SORT(S,1,n)                   // nlg(n)
 2  r = MODIFIED-LINEAR-SEARCH(S,n,x)   // n
 3  i = 1
 4  j = r
 5  while j > i                         // n (technically r)
 6      currentSum = S[i] + S[j]
 7      if currentSum == x
 8          return True
 9      else if currentSum < x
10          i = i + 1
11      else if currentSum > x
12          j = j - 1
13  return False
```

The while-loop in this algorithm on the sorted subset of $S$ such that $S_i + S_j \leq x$ iteratively checks the sum of the smallest and largest elements in this subset. If `currentsum` is too small, we switch out the smaller number for the 2nd smallest in the remaining subset, and if `currentsum` is too large, we switch out the larger number for the 2nd largest in the remaining subset. Because this subset is sorted, with this protocol, we will always be comparing the next best guess of pairs that sum to `x` (*this isn't very convincing, but I believe it is true, just not sure how to explain it*).

If we analyze the running time of this algorithm, the while-loop will take at most `r` iterations, but in the worst-case this would `n`. Thus, we can write the running time as:

$$
    T(n) = c_1n\lg n + c_2n + c_3n + c_4
$$

Which corresponds to a worst-case running time of $\Theta(n\lg n)$, due to the merge sort used at the beginning of the algorithm.

Another possible algorithm would be to sort the set, then starting with the largest value in the set, take the difference between that value and `x` and use binary search to find a value that equals this difference. This algorithm would be have a worst-case running-time of $n\lg n$ as well, both from the sorting step with merge sort, and from the loops to find a match ($n$ for the outer for-loop, and $\lg n$ for the inner binary search).

```
BINARY-SUM-SEARCH(S,n,x,sorted)
 1  if sorted == False
 2      MERGE-SORT(S,1,n)                           // nlg(n) 
 3  diff = x - S[n]
 4  if diff != 0
 5      index = BINARY-SEARCH(S,n-1,diff)           // lg(n)
 6      if index != NIL
 7          return True
 8      else
 9          BINARY-SUM-SEARCH(S[1:n-1,n-1,x,True])  // T(n-1)*
10  else                                            // * skip nlg(n) step
11      return True
```

Analyzing it's running time, it's obvious that the worst case is still $\Theta(n\lg n)$, but as an exercise we'll still calculate $T(n)$:

$$
\begin{align*}
    T(n) &= c_1n\lg n + c_2\lg n + T(n-1) + c_3 \\
    T(n-1) &= c_2\lg n + T(n-2) + c_3 \\
    \vdots \\
    T(2) &= c_2 + T(n-1) + c_3 \\
    T(1) &= c_3
\end{align*}
$$

Adding these up, we get:

$$
\begin{align*}
    T(n) &= c_1n\lg n + c_2 n\lg n + c_3n \\
    T(n) &= (c_1 + c_2)n\lg n + c_3n
\end{align*}
$$

Although the resultant equations of the algorithms aren't exactly the same (*that may be because I'm not calculating them correctly, however*), it seems like their times are more or less equivalent in general, with their worst-case running times being the same.

## Chapter 2 End-of-Chapter Problems

### 2-1
**(a)** For each of the $n/k$ sublists, we have a running time for insertion sort of (consolidating some constants):

$$
    T(k) = c_1k^2 + c_2k + c_3
$$

Multiplying this by the number of sublists we get:

$$
    \frac{n}{k}T(k) = c_1nk + \cdots
$$

So, the worst-case running time is $\Theta(nk)$. 

**(b)** Once `A` has been divided into $n/k$ sublists, we run insertion sort to sort the sublists. Now, when we merge them, for each merge we have the same running time as before. Merging two sublists still takes $\Theta(n)$, or approximately $2n$, time (for $n$ elements in the sublists).

$$
\begin{align*}
    sublists\ \quad& \\
    n/k:\quad& \frac{n}{2k}\times 2k = n \\
    n/2k:\quad& \frac{n}{4k}\times 4k = n \\
    \vdots \\
    2:\quad& 1 \times 2\frac{n}{2} = n \\
\end{align*}
$$

If each step takes $n$ running time, and from $n/k$ sublists it takes $\lg(n/k)$ steps to be fully merged ($(n/k)/2^x = 1$, solve for $x$), the worst-case running time is $\Theta(n\lg(n/k))$.

**(c)** If we let $k = \lg n$, the $nk$ term becomes $n\lg n$, which is larger than the $n \lg(n/k)$ term for this $k$. 