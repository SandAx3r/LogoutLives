package lives.logout;

import java.io.Serializable;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class LogoutVillager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String playerName;
	public UUID villagerUUID;
	public Boolean dead;
	public double x;
	public double y;
	public double z;

	public LogoutVillager(String playerName, Boolean dead) {
		this.playerName = playerName;
		this.dead = dead;
	}

	// Other methods

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public UUID getVillagerUUID() {
		return this.villagerUUID;
	}

	public double getVillagerX() {
		return this.x;
	}

	public double getVillagerY() {
		return this.y;
	}

	public double getVillagerZ() {
		return this.z;
	}

	public void setVillagerLocation(Location loc) {
		this.x = loc.getX();
		this.y = loc.getY();
		this.z = loc.getZ();
	}

	public Boolean isDead() {
		return dead;
	}

	public void setDead(Boolean dead) {
		this.dead = dead;
	}

	@Override
	public String toString() {
		return "LogoutVillager [playerName=" + playerName + ", dead=" + dead + "]";
	}

	public Entity create(Location loc) {
		/**
		 * 
		 * VILLAGER CREATION
		 * 
		 **/
		Entity villager = (Entity) Bukkit.getWorld("world").spawnEntity(loc, EntityType.VILLAGER);
		villager.setCustomName(this.playerName);
		villager.setCustomNameVisible(true);
		villager.setPersistent(true);

		this.villagerUUID = villager.getUniqueId();
		this.x = villager.getLocation().getX();
		this.y = villager.getLocation().getY();
		this.z = villager.getLocation().getZ();

		return villager;
	}

}
