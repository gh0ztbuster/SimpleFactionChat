package io.github.gh0ztbuster.simplefactionchat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;

public class SimpleFactionChat extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("Plugin Enabled =P");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin Disabled =O");
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player player = (Player) e.getPlayer();
        MPlayer uplayer = MPlayer.get(player);
        Faction faction = uplayer.getFaction();
        String factionname = uplayer.getFactionName();
        if(factionname != "") {
            if(e.getMessage().startsWith("@")) {
                e.setCancelled(true);
                for(Player p : faction.getOnlinePlayers() ) {
                    if(p != player) {
                        p.sendMessage("§2§lFaction §f" + player.getName() + " §a" + e.getMessage().replace("@", ""));
                    }
                }
                player.sendMessage("§2§lFaction §f" + player.getName() + " §a" + e.getMessage().replace("@", ""));
            }
        }
    }
}
