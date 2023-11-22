package backend;

public class MipsIns {
    private StringBuilder sb = new StringBuilder();
    private String insName;
    private int resReg;
    private int reg1;
    private int reg2;
    private String label;
    private int type;

    public MipsIns(String ins, int resReg, int reg1, int reg2) {
        insName = ins;
        this.resReg = resReg;
        this.reg1 = reg1;
        this.reg2 = reg2;
        label = "";
        type = 0;
    }

    public MipsIns(String ins, int reg1, int reg2) {
        insName = ins;
        this.reg1 = reg1;
        this.reg2 = reg2;
        label = "";
        type = 1;
    }

    public MipsIns(String ins, int reg1, int reg2, String label) {
        insName = ins;
        this.reg1 = reg1;
        this.reg2 = reg2;
        this.label = label;
        type = 2;
    }

    public MipsIns(String ins, String label) {
        insName = ins;
        this.label = label;
        type = 3;
    }

    public MipsIns(String ins, int resReg, String label) {
        insName = ins;
        this.resReg = resReg;
        this.label = label;
        type = 4;
    }

    public MipsIns(String ins) {
        this.insName = ins;
        type = 5;
    }

    public MipsIns(String ins, int resReg) {
        insName = ins;
        this.resReg = resReg;
        type = 6;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (type == 0) { //lw,sw,addu,subu,addiu
            switch (insName) {
                case "lw":
                case "sw":
                    sb.append(insName).append(" ");
                    sb.append("$").append(resReg);
                    sb.append(", ").append(reg1);
                    sb.append("($").append(reg2).append(")");
                    break;
                case "addiu":
                    sb.append(insName).append(" ");
                    sb.append("$").append(resReg);
                    sb.append(", $").append(reg1);
                    sb.append(", ").append(reg2);
                    break;
                default:
                    sb.append(insName).append(" ");
                    sb.append("$").append(resReg);
                    sb.append(", $").append(reg1);
                    sb.append(", $").append(reg2);
            }
        } else if (type == 1) { //li,move,div,mult
            switch (insName) {
                case "li":
                    sb.append(insName).append(" ");
                    sb.append("$").append(reg1);
                    sb.append(", ").append(reg2);
                    break;
                default:
                    sb.append(insName).append(" ");
                    sb.append("$").append(reg1);
                    sb.append(", $").append(reg2);
            }
        } else if (type == 2) { //beq,bne
            sb.append(insName).append(" ");
            sb.append("$").append(reg1);
            sb.append(", $").append(reg2);
            sb.append(", ").append(label);
        } else if (type == 3) { //jal,j
            sb.append(insName).append(" ").append(label);
        } else if (type == 4) { //la
            sb.append(insName).append(" $").append(resReg);
            sb.append(", ").append(label);
        } else if (type == 5) { //syscall
            sb.append(insName);
        } else { //mfhi,mflo,jr
            sb.append(insName).append(" $").append(resReg);
        }
        return sb.toString();
    }
}
