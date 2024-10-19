package de.mika.hhn.calculator.factories;

import de.mika.hhn.calculator.spi.ConstantProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

public class ConstantFactory {
    private static final Map<String, Double> CONSTANTS = new HashMap<>();

    static {
        ServiceLoader<ConstantProvider> loader = ServiceLoader.load(ConstantProvider.class);
        for (ConstantProvider provider : loader) {
            CONSTANTS.put(provider.getSymbol().toLowerCase(), provider.getValue());
            System.out.println("Loaded ConstantProvider: " + provider.getClass().getName() + " with symbol: " + provider.getSymbol());
        }
    }

    public static boolean isConstant(String symbol) {
        return CONSTANTS.containsKey(symbol.toLowerCase());
    }

    public static double getConstantValue(String symbol) {
        return CONSTANTS.get(symbol.toLowerCase());
    }
}