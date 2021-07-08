package scripts.Tasks.Canifis;

import scripts.API.Priority;
import scripts.API.Task;
import scripts.AgilityAPI.AgilUtils;
import scripts.AgilityAPI.COURSES;
import scripts.Data.AgilityAreas;
import scripts.Data.Vars;
import org.tribot.api2007.Player;
import scripts.PathingUtil;

public class GoToCanifis implements Task {

    @Override
    public String toString(){
        return "Going to Canifis course";
    }


    @Override
    public Priority priority() {
        return Priority.HIGH;
    }

    @Override
    public boolean validate() {
        return AgilUtils.isWithinLevelRange(40, 60) &&
                !AgilityAreas.ALL_CANIFIS_ROOFTOPS.contains(Player.getPosition()) &&
                !AgilityAreas.ALL_CANIFIS_ROOFTOPS_LEVEL_2.contains(Player.getPosition()) &&
                !AgilityAreas.CANIFIS_FINISHED_AREA.contains(Player.getPosition()) &&
                !AgilityAreas.CANIFIS_LARGE_START.contains(Player.getPosition())
                && Vars.get().donePriestInPeril;
    }

    @Override
    public void execute() {
        PathingUtil.walkToArea(AgilityAreas.CANIFIS_SMALL_START, false);
    }

    @Override
    public String course() {
        return COURSES.CANIFIS.courseName;
    }
}
