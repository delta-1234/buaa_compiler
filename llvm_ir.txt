declare i32 @getint()
declare void @putint(i32)
declare void @putch(i32)
declare void @putstr(i8*)
define dso_local i32 @f(i32 %arg1, i32 %arg2, i32 %arg3, i32 %arg4, i32 %arg5, i32 %arg6, i32 %arg7, i32 %arg8, i32 %arg9, i32 %arg10, i32 %arg11, i32 %arg12, i32 %arg13, i32 %arg14, i32 %arg15){

B0:
    %def0 = alloca i32
    store i32 %arg15, i32* %def0
    %def1 = alloca i32
    store i32 %arg14, i32* %def1
    %def2 = alloca i32
    store i32 %arg13, i32* %def2
    %def3 = alloca i32
    store i32 %arg12, i32* %def3
    %def4 = alloca i32
    store i32 %arg11, i32* %def4
    %def5 = alloca i32
    store i32 %arg10, i32* %def5
    %def6 = alloca i32
    store i32 %arg9, i32* %def6
    %def7 = alloca i32
    store i32 %arg8, i32* %def7
    %def8 = alloca i32
    store i32 %arg7, i32* %def8
    %def9 = alloca i32
    store i32 %arg6, i32* %def9
    %def10 = alloca i32
    store i32 %arg5, i32* %def10
    %def11 = alloca i32
    store i32 %arg4, i32* %def11
    %def12 = alloca i32
    store i32 %arg3, i32* %def12
    %def13 = alloca i32
    store i32 %arg2, i32* %def13
    %def14 = alloca i32
    store i32 %arg1, i32* %def14
    %calc0 = add i32 %arg1, %arg2
    %calc1 = add i32 %calc0, %arg3
    %calc2 = add i32 %calc1, %arg4
    %calc3 = add i32 %calc2, %arg5
    %calc4 = add i32 %calc3, %arg6
    %calc5 = add i32 %calc4, %arg7
    %calc6 = add i32 %calc5, %arg8
    %calc7 = add i32 %calc6, %arg9
    %calc8 = add i32 %calc7, %arg10
    %calc9 = add i32 %calc8, %arg11
    %calc10 = add i32 %calc9, %arg12
    %calc11 = add i32 %calc10, %arg13
    %calc12 = add i32 %calc11, %arg14
    %calc13 = add i32 %calc12, %arg15
    ret i32 %calc13
}

define dso_local i32 @main(){

B1:
    %call0 = call i32 @f(i32 1, i32 2, i32 3, i32 4, i32 5, i32 6, i32 7, i32 8, i32 9, i32 10, i32 11, i32 12, i32 13, i32 14, i32 15)
    call void @putint(i32 %call0)
    call void @putch(i32 10)
    ret i32 0
}


