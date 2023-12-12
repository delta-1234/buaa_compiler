; ModuleID = 'main.c'
source_filename = "main.c"
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

@p = dso_local constant i32 88, align 4
@q = dso_local global i32 66, align 4
@r = dso_local global i32 155, align 4
@s = dso_local global i32 15, align 4
@.str = private unnamed_addr constant [4 x i8] c"%d\0A\00", align 1
@.str.1 = private unnamed_addr constant [24 x i8] c"Your Num is too Big!!!\0A\00", align 1
@.str.2 = private unnamed_addr constant [10 x i8] c"19373022\0A\00", align 1
@.str.3 = private unnamed_addr constant [7 x i8] c"%d,%d\0A\00", align 1
@.str.4 = private unnamed_addr constant [6 x i8] c"b:%d\0A\00", align 1
@.str.5 = private unnamed_addr constant [17 x i8] c"Bool1 is false!\0A\00", align 1
@.str.6 = private unnamed_addr constant [17 x i8] c"Good!,Num is %d\0A\00", align 1
@.str.7 = private unnamed_addr constant [6 x i8] c"Oh!?\0A\00", align 1

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @max(i32 noundef %0, i32 noundef %1) #0 {
  %3 = alloca i32, align 4
  %4 = alloca i32, align 4
  %5 = alloca i32, align 4
  store i32 %0, i32* %4, align 4
  store i32 %1, i32* %5, align 4
  %6 = load i32, i32* %4, align 4
  %7 = load i32, i32* %5, align 4
  %8 = icmp sgt i32 %6, %7
  br i1 %8, label %9, label %11

9:                                                ; preds = %2
  %10 = load i32, i32* %4, align 4
  store i32 %10, i32* %3, align 4
  br label %13

11:                                               ; preds = %2
  %12 = load i32, i32* %5, align 4
  store i32 %12, i32* %3, align 4
  br label %13

13:                                               ; preds = %11, %9
  %14 = load i32, i32* %3, align 4
  ret i32 %14
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @min(i32 noundef %0, i32 noundef %1) #0 {
  %3 = alloca i32, align 4
  %4 = alloca i32, align 4
  %5 = alloca i32, align 4
  store i32 %0, i32* %4, align 4
  store i32 %1, i32* %5, align 4
  %6 = load i32, i32* %4, align 4
  %7 = load i32, i32* %5, align 4
  %8 = icmp slt i32 %6, %7
  br i1 %8, label %9, label %11

9:                                                ; preds = %2
  %10 = load i32, i32* %4, align 4
  store i32 %10, i32* %3, align 4
  br label %13

11:                                               ; preds = %2
  %12 = load i32, i32* %5, align 4
  store i32 %12, i32* %3, align 4
  br label %13

13:                                               ; preds = %11, %9
  %14 = load i32, i32* %3, align 4
  ret i32 %14
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @scan() #0 {
  %1 = alloca i32, align 4
  store i32 0, i32* %1, align 4
  %2 = call i32 (...) @getint()
  store i32 %2, i32* %1, align 4
  %3 = load i32, i32* %1, align 4
  %4 = add nsw i32 %3, 0
  ret i32 %4
}

declare i32 @getint(...) #1

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @print(i32 noundef %0) #0 {
  %2 = alloca i32, align 4
  store i32 %0, i32* %2, align 4
  %3 = load i32, i32* %2, align 4
  %4 = call i32 (i8*, ...) @printf(i8* noundef getelementptr inbounds ([4 x i8], [4 x i8]* @.str, i64 0, i64 0), i32 noundef %3)
  ret void
}

declare i32 @printf(i8* noundef, ...) #1

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @noUse(i32 noundef %0) #0 {
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  store i32 %0, i32* %2, align 4
  %4 = load i32, i32* %2, align 4
  store i32 %4, i32* %3, align 4
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @mid(i32 noundef %0, i32 noundef %1, i32 noundef %2) #0 {
  %4 = alloca i32, align 4
  %5 = alloca i32, align 4
  %6 = alloca i32, align 4
  %7 = alloca i32, align 4
  %8 = alloca i32, align 4
  store i32 %0, i32* %5, align 4
  store i32 %1, i32* %6, align 4
  store i32 %2, i32* %7, align 4
  store i32 0, i32* %8, align 4
  %9 = load i32, i32* %5, align 4
  %10 = load i32, i32* %6, align 4
  %11 = call i32 @max(i32 noundef %9, i32 noundef %10)
  %12 = load i32, i32* %6, align 4
  %13 = load i32, i32* %7, align 4
  %14 = call i32 @min(i32 noundef %12, i32 noundef %13)
  %15 = icmp eq i32 %11, %14
  br i1 %15, label %16, label %18

