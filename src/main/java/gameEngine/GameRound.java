/**
 *  Created time: 2019_01_08
 *  @Author: Devin Hua
 *  Function description:
 *  To get each round of one game.
 *  Set probability of the offensive pattern in basketball game based on the random number.
 */

package gameEngine;

import utils.StochasticActionSampling;

import java.util.HashMap;
import java.util.Map;

import static config.Configuration.ACTION_ARRAY_LENGTH;

public class GameRound {

    private String getPlayNumber() {
        return PlayNumber;
    }

    private void setPlayNumber(String playNumber) {
        PlayNumber = playNumber;
    }

    private String PlayNumber = "NULL";

    private String getOffencePlay() {
        return OffencePlay;
    }

    private void setOffencePlay(String offencePlay) {
        OffencePlay = offencePlay;
    }

    private String OffencePlay = "";

    private Map<String, Double> getAction_probability_map() {
        return action_probability_map;
    }

    private void setAction_probability_map() {
        this.action_probability_map.put("rate_3_shoot", rate_3_shoot);
        this.action_probability_map.put("rate_2_shoot", rate_2_shoot);
        this.action_probability_map.put("rate_layup", rate_layup);
        this.action_probability_map.put("rate_postup", rate_postup);
        this.action_probability_map.put("rate_fast_break", rate_fast_break);
        this.action_probability_map.put("rate_alleyoop", rate_alleyoop);
        this.action_probability_map.put("rate_turnover", rate_turnover);
    }

    private Map<String, Double> action_probability_map = new HashMap<String, Double>();

    public GameRound()
    {
        super();
        setAction_probability_map();
    }

    /**
     * The probability of making a three-point shot.
     */
    public static double rate_3_shoot = 0.25;
    /**
     * The probability of making a two-point shot.
     */
    public static double rate_2_shoot = 0.31;
    /**
     * The probability of making a break and lay-up.
     */
    public static double rate_layup = 0.15;
    /**
     * The probability of making a post-Up (Playing in a position near the basket).
     */
    public static double rate_postup = 0.15;
    /**
     * The probability of making a fast break.
     */
    public static double rate_fast_break = 0.09;
    /**
     * The probability of making a alley oop.
     */
    public static double rate_alleyoop = 0.03;
    /**
     * The probability of making a turnover.
     */
    public static double rate_turnover = 0.02;

    /**
     * Math.random() would generate one double random number;
     * If the value of number is between 0 and 0.27, then the number belongs to rate_3_shoot.
     * The number represents rate_3_shoot will be returned.
     * The private members or functions could only be accessed by inner call of current class.
     * All private members could not be called or accessed by external functions.
     * @return int
     */
    private void RandomPlayNumberGenerate()
    {
        int randomNumber;
        //The java.lang.Math.random() returns a double value with a positive sign, greater than or equal to 0.0 and less than 1.0.
        randomNumber = (int)(Math.random()*ACTION_ARRAY_LENGTH);
        StochasticActionSampling stochasticActionSampling = new StochasticActionSampling(action_probability_map);
        String[] result = stochasticActionSampling.getAction_array();
        setPlayNumber(result[randomNumber]);
    }

    private void OffencePlayTransition(){
        String playNumber = this.getPlayNumber();
        String offencePlay;
        if ("rate_3_shoot".equals(playNumber)) {
            offencePlay = "三分投篮";
        } else if ("rate_2_shoot".equals(playNumber)) {
            offencePlay = "两分投篮";
        } else if ("rate_layup".equals(playNumber)) {
            offencePlay = "上篮";
        } else if ("rate_postup".equals(playNumber)) {
            offencePlay = "内线单打";
        } else if ("rate_fast_break".equals(playNumber)) {
            offencePlay = "快攻";
        } else if ("rate_alleyoop".equals(playNumber)) {
            offencePlay = "空中接力";
        } else if ("rate_turnover".equals(playNumber)) {
            offencePlay = "进攻失误";
        } else offencePlay = "意外";
        setOffencePlay(offencePlay);
    }

    public String OffencePlayGenerate(){

        this.RandomPlayNumberGenerate();
        this.OffencePlayTransition();
        String play = this.getOffencePlay();
        return play;
    }

    }
