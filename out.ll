; ModuleID = 'llvm-link'
source_filename = "llvm-link"
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

@T = dso_local global i32 0
@Map = dso_local constant [2 x [2 x i32]] [[2 x i32] [i32 1, i32 1], [2 x i32] [i32 1, i32 0]]
@Bound = dso_local constant [2 x i32] [i32 2, i32 2]
@f = dso_local global [2 x i32] [i32 1, i32 0]
@ori = dso_local global [2 x [2 x i32]] [[2 x i32] [i32 1, i32 0], [2 x i32] zeroinitializer]
@.str = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str.1 = private unnamed_addr constant [3 x i8] c"%c\00", align 1
@.str.2 = private unnamed_addr constant [4 x i8] c"%d:\00", align 1
@.str.3 = private unnamed_addr constant [4 x i8] c" %d\00", align 1
@.str.4 = private unnamed_addr constant [2 x i8] c"\0A\00", align 1
@.str.5 = private unnamed_addr constant [3 x i8] c"%s\00", align 1

define dso_local void @calc([2 x i32]* %arg1, [2 x i32]* %arg2) {
  %def0 = alloca [2 x i32]*, align 8
  store [2 x i32]* %arg2, [2 x i32]** %def0, align 8
  %def1 = alloca [2 x i32]*, align 8
  store [2 x i32]* %arg1, [2 x i32]** %def1, align 8
  %def2 = alloca i32, align 4
  store i32 0, i32* %def2, align 4
  %def3 = alloca i32, align 4
  store i32 0, i32* %def3, align 4
  %def4 = alloca i32, align 4
  store i32 0, i32* %def4, align 4
  %def5 = alloca [2 x [2 x i32]], align 4
  br label %B1

B1:                                               ; preds = %B7, %0
  %ld0 = load i32, i32* %def2, align 4
  %cmp0 = icmp slt i32 %ld0, 2
  %zot0 = zext i1 %cmp0 to i32
  %cmp1 = icmp ne i32 %zot0, 0
  br i1 %cmp1, label %B2, label %B3

B2:                                               ; preds = %B1
  store i32 0, i32* %def3, align 4
  br label %B5

B5:                                               ; preds = %B11, %B2
  %ld1 = load i32, i32* %def3, align 4
  %cmp2 = icmp slt i32 %ld1, 2
  %zot1 = zext i1 %cmp2 to i32
  %cmp3 = icmp ne i32 %zot1, 0
  br i1 %cmp3, label %B6, label %B7

B6:                                               ; preds = %B5
  store i32 0, i32* %def4, align 4
  %def6 = alloca i32, align 4
  store i32 0, i32* %def6, align 4
  br label %B9

B9:                                               ; preds = %B10, %B6
  %ld2 = load i32, i32* %def4, align 4
  %cmp4 = icmp slt i32 %ld2, 2
  %zot2 = zext i1 %cmp4 to i32
  %cmp5 = icmp ne i32 %zot2, 0
  br i1 %cmp5, label %B10, label %B11

B10:                                              ; preds = %B9
  %ld3 = load i32, i32* %def6, align 4
  %ld4 = load i32, i32* %def2, align 4
  %ld5 = load i32, i32* %def4, align 4
  %ld6 = load [2 x i32]*, [2 x i32]** %def1, align 8
  %get0 = getelementptr [2 x i32], [2 x i32]* %ld6, i32 %ld4
  %get1 = getelementptr [2 x i32], [2 x i32]* %get0, i32 0, i32 %ld5
  %ld7 = load i32, i32* %get1, align 4
  %ld8 = load i32, i32* %def4, align 4
  %ld9 = load i32, i32* %def3, align 4
  %ld10 = load [2 x i32]*, [2 x i32]** %def0, align 8
  %get2 = getelementptr [2 x i32], [2 x i32]* %ld10, i32 %ld8
  %get3 = getelementptr [2 x i32], [2 x i32]* %get2, i32 0, i32 %ld9
  %ld11 = load i32, i32* %get3, align 4
  %calc0 = mul i32 %ld7, %ld11
  %calc1 = srem i32 %calc0, 10007
  %calc2 = add i32 %ld3, %calc1
  %calc3 = srem i32 %calc2, 10007
  store i32 %calc3, i32* %def6, align 4
  %ld12 = load i32, i32* %def4, align 4
  %calc4 = add i32 1, %ld12
  store i32 %calc4, i32* %def4, align 4
  br label %B9

B11:                                              ; preds = %B9
  %ld13 = load i32, i32* %def6, align 4
  %ld14 = load i32, i32* %def2, align 4
  %ld15 = load i32, i32* %def3, align 4
  %get4 = getelementptr [2 x [2 x i32]], [2 x [2 x i32]]* %def5, i32 0, i32 %ld14, i32 %ld15
  store i32 %ld13, i32* %get4, align 4
  %ld16 = load i32, i32* %def3, align 4
  %calc5 = add i32 1, %ld16
  store i32 %calc5, i32* %def3, align 4
  br label %B5

B7:                                               ; preds = %B5
  %ld17 = load i32, i32* %def2, align 4
  %calc6 = add i32 1, %ld17
  store i32 %calc6, i32* %def2, align 4
  br label %B1

B3:                                               ; preds = %B1
  store i32 0, i32* %def2, align 4
  store i32 0, i32* %def3, align 4
  br label %B13

B13:                                              ; preds = %B19, %B3
  %ld18 = load i32, i32* %def2, align 4
  %cmp6 = icmp slt i32 %ld18, 2
  %zot3 = zext i1 %cmp6 to i32
  %cmp7 = icmp ne i32 %zot3, 0
  br i1 %cmp7, label %B17, label %B15

B17:                                              ; preds = %B18, %B13
  %ld19 = load i32, i32* %def3, align 4
  %cmp8 = icmp slt i32 %ld19, 2
  %zot4 = zext i1 %cmp8 to i32
  %cmp9 = icmp ne i32 %zot4, 0
  br i1 %cmp9, label %B18, label %B19

B18:                                              ; preds = %B17
  %ld20 = load i32, i32* %def2, align 4
  %ld21 = load i32, i32* %def3, align 4
  %get5 = getelementptr [2 x [2 x i32]], [2 x [2 x i32]]* %def5, i32 0, i32 %ld20, i32 %ld21
  %ld22 = load i32, i32* %get5, align 4
  %ld23 = load i32, i32* %def2, align 4
  %ld24 = load i32, i32* %def3, align 4
  %ld25 = load [2 x i32]*, [2 x i32]** %def1, align 8
  %get6 = getelementptr [2 x i32], [2 x i32]* %ld25, i32 %ld23
  %get7 = getelementptr [2 x i32], [2 x i32]* %get6, i32 0, i32 %ld24
  store i32 %ld22, i32* %get7, align 4
  %ld26 = load i32, i32* %def3, align 4
  %calc7 = add i32 1, %ld26
  store i32 %calc7, i32* %def3, align 4
  br label %B17

B19:                                              ; preds = %B17
  %ld27 = load i32, i32* %def2, align 4
  %calc8 = add i32 1, %ld27
  store i32 %calc8, i32* %def2, align 4
  br label %B13

B15:                                              ; preds = %B13
  ret void
}

