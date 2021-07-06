package fr.kozen.skyrama.commands.subcommands;

import fr.kozen.skyrama.Skyrama;
import fr.kozen.skyrama.interfaces.ISubCommand;
import fr.kozen.skyrama.objects.islands.IslandDao;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements ISubCommand {

    @Override
    public String getName() {
        return "setspawn";
    }

    @Override
    public String getDescription() {
        return "Set spawn of your island to your position";
    }

    @Override
    public String getSyntax() {
        return "/island setspawn";
    }

    @Override
    public void perform(Player player, String[] args) {

        if(Skyrama.getGridManager().isInPlayerIsland(player, player.getLocation()) != 2) {
            player.sendMessage(Skyrama.getLocaleManager().getString("setspawn-out-island"));
        } else {
            Skyrama.getIslandManager().getPlayerIsland(player).setSpawn(player.getLocation());
            IslandDao.save(Skyrama.getIslandManager().getPlayerIsland(player));
            player.sendMessage(Skyrama.getLocaleManager().getString("setspawn-success"));
        }

    }

}
