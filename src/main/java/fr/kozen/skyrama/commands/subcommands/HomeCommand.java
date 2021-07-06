package fr.kozen.skyrama.commands.subcommands;

import fr.kozen.skyrama.Skyrama;
import fr.kozen.skyrama.interfaces.ISubCommand;
import fr.kozen.skyrama.objects.islands.IslandDao;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class HomeCommand implements ISubCommand {

    @Override
    public String getName() {
        return "home";
    }

    @Override
    public String getDescription() {
        return "Teleport to island home";
    }

    @Override
    public String getSyntax() {
        return "/island home";
    }

    @Override
    public void perform(Player player, String[] args) {

        if(Skyrama.getIslandManager().getPlayerIsland(player) != null) {
            player.sendMessage(Skyrama.getLocaleManager().getString("player-teleport-island"));
            Skyrama.getIslandManager().getPlayerIsland(player).getSpawn().setWorld(Bukkit.getWorld((String) Skyrama.getPlugin(Skyrama.class).getConfig().get("general.world")));
            player.teleport(Skyrama.getIslandManager().getPlayerIsland(player).getSpawn());
        } else {
            player.sendMessage(Skyrama.getLocaleManager().getString("player-no-island"));
        }

    }

}
