package front.parser;

import errorHandle.Error;
import front.lexer.*;
import front.symbolTable.Function;
import front.symbolTable.SymbolTable;

import java.util.ArrayList;

public class Block {
    private ArrayList<BlockItem> blockItems;
    public Block() {
        blockItems = new ArrayList<>();
    }

    //Block → '{' { BlockItem } '}'
    public void parse() {
        SymbolTable.addTable();
        Lexer.getInstance().next();
        while (true) {
            if (Lexer.getInstance().getLexType() == LexType.RBRACE) {
                break;
            }
            BlockItem blockItem = new BlockItem();
            blockItem.parse();
            blockItems.add(blockItem);
        }
        Function func = ((Function)SymbolTable.getCurrentTable().getFatherFunc());
        if (!SymbolTable.getCurrentTable().funcName.equals("") && func != null && func.retype == 1) {
            if (blockItems.size() == 0 || !blockItems.get(blockItems.size() - 1).isReturn()) {
                Error.error('g', Lexer.getInstance().getLineNum());
            }
        }
        Lexer.getInstance().next();
        SymbolTable.deleteTable();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LBRACE {\n");
        for (int i = 0; i < blockItems.size(); i++) {
            sb.append(blockItems.get(i).toString());
        }
        sb.append("RBRACE }\n");
        sb.append("<Block>\n");
        return sb.toString();
    }

    public ArrayList<BlockItem> getBlockItems() {
        return blockItems;
    }
}
