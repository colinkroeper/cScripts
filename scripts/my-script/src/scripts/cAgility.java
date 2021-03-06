package scripts;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.input.Mouse;
import org.tribot.api2007.Skills;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.Arguments;
import org.tribot.script.interfaces.Ending;
import org.tribot.script.interfaces.Painting;
import org.tribot.script.interfaces.Starting;
import scripts.API.Task;
import scripts.API.TaskSet;
import scripts.AgilityAPI.COURSES;
import scripts.Data.Vars;
import scripts.Tasks.Canifis.CanifisCourse;
import scripts.Tasks.Canifis.GoToCanifis;
import scripts.Tasks.DraynorVillage.DraynorCourse;
import scripts.Tasks.DraynorVillage.GoToDraynor;
import scripts.Tasks.Pollvniveach.GoToStart;
import scripts.Tasks.Pollvniveach.Pollniveach;
import scripts.Tasks.SeersCourse.GoToSeersStart;
import scripts.Tasks.SeersCourse.Seers;
import scripts.Tasks.TreeGnome.GoToTreeGnome;
import scripts.Tasks.TreeGnome.TreeGnomeCourse;
import scripts.Tasks.Varrock.GoToVarrock;
import scripts.Tasks.Varrock.VarrockCourse;
import scripts.dax_api.api_lib.WebWalkerServerApi;
import scripts.dax_api.api_lib.models.DaxCredentials;
import scripts.dax_api.api_lib.models.DaxCredentialsProvider;
import scripts.dax_api.teleports.Teleport;

import java.awt.*;
import java.util.HashMap;

@ScriptManifest(authors = {"Cass"}, category = "Agility", name = "cAgility v1", description = "Jumps on roofs and stuff (2)")
public class cAgility extends Script implements Painting, Starting, Ending, Arguments {

    final int startLevel = Skills.getActualLevel(Skills.SKILLS.AGILITY);
    final int startXp = Skills.getXP(Skills.SKILLS.AGILITY);
    int gainedXp = 0;
    int gainedLvl = 0;
    int currentXp = Skills.getXP(Skills.SKILLS.AGILITY);
    Skills.SKILLS currentSkill = Skills.SKILLS.AGILITY;
    public static boolean isRunning = true;
    public static String status = "Initializing...";
    public static String course = "Initializing...";


    public static Timer timeoutTimer = new Timer(General.random(300000, 420000));

    @Override
    public void run() {
        TaskSet tasks = new TaskSet(
                new Pollniveach(),
                new GoToStart(),
                new GoToTreeGnome(),
                new TreeGnomeCourse(),
                new GoToDraynor(),
                new DraynorCourse(),
                new GoToVarrock(),
                new VarrockCourse(),
                new GoToCanifis(),
                new CanifisCourse(),
                new Seers(),
                new GoToSeersStart()

        );

        if (Vars.get().afkMode) {
            Vars.get().afkIntervalMin = 240000; //4min
            Vars.get().afkIntervalMax = 540000;//9mi
            Vars.get().afkTimer = new Timer(General.random(Vars.get().afkIntervalMin, Vars.get().afkIntervalMax));

        }

        while (isRunning) {
            General.sleep(10);
            currentXp = Skills.getXP(currentSkill);

            Task task = tasks.getValidTask();
            if (task != null) {
                //status = task.toString();
                course = task.course();
                task.execute();
            }
            if (Skills.getXP(currentSkill) > currentXp)
                timeoutTimer.reset();

            if (!timeoutTimer.isRunning()) {
                General.println("[Error]: Script Timed Out due to no exp gain in ~5-7min");
                isRunning = false;
                break;
            }
        }
    }

    @Override
    public void onEnd() {
        General.println("[Ending]: Running for: " + Timing.msToString(getRunningTime()));
        General.println("[Ending]: Gained " + gainedXp + " xp (" + Vars.get().xpHr + "/hr)");
        General.println("[Ending]: Collected " + Vars.get().marksCollected + " marks of grace.");
        AntiBan.destroy();

    }

