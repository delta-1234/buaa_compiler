package llvm;

import llvm.value.User;
import llvm.value.Value;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class Use {
    private Value value;
    private User user;
    private static HashMap<String, Use> useMap = new HashMap<>();

    private Use(Value value, User user) {
        this.value = value;
        this.user = user;
    }

    public static Use getInstance(Value value, User user) {
        String ident = value.getIdent() + " " + user.getIdent();
        if (!useMap.containsKey(ident)) {
            useMap.put(ident, new Use(value, user));
            value.getUseList().add(useMap.get(ident));
            user.getOperands().add(useMap.get(ident));
        }
        return useMap.get(ident);
    }

    public Value getValue() {
        return value;
    }

    public User getUser() {
        return user;
    }
}
