package pl.godzina.avilon.helpers;

import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.command.*;
import java.util.*;

public class ItemHelper {
    public static void giveItems(final Player p, final ItemStack... items) {
        final Inventory i = (Inventory)p.getInventory();
        final HashMap<Integer, ItemStack> notStored = (HashMap<Integer, ItemStack>)i.addItem(items);
        for (final Map.Entry<Integer, ItemStack> e : notStored.entrySet()) {
            p.getWorld().dropItemNaturally(p.getLocation(), (ItemStack)e.getValue());
        }
    }

    public static void remove(final ItemStack is, final Player player, final int amount) {
        int removed = 0;
        boolean all = false;
        final List<ItemStack> toRemove = new ArrayList<ItemStack>();
        final ItemStack[] contents = player.getInventory().getContents();
        for (int i = 0; i < contents.length; ++i) {
            final ItemStack item = contents[i];
            if (item != null && !item.getType().equals((Object)Material.AIR) && item.getType().equals((Object)is.getType()) && item.getDurability() == is.getDurability() && !all && removed != amount) {
                if (item.getAmount() == amount) {
                    if (removed == 0) {
                        toRemove.add(item.clone());
                        all = true;
                        removed = item.getAmount();
                    }
                    else {
                        final int a = amount - removed;
                        final ItemStack s = item.clone();
                        s.setAmount(a);
                        toRemove.add(s);
                        removed += a;
                        all = true;
                    }
                }
                else if (item.getAmount() > amount) {
                    if (removed == 0) {
                        final ItemStack s2 = item.clone();
                        s2.setAmount(amount);
                        toRemove.add(s2);
                        all = true;
                        removed = amount;
                    }
                    else {
                        final int a = amount - removed;
                        final ItemStack s = item.clone();
                        s.setAmount(a);
                        toRemove.add(s);
                        removed += a;
                        all = true;
                    }
                }
                else if (item.getAmount() < amount) {
                    if (removed == 0) {
                        toRemove.add(item.clone());
                        removed = item.getAmount();
                    }
                    else {
                        final int a = amount - removed;
                        if (a == item.getAmount()) {
                            toRemove.add(item.clone());
                            removed += item.getAmount();
                            all = true;
                        }
                        else if (item.getAmount() > a) {
                            final ItemStack s = item.clone();
                            s.setAmount(a);
                            toRemove.add(s);
                            removed += a;
                            all = true;
                        }
                        else if (item.getAmount() < a) {
                            toRemove.add(item.clone());
                            removed += item.getAmount();
                        }
                    }
                }
            }
        }
        removeItem(player, toRemove);
    }

    public static void removeItem(final Player player, final List<ItemStack> items) {
        if (player == null || items == null || items.isEmpty()) {
            return;
        }
        for (final ItemStack is : items) {
            player.getInventory().removeItem(new ItemStack[] { is });
        }
    }

    public static void removeItems(final Player p, final String it, final int mod) {
        final List<ItemStack> items = getItems(it, mod);
        for (final ItemStack is : items) {
            final ItemStack item = new ItemStack(Material.getMaterial(is.getType().getId()), is.getAmount(), (short)is.getData().getData());
            if (p.getInventory().containsAtLeast(item, item.getAmount())) {
                p.getInventory().removeItem(new ItemStack[] { item });
            }
        }
    }

    public static ItemStack getItemStackFromString(final String itemstack) {
        final String[] splits = itemstack.split("@");
        final String type = splits[0];
        final String data = (splits.length == 2) ? splits[1] : null;
        if (data == null) {
            return new ItemStack(Material.getMaterial(type), 1);
        }
        return new ItemStack(Material.getMaterial(type), 1, (short)Integer.parseInt(data));
    }

    public static double round(final double value, final int decimals) {
        final double p = Math.pow(10.0, decimals);
        return Math.round(value * p) / p;
    }

    public static Location LocationFromString(final String s) {
        final String[] ss = s.split("_");
        final Location l = new Location((World)Bukkit.getWorlds().get(0), 0.0, 0.0, 0.0, 0.0f, 0.0f);
        l.setWorld(Bukkit.getWorld(ss[0]));
        l.setX(Double.parseDouble(ss[1]));
        l.setY(Double.parseDouble(ss[2]));
        l.setZ(Double.parseDouble(ss[3]));
        l.setYaw(Float.parseFloat(ss[4]));
        l.setPitch(Float.parseFloat(ss[5]));
        return l;
    }

