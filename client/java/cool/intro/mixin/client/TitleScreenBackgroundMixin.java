package cool.intro.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenBackgroundMixin {

    @Inject(method = "renderBackground", at = @At("HEAD"), cancellable = true)
    private void renderCustomBackground(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();

        Identifier CUSTOM_BACKGROUND = Identifier.of("chudcraftintro", "textures/frames/chudcraft_00477.png");

        int width = client.getWindow().getScaledWidth();
        int height = client.getWindow().getScaledHeight();

        context.drawTexture(
                RenderLayer::getGuiOpaqueTexturedBackground,
                CUSTOM_BACKGROUND,
                0, 0,
                0.0F, 0.0F,
                width, height,
                width, height
        );



        ci.cancel();
    }
}
