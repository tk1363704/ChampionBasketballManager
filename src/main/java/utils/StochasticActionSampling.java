/**
 *  Created time: 2019_01_15
 *  @Author: Devin Hua
 *  Function description:
 *  To sample a stochastic action based on action-probability distribution.
 */
package utils;

import org.jetbrains.annotations.Contract;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static config.Configuration.ACTION_ARRAY_LENGTH;

public class StochasticActionSampling {

    private static final int action_distribution_array_length = ACTION_ARRAY_LENGTH;

    public StochasticActionSampling(@org.jetbrains.annotations.NotNull Map<String, Double> mymap) {
        super();
        index_action_map = new HashMap<Integer, String>();
        probability_distribution_array = new double[mymap.size()];
        Iterator<Map.Entry<String, Double>> iterator = mymap.entrySet().iterator();
        int index = 0;
        while (iterator.hasNext())
        {
            Map.Entry entry = iterator.next();
            index_action_map.put(index, (String)entry.getKey());
            probability_distribution_array[index++] = (Double)entry.getValue();
        }
        int[] actions = new int[action_distribution_array_length];
        int[] actions_new = transformFromProbability2action(probability_distribution_array, actions);
        action_distribution_array = new int[actions_new.length];
        System.arraycopy(actions_new, 0, action_distribution_array, 0, actions_new.length);
        action_array = new String[action_distribution_array.length];
        setAction_array();
    }

    @Contract(pure = true)
    public double[] getProbability_distribution_array() {
        return probability_distribution_array;
    }

    @Contract(pure = true)
    public int[] getAction_distribution_array() {
        return action_distribution_array;
    }



    /**
     *  The array to store the action-probability distribution.
     *  The index of one element in array is corresponded to certain action
     *  and the value of this element is corresponded to the probability of this action's occurrence.
     *  For instance, the array [0.2, 0.3, 0.5] is represented as such probability distribution:
     *  The occurrence probability of action_0 is 0.2, while 0.3 for action_1 and 0.5 for action_2.
     */
    private double[] probability_distribution_array;

    /**
     *  The array to store the actions based on action-probability distribution.
     *  The length of the array is config.Configuration.ACTION_ARRAY_LENGTH.
     *  The number of different index is corresponded to the probability of such action.
     *  For instance, [0.2, 0.3, 0.5] is one action-probability distribution array,
     *  then 2000 '0's ('0' is the index of the first element in action-probability distribution array)
     *  would be contained in action array while 3000 '1's and 5000 '2's
     *  would also consist in the action array.
     */
    private int[] action_distribution_array;

    public String[] getAction_array() {
        return action_array;
    }

    private HashMap<Integer, String> getIndex_action_map() {
        return index_action_map;
    }

    private void setIndex_action_map(HashMap<Integer, String> index_action_map) {
        this.index_action_map = index_action_map;
    }

    private HashMap<Integer, String> index_action_map;

    private void setAction_array() {
        for(int i=0;i<action_distribution_array.length;i++)
        {
            if(index_action_map.containsKey(action_distribution_array[i]))
                this.action_array[i] = index_action_map.get(action_distribution_array[i]);
            else
                this.action_array[i] = "NULL";
        }
    }

    private String[] action_array;

    /**
     * Transform probability_distribution_array to action_distribution_array;
     */
    @Contract("_, _ -> param2")
    private int[] transformFromProbability2action(double[] probability_array, int[] action_array)
    {
        //Value of -1 represents error occurring.
        if(probability_array==null||probability_array.length==0||probability_array.length>ACTION_ARRAY_LENGTH)
        {
            Arrays.fill(action_array, -1);
            return action_array;
        }
        //It is presumed that the sum of all probabilities in probability_array is 1.0.
        double probability_sum = 0;
        for(double item : probability_array)
        {
            probability_sum += item;
        }
        if(probability_sum != 1.0)
        {
            Arrays.fill(action_array, -1);
            return action_array;
        }
        //If the probability_array has only one element, the value of the element is the probability of action0.
        else if(probability_array.length==1)
        {
            int action0_length = (int)(probability_array[0]*ACTION_ARRAY_LENGTH);
            Arrays.fill(action_array, action0_length, ACTION_ARRAY_LENGTH, 1);
            return action_array;
        }
        else
        {
            int start_index = 0, end_index;
            for(int i=0;i<probability_array.length;i++)
            {
                if(i==0)
                {
                    end_index = start_index + (int)(probability_array[i]*ACTION_ARRAY_LENGTH);
                    Arrays.fill(action_array, start_index, end_index, i);
                    start_index = end_index;
                }
                else
                {
                    end_index = start_index + (int)(probability_array[i]*ACTION_ARRAY_LENGTH);
                    Arrays.fill(action_array, start_index, end_index, i);
                    start_index = end_index;
                }
            }
        }
        return action_array;
    }
}
