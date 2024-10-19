package de.mika.hhn.calculator.factories;

import de.mika.hhn.calculator.spi.Operation;
import de.mika.hhn.calculator.spi.OperationProvider;

import java.util.HashMap;
import java.util.Map;

import java.util.ServiceLoader;

public class OperationFactory {
    private static final Map<String, Operation> OPERATIONS = new HashMap<>();

    static {
        ServiceLoader<OperationProvider> loader = ServiceLoader.load(OperationProvider.class);
        for (OperationProvider provider : loader) {
            registerOperation(provider.getSymbol(), provider.getOperation());
            System.out.println("Loaded OperationProvider: " + provider.getClass().getName() + " with symbol: " + provider.getSymbol());
        }
    }

    private static void registerOperation(String symbol, Operation operation) {
        OPERATIONS.put(symbol.toLowerCase(), operation);
    }

    public static Operation getOperation(String symbol) {
        return OPERATIONS.get(symbol.toLowerCase());
    }

    public static boolean isOperation(String symbol) {
        return OPERATIONS.containsKey(symbol.toLowerCase());
    }
}