package scripts.Data;

import org.tribot.api.General;
import org.tribot.api2007.Game;
import scripts.AntiBan;
import scripts.Timer;

public class Vars {

    private static Vars vars;

    public static Vars get() {
        return vars == null ? vars = new Vars() : vars;
    }


    public static void reset() {
        vars = new Vars();
    }

    public boolean scriptStatus = true;
    /**
     * Strings
     */
    public String target = null;

    public String status = "Initializing";

    public long currentTime;

    public long startTime;

    public int xpHr = 0;

    public boolean useStamina = true;

    public int mouseSpeed = General.random(105,115);

    public int eatAt = AntiBan.getEatAt();

    public boolean donePriestInPeril = Game.getSetting(302) >= 61;

    // are we using a course specified in args/GUI?
    public boolean overridingCourse = false;

    public boolean useSummerPie = false;

    public int marksCollected = 0;

    //for future arg support
    public int foodId = 0;

    public int abc2Chance = General.random(15,35); // how often it will abc2 sleep after an obstacle

    /**
     * AFK INFO
     */

    public int afkMin;
    public int afkMax;
    public int afkIntervalMin; //4min
    public int afkIntervalMax;//9min

    public boolean afkMode = false;
    public Timer afkTimer;

}
