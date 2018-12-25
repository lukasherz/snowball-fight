/*
 * Copyright (c) 2018. Lukas Herz
 */

package de.lukasherz.snowballFight.events;

import de.lukasherz.snowballFight.JsonFile;
import de.maltekp.json.JsonObject;
import org.bukkit.GameMode;
import org.bukkit.Particle;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SnowballMovesListener implements Listener {
    private double velocityFactor;
    private boolean headshot;

    public SnowballMovesListener(JsonFile jsonFile) {
        JsonObject settings = jsonFile.readJsonObject();

        try {
            if (settings.get("velocity_factor").toString().contains(".")) {
                this.velocityFactor = settings.getDouble("velocity_factor");
            } else {
                this.velocityFactor = settings.getInteger("velocity_factor");
            }

            this.headshot = settings.getBoolean("headshot");
        } catch (Exception e) {
            this.velocityFactor = 0.3;
            this.headshot = true;
            System.out.println("[SnowballFight] Problem found in config file! Using default settings.");
        }
    }

    public double getVelocityFactor() {
        return velocityFactor;
    }

    public void setVelocityFactor(double velocityFactor) {
        this.velocityFactor = velocityFactor;
    }

    public boolean isHeadshot() {
        return headshot;
    }

    public void setHeadshot(boolean headshot) {
        this.headshot = headshot;
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent projectileHitEvent) {
        Projectile projectile = projectileHitEvent.getEntity();

        if (projectile instanceof Snowball) {
            if (projectileHitEvent.getHitEntity().getType() == EntityType.PLAYER) {
                Player player = (Player) projectileHitEvent.getHitEntity();

                if (player.getGameMode() != GameMode.CREATIVE) {
                    player.damage(0.05, projectile);
                    player.setVelocity(projectile.getVelocity().multiply(velocityFactor));
                    player.getWorld().spawnParticle(Particle.SNOWBALL, player.getLocation(), 150);

                    if (headshot && player.getLocation().getY() - projectile.getLocation().getY() <= -1.45) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 25, 1, true));
                    }
                }
            }
        }
    }

}
