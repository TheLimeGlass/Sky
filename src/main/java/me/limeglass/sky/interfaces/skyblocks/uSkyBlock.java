package me.limeglass.sky.interfaces.skyblocks;

import java.util.Set;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.google.common.collect.Sets;

import me.limeglass.sky.interfaces.challenges.uSkyBlockChallenge;
import me.limeglass.sky.interfaces.challenges.SkyblockChallenge;
import me.limeglass.sky.interfaces.islands.uSkyBlockIsland;
import me.limeglass.sky.interfaces.islands.SkyblockIsland;
import us.talabrek.ultimateskyblock.api.uSkyBlockAPI;

public class uSkyBlock implements Skyblock {
	
	private uSkyBlockAPI instance;
	
	public uSkyBlock(uSkyBlockAPI instance) {
		this.instance = instance;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public Set<SkyblockIsland> getTrustedOn(OfflinePlayer player) {
		return instance.getPlayerInfo(player.getPlayer()).getTrustedOn().parallelStream()
				.map(username -> Bukkit.getOfflinePlayer(username))
				.filter(p -> p != null)
				.map(p -> getIslandOf(p))
				.collect(Collectors.toSet());
	}
	
	@Override
	public Set<SkyblockChallenge> getChallenges(Player player) {
		return instance.getPlayerInfo(player).getChallenges().parallelStream()
				.map(challenge -> new uSkyBlockChallenge(challenge, player))
				.collect(Collectors.toSet());
	}
	
	@Override
	public SkyblockIsland getIslandAt(Location location) {
		return new uSkyBlockIsland(instance.getIslandInfo(location));
	}
	
	@Override
	public SkyblockIsland getIslandOf(OfflinePlayer player) {
		return new uSkyBlockIsland(instance.getIslandInfo(player.getPlayer()));
	}
	
	@Override
	public Set<Location> getHomeLocations(OfflinePlayer player) {
		return Sets.newHashSet(instance.getPlayerInfo(player.getPlayer()).getHomeLocation());
	}
	
	@Override
	public SkyblockPlugin getPluginType() {
		return SkyblockPlugin.USKYBLOCK;
	}

	@Override
	public uSkyBlockAPI getInstance() {
		return instance;
	}
	
}
