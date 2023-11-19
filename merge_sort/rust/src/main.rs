use std::env;
use std::fs::read_to_string;
use std::path::PathBuf;
use std::time::Instant;

fn merge(arr: &mut Vec<i32>, p: usize, q: usize, r: usize) {
    let nl: usize = q - p + 1;
    let nr: usize = r - q;

    let arr_left: Vec<i32> = Vec::from(&arr[p..=q]);
    let arr_right: Vec<i32> = Vec::from(&arr[q+1..=r]);

    let (mut i,mut j,mut k) = (0,0,p);
    while (i < nl) && (j < nr) {
        if arr_left[i] <= arr_right[j] {
            arr[k] = arr_left[i];
            i += 1;
        } else {
            arr[k] = arr_right[j];
            j += 1;
        }
        k += 1;
    }

    while i < nl {
        arr[k] = arr_left[i];
        i += 1;
        k += 1;
    }

    while j < nr {
        arr[k] = arr_right[j];
        j += 1;
        k += 1;
    }
}

fn merge_sort(arr: &mut Vec<i32>, p: usize, r: usize) {
    if p != r {
        let q: usize = (p + r)/2;
        merge_sort(arr, p, q);
        merge_sort(arr, q+1, r);
        merge(arr, p, q, r);
    }
}

fn main() {
    let argv: Vec<String> = env::args().collect();

    let contents: Vec<Vec<i32>> = argv[1..=2]
        .iter()
        .map(|files|
            read_to_string(PathBuf::from(files))
            .expect("invalid path/file does not exist")
            .lines()
            .map(|elements| elements.parse::<i32>().unwrap()).collect()
        ).collect();
    
    let (mut a, a_correct): (Vec<i32>, Vec<i32>) = (
        contents[0].clone(),
        contents[1].clone()
    );

    let p = 0;
    let r = a.len()-1;
    
    let start = Instant::now();
    merge_sort(&mut a, p, r);
    let duration = start.elapsed();

    println!("Array \"A\" was{}sorted correctly", if a == a_correct {" "} else {" not "});
    println!("Runtime: {:?}\n", duration);
}