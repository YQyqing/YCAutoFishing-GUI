package top.terry_mc.yc_auto_fishing;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * 简单的 JSON 配置类，支持读写配置文件。
 *
 * 注意：目前主要在 Fabric 端使用，由 Fabric 端传入游戏目录路径进行初始化。
 */
public final class YCAutoFishingConfig {
    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private static Path CONFIG_PATH;
    private static YCAutoFishingConfig INSTANCE = new YCAutoFishingConfig();

    /** 是否在进入游戏时默认开启自动钓鱼（按键仍可手动切换） */
    public boolean autoFishingEnabledByDefault = false;
    /** 文本牌跟随停止距离（玩家与鱼群 TextDisplay 的距离） */
    public double stopDistance = 2.0;
    /** 是否显示 HUD（后续可扩展） */
    public boolean hudEnabled = true;
    /** 判定“卡住”重新寻路的 Tick 阈值 */
    public int stuckTickThreshold = 200;

    public static void init(Path gameDir) {
        CONFIG_PATH = gameDir.resolve("config").resolve("yc_auto_fishing.json");
        load();
    }

    public static YCAutoFishingConfig get() {
        return INSTANCE;
    }

    public static void load() {
        if (CONFIG_PATH == null) return;
        try {
            Files.createDirectories(CONFIG_PATH.getParent());
            if (Files.exists(CONFIG_PATH)) {
                try (Reader reader = Files.newBufferedReader(CONFIG_PATH)) {
                    YCAutoFishingConfig loaded = GSON.fromJson(reader, YCAutoFishingConfig.class);
                    if (loaded != null) {
                        INSTANCE = loaded;
                    }
                }
            } else {
                save(); // 写出默认配置
            }
        } catch (IOException e) {
            YCAutoFishing.LOGGER.error("Failed to load YC Auto Fishing config", e);
        }
    }

    public static void save() {
        if (CONFIG_PATH == null) return;
        try {
            Files.createDirectories(CONFIG_PATH.getParent());
            try (Writer writer = Files.newBufferedWriter(CONFIG_PATH)) {
                GSON.toJson(INSTANCE, writer);
            }
        } catch (IOException e) {
            YCAutoFishing.LOGGER.error("Failed to save YC Auto Fishing config", e);
        }
    }
}



