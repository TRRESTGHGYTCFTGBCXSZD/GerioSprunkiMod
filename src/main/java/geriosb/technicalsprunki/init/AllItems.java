package geriosb.technicalsprunki.init;

import geriosb.technicalsprunki.TechnicalSprunkiMod;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AllItems {

    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, TechnicalSprunkiMod.MODID);
    public static final RegistryObject<Item> POLO_SPAWNER = REGISTRY.register("polo_spawner", () -> new ForgeSpawnEggItem(Sprunkis.POLO, -40704, -21159, new Item.Properties()));
    public static final RegistryObject<Item> OREN_SPAWNER = REGISTRY.register("oren_spawner", () -> new ForgeSpawnEggItem(Sprunkis.OREN, -40704, -21159, new Item.Properties()));
    public static final RegistryObject<Item> SPRUNKI_CAST = block(AllBlocks.SPRUNKI_CAST);
    public static final RegistryObject<Item> OREN_BLOCK = block(AllBlocks.OREN_BLOCK);

    private static RegistryObject<Item> block(RegistryObject<Block> block) {
        return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
