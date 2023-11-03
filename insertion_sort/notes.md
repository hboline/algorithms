# Insertion Sort Notes

## Python
Implementing this algorithm in python was pretty simple, the only struggle I really had was reasoning how to number the indexes for the for loop compared to the algorithm in the book (arrays start at 1 and go to n in the book, python is of course 0 to n-1). However, that didn't take long to figure out. Coding in python is pretty nice. Even if I have to look a couple things up, it doesn't take long to write.

## Java
Implementing this in Java was pretty difficult, mostly since I'm still learning Java. The main issue I had was confusion about copying an array. At first I intended to have the `sort()` function return the sorted array and assign that array to a new array. However, upon doing so I found that the original array `A` would be sorted as well. I still don't fully understand it, but the `sort()` function changes `A`. I then tried to copy `A` to a new array and then sort it, but `A` still ended up getting sorted. As I understand it now, arrays are objects, and the variable assigned to an array is actually a pointer to this object (the same thing happens for classes). When I set the new array equal to `A`, I'm just pointing to the same array. As for why the `sort()` method alters `A`, I think its because by using `A` as the input of the method, acting on `A`, and returning `A`, I'm actualling acting on that same pointer. This threw me off compared to something like what I'd find in python where the scope of the method is different and as such its essentially a different variable. 

My first solution was to just print the original array, sort `A` (with `sort()` now returning void), and then print the sorted array. This works, but its not what I wanted to do. In the end, I had the `sort()` method use `clone()` to clone the original array.

Also, for printing the arrays, I originally use a while and for loop to do it:

```java
int i = 0;
String version = "Original: [";
while(i < 2) {
    System.out.print(version);
    for(int j = 0; j < A.length; j++) {
        if(j < A.length-1){
            System.out.print(A[j] + ",");
        } else {
            System.out.print(A[j]);
        }
    }
    System.out.print("]\n");
    sort(A);
    version = "  Sorted: [";
    i ++;
}
```

This works just fine, but `Array.toString()` works much better.