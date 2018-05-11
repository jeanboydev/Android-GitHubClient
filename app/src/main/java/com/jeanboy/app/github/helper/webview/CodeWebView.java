

package com.jeanboy.app.github.helper.webview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jeanboy.app.github.R;
import com.jeanboy.arch.base.helper.AppHelper;


public class CodeWebView extends WebView {

    private ContentChangedListener contentChangedListener;
    private int backgroundColor;

    public interface ContentChangedListener {
        void onContentChanged(int progress);

        void onScrollChanged(boolean reachedTop, int scroll);
    }

    public CodeWebView(Context context) {
        this(context,null);
    }

    public CodeWebView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public CodeWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @ColorInt
    private int getWindowBackground(Context context, int attr) {
        Resources.Theme theme = context.getTheme();
        TypedArray typedArray = theme.obtainStyledAttributes(new int[]{attr});
        final int color = typedArray.getColor(0, Color.LTGRAY);
        typedArray.recycle();
        return color;
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray tp = getContext().obtainStyledAttributes(attrs, R.styleable.CodeWebView);
            try {
                backgroundColor = tp.getColor(R.styleable.CodeWebView_webview_background,
                        getWindowBackground(getContext(), android.R.attr.windowBackground));
                setBackgroundColor(backgroundColor);
            } finally {
                tp.recycle();
            }
        }

        setWebChromeClient(new ChromeClient());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            setWebViewClient(new WebClientN());
        } else {
            setWebViewClient(new WebClient());
        }
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAppCachePath(getContext().getCacheDir().getPath());
        settings.setAppCacheEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setDefaultTextEncodingName("utf-8");
        boolean isLoadImageEnable = true;// FIXME: 2018/5/11 是否加载图片
        settings.setLoadsImagesAutomatically(isLoadImageEnable);
        settings.setBlockNetworkImage(!isLoadImageEnable);
        setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                HitTestResult result = getHitTestResult();
                if (hitLinkResult(result) && !TextUtils.isEmpty(result.getExtra())) {
                    AppHelper.copyToClipboard(getContext(), result.getExtra(), null);
                    return true;
                }
                return false;
            }
        });
    }

    public void setContentChangedListener(ContentChangedListener contentChangedListener) {
        this.contentChangedListener = contentChangedListener;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void setCodeSource(@NonNull String source, boolean wrap) {
        setCodeSource(source, wrap, null);
    }

    public void loadImage(@NonNull String url) {
        WebSettings settings = getSettings();
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        String html = HtmlHelper.generateImageHtml(url, getCodeBackgroundColor());
        loadData(html, "text/html", null);
    }

    public void setHtmlSource(@NonNull String htmlSource) {
        WebSettings settings = getSettings();
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        String html = HtmlHelper.generateHtmlSourceHtml(htmlSource,
                getCodeBackgroundColor(), getAccentColor());
        loadData(html, "text/html", null);
    }

    public void setMdSource(@NonNull String source, @Nullable String baseUrl) {
        setMdSource(source, baseUrl, false);
    }

    public void setMdSource(@NonNull String source, @Nullable String baseUrl, boolean wrapCode) {
        if (TextUtils.isEmpty(source)) return;
        // FIXME: 2018/5/11 夜览模式
        String page = HtmlHelper.generateMdHtml(source, baseUrl, false,
                getCodeBackgroundColor(), getAccentColor(), wrapCode);
        loadPage(page);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void setCodeSource(@NonNull String source, boolean wrap, @Nullable String extension) {
        if (TextUtils.isEmpty(source)) return;
        WebSettings settings = getSettings();
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);

        // FIXME: 2018/5/11 夜览模式
        String page = HtmlHelper.generateCodeHtml(source, extension, false,
                getCodeBackgroundColor(), wrap, true);
        loadPage(page);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void setDiffFileSource(@NonNull String source, boolean wrap) {
        if (TextUtils.isEmpty(source)) return;
        WebSettings settings = getSettings();
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);

        // FIXME: 2018/5/11 夜览模式
        String page = HtmlHelper.generateDiffHtml(source, false,
                getCodeBackgroundColor(), wrap);
        loadPage(page);
    }

    private void loadPageWithBaseUrl(final String baseUrl, final String page) {
        post(new Runnable() {
            @Override
            public void run() {
                loadDataWithBaseURL(baseUrl, page, "text/html", "utf-8", null);
            }
        });
    }

    private void loadPage(String page) {
        loadPageWithBaseUrl("file:///android_asset/code_prettify/", page);
    }

    private boolean hitLinkResult(HitTestResult result) {
        return result.getType() == HitTestResult.SRC_ANCHOR_TYPE ||
                result.getType() == HitTestResult.IMAGE_TYPE ||
                result.getType() == HitTestResult.SRC_IMAGE_ANCHOR_TYPE;
    }

    private class ChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int progress) {
            super.onProgressChanged(view, progress);
            if (contentChangedListener != null) {
                contentChangedListener.onContentChanged(progress);
            }
        }
    }

    private class WebClientN extends WebViewClient {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            startActivity(request.getUrl());
            return true;
        }
    }

    private class WebClient extends WebViewClient {
        @SuppressWarnings("deprecation")
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            startActivity(Uri.parse(url));
            return true;
        }
    }

    private String getCodeBackgroundColor() {
        return "#" + Integer.toHexString(backgroundColor).substring(2).toUpperCase();
    }

    private String getAccentColor() {
        return "#303030";
    }

    private void startActivity(Uri uri) {
        if (uri == null) return;
    }
}

