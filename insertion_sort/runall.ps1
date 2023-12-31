Write-Host "`npython:"
Write-Host "Runtime: $(Measure-Command { python .\python\insertion_sort.py | Out-Default } | Select-Object -ExpandProperty TotalSeconds) seconds`n"

Write-Host "java:"
Write-Host "Runtime: $(Measure-Command { java -cp .\java insertion_sort | Out-Default } | Select-Object -ExpandProperty TotalSeconds) seconds`n"

Write-Host "rust:"
Write-Host "Runtime: $(Measure-Command { .\rust\target\release\rust.exe | Out-Default } | Select-Object -ExpandProperty TotalSeconds) seconds`n"