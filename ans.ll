; ModuleID = 'llvm-link'
source_filename = "llvm-link"
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

@Mod = dso_local constant i32 10007, align 4
@Map = dso_local constant [2 x [2 x i32]] [[2 x i32] [i32 1, i32 1], [2 x i32] [i32 1, i32 0]], align 16
@Bound = dso_local constant [2 x i32] [i32 2, i32 2], align 4
@f = dso_local global [2 x i32] [i32 1, i32 0], align 4
@ori = dso_local global [2 x [2 x i32]] [[2 x i32] [i32 1, i32 0], [2 x i32] zeroinitializer], align 16
@.str = private unnamed_addr constant [4 x i8] c"%d\0A\00", align 1
@T = dso_local global i32 0, align 4
@.str.1 = private unnamed_addr constant [9 x i8] c"f%d: %d\0A\00", align 1
@.str.2 = private unnamed_addr constant [10 x i8] c"ori00:%d\0A\00", align 1
@.str.3 = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str.1.4 = private unnamed_addr constant [3 x i8] c"%c\00", align 1
@.str.2.5 = private unnamed_addr constant [4 x i8] c"%d:\00", align 1
@.str.3.6 = private unnamed_addr constant [4 x i8] c" %d\00", align 1
@.str.4 = private unnamed_addr constant [2 x i8] c"\0A\00", align 1
@.str.5 = private unnamed_addr constant [3 x i8] c"%s\00", align 1

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @calc([2 x i32]* noundef %0, [2 x i32]* noundef %1) #0 {
  %3 = alloca [2 x i32]*, align 8
  %4 = alloca [2 x i32]*, align 8
  %5 = alloca i32, align 4
  %6 = alloca i32, align 4
  %7 = alloca i32, align 4
  %8 = alloca [2 x [2 x i32]], align 16
  %9 = alloca i32, align 4
  store [2 x i32]* %0, [2 x i32]** %3, align 8
  store [2 x i32]* %1, [2 x i32]** %4, align 8
  store i32 0, i32* %5, align 4
  store i32 0, i32* %6, align 4
  store i32 0, i32* %7, align 4
  br label %10

10:                                               ; preds = %58, %2
  %11 = load i32, i32* %5, align 4
  %12 = load i32, i32* getelementptr inbounds ([2 x i32], [2 x i32]* @Bound, i64 0, i64 0), align 4
  %13 = icmp slt i32 %11, %12
  br i1 %13, label %14, label %61

14:                                               ; preds = %10
  store i32 0, i32* %6, align 4
  br label %15

15:                                               ; preds = %48, %14
  %16 = load i32, i32* %6, align 4
  %17 = load i32, i32* getelementptr inbounds ([2 x i32], [2 x i32]* @Bound, i64 0, i64 1), align 4
  %18 = icmp slt i32 %16, %17
  br i1 %18, label %19, label %58

19:                                               ; preds = %15
  store i32 0, i32* %7, align 4
  store i32 0, i32* %9, align 4
  br label %20

20:                                               ; preds = %24, %19
  %21 = load i32, i32* %7, align 4
  %22 = load i32, i32* getelementptr inbounds ([2 x i32], [2 x i32]* @Bound, i64 0, i64 0), align 4
  %23 = icmp slt i32 %21, %22
  br i1 %23, label %24, label %48

24:                                               ; preds = %20
  %25 = load i32, i32* %9, align 4
  %26 = load [2 x i32]*, [2 x i32]** %3, align 8
  %27 = load i32, i32* %5, align 4
  %28 = sext i32 %27 to i64
  %29 = getelementptr inbounds [2 x i32], [2 x i32]* %26, i64 %28
  %30 = load i32, i32* %7, align 4
  %31 = sext i32 %30 to i64
  %32 = getelementptr inbounds [2 x i32], [2 x i32]* %29, i64 0, i64 %31
  %33 = load i32, i32* %32, align 4
  %34 = load [2 x i32]*, [2 x i32]** %4, align 8
  %35 = load i32, i32* %7, align 4
  %36 = sext i32 %35 to i64
  %37 = getelementptr inbounds [2 x i32], [2 x i32]* %34, i64 %36
  %38 = load i32, i32* %6, align 4
  %39 = sext i32 %38 to i64
  %40 = getelementptr inbounds [2 x i32], [2 x i32]* %37, i64 0, i64 %39
  %41 = load i32, i32* %40, align 4
  %42 = mul nsw i32 %33, %41
  %43 = srem i32 %42, 10007
  %44 = add nsw i32 %25, %43
  %45 = srem i32 %44, 10007
  store i32 %45, i32* %9, align 4
  %46 = load i32, i32* %7, align 4
  %47 = add nsw i32 %46, 1
  store i32 %47, i32* %7, align 4
  br label %20, !llvm.loop !6

