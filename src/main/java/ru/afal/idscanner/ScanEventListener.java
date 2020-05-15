package ru.afal.idscanner;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

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

  @EventHandler
  public void onPlayerInteractEntityEvent(PlayerInteractAtEntityEvent event) {
    Player player = event.getPlayer();
    boolean isPlayerClicked = event.getRightClicked() instanceof Player;
    boolean isIdScannerUsed = player.getInventory().getItemInMainHand() == IDScanner.INSTANCE;
    if (isPlayerClicked && isIdScannerUsed) {
      Player targetPlayer = (Player) event.getRightClicked();

      String message = targetPlayer.hasPermission("id.scanner.immune")
        ? ChatColor.YELLOW + "The Revealer looks puzzled... It whispers to you that this one appears as " + getRandomShit()
        : ChatColor.YELLOW + "The Revealer whispers to you that this is " + ChatColor.DARK_AQUA + targetPlayer.getDisplayName();
      player.sendMessage(message);
    }
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
