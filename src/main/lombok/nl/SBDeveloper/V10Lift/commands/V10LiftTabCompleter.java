package nl.SBDeveloper.V10Lift.commands;

import nl.SBDeveloper.V10Lift.api.objects.Lift;
import nl.SBDeveloper.V10Lift.managers.DataManager;
import nl.SBDeveloper.V10Lift.managers.VaultManager;
import nl.SBDeveloper.V10Lift.V10LiftPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class V10LiftTabCompleter implements TabCompleter {

    private static final List<String> COMMANDS = Arrays.asList("create", "delete", "rename", "abort", "whois", "edit", "floor", "input", "build", "rope", "door", "speed", "realistic", "repair", "disable", "whitelist", "reload", "help", "start", "stop", "offline");
    private static final List<String> SUBRENAME = Arrays.asList("add", "del", "rename");
    private static final List<String> SUB = Arrays.asList("add", "del");
    @Override
    public List<String> onTabComplete(@Nonnull CommandSender commandSender, @Nonnull Command cmd, @Nonnull String label, @Nonnull String[] args) {
        if (label.equalsIgnoreCase("v10lift")) {
            if (args.length == 1) {
                return StringUtil.copyPartialMatches(args[0], COMMANDS, new ArrayList<>());
            } else if (args.length == 2) {
                //Command based sub-commands
                if (args[0].equalsIgnoreCase("delete")
                        || args[0].equalsIgnoreCase("edit")
                        || args[0].equalsIgnoreCase("whois")
                        || args[0].equalsIgnoreCase("repair")
                        || args[0].equalsIgnoreCase("disable")
                        || args[0].equalsIgnoreCase("start")
                        || args[0].equalsIgnoreCase("stop")) {
                    return StringUtil.copyPartialMatches(args[1], DataManager.getLifts().keySet(), new ArrayList<>());
                } else if (args[0].equalsIgnoreCase("floor")) {
                    return StringUtil.copyPartialMatches(args[1], SUBRENAME, new ArrayList<>());
                } else if (args[0].equalsIgnoreCase("input")
                        || args[0].equalsIgnoreCase("offline")
                        || args[0].equalsIgnoreCase("whitelist")) {
                    return StringUtil.copyPartialMatches(args[1], SUB, new ArrayList<>());
                }
            } else if (args.length == 3) {
                //Command based arguments
                if (args[0].equalsIgnoreCase("floor") && (args[1].equalsIgnoreCase("del") || args[1].equalsIgnoreCase("rename"))) {
                    if (commandSender instanceof Player) {
                        Player p = (Player) commandSender;
                        if (DataManager.containsEditPlayer(p.getUniqueId())) {
                            Lift lift = DataManager.getLift(DataManager.getEditPlayer(p.getUniqueId()));
                            return StringUtil.copyPartialMatches(args[2], lift.getFloors().keySet(), new ArrayList<>());
                        }
                    }
                } else if ((args[0].equalsIgnoreCase("input")
                        || args[0].equalsIgnoreCase("offline")) && args[1].equalsIgnoreCase("del")) {
                    if (commandSender instanceof Player) {
                        Player p = (Player) commandSender;
                        if (DataManager.containsEditPlayer(p.getUniqueId())) {
                            Lift lift = DataManager.getLift(DataManager.getEditPlayer(p.getUniqueId()));
                            return StringUtil.copyPartialMatches(args[2], lift.getFloors().keySet(), new ArrayList<>());
                        }
                    }
                } else if (args[0].equalsIgnoreCase("whitelist")) {
                    ArrayList<String> playerOrGroupNames = new ArrayList<>();
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        playerOrGroupNames.add(p.getName());
                    }
                    if (V10LiftPlugin.isVaultEnabled()) {
                        playerOrGroupNames.addAll(VaultManager.getGroups());
                    }
                    return StringUtil.copyPartialMatches(args[2], playerOrGroupNames, new ArrayList<>());
                }
            } else if (args.length == 4) {
                //Command based arguments
                if (args[0].equalsIgnoreCase("whitelist")) {
                    if (commandSender instanceof Player) {
                        Player p = (Player) commandSender;
                        if (DataManager.containsEditPlayer(p.getUniqueId())) {
                            Lift lift = DataManager.getLift(DataManager.getEditPlayer(p.getUniqueId()));
                            return StringUtil.copyPartialMatches(args[3], lift.getFloors().keySet(), new ArrayList<>());
                        }
                    }
                }
            }
        }
        return null;
    }

}
