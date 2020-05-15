package ru.afal.idscanner;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class IDScanner extends ItemStack {

  public static final IDScanner INSTANCE = new IDScanner();

  private IDScanner() {
    super(Material.JACK_O_LANTERN); // HEART_OF_THE_SEA, JACK_O_LANTERN, COMPARATOR
    ItemMeta itemMeta = getItemMeta();
    if (itemMeta != null) {
      itemMeta.setDisplayName("The Revealer");
      itemMeta.setUnbreakable(true);
      itemMeta.setLore(Collections.singletonList("You feel as though this creature can help you to identify the inhabitants of this realm..."));
      setItemMeta(itemMeta);
    }
  }
}
