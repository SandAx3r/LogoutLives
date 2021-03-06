package lives.logout.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import lives.logout.LogoutLives_MAIN;
import lives.logout.LogoutVillager;

public class EntityKill implements Listener {

	LogoutLives_MAIN logoutL = LogoutLives_MAIN.get();

	// Entity death
	@EventHandler
	public void onEntityDeath(EntityDeathEvent e) {
		Villager ent;
		if (e.getEntity() instanceof Villager) {
			ent = (Villager) e.getEntity();
		} else {
			return;
		}

		for (LogoutVillager lv : LogoutLives_MAIN.villagersL) {
			if (lv.villagerUUID.equals(ent.getUniqueId())) {
				System.out.println("[LogoutLives] An entity called: " + ent.getCustomName() + " died while OFFLINE");
				lv.setDead(true);
				lv.setVillagerLocation(ent.getLocation());

				// THUNDERSTRIke
				if (logoutL.getConfig().getBoolean("lightning")) {
					if (logoutL.getConfig().getString("lightningDamage").equals("DAMAGE")) {
						ent.getWorld().strikeLightning(ent.getLocation()); // DAMAGE
					} else {
						ent.getWorld().strikeLightningEffect(ent.getLocation()); // NO DAMAGE
					}
				}

				// MESSAGE + SOUND + TITLE
				logoutL.getServer().broadcastMessage(ChatColor.GOLD + lv.getPlayerName() + ChatColor.WHITE
						+ " died while " + ChatColor.LIGHT_PURPLE + "offline");
				
				if (logoutL.getConfig().getBoolean("enableTitle")) {
					for (Player p : logoutL.getServer().getOnlinePlayers()) {
						if (logoutL.getConfig().getBoolean("sound")) {
							p.playSound(p.getLocation(), Sound.ENTITY_GHAST_HURT, 1, 1);
						}
						String title = logoutL.getConfig().getString("mainTitle");
						p.sendTitle(ChatColor.LIGHT_PURPLE + title, lv.getPlayerName(), 10, 70, 20); // Los
																												// n?meros
																												// son
																												// los
																												// estandar

					}
				}
			}
		}

	}
}
