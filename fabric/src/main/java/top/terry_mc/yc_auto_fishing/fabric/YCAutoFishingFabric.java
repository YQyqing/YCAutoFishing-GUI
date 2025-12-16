package top.terry_mc.yc_auto_fishing.fabric;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;
import top.terry_mc.yc_auto_fishing.TextDisplayNavigator;
import net.fabricmc.loader.api.FabricLoader;
import top.terry_mc.yc_auto_fishing.YCAutoFishing;
import top.terry_mc.yc_auto_fishing.YCAutoFishingConfig;

public final class YCAutoFishingFabric implements ModInitializer {
    private static KeyMapping toggleAutoFishing;
    private static KeyMapping openConfig;
    private static boolean isToggleAutoFishingDown=false;
    private static boolean isOpenConfigDown=false;
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        // Run our common setup.
        // 初始化配置（使用游戏目录下的 config/yc_auto_fishing.json）
        YCAutoFishingConfig.init(FabricLoader.getInstance().getGameDir());

        YCAutoFishing.init();

        toggleAutoFishing = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                "key.yc_auto_fishing.toggle_auto_fishing",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_Y,
                "category.yc_auto_fishing"
        ));

        openConfig = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                "key.yc_auto_fishing.open_config",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_O,
                "category.yc_auto_fishing"
        ));

        HudRenderCallback.EVENT.register(new YCAutoFishingHud());
        ClientTickEvents.END_CLIENT_TICK.register((minecraft -> {
            YCAutoFishing.onTick();
            if(toggleAutoFishing.isDown()) {
                isToggleAutoFishingDown = true;
            }
            else if(isToggleAutoFishingDown) {
                isToggleAutoFishingDown = false;
                YCAutoFishing.autoFishingEnabled = !YCAutoFishing.autoFishingEnabled;
                if(YCAutoFishing.autoFishingEnabled) {
                    TextDisplayNavigator.navigateToLongestTextDisplay();
                }
                else if (TextDisplayNavigator.followProcess!=null) {
                    TextDisplayNavigator.followProcess.cancel();
                }
                YCAutoFishingHud.showToggleToast(YCAutoFishing.autoFishingEnabled);
            }

            if(openConfig.isDown()) {
                isOpenConfigDown = true;
            } else if (isOpenConfigDown) {
                isOpenConfigDown = false;
                // 打开配置界面
                minecraft.setScreen(new YCAutoFishingConfigScreen(minecraft.screen));
            }
        }));
        ClientReceiveMessageEvents.GAME.register((component, overlay) -> {
            YCAutoFishing.onChatMessage(component);
        });

    }
}
