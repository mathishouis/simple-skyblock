package fr.kozen.skyrama.commands;

import fr.kozen.skyrama.commands.subcommands.*;
import fr.kozen.skyrama.interfaces.ISubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandManager implements CommandExecutor {

    private final ArrayList<ISubCommand> subcommands = new ArrayList<>();

    public CommandManager(){

        subcommands.add(new CreateCommand());
        subcommands.add(new HomeCommand());
        subcommands.add(new SetSpawnCommand());
        subcommands.add(new VisitCommand());
        subcommands.add(new InviteCommand());
        subcommands.add(new InviteAcceptCommand());
        subcommands.add(new InviteDenyCommand());
        subcommands.add(new LeaveCommand());

    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;

            if (args.length > 0){
                for (int i = 0; i < getSubcommands().size(); i++){
                    if (args[0].equalsIgnoreCase(getSubcommands().get(i).getName())){
                        getSubcommands().get(i).perform(p, args);
                    }
                }
            }else if(args.length == 0){
                p.sendMessage("--------------------------------");
                for (int i = 0; i < getSubcommands().size(); i++){
                    p.sendMessage(getSubcommands().get(i).getSyntax() + " - " + getSubcommands().get(i).getDescription());
                }
                p.sendMessage("--------------------------------");
            }

        }


        return true;

    }

    public ArrayList<ISubCommand> getSubcommands(){
        return subcommands;
    }

}
