package de.mxscha.en.endoflife.listener.player;

import net.kyori.adventure.text.TextReplacementConfig;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import net.kyori.adventure.text.Component;

public class DeathListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getPlayer();
        Player target = event.getEntity().getKiller();

        Component component = event.deathMessage();
        if(component != null) {
            // Hier wird eine Config für den text der Abgeändert werden soll für den Player
            TextReplacementConfig.Builder builder = TextReplacementConfig.builder();
            builder.match(player.getName());
            builder.replacement(Component.text(player.getName()).color(TextColor.fromHexString("#00FF00")));

            // Hier ist bei dem Component der Player Abgeändert
            component = component.replaceText(builder.build());

            if (target != null) {
                // Hier wird eine Config für den text der Abgeändert werden soll für den Target
                builder = TextReplacementConfig.builder();
                builder.match(target.getName());
                builder.replacement(Component.text(target.getName()).color(TextColor.fromHexString("#FF0000")));

                // Hier ist bei dem Component den Target Player Abgeändert
                component = component.replaceText(builder.build());

                // Sende die message
                player.sendMessage(component);
                target.sendMessage(component);
            } else {
                // Ein Target existiert nicht es ist kein Player!
                // Nur noch dem Player eine nachricht senden
                player.sendMessage(component);
            }
        }

        event.deathMessage(null);
    }

}
