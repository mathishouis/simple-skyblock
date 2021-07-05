package fr.kozen.skyrama.commands.subcommands;

import fr.kozen.skyrama.interfaces.ISubCommand;
import fr.kozen.skyrama.objects.islands.Island;
import fr.kozen.skyrama.objects.islands.IslandDao;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class InviteDenyCommand implements ISubCommand {

    @Override
    public String getName() {
        return "deny";
    }

    @Override
    public String getDescription() {
        return "Decline player invitation";
    }

    @Override
    public String getSyntax() {
        return "/island deny playerName";
    }

    @Override
    public void perform(Player player, String[] args) {

        if(Bukkit.getPlayer(args[1]) != null) {
            Player target = Bukkit.getPlayer(args[1]);

            Island island = IslandDao.getIslandByPlayer(target);

            if(!island.getInvites().isEmpty() && island.getInvites().get(player) != null) {

                island.getInvites().remove(player);
                player.sendMessage(ChatColor.RED + "You declined the " + target.getName() + " island invitation.");
                target.sendMessage(ChatColor.RED + player.getName() + " declined your invitation");

            } else {
                player.sendMessage(ChatColor.RED + args[1] + " never invited you on his island.");
            }

        } else {
            player.sendMessage(ChatColor.RED + args[1] + " is not online.");
        }

    }

}
