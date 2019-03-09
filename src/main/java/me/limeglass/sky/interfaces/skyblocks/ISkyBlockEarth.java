package me.limeglass.sky.interfaces.skyblocks;

import java.util.HashSet;
import java.util.Set;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import me.goodandevil.skyblock.SkyBlock;
import me.goodandevil.skyblock.api.SkyBlockAPI;
import me.goodandevil.skyblock.island.Island;
import me.limeglass.sky.interfaces.challenges.SkyblockChallenge;
import me.limeglass.sky.interfaces.islands.ISkyBlockEarthIsland;
import me.limeglass.sky.interfaces.islands.SkyblockIsland;

public class ISkyBlockEarth implements Skyblock {
	
	private SkyBlock instance;
	
	public ISkyBlockEarth(SkyBlock instance) {
		this.instance = instance;
	}
	
	@Override
	public Set<SkyblockIsland> getTrustedOn(OfflinePlayer player) {
		Set<SkyblockIsland> islands = new HashSet<>();
		for (Island island : instance.getIslandManager().getIslands().values()) {
			if (island.getCoopPlayers().contains(player.getUniqueId())) {
				islands.add(new ISkyBlockEarthIsland(new me.goodandevil.skyblock.api.island.Island(island, player)));
			}
		}
		return islands;
	}
	
	@Override
	public Set<SkyblockChallenge> getChallenges(Player player) {
		throw new UnsupportedOperationException("SkyBlockEarth does not have challenges.");
	}
	
	@Override
	public SkyblockIsland getIslandAt(Location location) {
		return new ISkyBlockEarthIsland(SkyBlockAPI.getIslandManager().getIslandAtLocation(location));
	}
	
	@Override
	public SkyblockIsland getIslandOf(OfflinePlayer player) {
		return new ISkyBlockEarthIsland(SkyBlockAPI.getIslandManager().getIsland(player.getPlayer()));
	}
	
	@Override
	public Location getHomeLocation(OfflinePlayer player) {
		throw new UnsupportedOperationException("SkyBlockEarth does not support homes.");
	}
	
	@Override
	public SkyblockPlugin getPluginType() {
		return SkyblockPlugin.SKYBLOCKEARTH;
	}

	@Override
	public SkyBlock getInstance() {
		return instance;
	}
	
}