48:                                               ; preds = %20
  %49 = load i32, i32* %9, align 4
  %50 = load i32, i32* %5, align 4
  %51 = sext i32 %50 to i64
  %52 = getelementptr inbounds [2 x [2 x i32]], [2 x [2 x i32]]* %8, i64 0, i64 %51
  %53 = load i32, i32* %6, align 4
  %54 = sext i32 %53 to i64
  %55 = getelementptr inbounds [2 x i32], [2 x i32]* %52, i64 0, i64 %54
  store i32 %49, i32* %55, align 4
  %56 = load i32, i32* %6, align 4
  %57 = add nsw i32 %56, 1
  store i32 %57, i32* %6, align 4
  br label %15, !llvm.loop !8

58:                                               ; preds = %15
  %59 = load i32, i32* %5, align 4
  %60 = add nsw i32 %59, 1
  store i32 %60, i32* %5, align 4
  br label %10, !llvm.loop !9

61:                                               ; preds = %10
  store i32 0, i32* %5, align 4
  store i32 0, i32* %6, align 4
  br label %62

62:                                               ; preds = %88, %61
  %63 = load i32, i32* %5, align 4
  %64 = load i32, i32* getelementptr inbounds ([2 x i32], [2 x i32]* @Bound, i64 0, i64 0), align 4
  %65 = icmp slt i32 %63, %64
  br i1 %65, label %66, label %91

66:                                               ; preds = %62
  br label %67

67:                                               ; preds = %71, %66
  %68 = load i32, i32* %6, align 4
  %69 = load i32, i32* getelementptr inbounds ([2 x i32], [2 x i32]* @Bound, i64 0, i64 1), align 4
  %70 = icmp slt i32 %68, %69
  br i1 %70, label %71, label %88

71:                                               ; preds = %67
  %72 = load i32, i32* %5, align 4
  %73 = sext i32 %72 to i64
  %74 = getelementptr inbounds [2 x [2 x i32]], [2 x [2 x i32]]* %8, i64 0, i64 %73
  %75 = load i32, i32* %6, align 4
  %76 = sext i32 %75 to i64
  %77 = getelementptr inbounds [2 x i32], [2 x i32]* %74, i64 0, i64 %76
  %78 = load i32, i32* %77, align 4
  %79 = load [2 x i32]*, [2 x i32]** %3, align 8
  %80 = load i32, i32* %5, align 4
  %81 = sext i32 %80 to i64
  %82 = getelementptr inbounds [2 x i32], [2 x i32]* %79, i64 %81
  %83 = load i32, i32* %6, align 4
  %84 = sext i32 %83 to i64
  %85 = getelementptr inbounds [2 x i32], [2 x i32]* %82, i64 0, i64 %84
  store i32 %78, i32* %85, align 4
  %86 = load i32, i32* %6, align 4
  %87 = add nsw i32 %86, 1
  store i32 %87, i32* %6, align 4
  br label %67, !llvm.loop !10

88:                                               ; preds = %67
  %89 = load i32, i32* %5, align 4
  %90 = add nsw i32 %89, 1
  store i32 %90, i32* %5, align 4
  br label %62, !llvm.loop !11

91:                                               ; preds = %62
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @getans(i32* noundef %0) #0 {
  %2 = alloca i32*, align 8
  %3 = alloca i32, align 4
  %4 = alloca i32, align 4
  store i32* %0, i32** %2, align 8
  store i32 0, i32* %3, align 4
  store i32 0, i32* %4, align 4
  br label %5