    @Override
    public void onPaint(Graphics g) {
        int currentLvl = Skills.getActualLevel(currentSkill);
        gainedLvl = currentLvl - startLevel;
        gainedXp = Skills.getXP(currentSkill) - startXp;

        double timeRan = getRunningTime();
        double timeRanMin = (timeRan / 3600000);

        Vars.get().xpHr = (int) (gainedXp / timeRanMin);
        Vars.get().marksHr = (int) (Vars.get().marksCollected / timeRanMin);
        long xpToLevel = Skills.getXPToLevel(currentSkill, currentLvl + 1);

        Vars.get().timeToLevel = (long) (xpToLevel / ((gainedXp / timeRan)));

        String[] strings;
        if (Vars.get().afkTimer != null) {
            strings = new String[]{
                    "cAgility v1.0",
                    "Running For: " + Timing.msToString(getRunningTime()),
                    "Course: " + course,
                    "Task: " + status,
                    "AFK Enabled: " + Vars.get().afkMode,
                    "Next AFK in: " + Timing.msToString(Vars.get().afkTimer.getRemaining()),
                    "Experience Gained: " + Utils.addCommaToNum(gainedXp) + "xp "
                            + "("
                            + Utils.addCommaToNum(Vars.get().xpHr) + "/hr)",
                    "Time to Next Level (" + (currentLvl + 1) + "): "
                            + Timing.msToString(Vars.get().timeToLevel),
                    "Marks Of Grace: " + Vars.get().marksCollected +
                            " (" + Vars.get().marksHr + "/hr)"
            };
        } else {
            strings = new String[]{
                    "cAgility v1.0",
                    "Running For: " + Timing.msToString(getRunningTime()),
                    "Course: " + course,
                    "Task: " + status,
                    "AFK Enabled: " + Vars.get().afkMode,
                    "Experience Gained: " + Utils.addCommaToNum(gainedXp) + "xp "
                            + "("
                            + Utils.addCommaToNum(Vars.get().xpHr) + "/hr)",
                    "Time to Next Level (" + (currentLvl + 1) + "): "
                            + Timing.msToString(Vars.get().timeToLevel),
                    "Marks Of Grace: " + Vars.get().marksCollected +
                            " (" + Vars.get().marksHr + "/hr)"
            };
        }

        PaintUtil.createPaint(g, strings);
    }

    @Override
    public void onStart() {
        General.println("[ABC2]: Next eating at: " + Vars.get().eatAt);
        int b = Vars.get().mouseSpeed;
        Mouse.setSpeed(b);
        General.println("[Debug]: Setting mouse speed to " + b);
        AntiBan.create();

        Teleport.blacklistTeleports(Teleport.GLORY_KARAMJA, Teleport.RING_OF_WEALTH_MISCELLANIA,
                Teleport.RING_OF_DUELING_FEROX_ENCLAVE);
        Vars.get().startTime = Timing.currentTimeMillis();

        WebWalkerServerApi.getInstance().setDaxCredentialsProvider(new DaxCredentialsProvider() {
            @Override
            public DaxCredentials getDaxCredentials() {
                return new DaxCredentials("sub_DPjXXzL5DeSiPf", " PUBLIC-KEY");
            }
        });
        General.println("[Starting]: ABC2 Sleeps will occur after " + Vars.get().abc2Chance + "% of actions");
    }

    @Override
    public void passArguments(HashMap<String, String> hashMap) {
        String scriptSelect = hashMap.get("custom_input");
        String clientStarter = hashMap.get("autostart");
        String input = clientStarter != null ? clientStarter : scriptSelect;
        General.println("[Debug]: Argument entered: " + input);

        for (String arg : input.split(";")) {
            try {

                if (arg.toLowerCase().contains("afk")) {
                    General.println("[Arguments]: AFK mode enabled");
                    Vars.get().afkMode = true;
                    String[] splt = arg.toLowerCase().split("afk:");

                    if (splt.length > 0) {
                        General.println(splt[1], Color.RED);
                        String[] numString = splt[1].split(",");
                        if (numString.length > 1) {
                            int minLngth = Integer.parseInt(numString[0]);
                            int maxLngth = Integer.parseInt(numString[1]);
                            General.println("[Debug]: Min AFK length: " + minLngth);
                            General.println("[Debug]: Max AFK Length: " + maxLngth);
                            Vars.get().afkMin = minLngth;
                            Vars.get().afkMax = maxLngth;
                        }
                    }
                }
                if (arg.toLowerCase().contains("frequency:")) {

                }
                if (arg.toLowerCase().contains("abc2:")) {
                    General.println("[Arguments]: ABC2 % Modified by args");
                    String[] splt = arg.toLowerCase().split("abc2:");
                    if (splt.length > 1) {
                        int prcntChance = Integer.parseInt(splt[1]);
                        Vars.get().abc2Chance = prcntChance;
                    }
                }
                if (arg.toLowerCase().contains("seers")) {
                    General.println("[Arguments]: Overriding course selection");
                    General.println("[Arguments]: Course selected: " + COURSES.SEERS_VILLAGE.courseName, Color.RED);
                    Vars.get().overridingCourse = true;
                    Vars.get().course = COURSES.SEERS_VILLAGE;
                }
                if (arg.toLowerCase().contains("varrock")) {
                    General.println("[Arguments]: Overriding course selection");
                    General.println("[Arguments]: Course selected: " + COURSES.VARROCK.courseName, Color.RED);
                    Vars.get().overridingCourse = true;
                    Vars.get().course = COURSES.VARROCK;
                }

            } catch (Exception e) {
                e.printStackTrace();
                General.println("[Error]: Args are incorrect", Color.RED);
            }
        }
    }
}
