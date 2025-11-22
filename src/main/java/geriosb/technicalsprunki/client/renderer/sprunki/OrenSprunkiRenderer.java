package geriosb.technicalsprunki.client.renderer.sprunki;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import geriosb.technicalsprunki.common.entity.sprunki.OrenSprunkiEntity;
import geriosb.technicalsprunki.init.AllItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

import static geriosb.technicalsprunki.TechnicalSprunkiMod.rl;

public class OrenSprunkiRenderer extends EntityRenderer<OrenSprunkiEntity> {
    private final BlockRenderDispatcher blockRenderer;
    private static final ModelManager modelManager = Minecraft.getInstance().getModelManager();
    private static final ResourceLocation tex = rl("textures/sprunki/oren.png");
    private static final ResourceLocation headaccessory = rl("models/sprunki/oren_head");

    public OrenSprunkiRenderer(EntityRendererProvider.Context p_174008_) {
        super(p_174008_);
        this.blockRenderer = p_174008_.getBlockRenderDispatcher();
    }

    @Override
    public ResourceLocation getTextureLocation(OrenSprunkiEntity orenSprunkiEntity) {
        return tex;
    }

    @Override
    public void render(OrenSprunkiEntity sprunki, float yaw, float partialTicks, PoseStack ps,
                       MultiBufferSource bufSource, int packedLight) {

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        var itemRenderer = Minecraft.getInstance().getItemRenderer();

        ps.pushPose();

        int color = sprunki.isDeadOrDying() ? 0xffff8080 : (sprunki.hurtTime > 0 ? 0xffffa0a0 : 0xffffffff);
        if (sprunki.isDeadOrDying()) {
            ps.mulPose(Axis.XP.rotationDegrees(Math.min((sprunki.deathTime+partialTicks)*9f,90f)));
        }
        {
            // X is right, Y is up, Z is *in*
            // Our origin will be the lower-left corner of the scroll touching the wall
            // (so it has "negative" thickness)
            float bodyheight = 0.9f;
            float bodybottomsize = 0.25f;
            float bodytopsize = 0.15f;
            float headheight = 1.2f;
            float headsize = 0.9f;
            // body
            ps.pushPose();
            ps.mulPose(Axis.YP.rotationDegrees(-sprunki.getYRot()-90));
            SprunkiRenderUtils.RenderBody(sprunki,yaw,partialTicks,ps,bufSource,packedLight,tex,bodyheight,bodybottomsize,bodytopsize,color);
            ps.popPose();
            // head
            ps.pushPose();
            ps.translate(0f,headheight,0f);
            ps.mulPose(Axis.YP.rotationDegrees(-sprunki.getYHeadRot()-90));
            ps.mulPose(Axis.ZP.rotationDegrees(-sprunki.getXRot()));
            SprunkiRenderUtils.RenderHead(sprunki,yaw,partialTicks,ps,bufSource,packedLight,tex,headsize,color);
            ps.popPose();
            // headphones and antennas
            ps.pushPose();
            ps.translate(0f,headheight,0f);
            ps.pushPose();
            ps.mulPose(Axis.YP.rotationDegrees(-sprunki.getYHeadRot()+180));
            ps.mulPose(Axis.XP.rotationDegrees(-sprunki.getXRot()));
            itemRenderer.renderStatic(new ItemStack(AllItems.OREN_HEADPHONES.get()), ItemDisplayContext.FIXED, packedLight, OverlayTexture.NO_OVERLAY, ps, bufSource, sprunki.level(), 1);
            ps.popPose();

            ps.popPose();
        }

        ps.popPose();
        super.render(sprunki, yaw, partialTicks, ps, bufSource, packedLight);
    }

    private static void vertex(Matrix4f mat, Matrix3f normal, int light, VertexConsumer verts, float x, float y,
                               float z, float u,
                               float v, float nx, float ny, float nz, int color) {
        verts.vertex(mat, x, y, z)
                .color(color)
                .uv(u, v).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light)
                .normal(normal, nx, ny, nz)
                .endVertex();
    }
}
