declare i32 @getint()
declare void @putint(i32)
declare void @putch(i32)
declare void @putstr(i8*)
@v_common = dso_local global i32 0
@v_commonInit = dso_local global i32 10
@c_array = dso_local constant [3 x i32] [i32 1, i32 2, i32 3]
@c_matrix = dso_local constant [3 x [3 x i32]] [[3 x i32] [i32 1, i32 2, i32 3], [3 x i32] [i32 1, i32 2, i32 3], [3 x i32] [i32 1, i32 2, i32 3]]
@v_array = dso_local global [3 x i32] zeroinitializer
@v_matrix = dso_local global [3 x [3 x i32]] zeroinitializer
@v_arrayInit = dso_local global [3 x i32] [i32 1, i32 2, i32 3]
@v_matrixInit = dso_local global [3 x [3 x i32]] [[3 x i32] [i32 1, i32 2, i32 3], [3 x i32] [i32 1, i32 2, i32 3], [3 x i32] [i32 1, i32 2, i32 3]]
define dso_local void @print(i32 %arg1){

B0:
    %def0 = alloca i32
    store i32 %arg1, i32* %def0
    call void @putch(i32 10)
    call void @putch(i32 102)
    call void @putch(i32 50)
    call void @putch(i32 40)
    call void @putch(i32 41)
    call void @putch(i32 32)
    call void @putch(i32 102)
    call void @putch(i32 108)
    call void @putch(i32 97)
    call void @putch(i32 103)
    call void @putch(i32 32)
    call void @putch(i32 58)
    call void @putch(i32 32)
    call void @putint(i32 %arg1)
    ret void
}

define dso_local i32 @f0(){

B1:
    ret i32 1
}

define dso_local i32 @f1(i32* %arg2, [3 x i32]* %arg3, i32* %arg4){

B2:
    %def1 = alloca i32*
    store i32* %arg4, i32* * %def1
    %def2 = alloca [3 x i32]*
    store [3 x i32]* %arg3, [3 x i32]* * %def2
    %def3 = alloca i32*
    store i32* %arg2, i32* * %def3
    %ld1 = load i32*, i32* * %def3
    %get0 = getelementptr i32, i32* %ld1, i32 0
    %ld2 = load i32, i32* %get0
    %ld3 = load [3 x i32]*, [3 x i32]* * %def2
    %get1 = getelementptr [3 x i32], [3 x i32]* %ld3, i32 0
    %get2 = getelementptr [3 x i32], [3 x i32]* %get1, i32 0, i32 0
    %ld4 = load i32, i32* %get2
    %calc0 = add i32 %ld2, %ld4
    %ld5 = load i32*, i32* * %def1
    %get3 = getelementptr i32, i32* %ld5, i32 0
    %ld6 = load i32, i32* %get3
    %calc1 = add i32 %calc0, %ld6
    ret i32 %calc1
}

