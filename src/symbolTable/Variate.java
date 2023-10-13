package symbolTable;

public class Variate extends Symbol{
    public int dim; 		// 0 -> a, 1 -> a[], 2 -> a[][]
    public boolean con;		// true -> const, false -> var

    public Variate(int tableId, String varName, int dim, boolean con) {
        super.tableId = tableId;
        super.name = varName;
        this.dim = dim;
        this.con = con;
    }

}
