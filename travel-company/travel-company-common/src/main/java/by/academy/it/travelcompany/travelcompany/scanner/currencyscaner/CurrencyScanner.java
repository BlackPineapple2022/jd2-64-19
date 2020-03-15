package by.academy.it.travelcompany.travelcompany.scanner.currencyscaner;

/**
 * This is simple currency scanner
 * for converting currency which using Ryanair or Wizzair
 * to EUR, it will help on comparing ticket price in different currency
 */

public interface CurrencyScanner {

    /**
     * Get multiplier to get EUR from other currency
     * @param currencyCode String currency code
     * @return multiplier
     */

    Double getEURMultiplier(String currencyCode);

    /**
     * Update currency scanner, update all multiplier
     */

    void update();

}
