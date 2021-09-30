package lives.logout.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
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
		Villager v;

		// Find the villager in list
		for (LogoutVillager lv : LogoutLives_MAIN.villagersL) {
			v = (Villager) logoutL.getServer().getEntity(lv.villagerUUID);
			if (p.getDisplayName().equals(lv.getPlayerName())) {
				Location loc = new Location(p.getWorld(), lv.getVillagerX(), lv.getVillagerY(), lv.getVillagerZ());
				if (lv.isDead()) {
					p.teleport(loc);
					System.out.println("[LogoutLives] " + p.getDisplayName() + " died offline, now online");
					p.setHealth(0);
					LogoutLives_MAIN.villagersL.remove(lv);
					return;
				}

				p.teleport(v);
				System.out.println("[LogoutLives] Entity villager found, name= " + lv.getPlayerName());

				// Remove villager from saveTXT
				LogoutLives_MAIN.villagersL.remove(lv);

				v.remove();
				System.out.println("[LogoutLives] LogoutEntity removed: " + lv.getPlayerName());

				return;
			}
		}

		System.out.println("[LogoutLives] There are not living entities called " + p.getDisplayName());
	}	
	
}
