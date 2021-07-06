package fr.kozen.skyrama.commands.subcommands;

import fr.kozen.skyrama.Skyrama;
import fr.kozen.skyrama.interfaces.ISubCommand;
import fr.kozen.skyrama.objects.islands.Island;
import fr.kozen.skyrama.objects.islands.IslandDao;
import fr.kozen.skyrama.types.Rank;
import org.bukkit.Bukkit;
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

            Island newIsland = Skyrama.getIslandManager().getPlayerIsland(target);

            if(!newIsland.getInvites().isEmpty() && newIsland.getInvites().get(player) != null) {

                if(Skyrama.getIslandManager().getPlayerIsland(player) != null) {

                    Island island = Skyrama.getIslandManager().getPlayerIsland(player);

                    island.removePlayer(player);
                    newIsland.addPlayer(player, Rank.fromInt(1));

                }

                newIsland.addPlayer(player, Rank.fromInt(1));

                target.sendMessage(Skyrama.getLocaleManager().getString("player-join-island").replace("{0}", player.getName()));

                player.sendMessage(Skyrama.getLocaleManager().getString("player-join-island-success").replace("{0}", target.getName()));
                player.performCommand("is home");

            } else {
                player.sendMessage(Skyrama.getLocaleManager().getString("player-no-invited").replace("{0}", args[1]));
            }

        } else {
            player.sendMessage(Skyrama.getLocaleManager().getString("player-offline").replace("{0}", args[1]));
        }

    }

}
