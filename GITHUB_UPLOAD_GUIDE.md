# GitHub 上传指导文档

本指南将帮助您将 YC Auto Fishing GUI 项目上传到 GitHub 公共仓库。

## 📋 准备工作清单

### ✅ 1. 检查项目文件

在开始之前，请确认：

- [x] `.gitignore` 文件已配置（已完善）
- [ ] README.md 中的作者信息已更新（第124行）
- [ ] 确认没有敏感信息（API keys、密码等）
- [ ] 确认许可证文件存在（LICENSE）

### ✅ 2. 更新 README.md

请编辑 `README.md` 文件，将第124行的占位符替换为您的信息：

```markdown
- **GUI 版本维护**: [你的名字/GitHub 用户名]
```

改为：

```markdown
- **GUI 版本维护**: 您的名字或 GitHub 用户名
```

### ✅ 3. 检查敏感信息

已检查项目，未发现明显的敏感信息（API keys、密码等）。但请再次确认：

- [ ] 代码中没有硬编码的 API keys
- [ ] 没有包含个人密码或令牌
- [ ] 配置文件不包含敏感数据

## 🚀 上传步骤

### 步骤 1: 安装 Git（如果尚未安装）

1. 访问 https://git-scm.com/download/win
2. 下载并安装 Git for Windows
3. 安装完成后，打开 PowerShell 或 Git Bash

### 步骤 2: 配置 Git（首次使用）

在 PowerShell 中运行以下命令（替换为您的信息）：

```bash
git config --global user.name "您的名字"
git config --global user.email "您的邮箱@example.com"
```

### 步骤 3: 初始化 Git 仓库

在项目根目录（`D:\YCAutoFishing-1.0.8-Gui`）运行：

```bash
# 初始化 Git 仓库
git init

# 添加所有文件到暂存区
git add .

# 创建首次提交
git commit -m "Initial commit: YC Auto Fishing GUI v1.0.6"
```

### 步骤 4: 在 GitHub 上创建仓库

1. 登录 GitHub（如果没有账号，请先注册：https://github.com）
2. 点击右上角的 "+" 号，选择 "New repository"
3. 填写仓库信息：
   - **Repository name**: `YCAutoFishing-GUI`（或您喜欢的名称）
   - **Description**: `一个用于 Minecraft 1.21 的自动钓鱼模组，支持 Fabric 和 NeoForge，带图形化配置界面`
   - **Visibility**: 选择 **Public**（公共仓库）
   - **不要**勾选 "Initialize this repository with a README"（因为您已经有了）
4. 点击 "Create repository"

### 步骤 5: 连接本地仓库到 GitHub

GitHub 创建仓库后，会显示一个页面，其中有仓库的 URL。复制该 URL（格式类似：`https://github.com/您的用户名/YCAutoFishing-GUI.git`）

然后在 PowerShell 中运行：

```bash
# 添加远程仓库（替换为您的实际 URL）
git remote add origin https://github.com/您的用户名/YCAutoFishing-GUI.git

# 将代码推送到 GitHub
git branch -M main
git push -u origin main
```

如果提示输入用户名和密码：
- 用户名：您的 GitHub 用户名
- 密码：使用 **Personal Access Token**（不是账户密码）

### 步骤 6: 创建 Personal Access Token（如果需要）

如果 GitHub 要求使用 Token：

1. 访问：https://github.com/settings/tokens
2. 点击 "Generate new token" → "Generate new token (classic)"
3. 填写信息：
   - **Note**: `YCAutoFishing Upload`
   - **Expiration**: 选择过期时间（建议 90 天或更长）
   - **Scopes**: 勾选 `repo`（完整仓库访问权限）
4. 点击 "Generate token"
5. **重要**：立即复制生成的 token（只显示一次！）
6. 在 Git 提示输入密码时，粘贴这个 token

## 📝 后续更新代码

当您修改代码后，使用以下命令更新 GitHub：

```bash
# 查看修改的文件
git status

# 添加所有修改的文件
git add .

# 提交更改（使用有意义的提交信息）
git commit -m "描述您的更改，例如：修复自动钓鱼功能"

# 推送到 GitHub
git push
```

## 🔍 验证上传

上传完成后，访问您的 GitHub 仓库页面，确认：

- [ ] 所有源代码文件都已上传
- [ ] README.md 正确显示
- [ ] LICENSE 文件存在
- [ ] `.gitignore` 正常工作（build/、run/ 等目录未上传）

## ⚠️ 常见问题

### 问题 1: 推送时提示 "remote: Support for password authentication was removed"

**解决方案**：使用 Personal Access Token 代替密码（见步骤 6）

### 问题 2: 文件太大无法上传

**解决方案**：检查 `.gitignore` 是否正确忽略了 `libs/` 和 `build/` 目录

### 问题 3: 想删除已上传的文件

```bash
# 从 Git 中删除文件（但保留本地文件）
git rm --cached 文件名

# 提交更改
git commit -m "Remove file"
git push
```

### 问题 4: 想回退到之前的版本

```bash
# 查看提交历史
git log

# 回退到指定提交（替换 COMMIT_ID）
git reset --hard COMMIT_ID
git push --force
```

## 📚 推荐阅读

- [Git 官方文档](https://git-scm.com/doc)
- [GitHub 帮助文档](https://docs.github.com)
- [Git 常用命令速查](https://education.github.com/git-cheat-sheet-education.pdf)

## 🎉 完成！

上传成功后，您的项目就可以被其他人看到了！记得：

1. 定期提交代码更新
2. 使用有意义的提交信息
3. 及时回复 Issues 和 Pull Requests
4. 保持 README.md 的更新

祝您使用愉快！

