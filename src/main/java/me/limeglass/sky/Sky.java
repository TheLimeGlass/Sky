package me.limeglass.sky;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.wasteofplastic.askyblock.ASkyBlockAPI;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import me.limeglass.sky.interfaces.skyblocks.ASkyBlock;
import me.limeglass.sky.interfaces.skyblocks.BentoBoxPlugin;
import me.limeglass.sky.interfaces.skyblocks.uSkyBlock;
import me.limeglass.sky.interfaces.skyblocks.Skyblock;
import us.talabrek.ultimateskyblock.api.uSkyBlockAPI;
import world.bentobox.bentobox.BentoBox;

public class Sky extends JavaPlugin {

	private static Skyblock skyblock;
	private static Sky instance;
	private SkriptAddon addon;

	public void onEnable() {
		instance = this;
		Plugin plugin = Bukkit.getPluginManager().getPlugin("uSkyBlock");
		if (plugin != null && plugin.isEnabled()) {
			skyblock = new uSkyBlock((uSkyBlockAPI) plugin);
		} else {
			plugin = Bukkit.getPluginManager().getPlugin("ASkyBlock");
			if (plugin != null && plugin.isEnabled()) {
				skyblock = new ASkyBlock(ASkyBlockAPI.getInstance());
			} else {
				plugin = Bukkit.getPluginManager().getPlugin("BentoBox");
				if (plugin != null && plugin.isEnabled()) {
					skyblock = new BentoBoxPlugin(BentoBox.getInstance());
				}
			}
		}
		if (skyblock == null) {
			getLogger().info("No Skyblock plugin was found, disabling...");
			setEnabled(false);
			return;
		}
		try {
			addon = Skript.registerAddon(this).loadClasses("me.limeglass.sky", "elements");
		} catch (IOException e) {
			Skript.exception(e, "Could not load Sky's syntax elements");
		}
		getLogger().info("has been enabled!");
	}

	public SkriptAddon getAddonInstance() {
		return addon;
	}

	public static Skyblock getSkyblock() {
		return skyblock;
	}

	public static Sky getInstance() {
		return instance;
	}

}
