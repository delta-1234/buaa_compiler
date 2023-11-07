clang -emit-llvm -S libsysy.c -o lib.ll
cat testfile.txt > main.c
clang -emit-llvm -S main.c -o main.ll -O0
llvm-link main.ll lib.ll -S -o ans.ll
cat llvm_ir.txt > test.ll
llvm-link test.ll lib.ll -S -o out.ll