$N = $args[0]

python .\testing\make_random.py $N .\testing\

write-host "`nrust:"
.\rust\target\release\rust.exe .\testing\A.txt .\testing\A_sorted.txt

write-host "`npython:"
python .\python\insertion_sort.py .\testing\A.txt .\testing\A_sorted.txt

write-host "`n"

