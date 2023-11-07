package front.symbolTable;

import java.util.ArrayList;

public class Function extends Symbol{
    public int retype;     // 函数返回类型, 0 -> void, 1 -> int
    public int paramNum;   // 函数参数个数
    public ArrayList<Integer> paramList;  // 函数参数列表

    public Function(int tableId, String funcName, int retype) {
        super.name = funcName;
        super.tableId = tableId;
        this.retype = retype;
    }

    public void addParam(ArrayList<Integer> paramList) {
        this.paramNum = paramList.size();
        this.paramList = paramList;
    }
}
