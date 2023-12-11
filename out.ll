; ModuleID = 'llvm-link'
source_filename = "llvm-link"
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

@v_common = dso_local global i32 0
@v_commonInit = dso_local global i32 10
@c_array = dso_local constant [3 x i32] [i32 1, i32 2, i32 3]
@c_matrix = dso_local constant [3 x [3 x i32]] [[3 x i32] [i32 1, i32 2, i32 3], [3 x i32] [i32 1, i32 2, i32 3], [3 x i32] [i32 1, i32 2, i32 3]]
@v_array = dso_local global [3 x i32] zeroinitializer
@v_matrix = dso_local global [3 x [3 x i32]] zeroinitializer
@v_arrayInit = dso_local global [3 x i32] [i32 1, i32 2, i32 3]
@v_matrixInit = dso_local global [3 x [3 x i32]] [[3 x i32] [i32 1, i32 2, i32 3], [3 x i32] [i32 1, i32 2, i32 3], [3 x i32] [i32 1, i32 2, i32 3]]
@.str = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str.1 = private unnamed_addr constant [3 x i8] c"%c\00", align 1
@.str.2 = private unnamed_addr constant [4 x i8] c"%d:\00", align 1
@.str.3 = private unnamed_addr constant [4 x i8] c" %d\00", align 1
@.str.4 = private unnamed_addr constant [2 x i8] c"\0A\00", align 1
@.str.5 = private unnamed_addr constant [3 x i8] c"%s\00", align 1

