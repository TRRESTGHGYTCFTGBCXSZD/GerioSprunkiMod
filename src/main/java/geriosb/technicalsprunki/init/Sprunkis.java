package geriosb.technicalsprunki.init;

import at.petrak.hexcasting.api.HexAPI;
import geriosb.technicalsprunki.TechnicalSprunkiMod;
import geriosb.technicalsprunki.common.entity.sprunki.OrenSprunkiEntity;
import geriosb.technicalsprunki.common.entity.sprunki.SprunkiEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class Sprunkis {
    public static final DeferredRegister<EntityType<?>> SPRUNKIS = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TechnicalSprunkiMod.MODID);
    public static final RegistryObject<EntityType<OrenSprunkiEntity>> OREN = SPRUNKIS.register("oren", () -> EntityType.Builder.of(OrenSprunkiEntity::new, MobCategory.CREATURE).sized(1f,2f).build("oren"));

    public static void BrainSweepHandler(){
        //HexAPI.instance().registerCustomBrainsweepingBehavior(OREN.get(),() -> SprunkiEntity.BrainSweep());
    }
}
