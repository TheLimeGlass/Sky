package me.limeglass.sky;

import java.io.IOException;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.wasteofplastic.askyblock.ASkyBlockAPI;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import me.goodandevil.skyblock.api.SkyBlockAPI;
import me.limeglass.sky.interfaces.skyblocks.IASkyBlock;
import me.limeglass.sky.interfaces.skyblocks.ISkyBlockEarth;
import me.limeglass.sky.interfaces.skyblocks.IuSkyBlock;
import me.limeglass.sky.interfaces.skyblocks.Skyblock;
import us.talabrek.ultimateskyblock.api.uSkyBlockAPI;

public class Sky extends JavaPlugin {
	
	private String packageName = "me.limeglass.sky";
	private static Skyblock skyblock;
	private static Sky instance;
	private SkriptAddon addon;
	
	public void onEnable(){
		instance = this;
		Plugin plugin = Bukkit.getPluginManager().getPlugin("uSkyBlock");
		if (plugin != null && plugin.isEnabled()) {
			skyblock = new IuSkyBlock((uSkyBlockAPI) plugin);
		} else {
			plugin = Bukkit.getPluginManager().getPlugin("ASkyBlock");
			if (plugin != null && plugin.isEnabled()) {
				skyblock = new IASkyBlock(ASkyBlockAPI.getInstance());
			} else {
				plugin = Bukkit.getPluginManager().getPlugin("FabledSkyBlock");
				boolean check = false;
				if (plugin != null && plugin.isEnabled()) {
					check = true;
				} else {
					plugin = Bukkit.getPluginManager().getPlugin("SkyBlock");
					if (plugin != null && plugin.isEnabled()) {
						check = true;
					}
				}
				if (check) {
					List<String> authors = plugin.getDescription().getAuthors();
					if (authors.contains("GoodAndEvil") || authors.contains("Songoda")) {
						skyblock = new ISkyBlockEarth(SkyBlockAPI.getImplementation());
					}
				}
			}
		}
		if (skyblock == null) {
			getLogger().info("No Skyblock plugin was found, disabling...");
			setEnabled(false);
			return;
		}
		try {
			addon = Skript.registerAddon(this).loadClasses(packageName, "elements");
		} catch (IOException e) {
			Skript.exception(e, "Could not load Sky's syntax elements");
		}
		getLogger().info("has been enabled!");
	}
	
	public static Skyblock getSkyblock() {
		return skyblock;
	}
	
	public SkriptAddon getAddonInstance() {
		return addon;
	}
	
	public static Sky getInstance() {
		return instance;
	}
	
	public String getPackageName() {
		return packageName;
	}

}
