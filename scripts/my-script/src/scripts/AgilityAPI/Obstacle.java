package scripts.AgilityAPI;


import scripts.Data.Vars;
import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api2007.*;
import org.tribot.api2007.ext.Filters;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSObjectDefinition;;
import scripts.*;
import scripts.dax_api.walker.utils.AccurateMouse;
import scripts.dax_api.walker.utils.camera.DaxCamera;

public class Obstacle {

    int obstacleId;
    String obstacleName;
    public String obstacleAction;
    RSArea obstacleArea;
    RSArea nextObstacleArea;

    public Obstacle(int id, String action, RSArea area) {
        this.obstacleId = id;
        this.obstacleAction = action;
        this.obstacleArea = area;
    }


    public Obstacle(int id, String action, RSArea area, RSArea nextArea) {
        this.obstacleId = id;
        this.obstacleAction = action;
        this.obstacleArea = area;
        this.nextObstacleArea = nextArea;
    }


    public Obstacle(int id, String name, String action, RSArea area) {
        this.obstacleId = id;
        this.obstacleName = name;
        this.obstacleAction = action;
        this.obstacleArea = area;
    }


    public void setRun() {
        if (AntiBan.getRunAt() < Game.getRunEnergy() &&
                !Options.isRunEnabled()) {
            General.println("[Debug]: Enabling run");
            Options.setRunEnabled(true);
        }
    }

    public boolean eat() {
        if (Combat.getHP() < Vars.get().eatAt) {
            General.println("[Debug]: Eating food");
            if (!EatUtil.eatFood()) {
                General.println("[Debug]: We have no more food in inventory and need to eat. Ending");
                return cAgility.isRunning = false;
            }

            Vars.get().eatAt = AntiBan.getEatAt();
            General.println("[ABC2]: Next eating at: " + Vars.get().eatAt);
        }
        return true;
    }


    public boolean navigateObstacle() {
        if (this.obstacleArea.contains(Player.getPosition())) {

            if (Vars.get().afkTimer != null &&
                    !Vars.get().afkTimer.isRunning() && Vars.get().afkMode) {
                Utils.afk(General.random(Vars.get().afkMin, Vars.get().afkMax));
                Vars.get().afkTimer.reset();
            }

            if (!eat())
                return false;

            setRun();
            AgilUtils.getMark(this.obstacleArea);

            RSObject[] obj = Objects.findNearest(20,
                    Filters.Objects.actionsContains(this.obstacleAction)
                            .and(Filters.Objects.idEquals(this.obstacleId)));
            if (obj.length > 0) {
                //General.println(this.obstacleAction + " " + getObstacleName());
                cAgility.status = this.obstacleAction + " " + getObstacleName();
                if (!obj[0].isClickable()) {
                    if (obj[0].getPosition().distanceTo(Player.getPosition()) > General.random(8, 12)) {
                        General.println("[Agility]: Moving to " + getObstacleName());

                        if (Walking.blindWalkTo(obj[0].getPosition()))
                            Timer.slowWaitCondition(() -> obj[0].isClickable(), 6000, 10000);


                    } else
                        DaxCamera.focus(obj[0]);

                }
                if (this.nextObstacleArea != null) {
                    int chance = General.random(0, 100);
                    if (chance > Vars.get().abc2Chance) {

                        if (Player.isMoving()) {
                            General.println("[Debug]: Accurate Mouse clicking [ABC2] " + cAgility.status);
                            if (AccurateMouse.click(obj[0], this.obstacleAction)) {
                                Timer.waitCondition(Player::isMoving, 1200, 2200);

                                return Timer.abc2WaitCondition(() -> !Player.isMoving()
                                        && this.nextObstacleArea.contains(Player.getPosition())
                                        && Player.getAnimation() == -1, 6500, 9500);
                            }
                        }
                        General.println("[Debug]: Clicking [ABC2] " + this.obstacleAction + " " + getObstacleName());
                        if (DynamicClicking.clickRSObject(obj[0], this.obstacleAction)) {
                            Timer.waitCondition(Player::isMoving, 1200, 2200);

                            return Timer.abc2WaitCondition(() -> !Player.isMoving()
                                    && this.nextObstacleArea.contains(Player.getPosition())
                                    && Player.getAnimation() == -1, 6500, 9500);
                        }
                    } else {
                        General.println("[Debug]: Clicking " + this.obstacleAction + " " + getObstacleName());
                        if (DynamicClicking.clickRSObject(obj[0], this.obstacleAction)) {
                            Timer.waitCondition(Player::isMoving, 1200, 2200);
                            return Timer.agilityWaitCondition(() -> !Player.isMoving()
                                    && this.nextObstacleArea.contains(Player.getPosition())
                                    && Player.getAnimation() == -1, 6500, 9500);
                        }
                    }
                } else
                    return DynamicClicking.clickRSObject(obj[0], this.obstacleAction)
                            && Timer.agilityWaitCondition(() -> !Player.isMoving()
                            && !this.obstacleArea.contains(Player.getPosition()), 3500, 5500);
            }
        }
        return false;
    }

    public boolean navigateObstacle(int minTime, int maxTime) {
        if (this.obstacleArea.contains(Player.getPosition())) {
            if (Vars.get().afkTimer != null &&
                    !Vars.get().afkTimer.isRunning() && Vars.get().afkMode) {
                Utils.afk(General.random(Vars.get().afkMin, Vars.get().afkMax));
                Vars.get().afkTimer.reset();
            }
            AgilUtils.getMark(this.obstacleArea);
            RSObject[] obj = Objects.findNearest(20, Filters.Objects.actionsContains(this.obstacleAction)
                    .and(Filters.Objects.idEquals(this.obstacleId)));
            if (obj.length > 0) {
                if (!obj[0].isClickable()) {
                    if (obj[0].getPosition().distanceTo(Player.getPosition()) > General.random(8, 12)) {
                        General.println("[Agility]: Moving to object");

                        if (Walking.blindWalkTo(obj[0].getPosition()))
                            Timer.waitCondition(() -> obj[0].isClickable(), 6000, 10000);
                    } else
                        DaxCamera.focus(obj[0]);
                }
                if (this.nextObstacleArea != null) {
                    return DynamicClicking.clickRSObject(obj[0], this.obstacleAction)
                            && Timer.waitCondition(() -> Player.isMoving()
                            && this.nextObstacleArea.contains(Player.getPosition()), minTime, maxTime);
                } else
                    return DynamicClicking.clickRSObject(obj[0], this.obstacleAction)
                            && Timer.waitCondition(() -> Player.isMoving()
                            && !this.obstacleArea.contains(Player.getPosition()), minTime, maxTime);
            }
        }
        return false;
    }


    public boolean isValidObstacle() {
        if (this.obstacleArea.contains(Player.getPosition())) {
            // General.println("[Debug]: Obstacle validated: " + this.obstacleId);
            return this.obstacleArea.contains(Player.getPosition());
        }
        return this.obstacleArea.contains(Player.getPosition());
    }

    public String getObstacleName() {
        //if (this.obstacleName == null) {
        RSObjectDefinition def = RSObjectDefinition.get(this.obstacleId);
        if (def != null)
            return this.obstacleName = def.getName();

        return this.obstacleName;
    }


    public String getObstacleAction() {
        return this.obstacleAction;
    }
}
