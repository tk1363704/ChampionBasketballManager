package utils;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StochasticActionSamplingTest {

    @Test
    void getProbability_distribution_array() {
        Map<String, Double> mymap = new HashMap<String, Double>() {
            {
                put("3_shoot", 0.27);
                put("2_shoot", 0.33);
                put("layup", 0.14);
                put("postup", 0.16);
                put("fast_break", 0.09);
                put("alleyoop", 0.01);
            }
        };
        StochasticActionSampling stochasticActionSampling = new StochasticActionSampling(mymap);
        String[] result = stochasticActionSampling.getAction_array();
        System.out.println(Arrays.toString(result));
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (String item : result)
        {
            if (map.containsKey(item))
                map.put(item, map.get(item) + 1);
            else
                map.put(item, 1);
        }
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext())
        {
            Map.Entry entry = iterator.next();
            System.out.printf("Key: %s, Value: %s\n", entry.getKey(), entry.getValue());
        }
    }

    @Test
    void getAction_distribution_array() {
    }
}