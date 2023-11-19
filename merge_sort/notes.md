# Merge Sort Notes

## Python
Nothing much to write about here for this implementation. Used the xor-operator "`^`" for the first time, and I finally learned what "`*args`" and "`**kwargs`" means. Those are pretty handy. I used these to build a function to time the execution of a function in the function "`timeit`". Turns out this is already a Python package, but this works well enough.

## Java
Not much different from the implementation of insertion sort. For timing the merge sort function call, I decided to build a method that will format the results so its in milliseconds or seconds: whichever is more natural. 

## Rust
Again, nothing much different from insertion sort. The one thing that was interesting was that the compiler flagged me for writing a recursive function that had no way of exiting. I originally wrote the `merge_sort` function without first checking that `i` $\neq$ `j`. When I did this, the compiler gave an error (or warning) that the recursion could not cease. This is another example of what I find so fascinating about Rust. It really does its best to make you not make mistakes.

I ended up re-writing the Python scripts that created the random arrays for testing in Rust because I wanted to make arrays that were 10s-of-millions large, and Python was too slow (at least in the way I implemented it). Rust, however, is much, *much* faster for this task. This is a good example of why Rust is a good language to learn in complement to a language like Python. 

To create the random arrays for sorting, run (from the appropriate directory):

```
.\<path to>\make_random.exe <array size> <output path>
```

or, for example, from the `merge_sort` directory:

```
.\testing\make_random.exe 1000000 .\testing\
```

which will create an array and sorted array of 1 million integers from 0 to 999,999. 🪶