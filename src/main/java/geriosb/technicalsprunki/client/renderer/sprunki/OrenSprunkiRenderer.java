package geriosb.technicalsprunki.client.renderer.sprunki;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import geriosb.technicalsprunki.common.entity.sprunki.OrenSprunkiEntity;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

import static geriosb.technicalsprunki.TechnicalSprunkiMod.rl;

public class OrenSprunkiRenderer extends EntityRenderer<OrenSprunkiEntity> {
    private static final ResourceLocation tex = rl("textures/sprunki/oren.png");

    public OrenSprunkiRenderer(EntityRendererProvider.Context p_174008_) {
        super(p_174008_);
    }

    @Override
    public ResourceLocation getTextureLocation(OrenSprunkiEntity orenSprunkiEntity) {
        return null;
    }
    @Override
    public void render(OrenSprunkiEntity wallScroll, float yaw, float partialTicks, PoseStack ps,
                       MultiBufferSource bufSource, int packedLight) {

        RenderSystem.setShader(GameRenderer::getPositionTexShader);

        ps.pushPose();

        ps.mulPose(Axis.YP.rotationDegrees(180f - yaw));
        ps.mulPose(Axis.ZP.rotationDegrees(180f));


        {
            ps.pushPose();
            // X is right, Y is down, Z is *in*
            // Our origin will be the lower-left corner of the scroll touching the wall
            // (so it has "negative" thickness)
            var last = ps.last();
            var mat = last.pose();
            var norm = last.normal();
            float bodyheight = 1.5f;
            float bodybottomsize = 0.45f;
            float bodytopsize = 0.3f;
            float headheight = 1.2f;
            RenderType layer = RenderType.entityCutout(this.getTextureLocation(wallScroll));

            var verts = bufSource.getBuffer(layer);
            // Remember: CCW
            // body
            for (int ra = 1;ra<16;ra+=2) {
                float rx = (float) (Math.sin((0)/8.)*bodybottomsize);
                float ry = (float) (Math.cos((0)/8.)*bodybottomsize);
                vertex(mat, norm, packedLight, verts, rx, 0, ry, rx, ry, 0, 0, -1);
                rx = (float) (Math.sin((ra)/8.)*bodybottomsize);
                ry = (float) (Math.cos((ra)/8.)*bodybottomsize);
                vertex(mat, norm, packedLight, verts, rx, 0, ry, rx, ry, 0, 0, -1);
                rx = (float) (Math.sin((ra+1)/8.)*bodybottomsize);
                ry = (float) (Math.cos((ra+1)/8.)*bodybottomsize);
                vertex(mat, norm, packedLight, verts, rx, 0, ry, rx, ry, 0, 0, -1);
                rx = (float) (Math.sin((ra+2)/8.)*bodybottomsize);
                ry = (float) (Math.cos((ra+2)/8.)*bodybottomsize);
                vertex(mat, norm, packedLight, verts, rx, 0, ry, rx, ry, 0, 0, -1);
            }
            for (int ra = 0;ra<16;ra++) {
                float rx = (float) (Math.sin((ra)/8.)*bodytopsize);
                float ry = (float) (Math.cos((ra)/8.)*bodytopsize);
                vertex(mat, norm, packedLight, verts, rx, bodyheight, ry, ra/32, 0.25f, 0, 0, -1);
                rx = (float) (Math.sin((ra)/8.)*bodybottomsize);
                ry = (float) (Math.cos((ra)/8.)*bodybottomsize);
                vertex(mat, norm, packedLight, verts, rx, 0, ry, ra/32, 0.5f, 0, 0, -1);
                rx = (float) (Math.sin((ra+1)/8.)*bodybottomsize);
                ry = (float) (Math.cos((ra+1)/8.)*bodybottomsize);
                vertex(mat, norm, packedLight, verts, rx, 0, ry, (ra+1)/32, 0.5f, 0, 0, -1);
                rx = (float) (Math.sin((ra+1)/8.)*bodytopsize);
                ry = (float) (Math.cos((ra+1)/8.)*bodytopsize);
                vertex(mat, norm, packedLight, verts, rx, bodyheight, ry, (ra+1)/32, 0.25f, 0, 0, -1);
            }
        }

        ps.popPose();
        super.render(wallScroll, yaw, partialTicks, ps, bufSource, packedLight);
    }

    private static void vertex(Matrix4f mat, Matrix3f normal, int light, VertexConsumer verts, float x, float y,
                               float z, float u,
                               float v, float nx, float ny, float nz) {
        verts.vertex(mat, x, y, z)
                .color(0xffffffff)
                .uv(u, v).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light)
                .normal(normal, nx, ny, nz)
                .endVertex();
    }
}
