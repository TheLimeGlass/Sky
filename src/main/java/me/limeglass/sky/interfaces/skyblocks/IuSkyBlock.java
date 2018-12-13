package me.limeglass.sky.interfaces.skyblocks;

import java.util.Set;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import me.limeglass.sky.interfaces.challenges.IuSkyBlockChallenge;
import me.limeglass.sky.interfaces.challenges.SkyblockChallenge;
import me.limeglass.sky.interfaces.islands.IuSkyBlockIsland;
import me.limeglass.sky.interfaces.islands.SkyblockIsland;
import us.talabrek.ultimateskyblock.api.uSkyBlockAPI;

public class IuSkyBlock implements Skyblock {
	
	private uSkyBlockAPI instance;
	
	public IuSkyBlock(uSkyBlockAPI instance) {
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
				.map(challenge -> new IuSkyBlockChallenge(challenge, player))
				.collect(Collectors.toSet());
	}
	
	@Override
	public SkyblockIsland getIslandAt(Location location) {
		return new IuSkyBlockIsland(instance.getIslandInfo(location));
	}
	
	@Override
	public SkyblockIsland getIslandOf(OfflinePlayer player) {
		return new IuSkyBlockIsland(instance.getIslandInfo(player.getPlayer()));
	}
	
	@Override
	public Location getHomeLocation(OfflinePlayer player) {
		return instance.getPlayerInfo(player.getPlayer()).getHomeLocation();
	}
	
	@Override
	public SkyblockPlugin getPluginType() {
		return SkyblockPlugin.USKYBLOCK;
	}

	@Override
	public uSkyBlockAPI getInstance() {
		return instance;
	}
	
	@Override
	public boolean isASkyBlock() {
		return false;
	}
	
}
