package fr.kozen.skyrama.commands.subcommands;

import fr.kozen.skyrama.Skyrama;
import fr.kozen.skyrama.interfaces.ISubCommand;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

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
    public String getPermission() { return "skyrama.command.create"; }

    @Override
    public String getSyntax() {
        return "/island create";
    }

    @Override
    public List<String> getArgs() { return Arrays.asList(); }

    @Override
    public void perform(Player player, String[] args) {
        if(Skyrama.getIslandManager().getPlayerIsland(player) == null) {
            Skyrama.getIslandManager().create(player);
        } else {
            player.sendMessage(Skyrama.getLocaleManager().getString("player-already-have-island"));
        }
    }
}
