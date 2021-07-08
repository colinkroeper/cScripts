package scripts;

import scripts.dax_api.api_lib.DaxWalker;
import scripts.dax_api.api_lib.models.RunescapeBank;
import org.tribot.api.General;
import org.tribot.api2007.*;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.types.RSTile;
import org.tribot.api2007.util.DPathNavigator;
import scripts.dax_api.walker_engine.WalkerEngine;


import java.util.Arrays;

public class PathingUtil {

   public static DPathNavigator nav = new DPathNavigator();

    public static void movementIdle() {
        Timer.waitCondition(() -> Player.isMoving(), 3000, 5000);
        Timer.waitCondition(() -> !Player.isMoving(), 9000, 12000);
    }

    public static boolean localNavigation(RSTile destination){
        nav.setAcceptAdjacent(true);
        RSTile[] path =  nav.findPath(destination);
        if (path.length == 0){
            General.println("[PathingUtil]: DPathNavigator failed to generate a path");
            return false;
        }
        else {
            General.println("[PathingUtil]: DPathNavigator generated a path, feeding to dax");
            WalkerEngine.getInstance().walkPath(Arrays.asList(path));
            return true;
            //movementIdle();

        }
    }



    public static RSTile nearestBankTile(RSTile startTile) {

        int distance = startTile.distanceTo(RunescapeBank.VARROCK_WEST.getPosition());
        RSTile bankTile = RunescapeBank.VARROCK_WEST.getPosition();

        for (RunescapeBank tile : RunescapeBank.values()) {

            if (startTile.distanceTo(tile.getPosition()) < distance) {
                distance = startTile.distanceTo(tile.getPosition());
                bankTile = tile.getPosition();
                General.println(bankTile + "bankTile");
                General.println(distance);
            }
        }
        return bankTile;
    }

    public static boolean canReach(RSArea area){
        return PathFinding.canReach(area.getRandomTile(), false);
    }

    public static boolean canReach(RSTile tile){
        return PathFinding.canReach(tile, false);
    }


    public static boolean webWalkToArea(RSArea area) {
        int min = Game.isRunOn() ? 7000 : 12000;
        int max = Game.isRunOn() ? 9000 : 15000;

        if (!area.contains(Player.getPosition())) {
            for (int i = 0; i < 4; i++) {
                if (!WebWalking.walkTo(area.getRandomTile()))
                    General.println("[WebWalking]: Failed to walk, trying again");
                else
                    return Timer.waitCondition(() -> area.contains(Player.getPosition()), min, max);
            }
        }
        return false;
    }

    public static boolean webWalkToTile(RSTile tile) {
        int min = Game.isRunOn() ? 7000 : 12000;
        int max = Game.isRunOn() ? 9000 : 15000;

        if (tile.distanceTo(Player.getPosition()) > 10) {
            for (int i = 0; i < 4; i++) {
                if (!WebWalking.walkTo(tile))
                    General.println("[WebWalking]: Failed to walk, trying again");
                else
                    return Timer.waitCondition(() -> tile.distanceTo(Player.getPosition()) < 8, min, max);
            }
        }
        return false;
    }

    public static void checkRun() {
        if (Game.getRunEnergy() > (General.random(25, 45)))
            Options.setRunEnabled(true);
    }

    public static RSTile getTile(RSArea area, boolean first) {
        RSTile[] tile = area.getAllTiles();
        int length = tile.length;
        if (tile.length > 0 && first) {
            return tile[0];
        } else if (tile.length > 0) {
            return tile[length - 1];
        } else {
            General.println("[PathingUtils]: Failed to get first tile in area");
            return area.getRandomTile();
        }
    }

    public static RSArea makeLargerArea(RSArea area) {
        RSTile first = getTile(area, true);
        RSTile last = getTile(area, false);
        RSArea largerArea = new RSArea(first.translate(-2, 2), last.translate(2, -2));
        return largerArea;
    }

    public static RSArea makeLargerArea(RSTile tile) {
        RSArea largerArea = new RSArea(tile.translate(-2, 2), tile.translate(2, -2));
        return largerArea;
    }

    public static boolean testWalk(RSTile area) {
        checkRun();
        int sleepMin = Game.isRunOn() ? 8000 : 15000;
        int sleepMax = Game.isRunOn() ? 15000 : 20000;

        for (int i = 0; i < 3; i++) {
            if (Timer.waitCondition(() -> DaxWalker.getInstance().walkTo(area), 5000, 8000)) {
                General.println("[Pathing Utils]: Success");
                return true;
            }
            General.println("[Pathing Utils]: Failed to generate a path or timed out");
        }
        return false;
    }

