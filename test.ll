declare i32 @getint()
declare void @putint(i32)
declare void @putch(i32)
declare void @putstr(i8*)
@T = dso_local global i32 0
@Map = dso_local constant [2 x [2 x i32]] [[2 x i32] [i32 1, i32 1], [2 x i32] [i32 1, i32 0]]
@Bound = dso_local constant [2 x i32] [i32 2, i32 2]
@f = dso_local global [2 x i32] [i32 1, i32 0]
@ori = dso_local global [2 x [2 x i32]] [[2 x i32] [i32 1, i32 0], [2 x i32] [i32 0, i32 0]]
define dso_local void @calc([2 x i32]* %arg1, [2 x i32]* %arg2){
    %def0 = alloca [2 x i32]*
    store [2 x i32]* %arg2, [2 x i32]* * %def0
    %def1 = alloca [2 x i32]*
    store [2 x i32]* %arg1, [2 x i32]* * %def1
    %def2 = alloca i32
    store i32 0, i32* %def2
    %def3 = alloca i32
    store i32 0, i32* %def3
    %def4 = alloca i32
    store i32 0, i32* %def4
    %def5 = alloca [2 x [2 x i32]]
    br label %B1

B1:
    %ld0 = load i32, i32* %def2
    %cmp0 = icmp slt i32 %ld0, 2
    %zot0 = zext i1 %cmp0 to i32
    %cmp1 = icmp ne i32 %zot0, 0
    br i1 %cmp1, label %B2, label %B3

B2:
    store i32 0, i32* %def3
    br label %B5

B5:
    %ld1 = load i32, i32* %def3
    %cmp2 = icmp slt i32 %ld1, 2
    %zot1 = zext i1 %cmp2 to i32
    %cmp3 = icmp ne i32 %zot1, 0
    br i1 %cmp3, label %B6, label %B7

B6:
    store i32 0, i32* %def4
    %def6 = alloca i32
    store i32 0, i32* %def6
    br label %B9

B9:
    %ld2 = load i32, i32* %def4
    %cmp4 = icmp slt i32 %ld2, 2
    %zot2 = zext i1 %cmp4 to i32
    %cmp5 = icmp ne i32 %zot2, 0
    br i1 %cmp5, label %B10, label %B11

B10:
    %ld3 = load i32, i32* %def6
    %ld4 = load i32, i32* %def2
    %ld5 = load i32, i32* %def4
    %ld6 = load [2 x i32]*, [2 x i32]* * %def1
    %get0 = getelementptr [2 x i32], [2 x i32]* %ld6, i32 %ld4
    %get1 = getelementptr [2 x i32], [2 x i32]* %get0, i32 0, i32 %ld5
    %ld7 = load i32, i32* %get1
    %ld8 = load i32, i32* %def4
    %ld9 = load i32, i32* %def3
    %ld10 = load [2 x i32]*, [2 x i32]* * %def0
    %get2 = getelementptr [2 x i32], [2 x i32]* %ld10, i32 %ld8
    %get3 = getelementptr [2 x i32], [2 x i32]* %get2, i32 0, i32 %ld9
    %ld11 = load i32, i32* %get3
    %calc0 = mul i32 %ld7, %ld11
    %calc1 = srem i32 %calc0, 10007
    %calc2 = add i32 %ld3, %calc1
    %calc3 = srem i32 %calc2, 10007
    store i32 %calc3, i32* %def6
    %ld12 = load i32, i32* %def4
    %calc4 = add i32 1, %ld12
    store i32 %calc4, i32* %def4
    br label %B9

B11:
    %ld13 = load i32, i32* %def6
    %ld14 = load i32, i32* %def2
    %ld15 = load i32, i32* %def3
    %get4 = getelementptr [2 x [2 x i32]], [2 x [2 x i32]]* %def5, i32 0, i32 %ld14, i32 %ld15
    store i32 %ld13, i32* %get4
    %ld16 = load i32, i32* %def3
    %calc5 = add i32 1, %ld16
    store i32 %calc5, i32* %def3
    br label %B5

B7:
    %ld17 = load i32, i32* %def2
    %calc6 = add i32 1, %ld17
    store i32 %calc6, i32* %def2
    br label %B1

B3:
    store i32 0, i32* %def2
    store i32 0, i32* %def3
    br label %B13

B13:
    %ld18 = load i32, i32* %def2
    %cmp6 = icmp slt i32 %ld18, 2
    %zot3 = zext i1 %cmp6 to i32
    %cmp7 = icmp ne i32 %zot3, 0
    br i1 %cmp7, label %B17, label %B15

B17:
    %ld19 = load i32, i32* %def3
    %cmp8 = icmp slt i32 %ld19, 2
    %zot4 = zext i1 %cmp8 to i32
    %cmp9 = icmp ne i32 %zot4, 0
    br i1 %cmp9, label %B18, label %B19

B18:
    %ld20 = load i32, i32* %def2
    %ld21 = load i32, i32* %def3
    %get5 = getelementptr [2 x [2 x i32]], [2 x [2 x i32]]* %def5, i32 0, i32 %ld20, i32 %ld21
    %ld22 = load i32, i32* %get5
    %ld23 = load i32, i32* %def2
    %ld24 = load i32, i32* %def3
    %ld25 = load [2 x i32]*, [2 x i32]* * %def1
    %get6 = getelementptr [2 x i32], [2 x i32]* %ld25, i32 %ld23
    %get7 = getelementptr [2 x i32], [2 x i32]* %get6, i32 0, i32 %ld24
    store i32 %ld22, i32* %get7
    %ld26 = load i32, i32* %def3
    %calc7 = add i32 1, %ld26
    store i32 %calc7, i32* %def3
    br label %B17

B19:
    %ld27 = load i32, i32* %def2
    %calc8 = add i32 1, %ld27
    store i32 %calc8, i32* %def2
    br label %B13

B15:
    ret void
}

