package me.limeglass.sky;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.wasteofplastic.askyblock.ASkyBlockAPI;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import me.limeglass.sky.interfaces.skyblocks.IASkyBlock;
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
			}
		}
		try {
			addon = Skript.registerAddon(this).loadClasses(packageName, "elements");
		} catch (IOException e) {
			e.printStackTrace();
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
