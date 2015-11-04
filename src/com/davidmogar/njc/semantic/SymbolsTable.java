package com.davidmogar.njc.semantic;

import com.davidmogar.njc.ast.statements.definitions.Definition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SymbolsTable {

    private List<Map<String, Definition>> scopes;
    private Map<String, Definition> currentScope;

    private int scope;

    public SymbolsTable() {
        scopes = new ArrayList<>();
        scope = -1;
        createScope();
    }

    public boolean addDefinition(Definition definition) {
        boolean inserted = false;

        if (!currentScope.containsKey(definition.getName())) {
            inserted = true;
            currentScope.put(definition.getName(), definition);
            definition.setScope(scope);
        }

        return inserted;
    }

    public void createScope() {
        currentScope = new HashMap<>();
        scopes.add(currentScope);
        scope++;
    }

    public Definition getDefinition(String name) {
        return getDefinition(scopes.size() - 1, name);
    }

    public void removeScope() {
        scope--;
        if (scope >= 0 && scope < scopes.size()) {
            currentScope = scopes.get(scope);
            scopes.remove(scope + 1);
        }
    }

    private Definition getDefinition(int position, String name) {
        Definition definition = scopes.get(position).get(name);
        if (definition == null && position > 0) {
            definition = getDefinition(position - 1, name);
        }
        return definition;
    }

}