define dso_local i32 @getans(i32* %arg3){
    %def7 = alloca i32*
    store i32* %arg3, i32* * %def7
    %def8 = alloca i32
    store i32 0, i32* %def8
    %def9 = alloca i32
    store i32 0, i32* %def9
    br label %B22

B22:
    %ld28 = load i32, i32* %def8
    %cmp10 = icmp slt i32 %ld28, 2
    %zot5 = zext i1 %cmp10 to i32
    %cmp11 = icmp ne i32 %zot5, 0
    br i1 %cmp11, label %B23, label %B24

B23:
    %ld29 = load i32, i32* %def9
    %ld30 = load i32, i32* %def8
    %ld31 = load i32*, i32* * %def7
    %get8 = getelementptr i32, i32* %ld31, i32 %ld30
    %ld32 = load i32, i32* %get8
    %calc9 = add i32 %ld29, %ld32
    store i32 %calc9, i32* %def9
    %ld33 = load i32, i32* %def8
    %calc10 = add i32 1, %ld33
    store i32 %calc10, i32* %def8
    br label %B22

B24:
    %ld34 = load i32, i32* %def9
    ret i32 %ld34
}

define dso_local void @Copy([2 x i32]* %arg4){
    %def10 = alloca [2 x i32]*
    store [2 x i32]* %arg4, [2 x i32]* * %def10
    %ld35 = load [2 x i32]*, [2 x i32]* * %def10
    %get9 = getelementptr [2 x i32], [2 x i32]* %ld35, i32 0
    %get10 = getelementptr [2 x i32], [2 x i32]* %get9, i32 0, i32 0
    store i32 1, i32* %get10
    %ld36 = load [2 x i32]*, [2 x i32]* * %def10
    %get11 = getelementptr [2 x i32], [2 x i32]* %ld36, i32 0
    %get12 = getelementptr [2 x i32], [2 x i32]* %get11, i32 0, i32 1
    store i32 1, i32* %get12
    %ld37 = load [2 x i32]*, [2 x i32]* * %def10
    %get13 = getelementptr [2 x i32], [2 x i32]* %ld37, i32 1
    %get14 = getelementptr [2 x i32], [2 x i32]* %get13, i32 0, i32 0
    store i32 1, i32* %get14
    %ld38 = load [2 x i32]*, [2 x i32]* * %def10
    %get15 = getelementptr [2 x i32], [2 x i32]* %ld38, i32 1
    %get16 = getelementptr [2 x i32], [2 x i32]* %get15, i32 0, i32 1
    store i32 0, i32* %get16
    ret void
}

