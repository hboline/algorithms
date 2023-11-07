use rand::Rng;

// function that generates a vector of random integers from 0 to 99 of size N
fn gen_rand_arr<const N: usize>() -> Vec<i32> {
    let mut rng = rand::thread_rng();
    (0..N).map(|_| rng.gen_range(0..100)).collect()
}

// insertion sort function; takes a reference to an external vector
fn sort(a_in: &Vec<i32>) -> Vec<i32> {
    let mut a = a_in.to_vec();
    let n = a.len();

    let mut key: i32;
    let mut j: i32;
    for i in 1..n {
        key = a[i];
        j = (i - 1) as i32;
        // the second condition of the and-statement in the while
        // loop is conditioned on the first to avoid an overflow issue
        // when converting from j = -1int32 to usize
        while (j >= 0) & (if j >= 0 {a[j as usize] > key} else {false}) {
            a[(j+1) as usize] = a[j as usize];
            j -= 1;
        }
        a[(j+1) as usize] = key;
    }
    a
}

fn main() {
    const N: usize = 20;
    let a: Vec<i32> = gen_rand_arr::<N>();
    println!("Original: {:?}", a);
    
    let a_sorted = sort(&a);
    println!("  Sorted: {:?}", a_sorted);
}


