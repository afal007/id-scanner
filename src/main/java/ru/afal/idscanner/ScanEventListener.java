package ru.afal.idscanner;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ScanEventListener implements Listener {

  private static final Random RANDOM = new Random(System.currentTimeMillis());
  private static final String[] RANDOM_SHIT_ARR = new String[]{
    "NaM",
    "Satan",
    "Cum",
    "!@#$^&*()",
    "Your",
    "Dad",
    "POOP",
    "Lole",
    "Omega",
    "*****"
  };
  private static final AtomicInteger ATOMIC_INT = new AtomicInteger();

//  @EventHandler
//  public void onPlayerInteractEvent(PlayerInteractEvent event) {
//    ItemMeta mainHandMeta = event.getPlayer().getInventory().getItemInMainHand().getItemMeta();
//    if (event.hasBlock() && mainHandMeta != null && mainHandMeta.equals(IDScanner.INSTANCE.getItemMeta())) {
//      event.setCancelled(true);
//    }
//  }

  @EventHandler
  public void onBlockPlaceEvent(BlockPlaceEvent event) {
    ItemMeta mainHandMeta = event.getPlayer().getInventory().getItemInMainHand().getItemMeta();
    if (mainHandMeta != null && mainHandMeta.equals(IDScanner.INSTANCE.getItemMeta())) {
      event.setCancelled(true);
    }
  }

  @EventHandler
  public void onPlayerInteractEntityEvent(PlayerInteractAtEntityEvent event) {
    Player player = event.getPlayer();
    ItemMeta mainHandMeta = player.getInventory().getItemInMainHand().getItemMeta();
    boolean isPlayerClicked = event.getRightClicked() instanceof Player;
    boolean isIdScannerUsed = mainHandMeta != null && mainHandMeta.equals(IDScanner.INSTANCE.getItemMeta());
    if (isPlayerClicked && isIdScannerUsed) {
      Player targetPlayer = (Player) event.getRightClicked();

      String message = targetPlayer.hasPermission("id.scanner.immune")
        ? ChatColor.YELLOW + "The Revealer looks puzzled... It whispers to you that this one appears as " + getRandomShit()
        : ChatColor.YELLOW + "The Revealer whispers to you that this is " + getPlayerName(targetPlayer);
      player.sendMessage(message);
    }
  }

  @NotNull
  private String getPlayerName(Player targetPlayer) {
    Optional<Team> oTeam = Optional.ofNullable(Bukkit.getServer().getScoreboardManager())
      .map(ScoreboardManager::getMainScoreboard)
      .map(Scoreboard::getTeams)
      .orElse(Collections.emptySet())
      .stream()
      .filter(t -> t.hasEntry(targetPlayer.getName()))
      .findFirst();

    ChatColor chatColor = oTeam.map(Team::getColor).orElse(ChatColor.DARK_GRAY);
    return oTeam.map(Team::getDisplayName)
      .map(teamName -> chatColor + teamName + " " + targetPlayer.getDisplayName())
      .orElseGet(() -> chatColor + targetPlayer.getDisplayName());
  }

  private String getRandomShit() {
    String name;
    if (ATOMIC_INT.getAndIncrement() % 10 == 0) {
      name = "Your Mom";
    } else {
      int length = RANDOM_SHIT_ARR.length;
      name = RANDOM_SHIT_ARR[RANDOM.nextInt(length)] + " " + RANDOM_SHIT_ARR[RANDOM.nextInt(length)];
    }

    return ChatColor.MAGIC + name;
  }
}
