package fr.kozen.skyrama.commands.subcommands;

import fr.kozen.skyrama.interfaces.ISubCommand;
import fr.kozen.skyrama.objects.islands.Island;
import fr.kozen.skyrama.objects.islands.IslandDao;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class InviteAcceptCommand implements ISubCommand {

    @Override
    public String getName() {
        return "accept";
    }

    @Override
    public String getDescription() {
        return "Accept player invitation";
    }

    @Override
    public String getSyntax() {
        return "/island accept playerName";
    }

    @Override
    public void perform(Player player, String[] args) {

        if(Bukkit.getPlayer(args[1]) != null) {
            Player target = Bukkit.getPlayer(args[1]);

            Island newIsland = IslandDao.getIslandByPlayer(target);

            if(!newIsland.getInvites().isEmpty() && newIsland.getInvites().get(player) != null) {

                Island island = IslandDao.getIslandByPlayer(player);

                island.getPlayers().remove(player);
                island.getInvites().remove(player);
                newIsland.getPlayers().add(player);

                IslandDao.setPlayerIsland(player, newIsland);

                target.sendMessage(ChatColor.GREEN + target.getName() + " joined your island!");

                player.sendMessage(ChatColor.GREEN + "You joined " + target.getName() + " island with success!");
                player.performCommand("is home");

            } else {
                player.sendMessage(ChatColor.RED + args[1] + " never invited you on his island.");
            }

        } else {
            player.sendMessage(ChatColor.RED + args[1] + " is not online.");
        }

    }

}
