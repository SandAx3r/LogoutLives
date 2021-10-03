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

				// THUNDERSTRIKE
				// w.strikeLightning(death.getLocation()); // DAMAGE
				ent.getWorld().strikeLightningEffect(ent.getLocation()); // NO DAMAGE

				// SOUND + TITLE
				logoutL.getServer().broadcastMessage(ChatColor.GOLD + lv.getPlayerName() + ChatColor.WHITE + "died while " + ChatColor.LIGHT_PURPLE + "offline");
				for (Player p : logoutL.getServer().getOnlinePlayers()) {	
					p.playSound(p.getLocation(), Sound.ENTITY_GHAST_HURT, 1, 1);
					p.sendTitle(ChatColor.LIGHT_PURPLE + "F (offline)", lv.getPlayerName(), 10, 70, 20); // Los números
																											// son los
																											// estandar
				}
			}
		}

	}
}