    public static boolean walkToArea(RSArea area, boolean abc2Sleep, String message) {
        checkRun();
        int sleepMin = Game.isRunOn() ? 8000 : 15000;
        int sleepMax = Game.isRunOn() ? 15000 : 20000;
        RSArea largeArea = makeLargerArea(area);
        long currentTime;

        if (!largeArea.contains(Player.getPosition())) {
            for (int i = 0; i < 3; i++) {
                if (DaxWalker.getInstance().walkTo(area.getRandomTile())) {
                    currentTime = System.currentTimeMillis();
                    Timer.waitCondition(() -> largeArea.contains(Player.getPosition()), sleepMin, sleepMax);
                    if (abc2Sleep)
                        Utils.abc2ReactionSleep(currentTime);
                    return true;

                } else {
                    General.println("[PathingUtil]: Failed to generate a path, waiting and trying again. Message: " + message);
                    General.sleep(General.random(3500, 6500));
                }
            }

        } else // already in area
            return true;

        return false;
    }

    public static boolean walkToArea(RSArea area, boolean abc2Sleep) {
        checkRun();
        int sleepMin = Game.isRunOn() ? 8000 : 15000;
        int sleepMax = Game.isRunOn() ? 15000 : 20000;
        RSArea largeArea = makeLargerArea(area);
        long currentTime;
        if (!largeArea.contains(Player.getPosition())) {
            for (int i = 0; i < 3; i++) {
                if (DaxWalker.getInstance().walkTo(area.getRandomTile())) {
                    currentTime = System.currentTimeMillis();
                    Timer.waitCondition(() -> largeArea.contains(Player.getPosition()), sleepMin, sleepMax);
                    if (abc2Sleep)
                        Utils.abc2ReactionSleep(currentTime);
                    return true;

                } else {
                    General.println("[PathingUtil]: Failed to generate a path, waiting 3-5s and trying again.");
                    General.sleep(General.random(3500, 6500));
                }
            }

        } else // already in area
            return true;

        return false;
    }

    public static boolean walkToArea(RSArea area, int attempts, boolean abc2Sleep) {
        checkRun();
        int sleepMin = Game.isRunOn() ? 8000 : 15000;
        int sleepMax = Game.isRunOn() ? 15000 : 20000;
        RSArea largeArea = makeLargerArea(area);
        long currentTime;
        if (!largeArea.contains(Player.getPosition())) {
            for (int i = 0; i < attempts; i++) {
                if (DaxWalker.getInstance().walkTo(area.getRandomTile())) {
                   currentTime = System.currentTimeMillis();
                    Timer.waitCondition(() -> largeArea.contains(Player.getPosition()), sleepMin, sleepMax);
                    if (abc2Sleep)
                        Utils.abc2ReactionSleep(currentTime);
                    return true;

                } else {
                    General.println("[PathingUtil]: Failed to generate a path, waiting 3-5s and trying again.");
                    General.sleep(General.random(3500, 6500));
                }
            }

        } else // already in area
            return true;

        return false;
    }

    public static boolean walkToTile(RSTile tile, int sizeRadius, boolean abc2Sleep) {
        checkRun();
        int sleepMin = Game.isRunOn() ? 8000 : 15000;
        int sleepMax = Game.isRunOn() ? 15000 : 20000;
        RSArea largeArea = makeLargerArea(new RSArea(tile, sizeRadius));
        long currentTime;
        if (!largeArea.contains(Player.getPosition())) {
            for (int i = 0; i < 5; i++) {
                if (Timer.waitCondition(() ->
                        DaxWalker.getInstance().walkTo(tile), 5000, 8000)) {
                    currentTime = System.currentTimeMillis();
                    Timer.waitCondition(() -> largeArea.contains(Player.getPosition()), sleepMin, sleepMax);
                    if (abc2Sleep)
                        Utils.abc2ReactionSleep(currentTime);
                    return true;

                } else {
                    General.println("[Debug]: Failed to generate a path, waiting 2-5s and trying again.");
                    General.sleep(General.random(2500, 5500));
                }
            }

        } else // already in area
            return true;

        return false;
    }


    public final int[] RING_OF_DUELING = {2552, 2554, 2556, 2558, 2560, 2562, 2564, 2566};

    public boolean rodTele(String location) {
        RSItem[] rod = Equipment.find(RING_OF_DUELING);
        if (rod.length > 0) {
            for (int i = 0; i < 3; i++) {
               // Main.stage = "Going to " + location;
                if (rod[0].click(location))
                    return true;
            }
        }
        return false;
    }

}
