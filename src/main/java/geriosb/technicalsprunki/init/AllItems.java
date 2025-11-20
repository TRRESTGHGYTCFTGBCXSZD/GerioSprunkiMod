package geriosb.technicalsprunki.init;

import geriosb.technicalsprunki.TechnicalSprunkiMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AllItems {

    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, TechnicalSprunkiMod.MODID);
    public static final RegistryObject<Item> OREN_SPAWNER = REGISTRY.register("oren_spawner", () -> new ForgeSpawnEggItem(Sprunkis.OREN, -40704, -21159, new Item.Properties()));
}
