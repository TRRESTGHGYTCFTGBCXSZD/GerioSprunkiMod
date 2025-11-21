package geriosb.technicalsprunki.init;

import geriosb.technicalsprunki.TechnicalSprunkiMod;
import geriosb.technicalsprunki.common.blocks.sprunki.OrenBlock;
import geriosb.technicalsprunki.common.blocks.sprunki.SprunkiCast;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AllBlocks {
    public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, TechnicalSprunkiMod.MODID);
    public static final RegistryObject<Block> SPRUNKI_CAST = REGISTRY.register("sprunki_cast", SprunkiCast::new);
    public static final RegistryObject<Block> OREN_BLOCK = REGISTRY.register("oren_block", OrenBlock::new);

}
