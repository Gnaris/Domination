package classification;

import classification.kit.KitList;
import classification.team.TeamList;

public class Classification {

    private KitList kit;
    private TeamList team;


    public Classification(KitList kit, TeamList team)
    {
        this.kit = kit;
        this.team = team;
    }

    public void setKit(KitList kit)
    {
        this.kit = kit;
    }

    public void setTeam(TeamList team)
    {
        this.team = team;
    }

    public KitList getKit() {
        return kit;
    }

    public TeamList getTeam() {
        return team;
    }
}
