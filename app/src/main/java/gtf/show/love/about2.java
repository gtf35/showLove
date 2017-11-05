package gtf.show.love;
import android.app.*;
import android.os.*;
import android.widget.*;
import android.view.View.*;
import android.view.*;
import android.content.*;
import android.content.pm.*;
import java.io.*;
import android.webkit.*;
import android.graphics.*;

public class about2 extends Activity{
	WebView webView;
	String urlLove;
	ProgressDialog mProgressDialog;

	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
		mProgressDialog = new ProgressDialog(this);
		super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
		//开始显示内置网页
		webView = (WebView)findViewById(R.id.about);
		//urlLove ="http://www.baidu.com";
		urlLove = "file:///android_asset/about.html";
		webView.loadUrl(urlLove);
       webView.reload();


//设置网页缩放
//声明WebSettings子类
		WebSettings webSettings = webView.getSettings();

//如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
		webSettings.setJavaScriptEnabled(true);  

//支持插件
		//webSettings.setPluginsEnabled(true); 

//设置自适应屏幕，两者合用
		webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小 
		webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

//缩放操作
		webSettings.setSupportZoom(false); //支持缩放，默认为true。是下面那个的前提。
		webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
		webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

//其他细节操作
		webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存 
		webSettings.setAllowFileAccess(true); //设置可以访问文件 
		webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口 
		webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
		webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
		//复写WebViewClient类的shouldOverrideUrlLoading方法
		webView.setWebViewClient(new WebViewClient() {

				@Override
				public void onPageStarted(WebView view, String url, Bitmap favicon)
				{
					super.onPageStarted(view, url, favicon);
					mProgressDialog.show();
					mProgressDialog.setMessage("加载中……😂😂😂");
				}
				@Override
				public void onPageFinished(WebView view, String url)
				{
					super.onPageFinished(view, url);
					mProgressDialog.hide();
				}
			});

	}
	@Override
	protected void onResume()
	{
		/**
		 * 设置为横屏
		 */
		if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
		{
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		}
		webView.loadUrl(urlLove);
		super.onResume();
	}

	@Override
	protected void onStart()
	{
		webView.loadUrl(urlLove);
		super.onStart();
	}


	@Override
	protected void onRestart()
	{
		webView.loadUrl(urlLove);
		super.onRestart();
	}

	@Override
	protected void onDestroy()
	{
		// TODO: Implement this method
		super.onDestroy();
	}


	/**
	 * 监听Back键按下事件,方法1:
	 * 注意:
	 * super.onBackPressed()会自动调用finish()方法,关闭
	 * 当前Activity.
	 * 若要屏蔽Back键盘,注释该行代码即可
	 */
    @Override
    public void onBackPressed() {
		Toast toast = Toast.makeText(about2.this,  "感谢阅读！！", Toast.LENGTH_SHORT);
		toast.show();
		//显式intent跳转secret的activity
		Intent intent = new Intent(about2.this, notLove2.class);
		startActivity(intent);
		about2.this.finish();

    }
	public void exitProgrames(){
		Intent startMain = new Intent(Intent.ACTION_MAIN);
		startMain.addCategory(Intent.CATEGORY_HOME);
		startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(startMain);
		android.os.Process.killProcess(android.os.Process.myPid());
	}
}

