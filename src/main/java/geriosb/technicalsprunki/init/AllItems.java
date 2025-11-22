package geriosb.technicalsprunki.init;

import geriosb.technicalsprunki.TechnicalSprunkiMod;
import geriosb.technicalsprunki.common.items.HeadWearableItem;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AllItems {

    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, TechnicalSprunkiMod.MODID);
    public static final RegistryObject<Item> POLO_SPAWNER = REGISTRY.register("polo_spawner", () -> new ForgeSpawnEggItem(Sprunkis.POLO, -40704, -21159, new Item.Properties()));
    public static final RegistryObject<Item> OREN_SPAWNER = REGISTRY.register("oren_spawner", () -> new ForgeSpawnEggItem(Sprunkis.OREN, -40704, -21159, new Item.Properties()));
    public static final RegistryObject<Item> FUN_BOT_SPAWNER = REGISTRY.register("fun_bot_spawner", () -> new ForgeSpawnEggItem(Sprunkis.OREN, -40704, -21159, new Item.Properties()));
    public static final RegistryObject<Item> SPRUNKI_CAST = block(AllBlocks.SPRUNKI_CAST);
    public static final RegistryObject<Item> OREN_BLOCK = block(AllBlocks.OREN_BLOCK);
    public static final RegistryObject<Item> OREN_HEADPHONES = REGISTRY.register("oren_headphones", () -> new HeadWearableItem(new Item.Properties()));

    private static RegistryObject<Item> block(RegistryObject<Block> block) {
        return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
