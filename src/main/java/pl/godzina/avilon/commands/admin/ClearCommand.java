package pl.godzina.avilon.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.godzina.avilon.AvilonPlugin;
import pl.godzina.avilon.commands.api.PlayerCommand;
import pl.godzina.avilon.helpers.ChatHelper;

public class ClearCommand extends PlayerCommand {
    public ClearCommand(AvilonPlugin plugin) {
        super(plugin, "clear", "/clear (gracz)", "avilon.clear", "ci");
    }

    @Override
    public boolean onExecute(Player p, String label, String[] args) {
        if (args.length < 1) {

            p.getInventory().clear();
            p.getInventory().setHelmet((ItemStack) null);
            p.getInventory().setChestplate((ItemStack) null);
            p.getInventory().setLeggings((ItemStack) null);
            p.getInventory().setBoots((ItemStack) null);
            p.getInventory().setHeldItemSlot(0);
            p.updateInventory();
            return ChatHelper.sendMessage(p, "&d&lAvilon &8>> &fTwoj ekwipunek zostal wyczyszczony");
        }

    Player o = Bukkit.getPlayerExact(args[0]);
        o.getInventory().clear();
        o.getInventory().setHelmet((ItemStack)null);
        o.getInventory().setChestplate((ItemStack)null);
        o.getInventory().setLeggings((ItemStack)null);
        o.getInventory().setBoots((ItemStack)null);
        o.getInventory().setHeldItemSlot(0);
        o.updateInventory();

        ChatHelper.sendMessage(p, "&d&lAvilon &8>> &fPomyslnie wyczysciles ekwipunek gracza &d" + o.getName()) ;
        return ChatHelper.sendMessage(p, "&d&lAvilon &8>> &fTwoj ekwipunek zostal wyczyszczony przez administratora &d" + p.getName());
    }
}
