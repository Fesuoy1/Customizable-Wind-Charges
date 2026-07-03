package org.mod.cwcharges.mixins;

import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import static org.mod.cwcharges.CustomizableWindCharges.getCooldown;

@Mixin(ItemCooldowns.class)
public class ItemCooldownsMixin {

    @ModifyVariable(method = "addCooldown(Lnet/minecraft/world/item/ItemStack;I)V", at = @At("HEAD"), argsOnly = true, name = "time")
    private int use$modifyCooldownTime(int time, ItemStack item) {
        if (item.is(Items.WIND_CHARGE)) {
            return getCooldown();
        }
        return time;
    }
}
