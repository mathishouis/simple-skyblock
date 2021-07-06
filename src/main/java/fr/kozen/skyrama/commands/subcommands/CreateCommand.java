package fr.kozen.skyrama.commands.subcommands;

import fr.kozen.skyrama.Skyrama;
import fr.kozen.skyrama.interfaces.ISubCommand;
import fr.kozen.skyrama.objects.islands.IslandDao;
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

        if(Skyrama.getIslandManager().getPlayerIsland(player) == null) {
            Skyrama.getIslandManager().create(player);
        } else {
            player.sendMessage(Skyrama.getLocaleManager().getString("player-already-have-island"));
        }

    }


}
