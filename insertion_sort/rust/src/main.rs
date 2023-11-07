use rand::Rng;

fn gen_rand_arr<const N: usize>() -> Vec<i32> {
    let mut rng = rand::thread_rng();
    (1..N).map(|_| rng.gen_range(0..50)).collect()
}

fn main() {
    const N: usize = 39;
    let rand_vec: Vec<i32> = gen_rand_arr::<N>();

    println!("{:?}", rand_vec)
}

// Create vector in main()
/* fn main() {
    const N: i32 = 39;
    let mut rng = rand::thread_rng();
    let vec: Vec<i32> = (0..N).map(|_| rng.gen_range(0..100)).collect();

    println!("{:?}", vec);
} */

// Create array in main -- based on rust book, the recommendation is to use vectors when in doubt
/* fn main() {
    const N: usize = 39;
    let mut rng = rand::thread_rng();
    let arr: [i32; N] = [(); N].map(|_| rng.gen_range(0..50));

    println!("{:?}", arr);
}
 */


