/**
 *  Created time: 2019_01_08
 *  @Author: Devin Hua
 *  Function description:
 *  To get each round of one game.
 *  Set probability of the offensive pattern in basketball game based on the random number.
 */

package gameEngine;

public class GameRound {

    private int getPlayNumber() {
        return PlayNumber;
    }

    private void setPlayNumber(int playNumber) {
        PlayNumber = playNumber;
    }

    private int PlayNumber = -1;

    private String getOffencePlay() {
        return OffencePlay;
    }

    private void setOffencePlay(String offencePlay) {
        OffencePlay = offencePlay;
    }

    private String OffencePlay = "";

    /**
     * The probability of making a three-point shot.
     */
    public static double rate_3_shoot = 0.27;
    /**
     * The probability of making a two-point shot.
     */
    public static double rate_2_shoot = 0.33;
    /**
     * The probability of making a break and lay-up.
     */
    public static double rate_break_layup = 0.15;
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
    public static double rate_alleyoop = 0.01;

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
        double randomNumber;
        //The java.lang.Math.random() returns a double value with a positive sign, greater than or equal to 0.0 and less than 1.0.
        randomNumber = Math.random();
        if (randomNumber >= 0 && randomNumber < rate_3_shoot)
            setPlayNumber(0);
        else if (randomNumber >= rate_3_shoot && randomNumber < rate_3_shoot + rate_2_shoot)
            setPlayNumber(1);
        else if (randomNumber >= rate_3_shoot + rate_2_shoot
                && randomNumber < rate_3_shoot + rate_2_shoot + rate_break_layup)
            setPlayNumber(2);
        else if (randomNumber >= rate_3_shoot + rate_2_shoot + rate_break_layup
                && randomNumber < rate_3_shoot + rate_2_shoot + rate_break_layup + rate_postup)
            setPlayNumber(3);
        else if (randomNumber >= rate_3_shoot + rate_2_shoot + rate_break_layup + rate_postup
                && randomNumber < rate_3_shoot + rate_2_shoot + rate_break_layup + rate_postup + rate_fast_break)
            setPlayNumber(4);
        else if (randomNumber >= rate_3_shoot + rate_2_shoot + rate_break_layup + rate_postup + rate_fast_break
                && randomNumber < rate_3_shoot + rate_2_shoot + rate_break_layup + rate_postup + rate_fast_break
                + rate_alleyoop)
            setPlayNumber(5);
        else
            setPlayNumber(-1);
    }

    private void OffencePlayTransition(){
        int playNumber = this.getPlayNumber();
        String offencePlay = "";
        switch (playNumber) {
            case -1:
                offencePlay = "进攻失误";
                break;
            case 0:
                offencePlay = "三分投篮";
                break;
            case 1:
                offencePlay = "两分投篮";
                break;
            case 2:
                offencePlay = "上篮";
                break;
            case 3:
                offencePlay = "内线单打";
                break;
            case 4:
                offencePlay = "快攻";
                break;
            case 5:
                offencePlay = "空中接力";
                break;
        }
        setOffencePlay(offencePlay);
    }

    public String OffencePlayGenerate(){

        this.RandomPlayNumberGenerate();
        this.OffencePlayTransition();
        String play = this.getOffencePlay();
        return play;
    }


    }