define dso_local i32 @getans(i32* %arg3) {
  %def7 = alloca i32*, align 8
  store i32* %arg3, i32** %def7, align 8
  %def8 = alloca i32, align 4
  store i32 0, i32* %def8, align 4
  %def9 = alloca i32, align 4
  store i32 0, i32* %def9, align 4
  br label %B22

B22:                                              ; preds = %B23, %0
  %ld28 = load i32, i32* %def8, align 4
  %cmp10 = icmp slt i32 %ld28, 2
  %zot5 = zext i1 %cmp10 to i32
  %cmp11 = icmp ne i32 %zot5, 0
  br i1 %cmp11, label %B23, label %B24

B23:                                              ; preds = %B22
  %ld29 = load i32, i32* %def9, align 4
  %ld30 = load i32, i32* %def8, align 4
  %ld31 = load i32*, i32** %def7, align 8
  %get8 = getelementptr i32, i32* %ld31, i32 %ld30
  %ld32 = load i32, i32* %get8, align 4
  %calc9 = add i32 %ld29, %ld32
  store i32 %calc9, i32* %def9, align 4
  %ld33 = load i32, i32* %def8, align 4
  %calc10 = add i32 1, %ld33
  store i32 %calc10, i32* %def8, align 4
  br label %B22

B24:                                              ; preds = %B22
  %ld34 = load i32, i32* %def9, align 4
  ret i32 %ld34
}

