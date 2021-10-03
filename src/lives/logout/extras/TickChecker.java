package lives.logout.extras;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Villager;
import org.bukkit.plugin.Plugin;

import lives.logout.LogoutLives_MAIN;
import lives.logout.LogoutVillager;

public class TickChecker {
	
	public static void scheduleTimer(Plugin plugin, final World world) {
		plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			public void run() {
				for (LogoutVillager logoutL : LogoutLives_MAIN.villagersL) {
					if (!logoutL.isDead()) { // First check if is dead
						Villager v = (Villager) plugin.getServer().getEntity(logoutL.getVillagerUUID());
						if ((v == null)) {
							Location loc = new Location(world, logoutL.getVillagerX(), logoutL.getVillagerY(), logoutL.getVillagerZ());
							loc.getChunk().load();
							System.out.println("[LogoutLives] " + logoutL.getPlayerName() + ": Loading chunk...");
							
						} else {
							logoutL.setVillagerLocation(v.getLocation());
							//System.out.println(logoutL.getPlayerName() + ": Chunk loaded.");
						}
					}
				}
			}
		}, 1, 1);
	}
}
