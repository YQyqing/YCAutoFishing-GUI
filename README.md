# YC Auto Fishing - GUI Edition

一个用于 Minecraft 1.21 的自动钓鱼模组，支持 Fabric 和 NeoForge。本版本基于原作者的 YC Auto Fishing，新增了图形化配置界面和增强的用户体验。

## ✨ 功能特性

- 🎣 **自动钓鱼**：自动识别鱼群并导航到最佳位置进行钓鱼
- ⚙️ **图形化配置界面**：按 `O` 键打开配置界面，实时调整参数
- 📊 **实时状态显示**：右下角弹出式提示，绿色表示开启，红色表示关闭
- 🌐 **多语言支持**：支持中文和英文界面
- 💾 **配置文件支持**：支持 JSON 配置文件，可手动编辑或热加载
- 🎮 **快捷键控制**：按 `Y` 键快速切换自动钓鱼开关

## 📋 前置要求

- **Minecraft**: 1.21
- **Fabric Loader**: 0.16.14+ 或 **NeoForge**: 21.0+
- **Java**: 21+
- **Baritone**: 1.11.2（已包含在模组中）

## 📥 安装

1. 下载对应版本的 jar 文件
   - Fabric 版本：`yc_auto_fishing-fabric-<version>.jar`
   - NeoForge 版本：`yc_auto_fishing-neoforge-<version>.jar`
2. 将 jar 文件放入 Minecraft 的 `mods` 目录
3. 启动游戏

## 🎮 使用方法

### 基本操作

1. **切换自动钓鱼**：按 `Y` 键开启/关闭自动钓鱼功能
2. **打开配置界面**：按 `O` 键打开配置界面
3. **配置文件位置**：`config/yc_auto_fishing.json`

### 配置说明

配置文件位于 `config/yc_auto_fishing.json`，包含以下选项：

```json
{
  "autoFishingEnabledByDefault": false,  // 进入游戏时是否默认开启
  "stopDistance": 2.0,                   // 文本牌跟随停止距离
  "hudEnabled": true,                     // 是否显示 HUD 提示
  "stuckTickThreshold": 200               // 卡住检测阈值（tick）
}
```

### 快捷键

- `Y` - 切换自动钓鱼开关
- `O` - 打开配置界面

## 🔧 构建

### 环境要求

- JDK 21+
- Gradle（已包含 wrapper）

### 构建命令

```bash
# Windows
gradlew.bat :fabric:build        # 构建 Fabric 版本
gradlew.bat :neoforge:build      # 构建 NeoForge 版本

# Linux/Mac
./gradlew :fabric:build
./gradlew :neoforge:build
```

### 运行客户端测试

```bash
# Windows
gradlew.bat :fabric:runClient

# Linux/Mac
./gradlew :fabric:runClient
```

### 清理构建

```bash
gradlew.bat clean
```

## 📝 更新日志

### v1.0.6-GUI (当前版本)

**新增功能：**
- ✨ 添加图形化配置界面（按 `O` 键打开）
- ✨ 添加右下角弹出式状态提示（绿色/红色）
- ✨ 添加配置文件支持（JSON 格式）
- ✨ 添加配置热加载功能
- ✨ 添加多语言支持（中文/英文）
- 🐛 修复进入服务器时的崩溃问题（NPE）
- 🐛 修复自动钓鱼无法正常工作的问题
- 🔧 优化代码结构，提高稳定性

**配置项：**
- 自动钓鱼默认开关
- 文本牌跟随停止距离
- HUD 显示开关
- 卡住检测阈值

## 🛠️ 技术细节

- **架构**: Architectury API（支持 Fabric 和 NeoForge）
- **路径查找**: Baritone API
- **配置格式**: JSON（使用 Gson）
- **UI 框架**: Minecraft 原生 GUI 系统

## 📄 许可证

LGPL-3.0

## 👤 作者

- **原作者**: Terry_MC
- **GUI 版本维护**: [YQyqing]

## 🔗 相关链接

- **原作者仓库**: [Terry-MC123/YCAutoFishing](http://github.com/Terry-MC123/YCAutoFishing)
- **主页**: https://terry-mc.top/
- **问题反馈**: [GitHub Issues](http://github.com/Terry-MC123/YCAutoFishing/issues)

## 🤝 贡献

欢迎提交 Issue 和 Pull Request！

## ⚠️ 注意事项

1. 本模组仅适用于特定服务器（YC 服务器）
2. 需要安装 Baritone 作为前置（已包含在模组中）
3. 建议在单人或允许使用辅助工具的服务器上使用
4. 使用前请确认服务器规则允许使用自动钓鱼功能

## 📸 截图

（可以添加配置界面和 HUD 的截图）

---

**基于原版 YC Auto Fishing 开发，添加了 GUI 和增强功能。**