5:                                                ; preds = %9, %1
  %6 = load i32, i32* %3, align 4
  %7 = load i32, i32* getelementptr inbounds ([2 x i32], [2 x i32]* @Bound, i64 0, i64 0), align 4
  %8 = icmp slt i32 %6, %7
  br i1 %8, label %9, label %19

9:                                                ; preds = %5
  %10 = load i32, i32* %4, align 4
  %11 = load i32*, i32** %2, align 8
  %12 = load i32, i32* %3, align 4
  %13 = sext i32 %12 to i64
  %14 = getelementptr inbounds i32, i32* %11, i64 %13
  %15 = load i32, i32* %14, align 4
  %16 = add nsw i32 %10, %15
  store i32 %16, i32* %4, align 4
  %17 = load i32, i32* %3, align 4
  %18 = add nsw i32 %17, 1
  store i32 %18, i32* %3, align 4
  br label %5, !llvm.loop !12

19:                                               ; preds = %5
  %20 = load i32, i32* %4, align 4
  ret i32 %20
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @Copy([2 x i32]* noundef %0) #0 {
  %2 = alloca [2 x i32]*, align 8
  store [2 x i32]* %0, [2 x i32]** %2, align 8
  %3 = load i32, i32* getelementptr inbounds ([2 x [2 x i32]], [2 x [2 x i32]]* @Map, i64 0, i64 0, i64 0), align 16
  %4 = load [2 x i32]*, [2 x i32]** %2, align 8
  %5 = getelementptr inbounds [2 x i32], [2 x i32]* %4, i64 0
  %6 = getelementptr inbounds [2 x i32], [2 x i32]* %5, i64 0, i64 0
  store i32 %3, i32* %6, align 4
  %7 = load i32, i32* getelementptr inbounds ([2 x [2 x i32]], [2 x [2 x i32]]* @Map, i64 0, i64 0, i64 1), align 4
  %8 = load [2 x i32]*, [2 x i32]** %2, align 8
  %9 = getelementptr inbounds [2 x i32], [2 x i32]* %8, i64 0
  %10 = getelementptr inbounds [2 x i32], [2 x i32]* %9, i64 0, i64 1
  store i32 %7, i32* %10, align 4
  %11 = load i32, i32* getelementptr inbounds ([2 x [2 x i32]], [2 x [2 x i32]]* @Map, i64 0, i64 1, i64 0), align 8
  %12 = load [2 x i32]*, [2 x i32]** %2, align 8
  %13 = getelementptr inbounds [2 x i32], [2 x i32]* %12, i64 1
  %14 = getelementptr inbounds [2 x i32], [2 x i32]* %13, i64 0, i64 0
  store i32 %11, i32* %14, align 4
  %15 = load i32, i32* getelementptr inbounds ([2 x [2 x i32]], [2 x [2 x i32]]* @Map, i64 0, i64 1, i64 1), align 4
  %16 = load [2 x i32]*, [2 x i32]** %2, align 8
  %17 = getelementptr inbounds [2 x i32], [2 x i32]* %16, i64 1
  %18 = getelementptr inbounds [2 x i32], [2 x i32]* %17, i64 0, i64 1
  store i32 %15, i32* %18, align 4
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @main() #0 {
  %1 = alloca i32, align 4
  %2 = alloca [2 x [2 x i32]], align 16
  %3 = alloca i32, align 4
  store i32 0, i32* %1, align 4
  %4 = load i32, i32* getelementptr inbounds ([2 x i32], [2 x i32]* @f, i64 0, i64 0), align 4
  %5 = call i32 (i8*, ...) @printf(i8* noundef getelementptr inbounds ([4 x i8], [4 x i8]* @.str, i64 0, i64 0), i32 noundef %4)
  %6 = call i32 (...) bitcast (i32 ()* @getint to i32 (...)*)()
  store i32 %6, i32* @T, align 4
  %7 = getelementptr inbounds [2 x [2 x i32]], [2 x [2 x i32]]* %2, i64 0, i64 0
  call void @Copy([2 x i32]* noundef %7)
  br label %8

8:                                                ; preds = %20, %0
  %9 = load i32, i32* @T, align 4
  %10 = icmp ne i32 %9, 0
  br i1 %10, label %11, label %25

