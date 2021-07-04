package fr.kozen.skyrama.commands.subcommands;

import fr.kozen.skyrama.Skyrama;
import fr.kozen.skyrama.interfaces.ISubCommand;
import fr.kozen.skyrama.objects.islands.IslandDao;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class CreateCommand implements ISubCommand {

    @Override
    public String getName() {
        return "create";
    }

    @Override
    public String getDescription() {
        return "Create an island";
    }

    @Override
    public String getSyntax() {
        return "/island create";
    }

    @Override
    public void perform(Player player, String[] args) {

        if(!IslandDao.haveIsland(player)) {
            Skyrama.getIslandManager().create(player);
        } else {
            player.sendMessage(ChatColor.RED + "You already have an island!");
        }

    }


}