16:                                               ; preds = %3
  %17 = load i32, i32* %6, align 4
  store i32 %17, i32* %4, align 4
  br label %30

18:                                               ; preds = %3
  %19 = load i32, i32* %5, align 4
  %20 = load i32, i32* %6, align 4
  %21 = call i32 @max(i32 noundef %19, i32 noundef %20)
  %22 = load i32, i32* %5, align 4
  %23 = load i32, i32* %7, align 4
  %24 = call i32 @min(i32 noundef %22, i32 noundef %23)
  %25 = icmp ne i32 %21, %24
  br i1 %25, label %26, label %28

26:                                               ; preds = %18
  %27 = load i32, i32* %7, align 4
  store i32 %27, i32* %4, align 4
  br label %30

28:                                               ; preds = %18
  %29 = load i32, i32* %5, align 4
  store i32 %29, i32* %4, align 4
  br label %30

30:                                               ; preds = %28, %26, %16
  %31 = load i32, i32* %4, align 4
  ret i32 %31
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @factorial(i32 noundef %0) #0 {
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  %4 = alloca i32, align 4
  %5 = alloca i32, align 4
  store i32 %0, i32* %3, align 4
  %6 = load i32, i32* %3, align 4
  store i32 %6, i32* %4, align 4
  store i32 1, i32* %5, align 4
  %7 = load i32, i32* %3, align 4
  %8 = icmp sgt i32 %7, 20
  br i1 %8, label %9, label %11

9:                                                ; preds = %1
  %10 = call i32 (i8*, ...) @printf(i8* noundef getelementptr inbounds ([24 x i8], [24 x i8]* @.str.1, i64 0, i64 0))
  store i32 -1, i32* %2, align 4
  br label %23

11:                                               ; preds = %1
  br label %12

12:                                               ; preds = %15, %11
  %13 = load i32, i32* %4, align 4
  %14 = icmp ne i32 %13, 0
  br i1 %14, label %15, label %21

15:                                               ; preds = %12
  %16 = load i32, i32* %5, align 4
  %17 = load i32, i32* %4, align 4
  %18 = mul nsw i32 %16, %17
  store i32 %18, i32* %5, align 4
  %19 = load i32, i32* %4, align 4
  %20 = sub nsw i32 %19, 1
  store i32 %20, i32* %4, align 4
  br label %12, !llvm.loop !6

21:                                               ; preds = %12
  %22 = load i32, i32* %5, align 4
  store i32 %22, i32* %2, align 4
  br label %23

23:                                               ; preds = %21, %9
  %24 = load i32, i32* %2, align 4
  ret i32 %24
}

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
  %10 = alloca i32, align 4
  store i32 0, i32* %1, align 4
  %11 = load i32, i32* @q, align 4
  %12 = call i32 @min(i32 noundef 88, i32 noundef %11)
  %13 = load i32, i32* @s, align 4
  %14 = call i32 @scan()
  %15 = call i32 @max(i32 noundef %13, i32 noundef %14)
  %16 = call i32 @max(i32 noundef %12, i32 noundef %15)
  store i32 %16, i32* %2, align 4
  %17 = load i32, i32* @r, align 4
  %18 = call i32 @scan()
  %19 = call i32 @min(i32 noundef %17, i32 noundef %18)
  store i32 %19, i32* %3, align 4
  store i32 58, i32* %4, align 4
  store i32 65535, i32* %5, align 4
  store i32 0, i32* %6, align 4
  store i32 1, i32* %7, align 4
  store i32 -1, i32* %8, align 4
  store i32 -10, i32* %9, align 4
  %20 = call i32 (i8*, ...) @printf(i8* noundef getelementptr inbounds ([10 x i8], [10 x i8]* @.str.2, i64 0, i64 0))
  %21 = load i32, i32* %2, align 4
  %22 = load i32, i32* %3, align 4
  %23 = call i32 (i8*, ...) @printf(i8* noundef getelementptr inbounds ([7 x i8], [7 x i8]* @.str.3, i64 0, i64 0), i32 noundef %21, i32 noundef %22)
  %24 = load i32, i32* %3, align 4
  %25 = load i32, i32* %4, align 4
  %26 = add nsw i32 %24, %25
  %27 = load i32, i32* %2, align 4
  %28 = sub nsw i32 %26, %27
  store i32 %28, i32* %3, align 4
  %29 = load i32, i32* %3, align 4
  %30 = call i32 (i8*, ...) @printf(i8* noundef getelementptr inbounds ([6 x i8], [6 x i8]* @.str.4, i64 0, i64 0), i32 noundef %29)
  %31 = load i32, i32* %7, align 4
  %32 = load i32, i32* %8, align 4
  %33 = add nsw i32 %31, %32
  %34 = load i32, i32* %6, align 4
  %35 = load i32, i32* %6, align 4
  %36 = icmp ne i32 %35, 0
  br i1 %36, label %39, label %37

