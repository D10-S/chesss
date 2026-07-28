# Chess

A low-poly 2D Android chess game for two players sharing one device. The app is fully offline: it has no network permissions and keeps all match state in memory.

## Gameplay

- Tap one of your pieces to select it, then tap a destination square.
- Turns alternate between White and Black, so players can pass the same Android device back and forth.
- Standard piece movement is enforced for pawns, knights, bishops, rooks, queens, and kings.
- Pawns promote to queens automatically when they reach the final rank.
- Capturing the king ends the local match; tap **New same-device match** to reset.

## Downloadable APK

Every push and pull request builds a debug APK in GitHub Actions. To download it:

1. Open the repository on GitHub.
2. Go to **Actions**.
3. Open the latest **Build downloadable Android APK** workflow run.
4. Download the **chess-debug-apk** artifact.
5. Unzip it and install `chess-debug.apk` on your Android device.

Android may ask you to allow installs from unknown sources because this is a debug APK.

## Build locally

Open the project in Android Studio, or install Gradle 8.14.4 and run:

```bash
gradle :app:assembleDebug
```

The local APK path is `app/build/outputs/apk/debug/app-debug.apk`.

## If GitHub Actions says `gradle: command not found`

The APK workflow installs Gradle with `gradle/actions/setup-gradle@v4` before running `gradle :app:assembleDebug`, so rerun the latest workflow after pulling this fix. The task name is `assembleDebug` with an **e**, not `assemblyDebug`.
