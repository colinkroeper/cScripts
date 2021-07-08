package scripts.Tasks.SeersCourse;


import org.tribot.api.General;
import org.tribot.api2007.Player;
import scripts.API.Priority;
import scripts.API.Task;
import scripts.AgilityAPI.COURSES;
import scripts.Data.AgilityAreas;
import scripts.PathingUtil;

public class GoToSeersStart implements Task {



    @Override
    public Priority priority() {
        return Priority.HIGHEST;
    }

    @Override
    public boolean validate() {
        return Player.getPosition().getPlane() == 0 &&
                !AgilityAreas.SEERS_WALL_AREA.contains(Player.getPosition());
    }

    @Override
    public void execute() {
        General.println("[Debug]: Going to Seers start");
        if (AgilityAreas.SEERS_END_AREA.contains(Player.getPosition())){
            PathingUtil.localNavigation(AgilityAreas.SEERS_WALL_AREA.getRandomTile());
        } else {
            PathingUtil.walkToArea(AgilityAreas.SEERS_WALL_AREA, false);
        }
    }

    @Override
    public String course() {
        return COURSES.SEERS_VILLAGE.courseName;
    }
}
