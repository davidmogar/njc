package com.davidmogar.njc.semantic;

import com.davidmogar.njc.ast.statements.definitions.Definition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SymbolsTable {

    private List<Map<String, Definition>> scopes;
    private Map<String, Definition> currentScope;

    public SymbolsTable() {
        scopes = new ArrayList<>();
        createScope();
    }

    public boolean addDefinition(Definition definition) {
        boolean inserted = false;

        if (!currentScope.containsKey(definition.getName())) {
            inserted = true;
            currentScope.put(definition.getName(), definition);
        }

        return inserted;
    }

    public void createScope() {
        currentScope = new HashMap<>();
        scopes.add(currentScope);
    }

    public Definition getDefinition(String name) {
        return getDefinition(scopes.size() - 1, name);
    }

    public void removeScope() {
        int previousIndex = scopes.size() - 2;
        if (previousIndex >= 0) {
            currentScope = scopes.get(previousIndex);
            scopes.remove(previousIndex + 1);
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
