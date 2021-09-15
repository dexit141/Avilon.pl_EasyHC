package pl.godzina.avilon.basic.user.addons;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pl.godzina.avilon.AvilonPlugin;
import pl.godzina.avilon.basic.user.User;
import pl.godzina.avilon.helpers.ChatHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.UUID;

@Getter
@Setter
public class CustomEnderchest {
    private final String name;
    private final User owner;
    private String lore;
    private final String permission;
    private final int slots;
    private ItemStack[] contents;

    public CustomEnderchest(String name, User owner, String permission, int slots) {
        this.name = name;
        this.owner = owner;
        this.lore = "&fBrak opisu.";
        this.permission = permission;
        this.slots = slots;
    }
    public CustomEnderchest(ResultSet rs) throws SQLException {
        this.name = rs.getString("name");
        this.owner = AvilonPlugin.getInstance().getUserManager().getUser(UUID.fromString(rs.getString("owner")));
        this.lore = rs.getString("lore");
        this.permission = rs.getString("permission");
        this.slots = rs.getInt("slots");
    }
    public void openEnderchest() {
        ItemStack[] enderchest = this.getContents();
        Inventory inventory = Bukkit.createInventory(null, 54, ChatHelper.fixColor("&d" + name));
        if (this.getContents() != null) {
            inventory.setContents(enderchest);
        }
        this.getOwner().getPlayer().openInventory(inventory);
    }

    public void closeEnderchest(Inventory inventory) {
        ItemStack[] current = inventory.getContents();
        ItemStack[] last = this.getContents();
        if (!Arrays.equals(current, last)) {
            this.setContents(current);
        }
    }
}
