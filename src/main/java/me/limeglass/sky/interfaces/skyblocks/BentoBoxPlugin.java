package me.limeglass.sky.interfaces.skyblocks;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.eclipse.jdt.annotation.Nullable;

import me.limeglass.sky.interfaces.challenges.BentoBoxChallenge;
import me.limeglass.sky.interfaces.challenges.SkyblockChallenge;
import me.limeglass.sky.interfaces.islands.BentoBoxIsland;
import me.limeglass.sky.interfaces.islands.SkyblockIsland;
import world.bentobox.bentobox.BentoBox;
import world.bentobox.bentobox.api.addons.Addon;
import world.bentobox.bentobox.database.objects.Island;
import world.bentobox.bentobox.managers.RanksManager;
import world.bentobox.challenges.ChallengesAddon;

public class BentoBoxPlugin implements Skyblock {
	
	private BentoBox instance;
	
	public BentoBoxPlugin(BentoBox instance) {
		this.instance = instance;
	}
	
	@Override
	public Collection<SkyblockIsland> getTrustedOn(OfflinePlayer player) {
		return instance.getIslandsManager().getIslands().parallelStream()
				.filter(island -> island.getMemberSet(RanksManager.TRUSTED_RANK).contains(player.getUniqueId()))
				.map(island -> new BentoBoxIsland(island))
				.collect(Collectors.toSet());
	}

	@Override
	public Set<SkyblockChallenge> getChallenges(Player player) {
		Optional<Addon> addon = instance.getAddonsManager().getAddonByName("Challenges");
		if (!addon.isPresent())
			return null;
		ChallengesAddon challenges = (ChallengesAddon) addon.get();
		return Bukkit.getWorlds().stream()
				.flatMap(world -> challenges.getChallengesManager().getAllChallengesNames(world).stream())
				.map(challenge -> {
					try {
						return new BentoBoxChallenge(challenge, player.getUniqueId());
					} catch (IllegalAccessException e) {
						return null;
					}
				})
				.collect(Collectors.toSet());
	}

	@Override
	public Collection<SkyblockIsland> getIslandsOf(World world) {
		return instance.getIslandsManager().getIslands(world).stream()
				.map(island -> new BentoBoxIsland(island))
				.collect(Collectors.toList());
	}

	@Override
	public BentoBoxIsland getIslandAt(Location location) {
		Optional<Island> island = instance.getIslandsManager().getIslandAt(location);
		if (!island.isPresent())
			return null;
		return new BentoBoxIsland(island.get());
	}

	@Override
	public BentoBoxIsland getIslandOf(OfflinePlayer player) {
		Collection<Island> islands = instance.getIslandsManager().getIslands();
		Optional<Island> island = islands.parallelStream()
				.filter(i -> i.getOwner().equals(player.getUniqueId()))
				.findFirst();
		if (!island.isPresent())
			return null;
		return new BentoBoxIsland(island.get());
	}

	@Override
	public BentoBoxIsland getIslandOf(@Nullable World world, OfflinePlayer player) {
		if (world == null)
			return getIslandOf(player);
		Island island = instance.getIslandsManager().getIsland(world, player.getUniqueId());
		if (island == null)
			return null;
		return new BentoBoxIsland(island);
	}

	@Override
	public Collection<Location> getHomeLocations(OfflinePlayer player) {
		return instance.getIslandsManager().getIslands().parallelStream()
				.filter(island -> island.getMemberSet().contains(player.getUniqueId()))
				.flatMap(island -> island.getHomes().values().stream())
				.collect(Collectors.toSet());
	}

	@Override
	public SkyblockPlugin getPluginType() {
		return SkyblockPlugin.BENTOBOX;
	}

	@Override
	public BentoBox getInstance() {
		return instance;
	}

}