    public static int getAmountof(final Player p, final Material m, final Short d) {
        int ilosc = 0;
        for (int i = 0; i < p.getInventory().getSize(); ++i) {
            final ItemStack s = p.getInventory().getItem(i);
            if (s != null && s.getType().equals((Object)m) && s.getDurability() == d) {
                ilosc += s.getAmount();
            }
        }
        return ilosc;
    }

    public static ItemStack getPlayerHead(final String name) {
        final ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        final SkullMeta meta = (SkullMeta)itemStack.getItemMeta();
        meta.setOwner(name);
        meta.setDisplayName(name);
        itemStack.setItemMeta((ItemMeta)meta);
        return itemStack;
    }

    public static boolean checkItems(final Player p, final String it, final int mod) {
        final List<ItemStack> items = getItems(it, mod);
        return items.stream().map(is -> new ItemStack(Material.getMaterial(is.getType().getId()), is.getAmount(), (short)is.getData().getData())).allMatch(item -> p.getInventory().containsAtLeast(item, item.getAmount()));
    }

    public static List<ItemStack> getItems(final String string, final int modifier) {
        final List<ItemStack> items = new ArrayList<ItemStack>();
        for (final String s : string.split(";")) {
            final String[] split = s.split("-");
            final int id = Integer.parseInt(split[0].split(":")[0]);
            final int data = Integer.parseInt(split[0].split(":")[1]);
            final int amount = Integer.parseInt(split[1].split(":")[0]) * modifier;
            final String name = split[1].split(":")[1];
            final ItemStack is = new ItemStack(Material.getMaterial(id), amount, (short)data);
            final ItemMeta meta = is.getItemMeta();
            meta.setDisplayName(name);
            is.setItemMeta(meta);
            items.add(is);
        }
        return items;
    }

    public static String getItem(final Player p, final String it, final int mod) {
        final List<ItemStack> items = getItems(it, mod);
        ChatHelper.sendMessage((CommandSender)p, "&cBrakuje ci:");
        for (final ItemStack is : items) {
            final int id = is.getType().getId();
            final int data = is.getData().getData();
            final int amount = is.getAmount();
            final int ii = getItemAmount(Material.getMaterial(id), p, (short)data);
            ChatHelper.sendMessage((CommandSender)p, color(ii, amount) + " - " + is.getItemMeta().getDisplayName() + " " + ii + "/" + amount + " - " + ii / (double)amount * 100.0 + "%\n");
        }
        return null;
    }

    public static int getItemAmount(final Material material, final Player player, final short durability) {
        int amount = 0;
        ItemStack[] contents;
        for (int length = (contents = player.getInventory().getContents()).length, i = 0; i < length; ++i) {
            final ItemStack itemStack = contents[i];
            if (itemStack != null && itemStack.getType().equals((Object)material) && itemStack.getDurability() == durability) {
                amount += itemStack.getAmount();
            }
        }
        return amount;
    }

    private static String color(final int i, final int i2) {
        if (i >= i2) {
            return ChatHelper.fixColor("&a");
        }
        return ChatHelper.fixColor("&c");
    }

    public static List<String> fixColor(final List<String> msg) {
        final List<String> s = new ArrayList<String>();
        for (final String m : msg) {
            s.add(String.valueOf(fixColor(Collections.singletonList(m))));
        }
        return s;
    }

    public static ItemStack getItem(final Material m, final String name, final List<String> lore) {
        final ItemStack item = new ItemStack(m, 1);
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(String.valueOf(fixColor(Collections.singletonList(name))));
        meta.setLore((List)fixColor(lore));
        item.setItemMeta(meta);
        return item;
    }

    public static boolean checkItem(final ItemStack i1, final ItemStack i2) {
        return i1.getType().equals((Object)i2.getType()) && i1.hasItemMeta() == i2.hasItemMeta() && i1.getItemMeta().getDisplayName() != null && i2.getItemMeta().getDisplayName() != null && i1.getItemMeta().getDisplayName().equalsIgnoreCase(i2.getItemMeta().getDisplayName());
    }
}
