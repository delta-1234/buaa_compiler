; ModuleID = 'llvm-link'
source_filename = "llvm-link"
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

@c_common = dso_local constant i32 10, align 4
@c_array = dso_local constant [3 x i32] [i32 1, i32 2, i32 3], align 4
@c_matrix = dso_local constant [3 x [3 x i32]] [[3 x i32] [i32 1, i32 2, i32 3], [3 x i32] [i32 1, i32 2, i32 3], [3 x i32] [i32 1, i32 2, i32 3]], align 16
@v_commonInit = dso_local global i32 10, align 4
@v_arrayInit = dso_local global [3 x i32] [i32 1, i32 2, i32 3], align 4
@v_matrixInit = dso_local global [3 x [3 x i32]] [[3 x i32] [i32 1, i32 2, i32 3], [3 x i32] [i32 1, i32 2, i32 3], [3 x i32] [i32 1, i32 2, i32 3]], align 16
@.str = private unnamed_addr constant [16 x i8] c"\0Af2() flag : %d\00", align 1
@__const.f2.a1 = private unnamed_addr constant [2 x i32] [i32 1, i32 2], align 4
@__const.f2.a2 = private unnamed_addr constant [3 x [3 x i32]] [[3 x i32] [i32 1, i32 2, i32 3], [3 x i32] [i32 1, i32 2, i32 3], [3 x i32] [i32 1, i32 2, i32 3]], align 16
@.str.1 = private unnamed_addr constant [30 x i8] c"\0Aflag = %d : c1 = %d, c2 = %d\00", align 1
@v_common = dso_local global i32 0, align 4
@v_array = dso_local global [3 x i32] zeroinitializer, align 4
@v_matrix = dso_local global [3 x [3 x i32]] zeroinitializer, align 16
@.str.3 = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str.1.4 = private unnamed_addr constant [3 x i8] c"%c\00", align 1
@.str.2 = private unnamed_addr constant [4 x i8] c"%d:\00", align 1
@.str.3.5 = private unnamed_addr constant [4 x i8] c" %d\00", align 1
@.str.4 = private unnamed_addr constant [2 x i8] c"\0A\00", align 1
@.str.5 = private unnamed_addr constant [3 x i8] c"%s\00", align 1

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @print(i32 noundef %0) #0 {
  %2 = alloca i32, align 4
  store i32 %0, i32* %2, align 4
  %3 = load i32, i32* %2, align 4
  %4 = call i32 (i8*, ...) @printf(i8* noundef getelementptr inbounds ([16 x i8], [16 x i8]* @.str, i64 0, i64 0), i32 noundef %3)
  ret void
}

declare i32 @printf(i8* noundef, ...) #1

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @f0() #0 {
  ret i32 1
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @f1(i32* noundef %0, [3 x i32]* noundef %1, i32* noundef %2) #0 {
  %4 = alloca i32*, align 8
  %5 = alloca [3 x i32]*, align 8
  %6 = alloca i32*, align 8
  store i32* %0, i32** %4, align 8
  store [3 x i32]* %1, [3 x i32]** %5, align 8
  store i32* %2, i32** %6, align 8
  %7 = load i32*, i32** %4, align 8
  %8 = getelementptr inbounds i32, i32* %7, i64 0
  %9 = load i32, i32* %8, align 4
  %10 = load [3 x i32]*, [3 x i32]** %5, align 8
  %11 = getelementptr inbounds [3 x i32], [3 x i32]* %10, i64 0
  %12 = getelementptr inbounds [3 x i32], [3 x i32]* %11, i64 0, i64 0
  %13 = load i32, i32* %12, align 4
  %14 = add nsw i32 %9, %13
  %15 = load i32*, i32** %6, align 8
  %16 = getelementptr inbounds i32, i32* %15, i64 0
  %17 = load i32, i32* %16, align 4
  %18 = add nsw i32 %14, %17
  ret i32 %18
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @f2(i32 noundef %0, i32 noundef %1) #0 {
  %3 = alloca i32, align 4
  %4 = alloca i32, align 4
  %5 = alloca i32, align 4
  %6 = alloca i32, align 4
  %7 = alloca [2 x i32], align 4
  %8 = alloca [3 x [3 x i32]], align 16
  store i32 %0, i32* %3, align 4
  store i32 %1, i32* %4, align 4
  %9 = bitcast [2 x i32]* %7 to i8*
  call void @llvm.memcpy.p0i8.p0i8.i64(i8* align 4 %9, i8* align 4 bitcast ([2 x i32]* @__const.f2.a1 to i8*), i64 8, i1 false)
  %10 = bitcast [3 x [3 x i32]]* %8 to i8*
  call void @llvm.memcpy.p0i8.p0i8.i64(i8* align 16 %10, i8* align 16 bitcast ([3 x [3 x i32]]* @__const.f2.a2 to i8*), i64 36, i1 false)
  %11 = load i32, i32* %3, align 4
  %12 = load i32, i32* %4, align 4
  %13 = mul nsw i32 %11, %12
  %14 = sub nsw i32 0, %13
  %15 = load i32, i32* %3, align 4
  %16 = load i32, i32* %4, align 4
  %17 = sdiv i32 %15, %16
  %18 = add nsw i32 %14, %17
  %19 = load i32, i32* %3, align 4
  %20 = load i32, i32* %4, align 4
  %21 = srem i32 %19, %20
  %22 = add nsw i32 %18, %21
  %23 = sub nsw i32 %22, 1
  %24 = getelementptr inbounds [2 x i32], [2 x i32]* %7, i64 0, i64 0
  %25 = getelementptr inbounds [3 x [3 x i32]], [3 x [3 x i32]]* %8, i64 0, i64 0
  %26 = getelementptr inbounds [3 x [3 x i32]], [3 x [3 x i32]]* %8, i64 0, i64 0
  %27 = getelementptr inbounds [3 x i32], [3 x i32]* %26, i64 0, i64 0
  %28 = call i32 @f1(i32* noundef %24, [3 x i32]* noundef %25, i32* noundef %27)
  %29 = add nsw i32 %23, %28
  %30 = call i32 @f0()
  %31 = sub nsw i32 %29, %30
  store i32 %31, i32* %5, align 4
  %32 = load i32, i32* %3, align 4
  %33 = icmp sgt i32 %32, 10
  br i1 %33, label %34, label %38

