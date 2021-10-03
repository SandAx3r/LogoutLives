package lives.logout.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import lives.logout.LogoutLives_MAIN;
import lives.logout.LogoutVillager;

public class PlayerJoinServer implements Listener{

	LogoutLives_MAIN logoutL = LogoutLives_MAIN.get();
	
	// Player Login
	@EventHandler
	public void onPlayerLogin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		Entity v;
		p.getWorld().loadChunk(p.getLocation().getChunk());

		// Find the villager in list
		for (LogoutVillager lv : LogoutLives_MAIN.villagersL) {

			if (p.getDisplayName().equals(lv.getPlayerName())) {
				v = (Entity) logoutL.getServer().getEntity(lv.getVillagerUUID());
				
				logoutL.getServer().getEntity(lv.getVillagerUUID());
				Location loc = new Location(p.getWorld(), lv.getVillagerX(), lv.getVillagerY(), lv.getVillagerZ());
				if (lv.isDead()) {
					p.teleport(loc);
					System.out.println("[LogoutLives] " + p.getDisplayName() + " died offline, now online");
					//p.setHealth(0);
					LogoutLives_MAIN.villagersL.remove(lv);
					return;
				}
				
				// Remove villager from saveTXT
				LogoutLives_MAIN.villagersL.remove(lv);
				
				// First remove to prevent errors
				v.remove();
				p.teleport(v);
				System.out.println("[LogoutLives] Entity villager found, name= " + lv.getPlayerName());
				

				System.out.println("[LogoutLives] LogoutEntity removed: " + lv.getPlayerName());

				return;
			}
		}

		System.out.println("[LogoutLives] There are not living entities called " + p.getDisplayName());
	}	
	
}
