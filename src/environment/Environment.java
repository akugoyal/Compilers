package environment;

import java.util.HashMap;

public class Environment
{
    private HashMap<String, Integer> vars;

    public Environment() {
        vars = new HashMap<String, Integer>();
    }

    public void setVariable(String var, Integer val) {
        vars.put(var, val);
    }

    public int getVariable(String var) {
        return vars.get(var);
    }
}
