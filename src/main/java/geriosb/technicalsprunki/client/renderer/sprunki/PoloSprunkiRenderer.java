package geriosb.technicalsprunki.client.renderer.sprunki;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import geriosb.technicalsprunki.common.entity.sprunki.PoloSprunkiEntity;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

import static geriosb.technicalsprunki.TechnicalSprunkiMod.rl;

public class PoloSprunkiRenderer extends EntityRenderer<PoloSprunkiEntity> {
    private static final ResourceLocation tex = rl("textures/sprunki/polo.png");

    public PoloSprunkiRenderer(EntityRendererProvider.Context p_174008_) {
        super(p_174008_);
    }

    @Override
    public ResourceLocation getTextureLocation(PoloSprunkiEntity orenSprunkiEntity) {
        return tex;
    }
    @Override
    public void render(PoloSprunkiEntity sprunki, float yaw, float partialTicks, PoseStack ps,
                       MultiBufferSource bufSource, int packedLight) {

        RenderSystem.setShader(GameRenderer::getPositionTexShader);

        ps.pushPose();

        int color = sprunki.isDeadOrDying() ? 0xffff8080 : (sprunki.hurtTime > 0 ? 0xffffa0a0 : 0xffffffff);

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
