package gameEngine;

import org.junit.jupiter.api.Test;

class GameRoundTest {
    @Test
    void percentageRandom() {

        int i = 0;
        int count0 = 0;
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;
        int count5 = 0;
        int count6 = 0;
        int count7 = 0;
        GameRound a = new GameRound();
        for (i = 0; i <= 10000; i++)
        {
            String play = a.OffencePlayGenerate();
            System.out.println(play);
            if(play.equalsIgnoreCase("三分投篮"))
                count0++;
            else if(play.equalsIgnoreCase("两分投篮"))
                count1++;
            else if(play.equalsIgnoreCase("上篮"))
                count2++;
            else if(play.equalsIgnoreCase("内线单打"))
                count3++;
            else if(play.equalsIgnoreCase("快攻"))
                count4++;
            else if(play.equalsIgnoreCase("空中接力"))
                count5++;
            else if(play.equalsIgnoreCase("进攻失误"))
                count6++;
            else
                count7++;
        }
        System.out.println("三分投篮: "+count0);
        System.out.println("两分投篮: "+count1);
        System.out.println("上篮: "+count2);
        System.out.println("内线单打: "+count3);
        System.out.println("快攻: "+count4);
        System.out.println("空中接力: "+count5);
        System.out.println("进攻失误: "+count6);
        System.out.println("意外: "+count7);
    }

}