34:                                               ; preds = %2
  %35 = load i32, i32* %4, align 4
  %36 = icmp sgt i32 %35, 10
  br i1 %36, label %37, label %38

37:                                               ; preds = %34
  call void @print(i32 noundef 1)
  store i32 0, i32* %6, align 4
  br label %38

38:                                               ; preds = %37, %34, %2
  %39 = load i32, i32* %3, align 4
  %40 = icmp sgt i32 %39, 10
  br i1 %40, label %41, label %45

41:                                               ; preds = %38
  %42 = load i32, i32* %4, align 4
  %43 = icmp sle i32 %42, 10
  br i1 %43, label %44, label %45

44:                                               ; preds = %41
  call void @print(i32 noundef 2)
  store i32 0, i32* %6, align 4
  br label %45

45:                                               ; preds = %44, %41, %38
  %46 = load i32, i32* %3, align 4
  %47 = icmp sle i32 %46, 10
  br i1 %47, label %48, label %52

48:                                               ; preds = %45
  %49 = load i32, i32* %4, align 4
  %50 = icmp sle i32 %49, 10
  br i1 %50, label %51, label %52

51:                                               ; preds = %48
  call void @print(i32 noundef 3)
  store i32 1, i32* %6, align 4
  br label %52

52:                                               ; preds = %51, %48, %45
  %53 = load i32, i32* %3, align 4
  %54 = icmp sle i32 %53, 10
  br i1 %54, label %55, label %59

55:                                               ; preds = %52
  %56 = load i32, i32* %4, align 4
  %57 = icmp sgt i32 %56, 10
  br i1 %57, label %58, label %59

58:                                               ; preds = %55
  call void @print(i32 noundef 4)
  store i32 1, i32* %6, align 4
  br label %59

59:                                               ; preds = %58, %55, %52
  %60 = load i32, i32* %6, align 4
  ret i32 %60
}

; Function Attrs: argmemonly nofree nounwind willreturn
declare void @llvm.memcpy.p0i8.p0i8.i64(i8* noalias nocapture writeonly, i8* noalias nocapture readonly, i64, i1 immarg) #2

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @main() #0 {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  %4 = alloca i32, align 4
  %5 = alloca i32, align 4
  %6 = alloca i32, align 4
  %7 = alloca i32, align 4
  %8 = alloca i32, align 4
  %9 = alloca i32, align 4
  store i32 0, i32* %1, align 4
  %10 = call i32 (...) bitcast (i32 ()* @getint to i32 (...)*)()
  store i32 %10, i32* %2, align 4
  %11 = call i32 (...) bitcast (i32 ()* @getint to i32 (...)*)()
  store i32 %11, i32* %3, align 4
  store i32 0, i32* %7, align 4
  %12 = load i32, i32* %2, align 4
  %13 = load i32, i32* %3, align 4
  %14 = icmp ne i32 %12, %13
  br i1 %14, label %15, label %59

15:                                               ; preds = %0
  br label %16

16:                                               ; preds = %57, %52, %15
  %17 = load i32, i32* %7, align 4
  %18 = icmp slt i32 %17, 3
  br i1 %18, label %19, label %58

19:                                               ; preds = %16
  %20 = load i32, i32* %2, align 4
  %21 = load i32, i32* %3, align 4
  %22 = add nsw i32 %20, %21
  store i32 %22, i32* %8, align 4
  %23 = load i32, i32* %2, align 4
  %24 = load i32, i32* %3, align 4
  %25 = sub nsw i32 %23, %24
  store i32 %25, i32* %9, align 4
  %26 = load i32, i32* %8, align 4
  %27 = load i32, i32* %9, align 4
  %28 = call i32 @f2(i32 noundef %26, i32 noundef %27)
  store i32 %28, i32* %4, align 4
  %29 = load i32, i32* %9, align 4
  %30 = load i32, i32* %8, align 4
  %31 = call i32 @f2(i32 noundef %29, i32 noundef %30)
  store i32 %31, i32* %5, align 4
  %32 = load i32, i32* %4, align 4
  %33 = icmp eq i32 %32, 0
  br i1 %33, label %37, label %34

