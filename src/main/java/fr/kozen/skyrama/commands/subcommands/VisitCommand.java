package fr.kozen.skyrama.commands.subcommands;

import fr.kozen.skyrama.Skyrama;
import fr.kozen.skyrama.interfaces.ISubCommand;
import fr.kozen.skyrama.objects.islands.IslandDao;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class VisitCommand implements ISubCommand {

    @Override
    public String getName() {
        return "visit";
    }

    @Override
    public String getDescription() {
        return "Visit player island";
    }

    @Override
    public String getSyntax() {
        return "/island visit playerName";
    }

    @Override
    public void perform(Player player, String[] args) {

        Player target = null;

        if(Bukkit.getPlayer(args[1]) != null) {
            target = Bukkit.getPlayer(args[1]);
        } else {
            OfflinePlayer offlinePlayer = Skyrama.getPlugin(Skyrama.class).getServer().getOfflinePlayer(args[1]);
            if(offlinePlayer.hasPlayedBefore()) {
                target = offlinePlayer.getPlayer();
            }
        }

        if(target != null && Skyrama.getIslandManager().getPlayerIsland(player) != null) {
            player.sendMessage(Skyrama.getLocaleManager().getString("player-visit-island").replace("{0}", target.getName()));
            Skyrama.getIslandManager().getPlayerIsland(target).getSpawn().setWorld(Bukkit.getWorld((String) Skyrama.getPlugin(Skyrama.class).getConfig().get("general.world")));
            player.teleport(Skyrama.getIslandManager().getPlayerIsland(target).getSpawn());
        } else {
            player.sendMessage(Skyrama.getLocaleManager().getString("player-offline-island").replace("{0}", args[1]));
        }

    }

}
