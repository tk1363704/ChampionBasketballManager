/**
 *  Created time: 2019_01_15
 *  @Author: Devin Hua
 *  Function description:
 *  To sample a stochastic action based on action-probability distribution.
 */

package utils;

import java.util.Arrays;

import static config.Configuration.ACTION_ARRAY_LENGTH;

public class StochasticActionSampling {

    private static final int action_distribution_array_length = 10000;

    public StochasticActionSampling(double[] numbers) {
        this.probability_distribution_array = new double[numbers.length];
        for(int i=0;i<numbers.length;i++)
        {
            this.probability_distribution_array[i] = numbers[i];
        }
        int[] actions = new int[this.action_distribution_array_length];
        actions = transformFromProbability2action(this.probability_distribution_array, actions);
        this.action_distribution_array = new int[actions.length];
        for(int i=0;i<actions.length;i++)
        {
            this.action_distribution_array[i] = actions[i];
        }
    }

    public final double[] getProbability_distribution_array() {
        return probability_distribution_array;
    }

    public final int[] getAction_distribution_array() {
        return action_distribution_array;
    }

    /**
     *  The array to store the action-probability distribution.
     *  The index of one element in array is corresponded to certain action
     *  and the value of this element is corresponded to the probability of this action's occurrence.
     *  For instance, the array [0.2, 0.3, 0.5] is represented as such probability distribution:
     *  The occurrence probability of action_0 is 0.2, while 0.3 for action_1 and 0.5 for action_2.
     */
    private final double[] probability_distribution_array;

    /**
     *  The array to store the actions based on action-probability distribution.
     *  The length of the array is 10000.
     *  The number of different index is corresponded to the probability of such action.
     *  For instance, [0.2, 0.3, 0.5] is one action-probability distribution array,
     *  then 2000 '0's ('0' is the index of the first element in action-probability distribution array)
     *  would be contained in action array while 3000 '1's and 5000 '2's
     *  would also consist in the action array.
     */
    private final int[] action_distribution_array;

    /**
     * Transform probability_distribution_array to action_distribution_array;
     */
    private int[] transformFromProbability2action(double[] probability_array, int[] action_array)
    {
        //It is presumed that the sum of all probabilities in probability_array is 1.0.
        //Value of -1 represents error occurring.
        if(probability_array==null||probability_array.length==0||probability_array.length>ACTION_ARRAY_LENGTH)
        {
            Arrays.fill(action_array, -1);
        }
        //If the probability_array has only one element, the value of the element is the probability of action0.
        else if(probability_array.length==1)
        {
            int action0_length = (int)(probability_array[0]*10000);
            Arrays.fill(action_array, action0_length, ACTION_ARRAY_LENGTH, 1);
        }
        else if(probability_array.length>1&&probability_array.length<=ACTION_ARRAY_LENGTH)
        {
            int start_index = 0;
            int end_index = 0;
            for(int i=0;i<probability_array.length;i++)
            {
                if(i==0)
                {
                    start_index = 0;
                    end_index = start_index + (int)(probability_array[i]*10000);
                    Arrays.fill(action_array, start_index, end_index, i);
                    start_index = end_index;
                }
                else
                {
                    end_index = start_index + (int)(probability_array[i]*10000);
                    Arrays.fill(action_array, start_index, end_index, i);
                    start_index = end_index;
                }
            }
        }
        return action_array;
    }


}