37:                                               ; preds = %0
  %38 = call i32 (i8*, ...) @printf(i8* noundef getelementptr inbounds ([17 x i8], [17 x i8]* @.str.5, i64 0, i64 0))
  br label %39

39:                                               ; preds = %37, %0
  store i32 0, i32* %10, align 4
  %40 = call i32 @scan()
  store i32 %40, i32* %10, align 4
  %41 = load i32, i32* %10, align 4
  %42 = add nsw i32 %41, 10
  call void @print(i32 noundef %42)
  %43 = load i32, i32* %10, align 4
  store i32 %43, i32* %4, align 4
  %44 = call i32 @scan()
  store i32 %44, i32* %2, align 4
  %45 = call i32 @scan()
  store i32 %45, i32* %3, align 4
  %46 = call i32 @scan()
  store i32 %46, i32* %4, align 4
  %47 = load i32, i32* %2, align 4
  %48 = load i32, i32* %3, align 4
  %49 = load i32, i32* %4, align 4
  %50 = call i32 @mid(i32 noundef %47, i32 noundef %48, i32 noundef %49)
  %51 = load i32, i32* %2, align 4
  %52 = icmp sle i32 %50, %51
  br i1 %52, label %53, label %69

53:                                               ; preds = %39
  %54 = load i32, i32* %2, align 4
  %55 = load i32, i32* %2, align 4
  %56 = load i32, i32* %3, align 4
  %57 = load i32, i32* %4, align 4
  %58 = call i32 @mid(i32 noundef %55, i32 noundef %56, i32 noundef %57)
  %59 = sdiv i32 %58, 6
  %60 = load i32, i32* %4, align 4
  %61 = mul nsw i32 %59, %60
  %62 = srem i32 %61, 2
  %63 = add nsw i32 %54, %62
  %64 = load i32, i32* %6, align 4
  %65 = load i32, i32* %8, align 4
  %66 = mul nsw i32 %64, %65
  %67 = sub nsw i32 %63, %66
  %68 = call i32 (i8*, ...) @printf(i8* noundef getelementptr inbounds ([17 x i8], [17 x i8]* @.str.6, i64 0, i64 0), i32 noundef %67)
  br label %87

69:                                               ; preds = %39
  %70 = load i32, i32* %2, align 4
  %71 = load i32, i32* %3, align 4
  %72 = load i32, i32* %4, align 4
  %73 = call i32 @mid(i32 noundef %70, i32 noundef %71, i32 noundef %72)
  %74 = load i32, i32* %4, align 4
  %75 = icmp slt i32 %73, %74
  br i1 %75, label %76, label %78

76:                                               ; preds = %69
  %77 = call i32 (i8*, ...) @printf(i8* noundef getelementptr inbounds ([6 x i8], [6 x i8]* @.str.7, i64 0, i64 0))
  br label %86

78:                                               ; preds = %69
  %79 = load i32, i32* %2, align 4
  %80 = load i32, i32* %3, align 4
  %81 = load i32, i32* %4, align 4
  %82 = call i32 @mid(i32 noundef %79, i32 noundef %80, i32 noundef %81)
  %83 = srem i32 %82, 65535
  %84 = call i32 @factorial(i32 noundef %83)
  %85 = call i32 (i8*, ...) @printf(i8* noundef getelementptr inbounds ([4 x i8], [4 x i8]* @.str, i64 0, i64 0), i32 noundef %84)
  br label %86

86:                                               ; preds = %78, %76
  br label %87

87:                                               ; preds = %86, %53
  %88 = load i32, i32* %2, align 4
  call void @noUse(i32 noundef %88)
  ret i32 0
}

attributes #0 = { noinline nounwind optnone uwtable "frame-pointer"="all" "min-legal-vector-width"="0" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "tune-cpu"="generic" }
attributes #1 = { "frame-pointer"="all" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "tune-cpu"="generic" }

!llvm.module.flags = !{!0, !1, !2, !3, !4}
!llvm.ident = !{!5}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{i32 7, !"PIC Level", i32 2}
!2 = !{i32 7, !"PIE Level", i32 2}
!3 = !{i32 7, !"uwtable", i32 1}
!4 = !{i32 7, !"frame-pointer", i32 2}
!5 = !{!"Ubuntu clang version 14.0.0-1ubuntu1.1"}
!6 = distinct !{!6, !7}
!7 = !{!"llvm.loop.mustprogress"}
