package fr.kozen.skyrama.commands.subcommands;

import fr.kozen.skyrama.Skyrama;
import fr.kozen.skyrama.interfaces.ISubCommand;
import fr.kozen.skyrama.objects.islands.IslandDao;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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

        if(target != null && IslandDao.haveIsland(target)) {
            player.sendMessage(ChatColor.GREEN + "Teleporting to "+target.getName()+" island...");
            IslandDao.getIslandByPlayer(target).getSpawn().setWorld(Bukkit.getWorld((String) Skyrama.getPlugin(Skyrama.class).getConfig().get("general.world")));
            player.teleport(IslandDao.getIslandByPlayer(target).getSpawn());
        } else {
            player.sendMessage(ChatColor.RED + args[1] + " don't exist or don't have an island.");
        }

    }

}
