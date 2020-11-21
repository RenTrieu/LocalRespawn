package v1c4t.localRespawn;

import java.util.Random;

import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.Material;

public class Main 
    extends JavaPlugin 
    implements Listener {
    
    private FileConfiguration config;
    
    public void onEnable() {
        // Registering Main class as a listener
        getServer().getPluginManager().registerEvents(this,  this);
        
        // Loading configuration file if exists, if not generates default
        saveDefaultConfig();
        this.config = getConfig();
    }
    
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Location deathLoc = event.getPlayer().getLocation();
        Location respawnLoc = deathLoc;

        // Choosing a random coordinate within respawnRadius blocks
        // of deathLoc
        // If the chosen coordinates are not safe (e.g. there is lava)
        // rechooses the coordinates
        Boolean safe = false;
        int respawnRetries = config.getInt("RespawnRetries");
        while (!safe && (respawnRetries > 0)) {
            
            // Choosing random coordinate
            int respawnRadius = config.getInt("DeathRespawnRadius");
            Random rand = new Random();
            int xOffset = rand.nextInt(2*respawnRadius)-respawnRadius;
            int zOffset = rand.nextInt(2*respawnRadius)-respawnRadius;
            int newX = (int) Math.floor(respawnLoc.getX() + xOffset);
            int newZ = (int) Math.floor(respawnLoc.getZ() + zOffset);

            // Choosing ground-level y coordinate
            int newY = event.getPlayer().getWorld()
                       .getHighestBlockAt(newX, newZ).getY();
            
            // Safety check
            Material spawnBlock = event.getPlayer().getWorld()
                               .getHighestBlockAt(newX, newZ).getType();
            if ((spawnBlock == Material.LAVA)
                || (spawnBlock == Material.WATER)) {
                respawnRetries -= 1;
                safe = false;
                // Defaults to world spawn if safe random coordinates
                // cannot be found within the alloted amount of retries
                if (respawnRetries == 0) {
                    respawnLoc = event.getPlayer().getWorld()
                                 .getSpawnLocation();
                    event.getPlayer().sendMessage(ChatColor.RED
                            + "Could not find safe respawn location. "
                            + "Defaulting to world spawn.");
                }
            }
            else {
                safe = true;
                respawnLoc.setX(newX);
                respawnLoc.setZ(newZ);
                respawnLoc.setY(newY);
            }
        }

        event.setRespawnLocation(respawnLoc);
    }
}
