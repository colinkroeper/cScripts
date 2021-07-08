package scripts.Tasks.SeersCourse;


import scripts.API.Priority;
import scripts.API.Task;
import scripts.AgilityAPI.COURSES;
import scripts.AgilityAPI.Obstacle;
import scripts.Data.AgilityAreas;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Seers implements Task {

    public Obstacle WALL = new Obstacle(14927, "Climb-up",
            AgilityAreas.SEERS_WALL_AREA,
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

    List<Obstacle> allObstacles = new ArrayList<>();

    public  List<Obstacle> createList() {
        allObstacles.add(WALL);
        allObstacles.add(GAP_ONE);
        allObstacles.add(TIGHT_ROPE);
        allObstacles.add(GAP_TWO);
        allObstacles.add(GAP_THREE);
        allObstacles.add(EDGE);
        return allObstacles;
    }

    /**
     * Gets vailid obstacle
     * @return Optional of valid obstacle or empty optional
     */
    public Optional<Obstacle> getCurrentObstacle() {
        if (allObstacles.isEmpty())
            allObstacles = createList();

        for (int i = 0; i < allObstacles.size(); i++) {
            if (allObstacles.get(i).isValidObstacle()) {
                return Optional.ofNullable(allObstacles.get(i));
            }
        }
        return Optional.empty();
    }


    @Override
    public String toString(){
        return "Seers Course";
    }

    @Override
    public Priority priority() {
        return Priority.HIGH;
    }

    @Override
    public boolean validate() {
        return getCurrentObstacle().isPresent();
    }

    @Override
    public void execute() {
        Optional<Obstacle> obs = getCurrentObstacle();
        obs.ifPresent(Obstacle::navigateObstacle);
    }

    @Override
    public String course() {
        return COURSES.SEERS_VILLAGE.courseName;
    }

}
