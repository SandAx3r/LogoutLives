package lives.logout;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import lives.logout.extras.SaveFilesLL;
import lives.logout.extras.TickChecker;
import lives.logout.listeners.EntityKill;
import lives.logout.listeners.PlayerJoinServer;
import lives.logout.listeners.PlayerQuitServer;

public class LogoutLives_MAIN extends JavaPlugin {

	public static List<LogoutVillager> villagersL = new ArrayList<LogoutVillager>();
	public static File villagers_f;
	public static LogoutLives_MAIN logoutL;
	public FileConfiguration config;
	
	// Fired when plugin is first enabled
	@Override
	public void onEnable() {
		logoutL = this;
		
		// Configurations
		this.saveDefaultConfig();
		config = this.getConfig();
		createConfig(config);
		config.options().copyDefaults(true);
		saveConfig();
		
		// Implement listeners
		PluginManager pm = getServer().getPluginManager();
		
		pm.registerEvents(new PlayerQuitServer(), this);
		pm.registerEvents(new PlayerJoinServer(), this);
		pm.registerEvents(new EntityKill(), this);

		// Create main folder
		createFolder();
		
		// Create save file for offlinePlayer
		SaveFilesLL.readLogoutVillagers("plugins/LogoutLives/offlinePlayers.data");
		
		TickChecker.scheduleTimer(this, this.getServer().getWorld("world"));

		// Finish
		System.out.println("[LogoutLives] Initialized !");
	}

	// Fired when plugin is disabled
	@Override
	public void onDisable() {
		// Save files
		SaveFilesLL.saveLogoutVillagers(villagersL, "plugins/LogoutLives/offlinePlayers.data");
	}
	
	public void createFolder() {
		// Creates plugin main folder
		File directory = new File("plugins/LogoutLives");
		if (!directory.exists()) {
			if (directory.mkdir()) {
				System.out.println("[LogoutLives] Directory is created");
			} else {
				System.out.println("[LogoutLives] Directory not created");
			}
		}
	}
	
	public void createConfig(FileConfiguration config) {
		
		config.addDefault("enableTitle", true);
		config.addDefault("mainTitle", "F (offline)");
		config.addDefault("lightningDamage", "NO DAMAGE");
		config.addDefault("lightning", true);
		config.addDefault("sound", true);
	}
	
	public static LogoutLives_MAIN get() {
		return logoutL;
	}

}
