package geriosb.technicalsprunki.init;

import at.petrak.hexcasting.api.HexAPI;
import geriosb.technicalsprunki.TechnicalSprunkiMod;
import geriosb.technicalsprunki.common.entity.sprunki.FunBotSprunkiEntity;
import geriosb.technicalsprunki.common.entity.sprunki.OrenSprunkiEntity;
import geriosb.technicalsprunki.common.entity.sprunki.PoloSprunkiEntity;
import geriosb.technicalsprunki.common.entity.sprunki.SprunkiEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Async;

import static geriosb.technicalsprunki.TechnicalSprunkiMod.MODID;


@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Sprunkis {
    public static final DeferredRegister<EntityType<?>> SPRUNKIS = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TechnicalSprunkiMod.MODID);
    public static final RegistryObject<EntityType<PoloSprunkiEntity>> POLO = SPRUNKIS.register("polo", () -> EntityType.Builder.<PoloSprunkiEntity>of(PoloSprunkiEntity::new, MobCategory.CREATURE).sized(0.7f,1.5f).build("polo"));

    public static final RegistryObject<EntityType<OrenSprunkiEntity>> OREN = SPRUNKIS.register("oren", () -> EntityType.Builder.<OrenSprunkiEntity>of(OrenSprunkiEntity::new, MobCategory.CREATURE).sized(0.7f,1.5f).build("oren"));
    //public static final RegistryObject<EntityType<RaddySprunkiEntity>> RADDY;
    //public static final RegistryObject<EntityType<ClukrSprunkiEntity>> CLUKR;
    public static final RegistryObject<EntityType<FunBotSprunkiEntity>> FUN_BOT = SPRUNKIS.register("fun_bot", () -> EntityType.Builder.<FunBotSprunkiEntity>of(FunBotSprunkiEntity::new, MobCategory.CREATURE).sized(0.7f,1.5f).build("fun_bot"));
    //public static final RegistryObject<EntityType<VineriaSprunkiEntity>> VINERIA;

    //public static final RegistryObject<EntityType<GraySprunkiEntity>> GRAY;
    //public static final RegistryObject<EntityType<brudSprunkiEntity>> brud;
    //public static final RegistryObject<EntityType<GarnoldSprunkiEntity>> GARNOLD;
    //public static final RegistryObject<EntityType<OWAKCXSprunkiEntity>> OWAKCX;
    //public static final RegistryObject<EntityType<SkySprunkiEntity>> SKY;

    //public static final RegistryObject<EntityType<MisterSunSprunkiEntity>> MISTER_SUN;
    //public static final RegistryObject<EntityType<DurpleSprunkiEntity>> DURPLE;
    //public static final RegistryObject<EntityType<MisterTreeSprunkiEntity>> MISTER_TREE;
    //public static final RegistryObject<EntityType<SimonSprunkiEntity>> SIMON;
    //public static final RegistryObject<EntityType<TunnerSprunkiEntity>> TUNNER;

    //public static final RegistryObject<EntityType<MisterFunComputerSprunkiEntity>> MISTER_FUN_COMPUTER;
    //public static final RegistryObject<EntityType<WendaSprunkiEntity>> WENDA;
    //public static final RegistryObject<EntityType<PinkiSprunkiEntity>> PINKI;
    //public static final RegistryObject<EntityType<JevinSprunkiEntity>> JEVIN;
    //public static final RegistryObject<EntityType<BlackSprunkiEntity>> BLACK;

    //OCS

    //public static final RegistryObject<EntityType<GerioSprunkiEntity>> GERIO;
    //public static final RegistryObject<EntityType<TricSprunkiEntity>> TRIC;

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){
        event.put(OREN.get(), OrenSprunkiEntity.createAttributes().build());
        event.put(POLO.get(), PoloSprunkiEntity.createAttributes().build());
    }
}
