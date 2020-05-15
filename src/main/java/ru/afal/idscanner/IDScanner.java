package ru.afal.idscanner;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class IDScanner extends ItemStack {

  public static final IDScanner INSTANCE = new IDScanner();

  private IDScanner() {
    super(Material.SKELETON_SKULL); // HEART_OF_THE_SEA, JACK_O_LANTERN, COMPARATOR, SKELETON_SKULL, WITHER_SKELETON_SKULL
    ItemMeta itemMeta = getItemMeta();
    if (itemMeta != null) {
      itemMeta.setDisplayName("The Revealer");
      itemMeta.setUnbreakable(true);
      itemMeta.setLore(Arrays.asList("You feel as though this creature can help you", "to identify the inhabitants of this realm..."));
      setItemMeta(itemMeta);
    }
  }
}
