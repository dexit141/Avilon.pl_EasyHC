package pl.godzina.avilon.menus;

import me.mattstudios.mfgui.gui.components.ItemBuilder;
import me.mattstudios.mfgui.gui.guis.Gui;
import me.mattstudios.mfgui.gui.guis.GuiItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import pl.godzina.avilon.AvilonPlugin;
import pl.godzina.avilon.basic.user.User;
import pl.godzina.avilon.helpers.ChatHelper;

import java.util.Arrays;

public class HomeMenus {
    private final AvilonPlugin plugin;

    public HomeMenus(AvilonPlugin plugin) {
        this.plugin = plugin;
    }
    public void open(Player p) {
        Gui gui = new Gui(6, ChatHelper.fixColor("&8>> &dHome"));
        User user = this.plugin.getUserManager().getUser(p);

        gui.getFiller().fillBorder(new GuiItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15)));

        user.getHomes().forEach(home -> {
            ItemStack guiItem = ItemBuilder.from(Material.GOLD_BOOTS).setName(ChatHelper.fixColor("&d&l" + home.getName()))
                    .setLore(ChatHelper.fixColor(Arrays.asList("",
                            " &8>> &fStatus home: " + home.isClaimed(p), "","&8>> &fPosiadasz dostęp: " + (p.hasPermission(home.getPermission()) ? "&a&lTAK" : "&c&lNIE"), "&8>> &fKliknij &a&lLPM &faby teleportowac sie do home", "&8>> &fKliknij &a&lPPM &faby ustawic home!"))).build();
            gui.setItem(home.getSlots(), ItemBuilder.from(guiItem).asGuiItem(event -> {
                if (event.getClick() == ClickType.RIGHT) {
                        home.setLocation(p.getLocation());
                        ChatHelper.sendMessage(p, "&d&lAvilon &8>> &fPomyslnie ustawiles home!");
                } else if (event.getClick() == ClickType.LEFT){
                    plugin.getTeleportManager().teleport(p, home.getLocation(), 5);
                }
            }));
        });
        gui.setDefaultClickAction(event -> {
            event.setCancelled(true);
        });
        gui.open(p);
    }
}
