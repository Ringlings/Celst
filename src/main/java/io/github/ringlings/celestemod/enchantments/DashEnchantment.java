package io.github.ringlings.celestemod.enchantments;

import io.github.ringlings.celestemod.config.CelesteConfigManager;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class DashEnchantment extends Enchantment {
	public DashEnchantment() {
		super(Rarity.RARE, EnchantmentTarget.ARMOR_CHEST, new EquipmentSlot[]{EquipmentSlot.CHEST});
	}

	@Override
	public int getMaxLevel() {
		return CelesteConfigManager.MAX_DASH_LEVEL.value();
	}
}
