package javaTest;

import java.util.*;
import java.util.stream.*;

class Tx {
    int year;
    String trader;
    int value;
    Tx(int year, String trader, int value) {
        this.year = year;
        this.trader = trader;
        this.value = value;
    }
}

public class TopTraders {
    public static void main(String[] args) {


        List<Tx> txs = Arrays.asList(
            new Tx(2022, "Dipesh", 100),
            new Tx(2022, "Adarsh", 200),
            new Tx(2022, "Manish", 150),
            new Tx(2022, "Moksh", 200)
        );
        Map<Integer, List<String>> topTradersByYear = txs.stream()
            .collect(Collectors.groupingBy(
                tx -> tx.year,
                Collectors.collectingAndThen(
                    Collectors.groupingBy(tx -> tx.trader, Collectors.summingInt(tx -> tx.value)),
                    traderTotals -> traderTotals.entrySet().stream()
                        .sorted(Comparator.comparing((Map.Entry<String, Integer> e) -> -e.getValue())
                                .thenComparing(Map.Entry::getKey))
                        .limit(3)
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toList())
                )
            ));
        System.out.println(topTradersByYear);
    }
}

