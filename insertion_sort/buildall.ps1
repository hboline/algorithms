#compile the java and rust source codes
Write-Output "building..."

#java
javac .\java\insertion_sort.java

#rust
Set-Location .\rust\; cargo build -q --release; Set-Location ..

Write-Output "done."