define dso_local void @Copy([2 x i32]* %arg4) {
  %def10 = alloca [2 x i32]*, align 8
  store [2 x i32]* %arg4, [2 x i32]** %def10, align 8
  %ld35 = load [2 x i32]*, [2 x i32]** %def10, align 8
  %get9 = getelementptr [2 x i32], [2 x i32]* %ld35, i32 0
  %get10 = getelementptr [2 x i32], [2 x i32]* %get9, i32 0, i32 0
  store i32 1, i32* %get10, align 4
  %ld36 = load [2 x i32]*, [2 x i32]** %def10, align 8
  %get11 = getelementptr [2 x i32], [2 x i32]* %ld36, i32 0
  %get12 = getelementptr [2 x i32], [2 x i32]* %get11, i32 0, i32 1
  store i32 1, i32* %get12, align 4
  %ld37 = load [2 x i32]*, [2 x i32]** %def10, align 8
  %get13 = getelementptr [2 x i32], [2 x i32]* %ld37, i32 1
  %get14 = getelementptr [2 x i32], [2 x i32]* %get13, i32 0, i32 0
  store i32 1, i32* %get14, align 4
  %ld38 = load [2 x i32]*, [2 x i32]** %def10, align 8
  %get15 = getelementptr [2 x i32], [2 x i32]* %ld38, i32 1
  %get16 = getelementptr [2 x i32], [2 x i32]* %get15, i32 0, i32 1
  store i32 0, i32* %get16, align 4
  ret void
}

