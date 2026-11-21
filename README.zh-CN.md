# Wayneedle 简体中文版

Wayneedle 是一款离线 Android GPS 速度表和行程记录器。它可以显示实时速度、记录行程统计，并把数据保存在手机上。这个 fork 提供独立安装的简体中文版，应用界面和行程通知直接显示简体中文。

**[下载中文版 APK](https://github.com/Heyebao/Wayneedle/releases/latest)** · [官方项目](https://github.com/riyadmondol2006/Wayneedle) · [English documentation](README.md)

## 下载与安装

1. 在 [Releases](https://github.com/Heyebao/Wayneedle/releases) 找到对应版本，在 **Assets** 下下载 `Wayneedle-版本号-zh-CN.apk`。安装包支持 Android 8.0 及以上版本。
2. 如需校验文件，下载同名的 `.apk.sha256`，将其中的 SHA-256 与下载文件的计算结果对比。在 Windows PowerShell 中，可运行 `Get-FileHash .\Wayneedle-1.0.2-zh-CN.apk -Algorithm SHA256`。
3. 在手机上打开 APK。按 Android 提示授权本次安装来源。首次使用时，按应用提示授予精确定位权限，以便通过 GPS 获取速度。

中文版的应用标识为 `app.wayneedle.debug`，可以与官方版同时安装。两个版本分别保存行程和设置。以后升级中文版时，用同一签名发布的新 APK 覆盖安装即可保留数据。Android 的安装和授权弹窗使用手机的系统语言。

## 使用方法

| 功能 | 操作 |
| --- | --- |
| 查看实时速度 | 打开「速度表」。获得 GPS 定位后会显示当前速度，点击 GPS 指示可查看信号和定位精度。 |
| 记录行程 | 点击「开始」，结束时点击「结束」并确认保存。记录过程中可以暂停和继续。 |
| 查看统计和导出 | 在「历史记录」查看单次行程或累计统计，也可以将行程导出为 CSV。 |
| 设置超速提醒 | 在「设置」开启「超速提醒」，调整提醒阈值，可选提示音。 |
| 使用抬头显示 | 在速度表打开「抬头显示（HUD）」，需要风挡投映时开启「风挡镜像」。 |

测速、记录和导出可以离线使用。进行中的行程可通过前台服务继续记录，Android 可能会根据定位、通知和省电设置限制后台运行。

## 构建与验证

本分支的首个版本基于官方 `v1.0.2`。简体中文文案位于 `app/src/debug/res/values`，对应 `debug` 构建。需要 JDK 17 和 Android SDK Platform 37.2。使用仓库自带的 Gradle Wrapper，在 Windows 上运行：

```powershell
.\gradlew.bat --no-daemon :app:assembleDebug
.\gradlew.bat --no-daemon --continue :app:testDebugUnitTest :app:lintDebug
```

生成的 APK 位于 `app/build/outputs/apk/debug/app-debug.apk`。在 macOS 或 Linux 上使用 `./gradlew` 运行相同任务。

首版通过了 Kotlin 编译、APK 签名和 225 项中文资源检查。Windows 上有 5 项设置存储单测失败，Lint 有 2 项构建工具版本提示。这些结果均在未修改的官方 1.0.2 源码中复现。首版尚未进行真机安装与运行验证。

## 跟进官方更新

仓库的 `main` 用于跟进上游，中文版源码和发布记录在 `wayneedle-zh-cn` 分支。

1. 获取官方新版本的标签，将它合并到 `wayneedle-zh-cn`。
2. 对照 `app/src/main/res/values` 和 `app/src/debug/res/values`，补翻或修订界面与通知文案。核对格式占位符、数量形式和单位。
3. 构建前设置 `WAYNEEDLE_VERSION_NAME` 为新的显示版本，并将 `WAYNEEDLE_VERSION_CODE` 提高到已发布版本之上。首版的版本代码为 `1`。
4. 保持应用标识 `app.wayneedle.debug`，设置 `ANDROID_USER_HOME` 指向包含原发布签名 `debug.keystore` 的私有目录。用同一密钥签名，才能覆盖安装并保留中文版数据。
5. 核验新 APK 的签名和 SHA-256，然后将 APK 与校验文件上传到这个 fork 的 Releases。签名密钥请单独私密备份。

## 许可证

Wayneedle 采用 [Apache License 2.0](LICENSE)。原作者与版权信息见 [NOTICE](NOTICE)。
