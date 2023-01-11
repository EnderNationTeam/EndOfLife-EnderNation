package de.mxscha.en.endoflife.listener.player;

import de.mxscha.en.endoflife.EndoflifeCore;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.util.Vector;

/*
 * Author: TheEnderHacker
 * Time: 11.01.2023
 */
public class SnowballDamageListener implements Listener {

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (event.getEntity() instanceof Snowball) {
            Snowball snowball = (Snowball) event.getEntity();
            if (snowball.getShooter() instanceof Player) {
                Player shooter = (Player) snowball.getShooter();
                if (event.getHitEntity() instanceof LivingEntity) {
                    LivingEntity hitEntity = (LivingEntity) event.getHitEntity();
                    if(hitEntity instanceof Player || hitEntity instanceof LivingEntity) {
                        onPlayerInteract(shooter,hitEntity);
                    }
                    EntityDamageByEntityEvent damageEvent = new EntityDamageByEntityEvent(shooter, hitEntity, EntityDamageByEntityEvent.DamageCause.CUSTOM, 0.0000001);
                    EndoflifeCore.getInstance().getServer().getPluginManager().callEvent(damageEvent);
                    if (!damageEvent.isCancelled()) {
                        hitEntity.damage(damageEvent.getDamage());
                    }
                }
            }
        }
    }

    private void onPlayerInteract(Player player, LivingEntity entity) {
        Vector vector = entity.getLocation().toVector().subtract(player.getLocation().toVector());
        vector.setY(0.7); // add upward velocity
        vector.normalize();
        vector.multiply(0.9);
        entity.setVelocity(vector);
    }
}
