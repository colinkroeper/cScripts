package scripts.QuestSteps;

import org.tribot.api.General;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;
import scripts.NpcChat;
import scripts.PathingUtil;

import scripts.dax_api.walker_engine.interaction_handling.NPCInteraction;

public class NPCStep {


    private int npcID;
    private RSArea area;
    public RSTile npcTile;
    private String[] chatOptions;
    public String npcName;

    public NPCStep(int npcID, RSArea npcArea) {
        this.npcID = npcID;
        this.area = npcArea;
    }

    public NPCStep(String npcString, RSArea npcArea) {
        this.npcName = npcString;
        this.area = npcArea;
    }
    public NPCStep(String npcString, RSTile npcTile) {
        this.npcName = npcString;
        this.npcTile = npcTile;
    }

    public NPCStep(int npcID, RSTile npcTile) {
        this.npcID = npcID;
        this.npcTile = npcTile;
    }

    public NPCStep(int npcID, RSArea npcArea, String[] chatText) {
        this.npcID = npcID;
        this.area = npcArea;
        this.chatOptions = chatText;
    }

    public NPCStep(int npcID, RSTile npcTile, String[] chatText) {
        this.npcID = npcID;
        this.npcTile = npcTile;
        this.chatOptions = chatText;
    }

    public NPCStep(String npcName, RSTile npcTile, String[] chatText) {
        this.npcName = npcName;
        this.npcTile = npcTile;
        this.chatOptions = chatText;
    }

    public void execute() {
        if (this.area != null) {
            PathingUtil.walkToArea(this.area, false);
        } else if (this.npcTile != null) {
            PathingUtil.walkToTile(this.npcTile, 2, false);
        }

        General.println("[NPCStep]: Talking to npc: " + this.npcID);
        if (NpcChat.talkToNPC(this.npcID)) {
            NPCInteraction.waitForConversationWindow();
            if (chatOptions != null)
                NPCInteraction.handleConversation(chatOptions);
            NPCInteraction.handleConversation();
        }

    }
}
