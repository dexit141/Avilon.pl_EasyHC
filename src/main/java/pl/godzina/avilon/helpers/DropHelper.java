package pl.godzina.avilon.helpers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class DropHelper {
    public static int addFortuneEnchant(int amount, ItemStack tool) {
        int a = amount;
        if (RandomHelper.getChance(20.0D) && tool.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) >= 1) {
            a++;
        } else if (RandomHelper.getChance(30.0D) && tool.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) >= 2) {
            a += 2;
        } else if (RandomHelper.getChance(50.0D) && tool.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) >= 3) {
            a += 3;
        }
        return a;
    }

    public static void addItemsToPlayer(Player player, List<ItemStack> items, Block b) {
        PlayerInventory inv = player.getInventory();
        HashMap<Integer, ItemStack> notStored = inv.addItem(items.<ItemStack>toArray(new ItemStack[items.size()]));
        for (Map.Entry<Integer, ItemStack> en : notStored.entrySet())
            b.getWorld().dropItemNaturally(b.getLocation(), en.getValue());
    }

    public static void recalculateDurability(Player player, ItemStack item) {
        if (item.getType().getMaxDurability() == 0)
            return;
        int enchantLevel = item.getEnchantmentLevel(Enchantment.DURABILITY);
        short d = item.getDurability();
        if (enchantLevel > 0) {
            if (100 / (enchantLevel + 1) > RandomHelper.getRandInt(0, 100))
                if (d == item.getType().getMaxDurability()) {
                    player.getInventory().clear(player.getInventory().getHeldItemSlot());
                    player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
                } else {
                    item.setDurability((short)(d + 1));
                }
        } else if (d == item.getType().getMaxDurability()) {
            player.getInventory().clear(player.getInventory().getHeldItemSlot());
            player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
        } else {
            item.setDurability((short)(d + 1));
        }
    }
}

