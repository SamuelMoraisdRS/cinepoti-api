package br.com.cinepoti.cinepoti_api.util;

import java.util.HashMap;
import java.util.Map;

import java.util.function.Function;

public class MapTransformer {
  public static <K,V,P> Map<K, V> transformMap(Map<K, P> inputMap,
      Function<P, V> transformer) {
        Map<K, V> transformedMap = new HashMap<>();
        inputMap.entrySet().forEach(entry -> transformedMap.put(entry.getKey(), transformer.apply(entry.getValue())));
        return transformedMap;
      }
}
