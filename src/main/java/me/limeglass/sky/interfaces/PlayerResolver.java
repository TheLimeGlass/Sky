package me.limeglass.sky.interfaces;

import java.util.HashSet;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public interface PlayerResolver {
	
	@SuppressWarnings("deprecation")
	public default Set<Player> resolveSet(Object[] objects) {
		Set<Player> players = new HashSet<>();
		for (Object object : objects) {
			if (object instanceof Player)
				players.add((Player) object);
			else if (object instanceof String) {
				OfflinePlayer player = Bukkit.getOfflinePlayer((String) object);
				if (player != null)
					players.add(player.getPlayer());
			}
		}
		return players;
	}
	
	@SuppressWarnings("deprecation")
	public default Player[] resolve(Object[] objects) {
		Player[] players = new Player[objects.length];
		for (int i = 0; i< objects.length; i++) {
			if (objects[i] instanceof Player)
				players[i] = (Player) objects[i];
			else if (objects[i] instanceof String) {
				OfflinePlayer player = Bukkit.getOfflinePlayer((String) objects[i]);
				if (player != null)
					players[i] = player.getPlayer();
			}
		}
		return players;
	}
	
	public default Player[] resolveOnline(Object[] objects) {
		Player[] players = new Player[objects.length];
		for (int i = 0; i< objects.length; i++) {
			if (objects[i] instanceof Player)
				players[i] = (Player) objects[i];
			else if (objects[i] instanceof String) {
				Player player = Bukkit.getPlayer((String) objects[i]);
				if (player != null)
					players[i] = player;
			}
		}
		return players;
	}
	
	@SuppressWarnings("deprecation")
	public default OfflinePlayer[] resolveOffline(Object[] objects) {
		OfflinePlayer[] players = new Player[objects.length];
		for (int i = 0; i< objects.length; i++) {
			if (objects[i] instanceof Player)
				players[i] = (Player) objects[i];
			else if (objects[i] instanceof String) {
				OfflinePlayer player = Bukkit.getOfflinePlayer((String) objects[i]);
				if (player != null)
					players[i] = player;
			}
		}
		return players;
	}
	
}
