# Wayneedle 简体中文版

Wayneedle 是一款离线 Android GPS 速度表和行程记录器。它可以显示实时速度、记录行程统计，并把数据保存在手机上。本仓库提供独立安装的简体中文版，应用界面和行程通知直接显示简体中文。

**[下载中文版 APK](https://github.com/Heyebao/Wayneedle/releases/latest)** · **[添加到 Obtainium](https://apps.obtainium.imranr.dev/redirect?r=obtainium%3A%2F%2Fapp%2F%257B%2522id%2522%253A%2522app.wayneedle.debug%2522%252C%2522url%2522%253A%2522https%253A%252F%252Fgithub.com%252FHeyebao%252FWayneedle%2522%252C%2522author%2522%253A%2522Heyebao%2522%252C%2522name%2522%253A%2522Wayneedle%2520%25E4%25B8%25AD%25E6%2596%2587%25E7%2589%2588%2522%252C%2522additionalSettings%2522%253A%2522%257B%255C%2522verifyLatestTag%255C%2522%253Atrue%252C%255C%2522versionExtractionRegEx%255C%2522%253A%255C%2522%255B0-9%255D%252B%2524%255C%2522%252C%255C%2522versionDetection%255C%2522%253Atrue%252C%255C%2522useVersionCodeAsOSVersion%255C%2522%253Atrue%257D%2522%257D)** · [官方项目](https://github.com/riyadmondol2006/Wayneedle) · [英文文档](README.md)

## 下载与安装

1. 在 [Releases](https://github.com/Heyebao/Wayneedle/releases) 找到对应版本，在 **Assets** 下下载 `Wayneedle-版本号-zh-CN.apk`。安装包支持 Android 8.0 及以上版本。
2. 如需校验文件，下载同名的 `.apk.sha256`，将其中的 SHA-256 与下载文件的计算结果对比。在 Windows PowerShell 中，可运行 `Get-FileHash .\Wayneedle-1.0.2-zh-CN.apk -Algorithm SHA256`。
3. 在手机上打开 APK。按 Android 提示授权本次安装来源。首次使用时，按应用提示授予精确定位权限，以便通过 GPS 获取速度。

中文版的应用标识为 `app.wayneedle.debug`，可以与官方版同时安装。两个版本分别保存行程和设置。以后升级中文版时，可直接覆盖安装以保留行程和设置。

## 更新中文版

- **用 Obtainium 更新**。在安卓手机上点顶部的「添加到 Obtainium」并确认导入。链接已配置使用 Android 版本代码，从 GitHub 发布标签末尾读取对应版本。如果手动添加，来源填写 `https://github.com/Heyebao/Wayneedle`，开启「使用版本代码作为系统版本」和版本检测，版本提取正则表达式填 `[0-9]+$`。
- **手动更新**。打开应用的「设置 → 版本 → 查看中文版更新」，浏览器会打开[本仓库的发布页](https://github.com/Heyebao/Wayneedle/releases)。应用本身不会自动检查更新。

## 使用方法

| 功能 | 操作 |
| --- | --- |
| 查看实时速度 | 打开「速度表」。获得 GPS 定位后会显示当前速度，点击 GPS 指示可查看信号和定位精度。 |
| 记录行程 | 点击「开始」，结束时点击「结束」并确认保存。记录过程中可以暂停和继续。 |
| 查看统计和导出 | 在「历史记录」查看单次行程或累计统计，也可以将行程导出为 CSV。 |
| 设置超速提醒 | 在「设置」开启「超速提醒」，调整提醒速度，可选提示音。 |
| 使用抬头显示 | 在速度表打开「抬头显示（HUD）」，需要将速度投映到挡风玻璃时开启「挡风玻璃镜像」。 |

测速、记录和导出可以离线使用。进行中的行程可通过前台服务继续记录，Android 可能会根据定位、通知和省电设置限制后台运行。

应用内日期使用简体中文，数字格式以及 Android 的安装和授权弹窗沿用手机的系统设置。

## 构建与验证

本分支的首个版本基于官方 `v1.0.2`。简体中文文案位于 `app/src/debug/res/values`，对应 `debug` 构建。需要 JDK 17 和 Android SDK Platform 37.2。使用仓库自带的 Gradle Wrapper，在 Windows 上运行：

```powershell
.\gradlew.bat --no-daemon :app:assembleDebug
.\gradlew.bat --no-daemon --continue :app:testDebugUnitTest :app:lintDebug
```

生成的 APK 位于 `app/build/outputs/apk/debug/app-debug.apk`。这是调试构建，关于页会显示带 `-debug` 后缀的版本号，例如 `1.0.2-zh-cn.2-debug`。在 macOS 或 Linux 上使用 `./gradlew` 运行相同任务。

首版通过了 Kotlin 编译、APK 签名和 225 项中文资源检查。Windows 上有 5 项设置存储单测失败，Lint 有 2 项构建工具版本提示。这些结果均在未修改的官方 1.0.2 源码中复现。首版尚未进行真机安装与运行验证。

## 跟进官方更新

仓库的 `main` 用于跟进上游，中文版源码和发布记录在 `wayneedle-zh-cn` 分支。

1. 获取官方新版本的标签，将它合并到 `wayneedle-zh-cn`。
2. 对照 `app/src/main/res/values` 和 `app/src/debug/res/values`，补翻或修订界面与通知文案。核对格式占位符、数量形式和单位。
3. 构建前设置 `WAYNEEDLE_VERSION_NAME`，将 `WAYNEEDLE_VERSION_CODE` 提高到已发布版本之上。发布标签的最后一段必须等于版本代码，供 Obtainium 比对。例如 `v1.0.2-zh-cn.2` 对应版本代码 `2`。
4. 保持应用标识 `app.wayneedle.debug`，设置 `ANDROID_USER_HOME` 指向包含原发布签名 `debug.keystore` 的私有目录。用同一密钥签名，才能覆盖安装并保留中文版数据。
5. 核验新 APK 的签名和 SHA-256，然后将 APK 与校验文件上传到本仓库的 Releases，并将该版本标记为最新正式发布。签名密钥请单独私密备份。

## 许可证

Wayneedle 采用 [Apache License 2.0](LICENSE)。原作者与版权信息见 [NOTICE](NOTICE)。
