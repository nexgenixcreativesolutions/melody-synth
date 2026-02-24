// ═══════════════════════════════════════════════════════════
//  MainActivity.java — Melody Synth Go
//  Android WebView + AdMob Rewarded Ads Integration
// ═══════════════════════════════════════════════════════════

package com.melodysynthgo;

import android.os.Bundle;
import android.webkit.*;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.ads.*;
import com.google.android.gms.ads.rewarded.*;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private RewardedAd rewardedAd;

    // ── Replace with your real AdMob IDs ──
    // Test IDs (safe to use during development):
    private static final String REWARDED_AD_UNIT = "ca-app-pub-3940256099942544/5224354917";
    private static final String BANNER_AD_UNIT   = "ca-app-pub-3940256099942544/6300978111";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Initialize AdMob
        MobileAds.initialize(this, initializationStatus -> {});

        // 2. Setup WebView
        webView = findViewById(R.id.webView);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setMediaPlaybackRequiresUserGesture(false);
        settings.setAllowFileAccessFromFileURLs(true);

        // 3. Inject JavaScript Bridge
        webView.addJavascriptInterface(new AdMobBridge(), "Android");

        // 4. Load your app (local asset or remote URL)
        webView.loadUrl("file:///android_asset/index.html");
        // OR for remote:
        // webView.loadUrl("https://melody-synth-go.base44.app");

        // 5. Pre-load rewarded ad
        loadRewardedAd();
    }

    // ── Load (pre-cache) a Rewarded Ad ──────────────────────
    private void loadRewardedAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(this, REWARDED_AD_UNIT, adRequest,
            new RewardedAdLoadCallback() {
                @Override
                public void onAdLoaded(RewardedAd ad) {
                    rewardedAd = ad;
                }
                @Override
                public void onAdFailedToLoad(LoadAdError error) {
                    rewardedAd = null;
                    // Reload after failure
                    loadRewardedAd();
                }
            });
    }

    // ── JavaScript Bridge ────────────────────────────────────
    private class AdMobBridge {

        // Called from JS: Android.showRewardedAd()
        @JavascriptInterface
        public void showRewardedAd() {
            runOnUiThread(() -> {
                if (rewardedAd != null) {
                    rewardedAd.show(MainActivity.this, rewardItem -> {
                        // User earned the reward — notify the web app
                        webView.post(() ->
                            webView.loadUrl("javascript:window.onAdRewarded()")
                        );
                        // Pre-load next ad
                        rewardedAd = null;
                        loadRewardedAd();
                    });
                } else {
                    // Ad not ready — fall back to simulated ad in JS
                    webView.post(() ->
                        webView.loadUrl("javascript:simulateRewardedAd()")
                    );
                    loadRewardedAd();
                }
            });
        }
    }
}

// ═══════════════════════════════════════════════════════════
//  res/layout/activity_main.xml
// ═══════════════════════════════════════════════════════════
/*
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <WebView
        android:id="@+id/webView"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"/>

    <!-- AdMob Banner at the bottom -->
    <com.google.android.gms.ads.AdView
        xmlns:ads="http://schemas.android.com/apk/res-auto"
        android:id="@/id/adView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_gravity="bottom|center_horizontal"
        ads:adSize="BANNER"
        ads:adUnitId="ca-app-pub-3940256099942544/6300978111"/>

</LinearLayout>
*/

// ═══════════════════════════════════════════════════════════
//  AndroidManifest.xml additions
// ═══════════════════════════════════════════════════════════
/*
<manifest ...>
    <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.RECORD_AUDIO"/>

    <application ...>

        <!-- AdMob App ID — replace with your real one -->
        <meta-data
            android:name="com.google.android.gms.ads.APPLICATION_ID"
            android:value="ca-app-pub-2619727838493895~XXXXXXXXXX"/>

        <activity android:name=".MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN"/>
                <category android:name="android.intent.category.LAUNCHER"/>
            </intent-filter>
        </activity>

    </application>
</manifest>
*/

// ═══════════════════════════════════════════════════════════
//  build.gradle (app level) — dependencies to add
// ═══════════════════════════════════════════════════════════
/*
dependencies {
    implementation 'com.google.android.gms:play-services-ads:23.0.0'
}
*/