define dso_local i32 @main(){
    %get17 = getelementptr [2 x i32], [2 x i32]* @f, i32 0, i32 0
    %ld39 = load i32, i32* %get17
    call void @putint(i32 %ld39)
    call void @putch(i32 10)
    %call2 = call i32 @getint()
    store i32 %call2, i32* @T
    %def11 = alloca [2 x [2 x i32]]
    %get18 = getelementptr [2 x [2 x i32]], [2 x [2 x i32]]* %def11, i32 0, i32 0
    call void @Copy([2 x i32]* %get18)
    br label %B28

B28:
    %ld40 = load i32, i32* @T
    %cmp12 = icmp ne i32 %ld40, 0
    br i1 %cmp12, label %B32, label %B30

B32:
    %ld41 = load i32, i32* @T
    %calc11 = sdiv i32 %ld41, 2
    %calc12 = mul i32 %calc11, 2
    %ld42 = load i32, i32* @T
    %cmp13 = icmp ne i32 %calc12, %ld42
    br i1 %cmp13, label %B33, label %B35

B33:
    %get19 = getelementptr [2 x [2 x i32]], [2 x [2 x i32]]* @ori, i32 0, i32 0
    %get20 = getelementptr [2 x [2 x i32]], [2 x [2 x i32]]* %def11, i32 0, i32 0
    call void @calc([2 x i32]* %get19, [2 x i32]* %get20)
    br label %B35

B35:
    %ld43 = load i32, i32* @T
    %calc13 = sdiv i32 %ld43, 2
    store i32 %calc13, i32* @T
    %get21 = getelementptr [2 x [2 x i32]], [2 x [2 x i32]]* %def11, i32 0, i32 0
    %get22 = getelementptr [2 x [2 x i32]], [2 x [2 x i32]]* %def11, i32 0, i32 0
    call void @calc([2 x i32]* %get21, [2 x i32]* %get22)
    br label %B28

B30:
    %def12 = alloca i32
    store i32 0, i32* %def12
    br label %B36

B36:
    %ld44 = load i32, i32* %def12
    %cmp14 = icmp slt i32 %ld44, 2
    %zot6 = zext i1 %cmp14 to i32
    %cmp15 = icmp ne i32 %zot6, 0
    br i1 %cmp15, label %B37, label %B38

B37:
    call void @putch(i32 102)
    %ld45 = load i32, i32* %def12
    %get23 = getelementptr [2 x i32], [2 x i32]* @f, i32 0, i32 %ld45
    %ld46 = load i32, i32* %get23
    call void @putint(i32 %ld46)
    call void @putch(i32 58)
    call void @putch(i32 32)
    %ld47 = load i32, i32* %def12
    %get24 = getelementptr [2 x [2 x i32]], [2 x [2 x i32]]* @ori, i32 0, i32 %ld47
    %get25 = getelementptr [2 x i32], [2 x i32]* %get24, i32 0, i32 0
    %call10 = call i32 @getans(i32* %get25)
    call void @putint(i32 %call10)
    call void @putch(i32 10)
    %ld48 = load i32, i32* %def12
    %calc14 = add i32 1, %ld48
    store i32 %calc14, i32* %def12
    br label %B36

B38:
    call void @putch(i32 111)
    call void @putch(i32 114)
    call void @putch(i32 105)
    call void @putch(i32 48)
    call void @putch(i32 48)
    call void @putch(i32 58)
    %get26 = getelementptr [2 x [2 x i32]], [2 x [2 x i32]]* @ori, i32 0, i32 0, i32 0
    %ld49 = load i32, i32* %get26
    call void @putint(i32 %ld49)
    call void @putch(i32 10)
    ret i32 0
}


