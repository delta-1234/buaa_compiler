package llvm.value.instruction;

public enum Operation {
    ADD, //加法
    SUB, //减法
    MUL, //乘法
    SDIV, //有符号除法
    SREM, //取模
    ICMP, //比较指令
    AND, //与
    OR, //或
    CALL, //函数调用
    ALLOCA, //分配内存
    LOAD, //读内存
    STORE, //写内存
    GETELEMENTPTR, //计算目标元素的位置
    PHI, //根据不同的控制流路径选择正确的输入值
    ZEXT, //将 ty的value的type扩充为ty2
    TRUNC, //将 ty的value的type缩减为ty2
    BR, //跳转
    RET, //退出函数
    NULL,
}
