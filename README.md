# BayMcSuicide 插件

**BayMcSuicide** 是一款用于 `Minecraft Spigot/Paper` 服务器的插件，允许玩家通过命令自杀。此插件包括以下功能：

- 支持多种命令执行自杀操作：`/kill`，`/suicide`，`/die`，`/zisha`，`/514`
- 提供权限控制，允许管理员限制哪些玩家可以执行自杀命令
- 自杀时播放音效，并限制玩家每秒只能执行一次自杀命令

## 功能概述

- **多种自杀命令**：支持多种命令别名，玩家可以使用 `/kill`，`/suicide`，`/die`，`/zisha`，`/514` 等命令来执行自杀操作。
- **音效播放**：在玩家自杀时，会先播放音效，音效播放完后玩家才会死亡。
- **频率限制**：玩家每秒只能执行一次自杀命令，防止滥用。
- **权限控制**：服务器管理员可以通过权限插件控制哪些玩家可以使用自杀命令。

## 插件命令

| 命令         | 描述           | 权限           |
|--------------|----------------|----------------|
| `/kill`      | 玩家自杀       | `baymc.suicide`|
| `/suicide`   | 玩家自杀       | `baymc.suicide`|
| `/die`       | 玩家自杀       | `baymc.suicide`|
| `/zisha`     | 玩家自杀       | `baymc.suicide`|

## 权限

- **`baymc.suicide`**: 允许玩家使用自杀命令。默认情况下，所有玩家都有这个权限。可以通过权限管理插件（如 LuckPerms）进行控制。

## 安装教程

### 环境要求

- **Minecraft** 版本：1.13 或更高版本
- **Java** 版本：17 或更高版本
- **Spigot / Paper** 服务端

### 安装步骤

1. **下载插件**：前往 [Releases 页面](https://github.com/Konsheng/BayMcSuicide/releases) 下载最新版本的 `BayMcSuicide.jar` 文件。

2. **将插件放入插件文件夹**：将下载的 `BayMcSuicide.jar` 文件放入 Minecraft 服务器的 `plugins` 文件夹中。

3. **启动服务器**：启动或重启 Minecraft 服务器，插件会自动加载。

4. **验证插件是否加载**：使用 `/plugins` 命令查看已安装插件列表，确保 `BayMcSuicide` 插件已成功加载。

### 权限设置

如果你想限制某些玩家或群组使用自杀命令，可以使用权限管理插件（如 LuckPerms）来控制权限
