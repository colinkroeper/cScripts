package scripts;

import org.tribot.api.Timing;
import org.tribot.api2007.NPCChat;
import org.tribot.api2007.NPCs;
import org.tribot.api2007.types.RSNPC;
import scripts.dax_api.walker.utils.AccurateMouse;
import scripts.dax_api.walker_engine.interaction_handling.NPCInteraction;

public class NpcChat extends NPCChat {


    public static boolean talkToNPC(String npcName) {
        RSNPC[] targetNPC = NPCs.findNearest(npcName);
        if (targetNPC.length > 0) {
            if (!targetNPC[0].isOnScreen()) {
                if (PathingUtil.walkToTile(targetNPC[0].getPosition(), 2, false))
                    Timing.waitCondition(() -> targetNPC[0].isClickable(), 8000);
            }
            for (int i = 0; i < 3; i++) {
                if (AccurateMouse.click(targetNPC[0], "Talk-to"))
                    return NPCInteraction.waitForConversationWindow();

            }
        }
        return false;
    }

    public static boolean talkToNPC(int npcName) {
        RSNPC[] targetNPC = NPCs.findNearest(npcName);
        if (targetNPC.length > 0) {
            if (!targetNPC[0].isOnScreen()) {
                if (PathingUtil.walkToTile(targetNPC[0].getPosition(), 2, false))
                    Timer.waitCondition(() -> targetNPC[0].isClickable(), 8000);
            }
            for (int i = 0; i < 3; i++) {
                if (AccurateMouse.click(targetNPC[0], "Talk-to"))
                    return NPCInteraction.waitForConversationWindow();
            }
        }
        return false;
    }
}