34:                                               ; preds = %19
  %35 = load i32, i32* %5, align 4
  %36 = icmp ne i32 %35, 0
  br i1 %36, label %38, label %37

37:                                               ; preds = %34, %19
  store i32 0, i32* %6, align 4
  br label %39

38:                                               ; preds = %34
  store i32 1, i32* %6, align 4
  br label %39

39:                                               ; preds = %38, %37
  %40 = load i32, i32* %6, align 4
  %41 = load i32, i32* %8, align 4
  %42 = load i32, i32* %9, align 4
  %43 = call i32 (i8*, ...) @printf(i8* noundef getelementptr inbounds ([30 x i8], [30 x i8]* @.str.1, i64 0, i64 0), i32 noundef %40, i32 noundef %41, i32 noundef %42)
  %44 = load i32, i32* %7, align 4
  %45 = add nsw i32 %44, 1
  store i32 %45, i32* %7, align 4
  %46 = load i32, i32* %2, align 4
  %47 = add nsw i32 %46, 5
  store i32 %47, i32* %2, align 4
  %48 = load i32, i32* %3, align 4
  %49 = add nsw i32 %48, 5
  store i32 %49, i32* %3, align 4
  %50 = load i32, i32* %7, align 4
  %51 = icmp sge i32 %50, 10
  br i1 %51, label %52, label %53

52:                                               ; preds = %39
  br label %16, !llvm.loop !6

53:                                               ; preds = %39
  %54 = load i32, i32* %7, align 4
  %55 = icmp slt i32 %54, 0
  br i1 %55, label %56, label %57

56:                                               ; preds = %53
  br label %58

57:                                               ; preds = %53
  br label %16, !llvm.loop !6

58:                                               ; preds = %56, %16
  br label %59

59:                                               ; preds = %58, %0
  ret i32 0
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @getint() #0 {
  %1 = alloca i32, align 4
  %2 = call i32 (i8*, ...) @__isoc99_scanf(i8* noundef getelementptr inbounds ([3 x i8], [3 x i8]* @.str.3, i64 0, i64 0), i32* noundef %1)
  %3 = load i32, i32* %1, align 4
  ret i32 %3
}

declare i32 @__isoc99_scanf(i8* noundef, ...) #1

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @getch() #0 {
  %1 = alloca i8, align 1
  %2 = call i32 (i8*, ...) @__isoc99_scanf(i8* noundef getelementptr inbounds ([3 x i8], [3 x i8]* @.str.1.4, i64 0, i64 0), i8* noundef %1)
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
  %5 = call i32 (i8*, ...) @__isoc99_scanf(i8* noundef getelementptr inbounds ([3 x i8], [3 x i8]* @.str.3, i64 0, i64 0), i32* noundef %3)
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
  %15 = call i32 (i8*, ...) @__isoc99_scanf(i8* noundef getelementptr inbounds ([3 x i8], [3 x i8]* @.str.3, i64 0, i64 0), i32* noundef %14)
  br label %16

16:                                               ; preds = %10
  %17 = load i32, i32* %4, align 4
  %18 = add nsw i32 %17, 1
  store i32 %18, i32* %4, align 4
  br label %6, !llvm.loop !8

19:                                               ; preds = %6
  %20 = load i32, i32* %3, align 4
  ret i32 %20
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @putint(i32 noundef %0) #0 {
  %2 = alloca i32, align 4
  store i32 %0, i32* %2, align 4
  %3 = load i32, i32* %2, align 4
  %4 = call i32 (i8*, ...) @printf(i8* noundef getelementptr inbounds ([3 x i8], [3 x i8]* @.str.3, i64 0, i64 0), i32 noundef %3)
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @putch(i32 noundef %0) #0 {
  %2 = alloca i32, align 4
  store i32 %0, i32* %2, align 4
  %3 = load i32, i32* %2, align 4
  %4 = call i32 (i8*, ...) @printf(i8* noundef getelementptr inbounds ([3 x i8], [3 x i8]* @.str.1.4, i64 0, i64 0), i32 noundef %3)
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
  %18 = call i32 (i8*, ...) @printf(i8* noundef getelementptr inbounds ([4 x i8], [4 x i8]* @.str.3.5, i64 0, i64 0), i32 noundef %17)
  br label %19

19:                                               ; preds = %12
  %20 = load i32, i32* %5, align 4
  %21 = add nsw i32 %20, 1
  store i32 %21, i32* %5, align 4
  br label %8, !llvm.loop !9

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
attributes #2 = { argmemonly nofree nounwind willreturn }

!llvm.ident = !{!0, !0}
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
!9 = distinct !{!9, !7}
