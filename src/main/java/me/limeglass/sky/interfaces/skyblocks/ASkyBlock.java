package me.limeglass.sky.interfaces.skyblocks;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.Player;

import com.google.common.collect.Sets;
import com.wasteofplastic.askyblock.ASkyBlockAPI;

import me.limeglass.sky.interfaces.challenges.ASkyBlockChallenge;
import me.limeglass.sky.interfaces.challenges.SkyblockChallenge;
import me.limeglass.sky.interfaces.islands.ASkyBlockIsland;
import me.limeglass.sky.interfaces.islands.SkyblockIsland;

public class ASkyBlock implements Skyblock {

	private ASkyBlockAPI instance;

	public ASkyBlock(ASkyBlockAPI instance) {
		this.instance = instance;
	}

	@Override
	public Set<SkyblockIsland> getTrustedOn(OfflinePlayer player) {
		return instance.getCoopIslands(player.getPlayer()).parallelStream()
				.map(location -> getIslandAt(location))
				.collect(Collectors.toSet());
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
			challenges.add(new ASkyBlockChallenge(name, times, uuid));
		}
		return challenges;
	}

	@Override
	public Collection<SkyblockIsland> getIslandsOf(World world) {
		return instance.getOwnedIslands().values().stream()
				.map(island -> new ASkyBlockIsland(island))
				.collect(Collectors.toList());
	}

	@Override
	public SkyblockIsland getIslandAt(Location location) {
		return new ASkyBlockIsland(instance.getIslandAt(location));
	}

	@Override
	public SkyblockIsland getIslandOf(OfflinePlayer player) {
		return new ASkyBlockIsland(instance.getIslandOwnedBy(player.getUniqueId()));
	}

	@Override
	public SkyblockIsland getIslandOf(World world, OfflinePlayer player) {
		return getIslandOf(player);
	}

	@Override
	public Set<Location> getHomeLocations(OfflinePlayer player) {
		return Sets.newHashSet(instance.getHomeLocation(player.getUniqueId()));
	}

	@Override
	public SkyblockPlugin getPluginType() {
		return SkyblockPlugin.ASKYBLOCK;
	}

	@Override
	public ASkyBlockAPI getInstance() {
		return instance;
	}

}
