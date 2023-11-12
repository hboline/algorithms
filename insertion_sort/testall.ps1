$N = $args[0]

python .\testing\make_random.py $N .\testing\

write-host "`nrust:"
cd .\rust
cargo run -q -- ..\testing\A.txt ..\testing\A_sorted.txt
cd ..

write-host "`npython:"
python .\python\insertion_sort.py .\testing\A.txt .\testing\A_sorted.txt

write-host "`n"