11:                                               ; preds = %8
  %12 = load i32, i32* @T, align 4
  %13 = sdiv i32 %12, 2
  %14 = mul nsw i32 %13, 2
  %15 = load i32, i32* @T, align 4
  %16 = icmp ne i32 %14, %15
  br i1 %16, label %17, label %19

17:                                               ; preds = %11
  %18 = getelementptr inbounds [2 x [2 x i32]], [2 x [2 x i32]]* %2, i64 0, i64 0
  call void @calc([2 x i32]* noundef getelementptr inbounds ([2 x [2 x i32]], [2 x [2 x i32]]* @ori, i64 0, i64 0), [2 x i32]* noundef %18)
  br label %20

19:                                               ; preds = %11
  br label %20

20:                                               ; preds = %19, %17
  %21 = load i32, i32* @T, align 4
  %22 = sdiv i32 %21, 2
  store i32 %22, i32* @T, align 4
  %23 = getelementptr inbounds [2 x [2 x i32]], [2 x [2 x i32]]* %2, i64 0, i64 0
  %24 = getelementptr inbounds [2 x [2 x i32]], [2 x [2 x i32]]* %2, i64 0, i64 0
  call void @calc([2 x i32]* noundef %23, [2 x i32]* noundef %24)
  br label %8, !llvm.loop !13

25:                                               ; preds = %8
  store i32 0, i32* %3, align 4
  br label %26

26:                                               ; preds = %30, %25
  %27 = load i32, i32* %3, align 4
  %28 = load i32, i32* getelementptr inbounds ([2 x i32], [2 x i32]* @Bound, i64 0, i64 0), align 4
  %29 = icmp slt i32 %27, %28
  br i1 %29, label %30, label %43

30:                                               ; preds = %26
  %31 = load i32, i32* %3, align 4
  %32 = sext i32 %31 to i64
  %33 = getelementptr inbounds [2 x i32], [2 x i32]* @f, i64 0, i64 %32
  %34 = load i32, i32* %33, align 4
  %35 = load i32, i32* %3, align 4
  %36 = sext i32 %35 to i64
  %37 = getelementptr inbounds [2 x [2 x i32]], [2 x [2 x i32]]* @ori, i64 0, i64 %36
  %38 = getelementptr inbounds [2 x i32], [2 x i32]* %37, i64 0, i64 0
  %39 = call i32 @getans(i32* noundef %38)
  %40 = call i32 (i8*, ...) @printf(i8* noundef getelementptr inbounds ([9 x i8], [9 x i8]* @.str.1, i64 0, i64 0), i32 noundef %34, i32 noundef %39)
  %41 = load i32, i32* %3, align 4
  %42 = add nsw i32 %41, 1
  store i32 %42, i32* %3, align 4
  br label %26, !llvm.loop !14

43:                                               ; preds = %26
  %44 = load i32, i32* getelementptr inbounds ([2 x [2 x i32]], [2 x [2 x i32]]* @ori, i64 0, i64 0, i64 0), align 16
  %45 = call i32 (i8*, ...) @printf(i8* noundef getelementptr inbounds ([10 x i8], [10 x i8]* @.str.2, i64 0, i64 0), i32 noundef %44)
  ret i32 0
}

declare i32 @printf(i8* noundef, ...) #1

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
  br label %6, !llvm.loop !15

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
  %7 = call i32 (i8*, ...) @printf(i8* noundef getelementptr inbounds ([4 x i8], [4 x i8]* @.str.2.5, i64 0, i64 0), i32 noundef %6)
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
  %18 = call i32 (i8*, ...) @printf(i8* noundef getelementptr inbounds ([4 x i8], [4 x i8]* @.str.3.6, i64 0, i64 0), i32 noundef %17)
  br label %19

19:                                               ; preds = %12
  %20 = load i32, i32* %5, align 4
  %21 = add nsw i32 %20, 1
  store i32 %21, i32* %5, align 4
  br label %8, !llvm.loop !16

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
!10 = distinct !{!10, !7}
!11 = distinct !{!11, !7}
!12 = distinct !{!12, !7}
!13 = distinct !{!13, !7}
!14 = distinct !{!14, !7}
!15 = distinct !{!15, !7}
!16 = distinct !{!16, !7}
