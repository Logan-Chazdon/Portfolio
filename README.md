Just a small site hosting some of my work and accomplishments. Made with Jetpack Compose multiplatform.

Run Web version via Gradle
./gradlew :webApp:wasmJsRun

Run Desktop version via Gradle
./gradlew :desktopApp:run

Install Android application via Gradle
./gradlew :androidApp:installDebug

Setup Environment
To run applications built with Kotlin/Wasm in a browser, you need a browser supporting wasm garbage collection feature:

For Chrome and Chromium-based browsers (Edge, Brave etc.), it should just work since version 119.
For Firefox 120 it should just work.
For Firefox 119:
Open about:config in the browser.
Enable javascript.options.wasm_gc.
Refresh the page.