package org.leminegaming.permsTool;

import org.bukkit.plugin.java.JavaPlugin;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;

public final class PermsTool extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Loading Plugin...");
        getLogger().info("Plugin enabled successfully!");

        // Register the /admin command
        LuckPerms api = LuckPermsProvider.get();
        if (api != null) {
            this.getCommand("admin").setExecutor(new AdminCommand(api));
        } else {
            getLogger().severe("LuckPerms not found! Disabling plugin.");
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Shutting down...");
        getLogger().info("Plugin disabled successfully!");
    }
}
