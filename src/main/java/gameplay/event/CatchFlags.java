package gameplay.event;

import classification.TeamList;
import map.core.flag.Flag;
import map.core.flag.FlagStatus;
import game.Game;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
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

        //Check if the flag status is CONFLICT
        if(this.flag.getStatus() == FlagStatus.CONFLICT)
        {
            if(this.flag.getTeam_catched() == TeamList.NONE)
            {
                this.flag.buildFlag(Material.PURPLE_CONCRETE, Material.PURPLE_STAINED_GLASS, (int) this.game.getGameCharacteristicValue("radius"));
            }
            this.cancel();
            this.bar.removePlayer(this.catcher);
            this.flag.getPlayer_capturing_list().put(this.game.getPlayer_list().get(this.catcher.getUniqueId()).getTeam(), null);
        }

        //Reduce progress bar
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
            TeamList player_team = this.game.getPlayer_list().get(this.catcher.getUniqueId()).getTeam();
            this.flag.buildFlag(player_team.getConcrete(), player_team.getGlass(), (int) this.game.getGameCharacteristicValue("radius"));
            this.bar.removePlayer(this.catcher);
        }

        //If flag captured
        if(this.flag.getStatus() == FlagStatus.CAPTURED)
        {
            this.cancel();
            this.bar.removePlayer(this.catcher);
            this.flag.setStatus(FlagStatus.NONE);
            this.flag.getPlayer_capturing_list().put(this.game.getPlayer_list().get(this.catcher.getUniqueId()).getTeam(), null);
        }
    }
}