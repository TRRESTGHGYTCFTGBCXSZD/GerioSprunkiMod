package geriosb.technicalsprunki;

import geriosb.technicalsprunki.client.renderer.sprunki.OrenSprunkiRenderer;
import geriosb.technicalsprunki.init.Sprunkis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static geriosb.technicalsprunki.TechnicalSprunkiMod.rl;
import static geriosb.technicalsprunki.TechnicalSprunkiMod.MODID;

@Mod.EventBusSubscriber(modid = MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TechnicalSprunkiClient {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers evt) {
        evt.registerEntityRenderer(Sprunkis.OREN.get(), OrenSprunkiRenderer::new);
    }
}
