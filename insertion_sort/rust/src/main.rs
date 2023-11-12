use rand::Rng;
use std::env;
use std::fs::read_to_string;
use std::path::PathBuf;
use std::time::Instant;

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
        
        while (j >= 0) && (a[j as usize] > key) {
            a[(j+1) as usize] = a[j as usize];
            j -= 1;
        }
        a[(j+1) as usize] = key;
    }
    a
}

fn main() {
    let mut args: Vec<String> = env::args().collect();
    args.remove(0);

    let (a, a_correct): (Vec<i32>, Option<Vec<i32>>) = if args.len() > 1 {
        let contents: Vec<Vec<i32>> = args
        .iter()
        .map(|x|
            read_to_string(PathBuf::from(x))
            .expect("invalid path/file does not exist")
            .lines()
            .map(|y| 
                y.parse::<i32>().unwrap()
            ).collect()
        ).collect();
        
        (
            contents[0].clone(),
            Some(contents[1].clone())
        )
    } else {
        const N: usize = 20;
        (gen_rand_arr::<N>(), None)
    };

    let start = Instant::now();
    let a_sorted = sort(&a);
    let duration = start.elapsed();

    match a_correct {
        Some(a_correct) => {
            println!{"Array \"A\" was{}sorted correctly", if a_sorted == a_correct {" "} else {" not "}};
            println!{"Runtime: {:?}", duration};
        },
        None => {
            println!("Original: {:?}", a);
            println!("  Sorted: {:?}", a_sorted);
        }
    }
}