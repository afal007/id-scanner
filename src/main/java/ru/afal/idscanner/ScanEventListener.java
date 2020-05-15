package ru.afal.idscanner;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import java.util.Random;

public class ScanEventListener implements Listener {

  private static final Random RANDOM = new Random(System.currentTimeMillis());
  private static final String[] RANDOM_SHIT_ARR = new String[]{
    "NaM",
    "Satan",
    "Lord",
    "$$##@@@#$@$@#$@",
    "Chuck",
    "Norris",
    "POOP",
    "Lole",
    "Omega",
    "*****"
  };

  @EventHandler
  public void onPlayerInteractEntityEvent(PlayerInteractAtEntityEvent event) {
    Player player = event.getPlayer();
    boolean isPlayerClicked = event.getRightClicked() instanceof Player;
    boolean isIdScannerUsed = player.getInventory().getItemInMainHand() == IDScanner.INSTANCE;
    if (isPlayerClicked && isIdScannerUsed) {
      Player targetPlayer = (Player) event.getRightClicked();

      String message = targetPlayer.hasPermission("id.scanner.immune")
        ? ChatColor.MAGIC + "It appears as though this is " + getRandomShit()
        : ChatColor.DARK_AQUA + "It appears as though this is " + targetPlayer.getDisplayName();
      player.sendMessage(message);
    }
  }

  private String getRandomShit() {
    int length = RANDOM_SHIT_ARR.length;
    return RANDOM_SHIT_ARR[RANDOM.nextInt(length)] + " " + RANDOM_SHIT_ARR[RANDOM.nextInt(length)];
  }
}
