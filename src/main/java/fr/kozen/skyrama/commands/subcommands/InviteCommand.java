package fr.kozen.skyrama.commands.subcommands;

import fr.kozen.skyrama.Skyrama;
import fr.kozen.skyrama.interfaces.ISubCommand;
import fr.kozen.skyrama.objects.islands.Island;
import fr.kozen.skyrama.objects.islands.IslandDao;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class InviteCommand implements ISubCommand {

    @Override
    public String getName() {
        return "invite";
    }

    @Override
    public String getDescription() {
        return "Invite player to join your island";
    }

    @Override
    public String getSyntax() {
        return "/island invite";
    }

    @Override
    public void perform(Player player, String[] args) {

        if(Bukkit.getPlayer(args[1]) != null) {
            Player target = Bukkit.getPlayer(args[1]);

            Island island = IslandDao.getIslandByPlayer(player);

            if(island.getInvites() != null && island.getInvites().get(target) == null) {
                player.sendMessage(ChatColor.GREEN + "Sending an invitation to "+target.getName()+"...");
                target.sendMessage(ChatColor.GREEN + " ");
                target.sendMessage(ChatColor.GRAY + player.getName() + " invited you to play on his island? If you accept your island will be deleted.");
                target.sendMessage(ChatColor.GREEN + " ");

                TextComponent messageYes = new TextComponent (ChatColor.GREEN + "[ACCEPT]");
                messageYes.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/island accept " + player.getName()));

                TextComponent messageNo = new TextComponent (ChatColor.RED + "[DECLINE] ");
                messageNo.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/island deny " + player.getName()));

                messageYes.addExtra(" ");
                messageYes.addExtra(messageNo);

                target.spigot().sendMessage(messageYes);
                target.sendMessage(ChatColor.GREEN + " ");

                island.getInvites().put(target, player);
            } else {
                player.sendMessage(ChatColor.RED + island.getInvites().get(target).getName() + " already invited " + target.getName());
            }
        } else {
            player.sendMessage(ChatColor.RED + args[1] + " is not online.");
        }

    }

}
