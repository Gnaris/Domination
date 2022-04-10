package classification;

import classification.kit.KitList;
import classification.team.TeamList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Classification {

    private KitList kit;
    private TeamList team;

    public Classification(KitList kit, TeamList team)
    {
        this.kit = kit;
        this.team = team;
    }
}