define dso_local i32 @main() {
  %get17 = getelementptr [2 x i32], [2 x i32]* @f, i32 0, i32 0
  %ld39 = load i32, i32* %get17, align 4
  call void @putint(i32 %ld39)
  call void @putch(i32 10)
  %call2 = call i32 @getint()
  store i32 %call2, i32* @T, align 4
  %def11 = alloca [2 x [2 x i32]], align 4
  %get18 = getelementptr [2 x [2 x i32]], [2 x [2 x i32]]* %def11, i32 0, i32 0
  call void @Copy([2 x i32]* %get18)
  br label %B28

B28:                                              ; preds = %B35, %0
  %ld40 = load i32, i32* @T, align 4
  %cmp12 = icmp ne i32 %ld40, 0
  br i1 %cmp12, label %B32, label %B30

B32:                                              ; preds = %B28
  %ld41 = load i32, i32* @T, align 4
  %calc11 = sdiv i32 %ld41, 2
  %calc12 = mul i32 %calc11, 2
  %ld42 = load i32, i32* @T, align 4
  %cmp13 = icmp ne i32 %calc12, %ld42
  br i1 %cmp13, label %B33, label %B35

B33:                                              ; preds = %B32
  %get19 = getelementptr [2 x [2 x i32]], [2 x [2 x i32]]* @ori, i32 0, i32 0
  %get20 = getelementptr [2 x [2 x i32]], [2 x [2 x i32]]* %def11, i32 0, i32 0
  call void @calc([2 x i32]* %get19, [2 x i32]* %get20)
  br label %B35

B35:                                              ; preds = %B33, %B32
  %ld43 = load i32, i32* @T, align 4
  %calc13 = sdiv i32 %ld43, 2
  store i32 %calc13, i32* @T, align 4
  %get21 = getelementptr [2 x [2 x i32]], [2 x [2 x i32]]* %def11, i32 0, i32 0
  %get22 = getelementptr [2 x [2 x i32]], [2 x [2 x i32]]* %def11, i32 0, i32 0
  call void @calc([2 x i32]* %get21, [2 x i32]* %get22)
  br label %B28

B30:                                              ; preds = %B28
  %def12 = alloca i32, align 4
  store i32 0, i32* %def12, align 4
  br label %B36

B36:                                              ; preds = %B37, %B30
  %ld44 = load i32, i32* %def12, align 4
  %cmp14 = icmp slt i32 %ld44, 2
  %zot6 = zext i1 %cmp14 to i32
  %cmp15 = icmp ne i32 %zot6, 0
  br i1 %cmp15, label %B37, label %B38

B37:                                              ; preds = %B36
  call void @putch(i32 102)
  %ld45 = load i32, i32* %def12, align 4
  %get23 = getelementptr [2 x i32], [2 x i32]* @f, i32 0, i32 %ld45
  %ld46 = load i32, i32* %get23, align 4
  call void @putint(i32 %ld46)
  call void @putch(i32 58)
  call void @putch(i32 32)
  %ld47 = load i32, i32* %def12, align 4
  %get24 = getelementptr [2 x [2 x i32]], [2 x [2 x i32]]* @ori, i32 0, i32 %ld47
  %get25 = getelementptr [2 x i32], [2 x i32]* %get24, i32 0, i32 0
  %call10 = call i32 @getans(i32* %get25)
  call void @putint(i32 %call10)
  call void @putch(i32 10)
  %ld48 = load i32, i32* %def12, align 4
  %calc14 = add i32 1, %ld48
  store i32 %calc14, i32* %def12, align 4
  br label %B36

B38:                                              ; preds = %B36
  call void @putch(i32 111)
  call void @putch(i32 114)
  call void @putch(i32 105)
  call void @putch(i32 48)
  call void @putch(i32 48)
  call void @putch(i32 58)
  %get26 = getelementptr [2 x [2 x i32]], [2 x [2 x i32]]* @ori, i32 0, i32 0, i32 0
  %ld49 = load i32, i32* %get26, align 4
  call void @putint(i32 %ld49)
  call void @putch(i32 10)
  ret i32 0
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @getint() #0 {
  %1 = alloca i32, align 4
  %2 = call i32 (i8*, ...) @__isoc99_scanf(i8* noundef getelementptr inbounds ([3 x i8], [3 x i8]* @.str, i64 0, i64 0), i32* noundef %1)
  %3 = load i32, i32* %1, align 4
  ret i32 %3
}

declare i32 @__isoc99_scanf(i8* noundef, ...) #1

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @getch() #0 {
  %1 = alloca i8, align 1
  %2 = call i32 (i8*, ...) @__isoc99_scanf(i8* noundef getelementptr inbounds ([3 x i8], [3 x i8]* @.str.1, i64 0, i64 0), i8* noundef %1)
  %3 = load i8, i8* %1, align 1
  %4 = sext i8 %3 to i32
  ret i32 %4
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @getarray(i32* noundef %0) #0 {
  %2 = alloca i32*, align 8
  %3 = alloca i32, align 4
  %4 = alloca i32, align 4
  store i32* %0, i32** %2, align 8
  %5 = call i32 (i8*, ...) @__isoc99_scanf(i8* noundef getelementptr inbounds ([3 x i8], [3 x i8]* @.str, i64 0, i64 0), i32* noundef %3)
  store i32 0, i32* %4, align 4
  br label %6

6:                                                ; preds = %16, %1
  %7 = load i32, i32* %4, align 4
  %8 = load i32, i32* %3, align 4
  %9 = icmp slt i32 %7, %8
  br i1 %9, label %10, label %19

10:                                               ; preds = %6
  %11 = load i32*, i32** %2, align 8
  %12 = load i32, i32* %4, align 4
  %13 = sext i32 %12 to i64
  %14 = getelementptr inbounds i32, i32* %11, i64 %13
  %15 = call i32 (i8*, ...) @__isoc99_scanf(i8* noundef getelementptr inbounds ([3 x i8], [3 x i8]* @.str, i64 0, i64 0), i32* noundef %14)
  br label %16

