package geriosb.technicalsprunki.client.renderer.sprunki;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import geriosb.technicalsprunki.common.entity.sprunki.OrenSprunkiEntity;
import geriosb.technicalsprunki.common.entity.sprunki.SprunkiEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class SprunkiRenderUtils {
    public static int headsegsx = 16;
    public static int headsegsy = 8;
    public static int bodysegs = 16;
    public static void RenderBody(SprunkiEntity sprunki, float yaw, float partialTicks, PoseStack ps,
                                  MultiBufferSource bufSource, int packedLight, ResourceLocation texture,
                                  float bodyheight, float bodybottomsize, float bodytopsize, int color){
        var last = ps.last();
        var mat = last.pose();
        var norm = last.normal();
        RenderType layer = RenderType.entityCutout(texture);

        var verts = bufSource.getBuffer(layer);
        // Remember: CCW
        // body
        for (int ra = 1;ra<bodysegs;ra+=2) {
            float rx = 0f;
            float ry = bodybottomsize;
            float ru = 0.5f;
            float rv = 0.25f;
            vertex(mat, norm, packedLight, verts, -rx, 0, ry, ru, rv, 0, -1, 0, color);
            rx = (float) (Math.sin((ra*Math.PI)/(bodysegs/2f))*bodybottomsize);
            ry = (float) (Math.cos((ra*Math.PI)/(bodysegs/2f))*bodybottomsize);
            ru = (float) ((Math.sin((ra*Math.PI)/(bodysegs/2f))+1)/8)+0.5f;
            rv = (float) ((Math.cos((ra*Math.PI)/(bodysegs/2f))+1)/8)+0.25f;
            vertex(mat, norm, packedLight, verts, -rx, 0, ry, ru, rv, 0, -1, 0, color);
            rx = (float) (Math.sin(((ra+1)*Math.PI)/(bodysegs/2f))*bodybottomsize);
            ry = (float) (Math.cos(((ra+1)*Math.PI)/(bodysegs/2f))*bodybottomsize);
            ru = (float) ((Math.sin(((ra+1)*Math.PI)/(bodysegs/2f))+1)/8)+0.5f;
            rv = (float) ((Math.cos(((ra+1)*Math.PI)/(bodysegs/2f))+1)/8)+0.25f;
            vertex(mat, norm, packedLight, verts, -rx, 0, ry, ru, rv, 0, -1, 0, color);
            rx = (float) (Math.sin(((ra+2)*Math.PI)/(bodysegs/2f))*bodybottomsize);
            ry = (float) (Math.cos(((ra+2)*Math.PI)/(bodysegs/2f))*bodybottomsize);
            ru = (float) ((Math.sin(((ra+2)*Math.PI)/(bodysegs/2f))+1)/8)+0.5f;
            rv = (float) ((Math.cos(((ra+2)*Math.PI)/(bodysegs/2f))+1)/8)+0.25f;
            vertex(mat, norm, packedLight, verts, -rx, 0, ry, ru, rv, 0, -1, 0, color);
        }
        for (int ra = 0;ra<16;ra++) {
            float rx = (float) (Math.sin((ra*Math.PI)/(bodysegs/2f))*bodytopsize);
            float ry = (float) (Math.cos((ra*Math.PI)/(bodysegs/2f))*bodytopsize);
            vertex(mat, norm, packedLight, verts, rx, bodyheight, ry, ra/(bodysegs*2f), 0.25f, (float) (Math.sin(((ra+.5)*Math.PI)/(bodysegs/2f))), 0, (float) (Math.cos(((ra+.5)*Math.PI)/(bodysegs/2f))), color);
            rx = (float) (Math.sin((ra*Math.PI)/(bodysegs/2f))*bodybottomsize);
            ry = (float) (Math.cos((ra*Math.PI)/(bodysegs/2f))*bodybottomsize);
            vertex(mat, norm, packedLight, verts, rx, 0, ry, ra/(bodysegs*2f), 0.5f, (float) (Math.sin(((ra+.5)*Math.PI)/(bodysegs/2f))), 0, (float) (Math.cos(((ra+.5)*Math.PI)/(bodysegs/2f))), color);
            rx = (float) (Math.sin(((ra+1)*Math.PI)/(bodysegs/2f))*bodybottomsize);
            ry = (float) (Math.cos(((ra+1)*Math.PI)/(bodysegs/2f))*bodybottomsize);
            vertex(mat, norm, packedLight, verts, rx, 0, ry, (ra+1)/(bodysegs*2f), 0.5f, (float) (Math.sin(((ra+1.5)*Math.PI)/(bodysegs/2f))), 0, (float) (Math.cos(((ra+1.5)*Math.PI)/(bodysegs/2f))), color);
            rx = (float) (Math.sin(((ra+1)*Math.PI)/(bodysegs/2f))*bodytopsize);
            ry = (float) (Math.cos(((ra+1)*Math.PI)/(bodysegs/2f))*bodytopsize);
            vertex(mat, norm, packedLight, verts, rx, bodyheight, ry, (ra+1)/(bodysegs*2f), 0.25f, (float) (Math.sin(((ra+1.5)*Math.PI)/(bodysegs/2f))), 0, (float) (Math.cos(((ra+1.5)*Math.PI)/(bodysegs/2f))), color);
        }
    }
    public static void RenderHead(SprunkiEntity sprunki, float yaw, float partialTicks, PoseStack ps,
                                  MultiBufferSource bufSource, int packedLight, ResourceLocation texture,
                                  float headsize, int color){ // head's anchor is located at sphere's center, look out!
        var last = ps.last();
        var mat = last.pose();
        var norm = last.normal();
        RenderType layer = RenderType.entityCutout(texture);

        var verts = bufSource.getBuffer(layer);
        for (int ra = 0;ra<headsegsx;ra++) {
            for (int rb = 0;rb<headsegsy;rb++) {
                float rr = (float) (Math.sin((rb*Math.PI)/((float) headsegsy))*headsize/2);
                float rz = (float) (Math.cos((rb*Math.PI)/((float) headsegsy))*headsize/2);
                float rx = (float) (Math.sin((ra*Math.PI)/(headsegsx/2f))*rr);
                float ry = (float) (Math.cos((ra*Math.PI)/(headsegsx/2f))*rr);
                vertex(mat, norm, packedLight, verts, rx, rz, ry, ra/(float)headsegsx, rb/(headsegsy*4f), (float) (Math.sin(((ra)*Math.PI)/(headsegsx/2f))*rr), (float) (Math.cos(((rb)*Math.PI)/(float)headsegsy)), (float) (Math.cos(((ra)*Math.PI)/(headsegsx/2f))*rr), color);
                rr = (float) (Math.sin(((rb+1)*Math.PI)/((float) headsegsy))*headsize/2);
                rz = (float) (Math.cos(((rb+1)*Math.PI)/((float) headsegsy))*headsize/2);
                rx = (float) (Math.sin((ra*Math.PI)/(headsegsx/2f))*rr);
                ry = (float) (Math.cos((ra*Math.PI)/(headsegsx/2f))*rr);
                vertex(mat, norm, packedLight, verts, rx, rz, ry, ra/(float)headsegsx, (rb+1)/(headsegsy*4f), (float) (Math.sin(((ra)*Math.PI)/(headsegsx/2f))*rr), (float) (Math.cos(((rb+1)*Math.PI)/(float)headsegsy)), (float) (Math.cos(((ra)*Math.PI)/(headsegsx/2f))*rr), color);
                rr = (float) (Math.sin(((rb+1)*Math.PI)/((float) headsegsy))*headsize/2);
                rz = (float) (Math.cos(((rb+1)*Math.PI)/((float) headsegsy))*headsize/2);
                rx = (float) (Math.sin(((ra+1)*Math.PI)/(headsegsx/2f))*rr);
                ry = (float) (Math.cos(((ra+1)*Math.PI)/(headsegsx/2f))*rr);
                vertex(mat, norm, packedLight, verts, rx, rz, ry, (ra+1)/(float)headsegsx, (rb+1)/(headsegsy*4f), (float) (Math.sin(((ra+1)*Math.PI)/(headsegsx/2f))*rr), (float) (Math.cos(((rb+1)*Math.PI)/(float)headsegsy)), (float) (Math.cos(((ra+1)*Math.PI)/(headsegsx/2f))*rr), color);
                rr = (float) (Math.sin((rb*Math.PI)/((float) headsegsy))*headsize/2);
                rz = (float) (Math.cos((rb*Math.PI)/((float) headsegsy))*headsize/2);
                rx = (float) (Math.sin(((ra+1)*Math.PI)/(headsegsx/2f))*rr);
                ry = (float) (Math.cos(((ra+1)*Math.PI)/(headsegsx/2f))*rr);
                vertex(mat, norm, packedLight, verts, rx, rz, ry, (ra+1)/(float)headsegsx, rb/(headsegsy*4f), (float) (Math.sin(((ra+1)*Math.PI)/(headsegsx/2f))*rr), (float) (Math.cos(((rb)*Math.PI)/(float)headsegsy)), (float) (Math.cos(((ra+1)*Math.PI)/(headsegsx/2f))*rr), color);
            }
        }
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
