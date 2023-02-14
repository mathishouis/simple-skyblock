package fr.kozen.skyrama.interfaces;

import org.bukkit.entity.Player;

import java.util.List;

public interface ISubCommand {

    public abstract String getName();

    public abstract String getDescription();

    public abstract String getPermission();

    public abstract String getSyntax();

    public abstract List<String> getArgs();

    public abstract void perform(Player player, String args[]);

}
