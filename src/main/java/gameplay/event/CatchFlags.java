package gameplay.event;

import classification.team.TeamList;
import coliseum.core.Flag;
import coliseum.core.FlagStatus;
import game.Game;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

@Getter
public class CatchFlags extends BukkitRunnable {

    private final Game game;
    private final Flag flag;
    private final Player catcher;
    private final BossBar bar;

    public CatchFlags(Game game, Flag flag, Player catcher)
    {
        this.game = game;
        this.flag = flag;
        this.catcher = catcher;
        this.bar = Bukkit.createBossBar("§aCapture de drapeau en cours !", BarColor.RED, BarStyle.SOLID);
        this.bar.setVisible(true);
        this.bar.setProgress(1.0);
    }

    @Override
    public void run() {
        double reduce_progress = 1.0 / (int) this.game.getGameCharacteristicValue("catchtimer");

        if(this.bar.getProgress() - reduce_progress >= 0)
        {
            this.bar.setProgress(this.bar.getProgress() - reduce_progress);
        }
        else
        {
            this.bar.setProgress(0);
        }

        if(this.bar.getProgress() <= 0)
        {
            this.flag.setStatus(FlagStatus.CAPTURED);
            this.flag.setTeam_catched(this.game.getPlayer_list().get(this.catcher.getUniqueId()).getTeam());
            TeamList team_color = null;
            int i = 0;
            while(team_color == null && i < TeamList.values().length)
            {
                if(this.game.getPlayer_list().get(this.catcher.getUniqueId()).getTeam() == TeamList.values()[i])
                {
                    team_color = TeamList.values()[i];
                    this.flag.buildFlag(team_color.getConcrete(), team_color.getGlass(), (int) this.game.getGameCharacteristicValue("radius"));
                }
                i++;
            }
            this.bar.removePlayer(this.catcher);
        }
    }
}