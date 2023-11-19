$N = $args[0]

.\testing\make_random.exe $N .\testing\

write-host "`nrust:"
.\rust\target\release\rust.exe .\testing\A.txt .\testing\A_sorted.txt

write-host "java:"
java -cp .\java merge_sort .\testing\A.txt .\testing\A_sorted.txt

write-host "python:"
python .\python\merge_sort.py .\testing\A.txt .\testing\A_sorted.txt
