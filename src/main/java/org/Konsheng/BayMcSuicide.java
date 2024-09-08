package org.Konsheng;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class BayMcSuicide extends JavaPlugin {

    private BukkitAudiences adventure;
    private final MiniMessage miniMessage = MiniMessage.miniMessage();
    private final String prefix = "<gradient:#495aff:#0acffe><b>BayMc</b></gradient> <gray>» ";
    private final String suicideMessage = "<white>您已进行自杀操作";
    private final String nonPlayerMessage = "<white>此命令只能由玩家执行";

    // 保存玩家上次执行命令的时间
    private final HashMap<UUID, Long> lastSuicideTime = new HashMap<>();

    @Override
    public void onEnable() {
        adventure = BukkitAudiences.create(this);
        getLogger().info("BayMcSuicide 插件已启用");
    }

    @Override
    public void onDisable() {
        if (adventure != null) {
            adventure.close();
        }
        getLogger().info("BayMcSuicide 插件已禁用");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {

            // 获取玩家的 UUID
            UUID playerUUID = player.getUniqueId();
            long currentTime = System.currentTimeMillis();

            // 检查是否已经执行过命令，并限制一秒内不能再次执行
            if (lastSuicideTime.containsKey(playerUUID)) {
                long lastTime = lastSuicideTime.get(playerUUID);
                if (currentTime - lastTime < 1000) {
                    sendFormattedMessage(player, "<white>您的操作过于频繁, 请稍后再试");
                    return true;
                }
            }

            // 记录当前执行时间
            lastSuicideTime.put(playerUUID, currentTime);

            // 检查输入的命令
            if (label.equalsIgnoreCase("kill") ||
                    label.equalsIgnoreCase("suicide") ||
                    label.equalsIgnoreCase("die") ||
                    label.equalsIgnoreCase("zisha")) {

                // 播放音效
                playSoundAndSuicide(player);
                return true;
            }
        } else {
            // 非玩家执行，显示提示消息
            sendFormattedMessage(sender, nonPlayerMessage);
            return true;
        }
        return false;
    }

    // 播放音效并在音效播放后杀死玩家
    private void playSoundAndSuicide(Player player) {
        // 播放音效
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);

        // 延迟2秒执行，确保音效播放完成后再杀死玩家
        Bukkit.getScheduler().runTaskLater(this, () -> {
            // 杀死玩家
            player.setHealth(0.0);

            // 发送提示消息
            sendFormattedMessage(player, suicideMessage);
        }, 40L); // 40 tick 等于 2 秒
    }

    // 使用 Adventure API 发送带前缀的格式化消息
    private void sendFormattedMessage(CommandSender sender, String message) {
        Audience audience = adventure.sender(sender);
        Component component = miniMessage.deserialize(prefix + message);
        audience.sendMessage(component);
    }
}
