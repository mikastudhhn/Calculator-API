package de.mika.hhn.calculator.factories;

import de.mika.hhn.calculator.context.CalculatorContext;
import de.mika.hhn.calculator.spi.ModeProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

public class ModeFactory {
    private static final Map<String, ModeProvider> MODES = new HashMap<>();

    static {
        ServiceLoader<ModeProvider> loader = ServiceLoader.load(ModeProvider.class);
        for (ModeProvider provider : loader) {
            registerMode(provider.getSymbol(), provider);
            System.out.println("Loaded ModeProvider: " + provider.getClass().getName() + " with symbol: " + provider.getSymbol());
        }
    }

    private static void registerMode(String symbol, ModeProvider provider) {
        MODES.put(symbol.toLowerCase(), provider);
    }

    public static boolean isMode(String symbol) {
        return MODES.containsKey(symbol.toLowerCase());
    }

    public static void applyMode(String symbol, CalculatorContext context) {
        ModeProvider provider = MODES.get(symbol.toLowerCase());
        if (provider != null) {
            provider.applyMode(context);
        } else {
            throw new IllegalArgumentException("Unknown mode: " + symbol);
        }
    }
}