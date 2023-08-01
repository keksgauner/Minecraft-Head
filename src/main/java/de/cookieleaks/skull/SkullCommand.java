package de.cookieleaks.skull;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

public class SkullCommand extends BukkitCommand {

    protected SkullCommand() {
        super("skull");
    }

    static HashMap<UUID, LocalDateTime> cooldowns = new HashMap<>();

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String label, @NotNull String[] strings) {
        if (sender instanceof Player player) {
            if (cooldowns.containsKey(player.getUniqueId())) {
                Duration remainingTime = Duration.between(LocalDateTime.now(), cooldowns.get(player.getUniqueId()));

                long hours = remainingTime.toHours() % 24;
                long minutes = remainingTime.toMinutes() % 60;
                long seconds = remainingTime.getSeconds() % 60;
                player.sendMessage("§cDu kannst deinen Kopf erst in " + hours + " Stunden " + minutes + " Minuten und " + seconds + " Sekunden wieder erhalten!");
                return false;
            }

            // check if enough space
            int freeSlots = 0;
            for (ItemStack item : player.getInventory().getContents()) {
                if (item == null) {
                    freeSlots++;
                }
            }

            if (freeSlots < 1) {
                player.sendMessage("§cDu hast nicht genug Platz im Inventar!");
                return false;
            }

            ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
            skullMeta.setOwningPlayer(player);
            skullMeta.setDisplayName("§aKopf von §7" + player.getName());
            itemStack.setItemMeta(skullMeta);
            player.getInventory().addItem(itemStack);
            player.sendMessage("§aDu hast deinen Kopf erhalten!");
            addTimer(player);
        }

        return false;
    }

    public void addTimer(Player player) {
        cooldowns.put(player.getUniqueId(), LocalDateTime.now().plusHours(24));
        new BukkitRunnable() {
            @Override
            public void run() {
                cooldowns.remove(player.getUniqueId());
            }
        }.runTaskLater(Skull.getInstance(), 20 * 86400);
    }


}
