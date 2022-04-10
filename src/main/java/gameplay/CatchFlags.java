package gameplay;

import classification.team.TeamList;
import coliseum.core.Flag;
import game.Game;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

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
        this.bar.addPlayer(catcher);
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
            this.flag.setCatched(true);
            this.flag.setTeam_catched(this.game.getPlayer_list().get(this.catcher.getUniqueId()).getTeam());
            for(TeamList team_list : TeamList.values())
            {
                if(this.game.getPlayer_list().get(this.catcher.getUniqueId()).getTeam() == team_list)
                {
                    this.flag.buildFlag(team_list.getConcrete(), team_list.getGlass(), (int) this.game.getGameCharacteristicValue("radius"));
                    break;
                }
            }
            this.bar.removePlayer(this.catcher);
        }
    }

    public BossBar getBar()
    {
        return this.bar;
    }
}