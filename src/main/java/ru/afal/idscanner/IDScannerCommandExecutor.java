package ru.afal.idscanner;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.logging.Logger;

public class IDScannerCommandExecutor implements CommandExecutor {

  private final Logger LOGGER;

  public IDScannerCommandExecutor(Logger logger) {
    this.LOGGER = logger;
  }

  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    boolean isPlayer = sender instanceof Player;
    if (!isPlayer) {
      LOGGER.info("Non-player tried to use command (MonkaS)");
      return true;
    }

    Player player = (Player) sender;
    if (!player.hasPermission("id.scan")) {
      player.damage(0.1);
      player.sendMessage(ChatColor.DARK_RED + "You feel as though divine forces are too much for you to handle...");
      return true;
    }

    Player target = args.length == 0 ? player : Bukkit.getPlayer(args[0]);
    if (target == null) {
      player.sendMessage(ChatColor.AQUA + "You feel as though this individual is not present in this realm at the given time...");
      return true;
    }

    Map<Integer, ItemStack> map = target.getInventory().addItem(IDScanner.INSTANCE);
    if (map.isEmpty()) {
      player.sendMessage(ChatColor.AQUA + "You feel as though something appeared in your backpack...");
    } else {
      target.getWorld().dropItemNaturally(target.getLocation().add(1, 1, 0), IDScanner.INSTANCE);
      player.sendMessage(ChatColor.AQUA + "You feel as though something appeared in front of you...");
    }

    return true;
  }
}
