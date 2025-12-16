package top.terry_mc.yc_auto_fishing.fabric;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import top.terry_mc.yc_auto_fishing.YCAutoFishing;
import top.terry_mc.yc_auto_fishing.YCAutoFishingConfig;

/**
 * 简易客户端配置界面：可以在游戏内实时调整部分设置并立即生效。
 */
public class YCAutoFishingConfigScreen extends Screen {
    private final Screen parent;

    protected YCAutoFishingConfigScreen(Screen parent) {
        super(Component.translatable("screen.yc_auto_fishing.config"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        YCAutoFishingConfig cfg = YCAutoFishingConfig.get();

        int centerX = this.width / 2;
        int y = this.height / 4;
        int buttonWidth = 200;
        int buttonHeight = 20;
        int gap = 24;

        // 自动钓鱼默认开启
        this.addRenderableWidget(Button.builder(
                        getAutoFishingText(cfg),
                        (button) -> {
                            cfg.autoFishingEnabledByDefault = !cfg.autoFishingEnabledByDefault;
                            button.setMessage(getAutoFishingText(cfg));
                            // 立即应用到当前状态
                            YCAutoFishing.autoFishingEnabled = cfg.autoFishingEnabledByDefault;
                            YCAutoFishingConfig.save();
                        })
                .bounds(centerX - buttonWidth / 2, y, buttonWidth, buttonHeight)
                .build());
        y += gap;

        // HUD 开关（目前预留，后续可用于显示状态 HUD）
        this.addRenderableWidget(Button.builder(
                        getHudText(cfg),
                        (button) -> {
                            cfg.hudEnabled = !cfg.hudEnabled;
                            button.setMessage(getHudText(cfg));
                            YCAutoFishingConfig.save();
                        })
                .bounds(centerX - buttonWidth / 2, y, buttonWidth, buttonHeight)
                .build());
        y += gap;

        // 停靠距离调整（简单用两个按钮 +/-）
        this.addRenderableWidget(Button.builder(
                        getStopDistanceText(cfg),
                        (button) -> {
                            cfg.stopDistance = Math.max(0.5, Math.min(6.0, cfg.stopDistance + 0.5));
                            button.setMessage(getStopDistanceText(cfg));
                            YCAutoFishingConfig.save();
                        })
                .bounds(centerX - buttonWidth / 2, y, buttonWidth / 2 - 4, buttonHeight)
                .build());

        this.addRenderableWidget(Button.builder(
                        Component.literal("-"),
                        (button) -> {
                            cfg.stopDistance = Math.max(0.5, Math.min(6.0, cfg.stopDistance - 0.5));
                            // 重新查找并更新文本按钮
                            this.rebuildWidgets();
                            YCAutoFishingConfig.save();
                        })
                .bounds(centerX + 4, y, buttonWidth / 2 - 4, buttonHeight)
                .build());
        y += gap;

        // 热加载（从文件重新读取配置）
        this.addRenderableWidget(Button.builder(
                        Component.translatable("screen.yc_auto_fishing.reload"),
                        (button) -> {
                            YCAutoFishingConfig.load();
                            this.rebuildWidgets();
                        })
                .bounds(centerX - buttonWidth / 2, y, buttonWidth, buttonHeight)
                .build());
        y += gap * 2;

        // 返回
        this.addRenderableWidget(Button.builder(
                        Component.translatable("gui.done"),
                        (button) -> Minecraft.getInstance().setScreen(parent))
                .bounds(centerX - buttonWidth / 2, y, buttonWidth, buttonHeight)
                .build());
    }

    private Component getAutoFishingText(YCAutoFishingConfig cfg) {
        return Component.translatable(
                "screen.yc_auto_fishing.auto_fishing_default",
                Component.translatable(cfg.autoFishingEnabledByDefault ? "options.on" : "options.off"));
    }

    private Component getHudText(YCAutoFishingConfig cfg) {
        return Component.translatable(
                "screen.yc_auto_fishing.hud_enabled",
                Component.translatable(cfg.hudEnabled ? "options.on" : "options.off"));
    }

    private Component getStopDistanceText(YCAutoFishingConfig cfg) {
        return Component.translatable(
                "screen.yc_auto_fishing.stop_distance",
                String.format("%.1f", cfg.stopDistance));
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        guiGraphics.drawCenteredString(this.font, this.title, this.width / 2, 20, 0xFFFFFF);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }
}


