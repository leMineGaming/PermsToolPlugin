package org.leminegaming.permsTool;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import org.bukkit.command.Command;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class AdminCommand implements CommandExecutor {

    private final LuckPerms luckPerms;

    public AdminCommand(LuckPerms luckPerms) {
        this.luckPerms = luckPerms;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "[PermsTool] This command can only be executed by a player.");
            return true;
        }

        Player player = (Player) sender;
        User user = luckPerms.getUserManager().getUser(player.getUniqueId());

        if (user == null) {
            player.sendMessage(ChatColor.RED + "[PermsTool] Could not find your user data.");
            return true;
        }

        if (user.getPrimaryGroup().equalsIgnoreCase("admindisabled")) {
            user.data().add(Node.builder("group.admin").build());
            user.data().remove(Node.builder("group.admindisabled").build());
            player.sendMessage(ChatColor.GREEN + "[PermsTool] You have been given admin permissions.");
            player.kickPlayer(ChatColor.RED + "Permissions Changed. Please rejoin!");
        } else if (user.getPrimaryGroup().equalsIgnoreCase("admin")) {
            user.data().add(Node.builder("group.admindisabled").build());
            user.data().remove(Node.builder("group.admin").build());
            player.sendMessage(ChatColor.YELLOW + "[PermsTool] Your admin permissions have been disabled.");
            player.kickPlayer(ChatColor.RED + "Permissions Changed. Please rejoin!");
        } else {
            player.sendMessage(ChatColor.RED + "[PermsTool] You do not have the required permissions.");
        }

        luckPerms.getUserManager().saveUser(user);
        return true;
    }
}