16:                                               ; preds = %10
  %17 = load i32, i32* %4, align 4
  %18 = add nsw i32 %17, 1
  store i32 %18, i32* %4, align 4
  br label %6, !llvm.loop !6

19:                                               ; preds = %6
  %20 = load i32, i32* %3, align 4
  ret i32 %20
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @putint(i32 noundef %0) #0 {
  %2 = alloca i32, align 4
  store i32 %0, i32* %2, align 4
  %3 = load i32, i32* %2, align 4
  %4 = call i32 (i8*, ...) @printf(i8* noundef getelementptr inbounds ([3 x i8], [3 x i8]* @.str, i64 0, i64 0), i32 noundef %3)
  ret void
}

declare i32 @printf(i8* noundef, ...) #1

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @putch(i32 noundef %0) #0 {
  %2 = alloca i32, align 4
  store i32 %0, i32* %2, align 4
  %3 = load i32, i32* %2, align 4
  %4 = call i32 (i8*, ...) @printf(i8* noundef getelementptr inbounds ([3 x i8], [3 x i8]* @.str.1, i64 0, i64 0), i32 noundef %3)
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @putarray(i32 noundef %0, i32* noundef %1) #0 {
  %3 = alloca i32, align 4
  %4 = alloca i32*, align 8
  %5 = alloca i32, align 4
  store i32 %0, i32* %3, align 4
  store i32* %1, i32** %4, align 8
  %6 = load i32, i32* %3, align 4
  %7 = call i32 (i8*, ...) @printf(i8* noundef getelementptr inbounds ([4 x i8], [4 x i8]* @.str.2, i64 0, i64 0), i32 noundef %6)
  store i32 0, i32* %5, align 4
  br label %8

8:                                                ; preds = %19, %2
  %9 = load i32, i32* %5, align 4
  %10 = load i32, i32* %3, align 4
  %11 = icmp slt i32 %9, %10
  br i1 %11, label %12, label %22

12:                                               ; preds = %8
  %13 = load i32*, i32** %4, align 8
  %14 = load i32, i32* %5, align 4
  %15 = sext i32 %14 to i64
  %16 = getelementptr inbounds i32, i32* %13, i64 %15
  %17 = load i32, i32* %16, align 4
  %18 = call i32 (i8*, ...) @printf(i8* noundef getelementptr inbounds ([4 x i8], [4 x i8]* @.str.3, i64 0, i64 0), i32 noundef %17)
  br label %19

19:                                               ; preds = %12
  %20 = load i32, i32* %5, align 4
  %21 = add nsw i32 %20, 1
  store i32 %21, i32* %5, align 4
  br label %8, !llvm.loop !8

22:                                               ; preds = %8
  %23 = call i32 (i8*, ...) @printf(i8* noundef getelementptr inbounds ([2 x i8], [2 x i8]* @.str.4, i64 0, i64 0))
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @putstr(i8* noundef %0) #0 {
  %2 = alloca i8*, align 8
  store i8* %0, i8** %2, align 8
  %3 = load i8*, i8** %2, align 8
  %4 = call i32 (i8*, ...) @printf(i8* noundef getelementptr inbounds ([3 x i8], [3 x i8]* @.str.5, i64 0, i64 0), i8* noundef %3)
  ret void
}

attributes #0 = { noinline nounwind optnone uwtable "frame-pointer"="all" "min-legal-vector-width"="0" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "tune-cpu"="generic" }
attributes #1 = { "frame-pointer"="all" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "tune-cpu"="generic" }

!llvm.ident = !{!0}
!llvm.module.flags = !{!1, !2, !3, !4, !5}

!0 = !{!"Ubuntu clang version 14.0.0-1ubuntu1.1"}
!1 = !{i32 1, !"wchar_size", i32 4}
!2 = !{i32 7, !"PIC Level", i32 2}
!3 = !{i32 7, !"PIE Level", i32 2}
!4 = !{i32 7, !"uwtable", i32 1}
!5 = !{i32 7, !"frame-pointer", i32 2}
!6 = distinct !{!6, !7}
!7 = !{!"llvm.loop.mustprogress"}
!8 = distinct !{!8, !7}