define dso_local i32 @f2(i32 %arg5, i32 %arg6){

B3:
    %def4 = alloca i32
    store i32 %arg6, i32* %def4
    %def5 = alloca i32
    store i32 %arg5, i32* %def5
    %def8 = alloca [2 x i32]
    %get4 = getelementptr [2 x i32], [2 x i32]* %def8, i32 0, i32 0
    store i32 1, i32* %get4
    %get5 = getelementptr [2 x i32], [2 x i32]* %def8, i32 0, i32 1
    store i32 2, i32* %get5
    %def9 = alloca [3 x [3 x i32]]
    %get6 = getelementptr [3 x [3 x i32]], [3 x [3 x i32]]* %def9, i32 0, i32 0, i32 0
    store i32 1, i32* %get6
    %get7 = getelementptr [3 x [3 x i32]], [3 x [3 x i32]]* %def9, i32 0, i32 0, i32 1
    store i32 2, i32* %get7
    %get8 = getelementptr [3 x [3 x i32]], [3 x [3 x i32]]* %def9, i32 0, i32 0, i32 2
    store i32 3, i32* %get8
    %get9 = getelementptr [3 x [3 x i32]], [3 x [3 x i32]]* %def9, i32 0, i32 1, i32 0
    store i32 1, i32* %get9
    %get10 = getelementptr [3 x [3 x i32]], [3 x [3 x i32]]* %def9, i32 0, i32 1, i32 1
    store i32 2, i32* %get10
    %get11 = getelementptr [3 x [3 x i32]], [3 x [3 x i32]]* %def9, i32 0, i32 1, i32 2
    store i32 3, i32* %get11
    %get12 = getelementptr [3 x [3 x i32]], [3 x [3 x i32]]* %def9, i32 0, i32 2, i32 0
    store i32 1, i32* %get12
    %get13 = getelementptr [3 x [3 x i32]], [3 x [3 x i32]]* %def9, i32 0, i32 2, i32 1
    store i32 2, i32* %get13
    %get14 = getelementptr [3 x [3 x i32]], [3 x [3 x i32]]* %def9, i32 0, i32 2, i32 2
    store i32 3, i32* %get14
    %get16 = getelementptr [3 x [3 x i32]], [3 x [3 x i32]]* %def9, i32 0, i32 0
    %get18 = getelementptr [3 x i32], [3 x i32]* %get16, i32 0, i32 0
    %call14 = call i32 @f1(i32* %get4, [3 x i32]* %get16, i32* %get18)
    %call15 = call i32 @f0()
    %def18 = alloca i32
    %def19 = alloca i32
    %def20 = alloca i32
    %def21 = alloca i32
    br label %B4

B4:
    %cmp0 = icmp sgt i32 %arg5, 10
    %zot0 = zext i1 %cmp0 to i32
    %cmp1 = icmp ne i32 %zot0, 0
    store i32 0, i32* %def18
    br i1 %cmp1, label %B8, label %B9

B8:
    %cmp2 = icmp sgt i32 %arg6, 10
    %zot1 = zext i1 %cmp2 to i32
    %cmp3 = icmp ne i32 %zot1, 0
    store i32 0, i32* %def18
    br i1 %cmp3, label %B5, label %B9

B5:
    call void @print(i32 1)
    store i32 0, i32* %def18
    br label %B9

B9:
    %ld43 = load i32, i32* %def18
    %cmp4 = icmp sgt i32 %arg5, 10
    %zot2 = zext i1 %cmp4 to i32
    %cmp5 = icmp ne i32 %zot2, 0
    store i32 %ld43, i32* %def19
    br i1 %cmp5, label %B13, label %B14

B13:
    %cmp6 = icmp sle i32 %arg6, 10
    %zot3 = zext i1 %cmp6 to i32
    %cmp7 = icmp ne i32 %zot3, 0
    store i32 %ld43, i32* %def19
    br i1 %cmp7, label %B10, label %B14

B10:
    call void @print(i32 2)
    store i32 0, i32* %def19
    br label %B14

B14:
    %ld44 = load i32, i32* %def19
    %cmp8 = icmp sle i32 %arg5, 10
    %zot4 = zext i1 %cmp8 to i32
    %cmp9 = icmp ne i32 %zot4, 0
    store i32 %ld44, i32* %def20
    br i1 %cmp9, label %B18, label %B19

B18:
    %cmp10 = icmp sle i32 %arg6, 10
    %zot5 = zext i1 %cmp10 to i32
    %cmp11 = icmp ne i32 %zot5, 0
    store i32 %ld44, i32* %def20
    br i1 %cmp11, label %B15, label %B19

B15:
    call void @print(i32 3)
    store i32 1, i32* %def20
    br label %B19

B19:
    %ld45 = load i32, i32* %def20
    %cmp12 = icmp sle i32 %arg5, 10
    %zot6 = zext i1 %cmp12 to i32
    %cmp13 = icmp ne i32 %zot6, 0
    store i32 %ld45, i32* %def21
    br i1 %cmp13, label %B23, label %B22

B23:
    %cmp14 = icmp sgt i32 %arg6, 10
    %zot7 = zext i1 %cmp14 to i32
    %cmp15 = icmp ne i32 %zot7, 0
    store i32 %ld45, i32* %def21
    br i1 %cmp15, label %B20, label %B22

B20:
    call void @print(i32 4)
    store i32 1, i32* %def21
    br label %B22

B22:
    %ld46 = load i32, i32* %def21
    ret i32 %ld46
}

