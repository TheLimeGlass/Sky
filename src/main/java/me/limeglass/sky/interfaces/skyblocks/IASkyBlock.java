package me.limeglass.sky.interfaces.skyblocks;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.wasteofplastic.askyblock.ASkyBlockAPI;

import me.limeglass.sky.interfaces.challenges.IASkyBlockChallenge;
import me.limeglass.sky.interfaces.challenges.SkyblockChallenge;
import me.limeglass.sky.interfaces.islands.IASkyBlockIsland;
import me.limeglass.sky.interfaces.islands.SkyblockIsland;

public class IASkyBlock implements Skyblock {
	
	private ASkyBlockAPI instance;
	
	public IASkyBlock(ASkyBlockAPI instance) {
		this.instance = instance;
	}
	
	@Override
	public Set<SkyblockChallenge> getChallenges(Player player) {
		UUID uuid = player.getUniqueId();
		Map<String, Integer> completions = instance.getChallengeTimes(uuid);
		Map<String, Boolean> status = instance.getChallengeStatus(uuid);
		Set<SkyblockChallenge> challenges = new HashSet<>();
		for (String name : status.keySet()) {
			int times = 0;
			if (completions.containsKey(name))
				times = completions.get(name);
			challenges.add(new IASkyBlockChallenge(name, times, uuid));
		}
		return challenges;
	}
	
	@Override
	public SkyblockIsland getIslandAt(Location location) {
		return new IASkyBlockIsland(instance.getIslandAt(location));
	}
	
	@Override
	public SkyblockIsland getIslandOf(OfflinePlayer player) {
		return new IASkyBlockIsland(instance.getIslandOwnedBy(player.getUniqueId()));
	}
	
	@Override
	public SkyblockPlugin getPluginType() {
		return SkyblockPlugin.ASKYBLOCK;
	}

	@Override
	public ASkyBlockAPI getInstance() {
		return instance;
	}
	
	@Override
	public boolean isASkyBlock() {
		return true;
	}
	
}
