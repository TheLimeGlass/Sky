package me.limeglass.sky.interfaces.skyblocks;

import java.util.Set;
import java.util.stream.Collectors;

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
