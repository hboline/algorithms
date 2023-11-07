use rand::Rng;

fn main() {
    const N: usize = 39;
    let mut rng = rand::thread_rng();
    let mut arr: [i32; N] = [0i32; N];

    arr = arr.map(|_| rng.gen_range(0..50));

    println!("{:?}", arr);
}

/*
fn main() {
    const N: i32 = 10;
    let mut rng = rand::thread_rng();
    let vec: Vec<i32> = (0..N).map(|_| rng.gen_range(0..100)).collect();

    println!("{:?}", vec);
}
*/