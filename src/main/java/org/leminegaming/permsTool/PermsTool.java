package org.leminegaming.permsTool;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class PermsTool extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Loading Plugin...");
        getLogger().info("Running on " + (isFolia() ? "Folia" : "Paper/Spigot") + ".");

        LuckPerms api;
        try {
            api = LuckPermsProvider.get();
        } catch (IllegalStateException e) {
            api = null;
        }

        if (api == null) {
            getLogger().severe("LuckPerms not found! Disabling plugin.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        // Register the /admin command
        PluginCommand command = getCommand("admin");
        if (command == null) {
            getLogger().severe("Command 'admin' is missing from plugin.yml! Disabling plugin.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        command.setExecutor(new AdminCommand(api));

        getLogger().info("Plugin enabled successfully!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Shutting down...");
        getLogger().info("Plugin disabled successfully!");
    }

    /**
     * Folia is the only server that ships the regionised server implementation.
     * Paper also exposes the Folia scheduler API, so probing for scheduler
     * classes would misidentify Paper as Folia.
     */
    private static boolean isFolia() {
        try {
            Class.forName(
                    "io.papermc.paper.threadedregions.RegionizedServer",
                    false,
                    PermsTool.class.getClassLoader()
            );
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