define dso_local void @print(i32 %arg1) {
B0:
  %def0 = alloca i32, align 4
  store i32 %arg1, i32* %def0, align 4
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

define dso_local i32 @f0() {
B1:
  ret i32 1
}

define dso_local i32 @f1(i32* %arg2, [3 x i32]* %arg3, i32* %arg4) {
B2:
  %def1 = alloca i32*, align 8
  store i32* %arg4, i32** %def1, align 8
  %def2 = alloca [3 x i32]*, align 8
  store [3 x i32]* %arg3, [3 x i32]** %def2, align 8
  %def3 = alloca i32*, align 8
  store i32* %arg2, i32** %def3, align 8
  %ld1 = load i32*, i32** %def3, align 8
  %get0 = getelementptr i32, i32* %ld1, i32 0
  %ld2 = load i32, i32* %get0, align 4
  %ld3 = load [3 x i32]*, [3 x i32]** %def2, align 8
  %get1 = getelementptr [3 x i32], [3 x i32]* %ld3, i32 0
  %get2 = getelementptr [3 x i32], [3 x i32]* %get1, i32 0, i32 0
  %ld4 = load i32, i32* %get2, align 4
  %calc0 = add i32 %ld2, %ld4
  %ld5 = load i32*, i32** %def1, align 8
  %get3 = getelementptr i32, i32* %ld5, i32 0
  %ld6 = load i32, i32* %get3, align 4
  %calc1 = add i32 %calc0, %ld6
  ret i32 %calc1
}

define dso_local i32 @f2(i32 %arg5, i32 %arg6) {
B3:
  %def4 = alloca i32, align 4
  store i32 %arg6, i32* %def4, align 4
  %def5 = alloca i32, align 4
  store i32 %arg5, i32* %def5, align 4
  %def8 = alloca [2 x i32], align 4
  %get4 = getelementptr [2 x i32], [2 x i32]* %def8, i32 0, i32 0
  store i32 1, i32* %get4, align 4
  %get5 = getelementptr [2 x i32], [2 x i32]* %def8, i32 0, i32 1
  store i32 2, i32* %get5, align 4
  %def9 = alloca [3 x [3 x i32]], align 4
  %get6 = getelementptr [3 x [3 x i32]], [3 x [3 x i32]]* %def9, i32 0, i32 0, i32 0
  store i32 1, i32* %get6, align 4
  %get7 = getelementptr [3 x [3 x i32]], [3 x [3 x i32]]* %def9, i32 0, i32 0, i32 1
  store i32 2, i32* %get7, align 4
  %get8 = getelementptr [3 x [3 x i32]], [3 x [3 x i32]]* %def9, i32 0, i32 0, i32 2
  store i32 3, i32* %get8, align 4
  %get9 = getelementptr [3 x [3 x i32]], [3 x [3 x i32]]* %def9, i32 0, i32 1, i32 0
  store i32 1, i32* %get9, align 4
  %get10 = getelementptr [3 x [3 x i32]], [3 x [3 x i32]]* %def9, i32 0, i32 1, i32 1
  store i32 2, i32* %get10, align 4
  %get11 = getelementptr [3 x [3 x i32]], [3 x [3 x i32]]* %def9, i32 0, i32 1, i32 2
  store i32 3, i32* %get11, align 4
  %get12 = getelementptr [3 x [3 x i32]], [3 x [3 x i32]]* %def9, i32 0, i32 2, i32 0
  store i32 1, i32* %get12, align 4
  %get13 = getelementptr [3 x [3 x i32]], [3 x [3 x i32]]* %def9, i32 0, i32 2, i32 1
  store i32 2, i32* %get13, align 4
  %get14 = getelementptr [3 x [3 x i32]], [3 x [3 x i32]]* %def9, i32 0, i32 2, i32 2
  store i32 3, i32* %get14, align 4
  %get16 = getelementptr [3 x [3 x i32]], [3 x [3 x i32]]* %def9, i32 0, i32 0
  %get18 = getelementptr [3 x i32], [3 x i32]* %get16, i32 0, i32 0
  %call14 = call i32 @f1(i32* %get4, [3 x i32]* %get16, i32* %get18)
  %call15 = call i32 @f0()
  %def18 = alloca i32, align 4
  %def19 = alloca i32, align 4
  %def20 = alloca i32, align 4
  %def21 = alloca i32, align 4
  br label %B4

B4:                                               ; preds = %B3
  %cmp0 = icmp sgt i32 %arg5, 10
  %zot0 = zext i1 %cmp0 to i32
  %cmp1 = icmp ne i32 %zot0, 0
  store i32 0, i32* %def18, align 4
  br i1 %cmp1, label %B8, label %B9

B8:                                               ; preds = %B4
  %cmp2 = icmp sgt i32 %arg6, 10
  %zot1 = zext i1 %cmp2 to i32
  %cmp3 = icmp ne i32 %zot1, 0
  store i32 0, i32* %def18, align 4
  br i1 %cmp3, label %B5, label %B9

B5:                                               ; preds = %B8
  call void @print(i32 1)
  store i32 0, i32* %def18, align 4
  br label %B9

B9:                                               ; preds = %B5, %B8, %B4
  %ld43 = load i32, i32* %def18, align 4
  %cmp4 = icmp sgt i32 %arg5, 10
  %zot2 = zext i1 %cmp4 to i32
  %cmp5 = icmp ne i32 %zot2, 0
  store i32 %ld43, i32* %def19, align 4
  br i1 %cmp5, label %B13, label %B14

B13:                                              ; preds = %B9
  %cmp6 = icmp sle i32 %arg6, 10
  %zot3 = zext i1 %cmp6 to i32
  %cmp7 = icmp ne i32 %zot3, 0
  store i32 %ld43, i32* %def19, align 4
  br i1 %cmp7, label %B10, label %B14

B10:                                              ; preds = %B13
  call void @print(i32 2)
  store i32 0, i32* %def19, align 4
  br label %B14

B14:                                              ; preds = %B10, %B13, %B9
  %ld44 = load i32, i32* %def19, align 4
  %cmp8 = icmp sle i32 %arg5, 10
  %zot4 = zext i1 %cmp8 to i32
  %cmp9 = icmp ne i32 %zot4, 0
  store i32 %ld44, i32* %def20, align 4
  br i1 %cmp9, label %B18, label %B19

B18:                                              ; preds = %B14
  %cmp10 = icmp sle i32 %arg6, 10
  %zot5 = zext i1 %cmp10 to i32
  %cmp11 = icmp ne i32 %zot5, 0
  store i32 %ld44, i32* %def20, align 4
  br i1 %cmp11, label %B15, label %B19

B15:                                              ; preds = %B18
  call void @print(i32 3)
  store i32 1, i32* %def20, align 4
  br label %B19

B19:                                              ; preds = %B15, %B18, %B14
  %ld45 = load i32, i32* %def20, align 4
  %cmp12 = icmp sle i32 %arg5, 10
  %zot6 = zext i1 %cmp12 to i32
  %cmp13 = icmp ne i32 %zot6, 0
  store i32 %ld45, i32* %def21, align 4
  br i1 %cmp13, label %B23, label %B22

B23:                                              ; preds = %B19
  %cmp14 = icmp sgt i32 %arg6, 10
  %zot7 = zext i1 %cmp14 to i32
  %cmp15 = icmp ne i32 %zot7, 0
  store i32 %ld45, i32* %def21, align 4
  br i1 %cmp15, label %B20, label %B22

B20:                                              ; preds = %B23
  call void @print(i32 4)
  store i32 1, i32* %def21, align 4
  br label %B22

B22:                                              ; preds = %B20, %B23, %B19
  %ld46 = load i32, i32* %def21, align 4
  ret i32 %ld46
}

define dso_local i32 @main() {
B24:
  %call20 = call i32 @getint()
  %call21 = call i32 @getint()
  %def22 = alloca i32, align 4
  %def23 = alloca i32, align 4
  %def29 = alloca i32, align 4
  %def30 = alloca i32, align 4
  br label %B25

B25:                                              ; preds = %B24
  %cmp16 = icmp ne i32 %call20, %call21
  store i32 %call20, i32* %def22, align 4
  store i32 %call21, i32* %def23, align 4
  store i32 0, i32* %def29, align 4
  br i1 %cmp16, label %B29, label %B28

B29:                                              ; preds = %B42, %B38, %B25
  %ld54 = load i32, i32* %def29, align 4
  %ld48 = load i32, i32* %def23, align 4
  %ld47 = load i32, i32* %def22, align 4
  %cmp17 = icmp slt i32 %ld54, 3
  %zot8 = zext i1 %cmp17 to i32
  %cmp18 = icmp ne i32 %zot8, 0
  br i1 %cmp18, label %B30, label %B28

B30:                                              ; preds = %B29
  %calc11 = add i32 %ld47, %ld48
  %calc12 = sub i32 %ld47, %ld48
  %call22 = call i32 @f2(i32 %calc11, i32 %calc12)
  %call23 = call i32 @f2(i32 %calc12, i32 %calc11)
  br label %B33

B33:                                              ; preds = %B30
  %cmp19 = icmp eq i32 %call22, 0
  br i1 %cmp19, label %B34, label %B37

B37:                                              ; preds = %B33
  %cmp20 = icmp eq i32 %call23, 0
  %zot9 = zext i1 %cmp20 to i32
  %cmp21 = icmp ne i32 %zot9, 0
  br i1 %cmp21, label %B34, label %B35

B34:                                              ; preds = %B37, %B33
  store i32 0, i32* %def30, align 4
  br label %B36

B35:                                              ; preds = %B37
  store i32 1, i32* %def30, align 4
  br label %B36

B36:                                              ; preds = %B35, %B34
  %ld55 = load i32, i32* %def30, align 4
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

B38:                                              ; preds = %B36
  %cmp22 = icmp sge i32 %calc13, 10
  %zot10 = zext i1 %cmp22 to i32
  %cmp23 = icmp ne i32 %zot10, 0
  store i32 %calc14, i32* %def22, align 4
  store i32 %calc15, i32* %def23, align 4
  store i32 %calc13, i32* %def29, align 4
  br i1 %cmp23, label %B29, label %B42

B42:                                              ; preds = %B38
  %cmp24 = icmp slt i32 %calc13, 0
  %zot11 = zext i1 %cmp24 to i32
  %cmp25 = icmp ne i32 %zot11, 0
  store i32 %calc14, i32* %def22, align 4
  store i32 %calc15, i32* %def23, align 4
  store i32 %calc13, i32* %def29, align 4
  br i1 %cmp25, label %B28, label %B29

B28:                                              ; preds = %B42, %B29, %B25
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
