package lives.logout.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import lives.logout.LogoutLives_MAIN;
import lives.logout.LogoutVillager;

public class PlayerQuitServer implements Listener {

	LogoutLives_MAIN logoutL = LogoutLives_MAIN.get();

	// Player Logout
	@EventHandler
	public void onPlayerLogout(PlayerQuitEvent e) {
		Player p = e.getPlayer();

		/**
		 * 
		 * VILLAGER CREATION
		 * 
		 **/
		if (p.getGameMode() != GameMode.SPECTATOR) {
			LogoutVillager lv = new LogoutVillager(p.getDisplayName(), false);
			lv.create(p.getLocation());

			// Save villager
			LogoutLives_MAIN.villagersL.add(lv);
		}
		
	}

}
