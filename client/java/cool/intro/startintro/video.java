package cool.intro.startintro;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.text.Text;
import net.minecraft.client.MinecraftClient;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.util.Identifier;

import static cool.intro.ChudcraftIntroClient.INTRO_MUSIC_EVENT;
import static cool.intro.ChudcraftIntroClient.INTRO_MUSIC_ID;

public class video extends Screen {

    private static final int totalFrames = 477;
    private int currentFrame = 1;
    private long lastFrameUpdateTime;
    private static final long FRAME_DURATION_MS = 1000 / 28;

    public video() {
        super(Text.literal("Video Screen"));

    }

    private boolean hasStartedMusic = false;
    private net.minecraft.client.sound.PositionedSoundInstance currentMusic;

    @Override
    public void render(DrawContext drawContext, int mouseX, int mouseY, float delta) {

        MinecraftClient mc = MinecraftClient.getInstance();
        int screenWidth = mc.getWindow().getScaledWidth();
        int screenHeight = mc.getWindow().getScaledHeight();

        String frameNumber = String.format("%05d", currentFrame);
        Identifier frame = Identifier.of("chudcraftintro", "textures/frames/chudcraft_" + frameNumber + ".png");

        if (!hasStartedMusic) {
            hasStartedMusic = true;

            currentMusic = net.minecraft.client.sound.PositionedSoundInstance.master(INTRO_MUSIC_EVENT, 1.0F);
            mc.getSoundManager().play(currentMusic);
        }

        drawContext.drawTexture(RenderLayer::getGuiTexturedOverlay, frame, 0, 0, 0.0F, 0.0F, screenWidth, screenHeight, screenWidth, screenHeight);

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastFrameUpdateTime >= FRAME_DURATION_MS) {
            lastFrameUpdateTime = currentTime;
            if (currentFrame < totalFrames) {
                currentFrame++;
            } else {
                mc.setScreen(new net.minecraft.client.gui.screen.TitleScreen());
            }
        }
    }

    @Override
    public boolean shouldCloseOnEsc() {
        MinecraftClient.getInstance().getSoundManager().stop(currentMusic);
        return true;
    }
}