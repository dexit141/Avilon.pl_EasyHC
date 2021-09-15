package pl.godzina.avilon.basic.drop;

import java.util.*;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.godzina.avilon.AvilonPlugin;
import pl.godzina.avilon.basic.configuration.DropSettings;
import pl.godzina.avilon.basic.user.User;
import pl.godzina.avilon.helpers.ChatHelper;
import pl.godzina.avilon.helpers.DropHelper;
import pl.godzina.avilon.helpers.RandomHelper;

public class DropManager {
    private final AvilonPlugin plugin;
    @Getter
    private final List<Drop> drops = new ArrayList<>();

    public DropManager(AvilonPlugin plugin) {
        this.plugin = plugin;
    }

    public void setup() {
        this.drops.clear();
        for (String s : DropSettings.getConfig().getConfigurationSection("random-drops").getKeys(false)) {
            Drop d = new Drop(s);
            this.drops.add(d);
        }
    }

    public Drop getDropByName(String name) {
        for (Drop d : this.drops) {
            if (d.getName().equalsIgnoreCase(name))
                return d;
        }
        return null;
    }

    public void breakBlock(Block block, Player player, ItemStack item) {
        User u = this.plugin.getUserManager().getUser(player);
        List<ItemStack> drop = new ArrayList<>();
        for (Drop d : this.drops) {
            ItemStack itemDrop = (new ItemStack(d.getWhat())).clone();
            int expDrop = d.getExp();
            int y = block.getLocation().getBlockY();
            if (y < d.getMinHeight())
                continue;
            if (y > d.getMaxHeight())
                continue;
            double chance = d.getChance();
            if (plugin.getConfig().getInt("drop.turbodrop") > System.currentTimeMillis())
                chance *= 1.5D;
            chance += 0.0D;
            if (!RandomHelper.getChance(chance))
                continue;
            if (item.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS) && d.isFortune()) {
                int a = DropHelper.addFortuneEnchant((d.getMinAmount() == d.getMaxAmount()) ? d.getMinAmount() : RandomHelper.getRandInt(d.getMinAmount(), d.getMaxAmount()), item);
                itemDrop.setAmount(a);
                expDrop *= a;
            }
            if (!d.isDisabled(player.getUniqueId()))
                drop.add(itemDrop);
            player.giveExp(expDrop);
            if (d.getMessage().equalsIgnoreCase(""))
                continue;
            String msg = d.getMessage();
            msg = msg.replace("{AMOUNT}", Integer.toString(itemDrop.getAmount()));
            msg = msg.replace("{EXP}", Integer.toString(expDrop));
            if (!u.isMessagesDrop())
                continue;

            if (d.getDisabled().contains(player.getUniqueId())) {
                DropHelper.addItemsToPlayer(player, drop, block);
                DropHelper.recalculateDurability(player, item);
                block.setType(Material.AIR);
                return;
            }
            ChatHelper.sendMessage(player, msg);
        }
        if (u.isCobbleDrop())
            drop.add(new ItemStack(item.containsEnchantment(Enchantment.SILK_TOUCH) ? Material.STONE : Material.COBBLESTONE, 1));
        DropHelper.addItemsToPlayer(player, drop, block);
        DropHelper.recalculateDurability(player, item);
        block.setType(Material.AIR);
    }
}
