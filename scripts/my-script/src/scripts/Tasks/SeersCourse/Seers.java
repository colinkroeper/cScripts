package scripts.Tasks.SeersCourse;


import org.tribot.api.General;
import scripts.API.Priority;
import scripts.API.Task;
import scripts.AgilityAPI.AgilUtils;
import scripts.AgilityAPI.COURSES;
import scripts.AgilityAPI.Obstacle;
import scripts.Data.AgilityAreas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Seers implements Task {

    public Obstacle WALL = new Obstacle(14927, "Climb-up",
            AgilityAreas.SEERS_LARGE_WALL_AREA,
            AgilityAreas.SEERS_GAP_ONE_AREA);
    public Obstacle GAP_ONE = new Obstacle(14928, "Jump",
            AgilityAreas.SEERS_GAP_ONE_AREA,
            AgilityAreas.SEERS_TIGHT_ROPE_AREA);
    public Obstacle TIGHT_ROPE = new Obstacle(14932, "Cross",
            AgilityAreas.SEERS_TIGHT_ROPE_AREA,
            AgilityAreas.SEERS_GAP_TWO_AREA);
    public Obstacle GAP_TWO = new Obstacle(14929, "Jump",
            AgilityAreas.SEERS_GAP_TWO_AREA,
            AgilityAreas.SEERS_GAP_THREE_AREA);
    public Obstacle GAP_THREE = new Obstacle(14930, "Jump",
            AgilityAreas.SEERS_GAP_THREE_AREA,
            AgilityAreas.SEERS_EDGE_AREA);
    public Obstacle EDGE = new Obstacle(14931, "Jump",
            AgilityAreas.SEERS_EDGE_AREA,
            AgilityAreas.SEERS_END_AREA);

    List<Obstacle> allObstacles = new ArrayList<>(Arrays.asList(
            WALL,
            GAP_ONE,
            TIGHT_ROPE,
            GAP_TWO,
            GAP_THREE,
            EDGE
    ));


    String message = "";

    @Override
    public String toString(){
        return message;
    }

    @Override
    public Priority priority() {
        return Priority.HIGH;
    }

    @Override
    public boolean validate() {
        return AgilUtils.isWithinLevelRange(60,80);
    }

    @Override
    public void execute() {
        Optional<Obstacle> obs = AgilUtils.getCurrentObstacle(allObstacles);
        obs.ifPresent(obstacle -> message = obstacle.getObstacleAction() + " " +
                obstacle.getObstacleName());
        obs.ifPresent(Obstacle::navigateObstacle);
    }

    @Override
    public String course() {
        return COURSES.SEERS_VILLAGE.courseName;
    }

}
