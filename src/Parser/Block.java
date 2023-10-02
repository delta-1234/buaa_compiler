package Parser;

import Lexer.*;

import java.util.ArrayList;

public class Block {
    private ArrayList<BlockItem> blockItems;
    public Block() {
        blockItems = new ArrayList<>();
    }

    //Block → '{' { BlockItem } '}'
    public void parse() {
        if (Lexer.getInstance().getLexType() != LexType.LBRACE) {
            System.out.println("Block error{");
            return; //error
        }
        Lexer.getInstance().next();
        while (true) {
            if (Lexer.getInstance().getLexType() == LexType.RBRACE) {
                break;
            }
            BlockItem blockItem = new BlockItem();
            blockItem.parse();
            blockItems.add(blockItem);
        }
        Lexer.getInstance().next();
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
}
