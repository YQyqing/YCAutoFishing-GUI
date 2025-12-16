package top.terry_mc.yc_auto_fishing.fabric;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.Mth;
import top.terry_mc.yc_auto_fishing.YCAutoFishingConfig;

/**
 * 在右下角显示开关提示的滑动 HUD，中文提示，绿/红区分开关。
 */
public final class YCAutoFishingHud implements HudRenderCallback {
    private static final int TOAST_WIDTH = 150;
    private static final int TOAST_HEIGHT = 30;
    private static final int PADDING = 8;
    private static final int SLIDE_TICKS = 18; // 减缓进出速度
    private static final int SHOW_TICKS = 70;

    private static int ticksLeft = 0;
    private static int totalTicks = 0;
    private static String currentText = "";
    private static int currentColor = 0xFF00FF00; // ARGB
    private static boolean currentEnabled = false;

    public static void showToggleToast(boolean enabled) {
        currentText = enabled ? "自动钓鱼：已开启" : "自动钓鱼：已关闭";
        currentColor = enabled ? 0xFF3ABF3A : 0xFFF05D5D;
        currentEnabled = enabled;
        totalTicks = SLIDE_TICKS * 2 + SHOW_TICKS;
        ticksLeft = totalTicks;
    }

    @Override
    public void onHudRender(GuiGraphics graphics, DeltaTracker deltaTracker) {
        if (!YCAutoFishingConfig.get().hudEnabled) return;
        if (ticksLeft <= 0) return;

        int screenWidth = graphics.guiWidth();
        int screenHeight = graphics.guiHeight();

        float tickDelta = deltaTracker.getGameTimeDeltaPartialTick(false);
        float t = totalTicks - ticksLeft + tickDelta;
        float slideIn = Mth.clamp(t / SLIDE_TICKS, 0.0F, 1.0F);
        float slideOut = Mth.clamp((totalTicks - t) / SLIDE_TICKS, 0.0F, 1.0F);
        float slide = Math.min(slideIn, slideOut); // 进入与离开取小值，形成滑动

        int x = screenWidth - TOAST_WIDTH - PADDING + (int) ((1 - slide) * (TOAST_WIDTH + PADDING));
        int y = screenHeight - TOAST_HEIGHT - PADDING;

        drawToast(graphics, x, y, currentColor, currentText);

        ticksLeft--;
    }

    private void drawToast(GuiGraphics g, int x, int y, int color, String text) {
        // 背景半透明黑
        int bgColor = 0xAA000000;
        fillRect(g, x, y, x + TOAST_WIDTH, y + TOAST_HEIGHT, bgColor);

        // 左侧色条
        fillRect(g, x, y, x + 6, y + TOAST_HEIGHT, color);

        // 文本
        g.drawString(Minecraft.getInstance().font, text, x + 12, y + 10, 0xFFFFFF, false);
    }

    private void fillRect(GuiGraphics g, int x1, int y1, int x2, int y2, int color) {
        g.fill(x1, y1, x2, y2, color);
    }

}