define dso_local i32 @main(){

B24:
    %call20 = call i32 @getint()
    %call21 = call i32 @getint()
    %def22 = alloca i32
    %def23 = alloca i32
    %def29 = alloca i32
    %def30 = alloca i32
    br label %B25

B25:
    %cmp16 = icmp ne i32 %call20, %call21
    store i32 %call20, i32* %def22
    store i32 %call21, i32* %def23
    store i32 0, i32* %def29
    br i1 %cmp16, label %B29, label %B28

B29:
    %ld54 = load i32, i32* %def29
    %ld48 = load i32, i32* %def23
    %ld47 = load i32, i32* %def22
    %cmp17 = icmp slt i32 %ld54, 3
    %zot8 = zext i1 %cmp17 to i32
    %cmp18 = icmp ne i32 %zot8, 0
    br i1 %cmp18, label %B30, label %B28

B30:
    %calc11 = add i32 %ld47, %ld48
    %calc12 = sub i32 %ld47, %ld48
    %call22 = call i32 @f2(i32 %calc11, i32 %calc12)
    %call23 = call i32 @f2(i32 %calc12, i32 %calc11)
    br label %B33

B33:
    %cmp19 = icmp eq i32 %call22, 0
    br i1 %cmp19, label %B34, label %B37

B37:
    %cmp20 = icmp eq i32 %call23, 0
    %zot9 = zext i1 %cmp20 to i32
    %cmp21 = icmp ne i32 %zot9, 0
    br i1 %cmp21, label %B34, label %B35

B34:
    store i32 0, i32* %def30
    br label %B36

B35:
    store i32 1, i32* %def30
    br label %B36

B36:
    %ld55 = load i32, i32* %def30
    call void @putch(i32 10)
    call void @putch(i32 102)
    call void @putch(i32 108)
    call void @putch(i32 97)
    call void @putch(i32 103)
    call void @putch(i32 32)
    call void @putch(i32 61)
    call void @putch(i32 32)
    call void @putint(i32 %ld55)
    call void @putch(i32 32)
    call void @putch(i32 58)
    call void @putch(i32 32)
    call void @putch(i32 99)
    call void @putch(i32 49)
    call void @putch(i32 32)
    call void @putch(i32 61)
    call void @putch(i32 32)
    call void @putint(i32 %calc11)
    call void @putch(i32 44)
    call void @putch(i32 32)
    call void @putch(i32 99)
    call void @putch(i32 50)
    call void @putch(i32 32)
    call void @putch(i32 61)
    call void @putch(i32 32)
    call void @putint(i32 %calc12)
    %calc13 = add i32 1, %ld54
    %calc14 = add i32 5, %ld47
    %calc15 = add i32 5, %ld48
    br label %B38

B38:
    %cmp22 = icmp sge i32 %calc13, 10
    %zot10 = zext i1 %cmp22 to i32
    %cmp23 = icmp ne i32 %zot10, 0
    store i32 %calc14, i32* %def22
    store i32 %calc15, i32* %def23
    store i32 %calc13, i32* %def29
    br i1 %cmp23, label %B29, label %B42

B42:
    %cmp24 = icmp slt i32 %calc13, 0
    %zot11 = zext i1 %cmp24 to i32
    %cmp25 = icmp ne i32 %zot11, 0
    store i32 %calc14, i32* %def22
    store i32 %calc15, i32* %def23
    store i32 %calc13, i32* %def29
    br i1 %cmp25, label %B28, label %B29

B28:
    ret i32 0
}


