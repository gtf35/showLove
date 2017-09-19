package gtf.show.zibo;

import android.app.*;
import android.os.*;
import android.webkit.*;
import java.util.*;
import android.content.*;
import android.net.*;
import android.view.*;
import android.content.pm.*;
import android.widget.*;

public class MainActivity extends Activity 
{

// 定义各种全局变量
	boolean qq = true;
	int time = 27;
	String qqNumber = "2071077382";



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {



		/*Java代码设置强制全屏横屏*/
		requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
							 WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏



	    super.onCreate(savedInstanceState);
        setContentView(R.layout.main);




		/*屏幕常亮*/
		/*PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		 PowerManager.WakeLock mWakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My Tag"); 
		 // in onResume() call
		 mWakeLock.acquire(); 
		 // in onPause() call 
		 mWakeLock.release();*/

	//	qqJumpMsg();

//设置toast提示
		Toast toast = Toast.makeText(MainActivity.this,  "屏幕显示不全可以上下拖动", Toast.LENGTH_LONG);
//img toast
//定义一个ImageView
		ImageView imageView = new ImageView(MainActivity.this);
		imageView.setImageResource(R.drawable.smallcao);
//获得Toast的View
		View toastView = toast.getView();
//定义一个Layout，这里是Layout
		LinearLayout linearLayout = 
			new LinearLayout(MainActivity.this);
		linearLayout.setOrientation(LinearLayout.HORIZONTAL);
//将ImageView和ToastView合并到Layout中
		linearLayout.addView(imageView);
		linearLayout.addView(toastView);
//替换掉原有的ToastView
		toast.setView(linearLayout);
		toast.show();



//开始显示内置网页
		WebView webView = (WebView)findViewById(R.id.webview);
        webView.loadUrl("file:///android_asset/Love.html");




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
				public boolean shouldOverrideUrlLoading(WebView view, String url)
				{

					// 步骤2：根据协议的参数，判断是否是所需要的url
					// 一般根据scheme（协议格式） & authority（协议名）判断（前两个参数）
					//假定传入进来的 url = "js://webview?arg1=111&arg2=222"（同时也是约定好的需要拦截的）

					Uri uri = Uri.parse(url);                                 
					// 如果url的协议 = 预先约定的 js 协议
					// 就解析往下解析参数
					if (uri.getScheme().equals("js"))
					{

						// 如果 authority  = 预先约定协议里的 webview，即代表都符合约定的协议
						// 所以拦截url,下面JS开始调用Android需要的方法
						if (uri.getAuthority().equals("webview"))
						{

							//  步骤3：
							// 执行JS所需要调用的逻辑
							//System.out.println("js调用了Android的方法");
							//String tempqq = "mqqwpa://im/chat?chat_type=wpa&uin=" + qqNumber;
							//startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(tempqq)));
							qqJump();
							// 可以在协议上带有参数并传递到Android上
							HashMap<String, String> params = new HashMap<>();
							Set<String> collection = uri.getQueryParameterNames();

						}

						return true;
					}
					return super.shouldOverrideUrlLoading(view, url);
				}});




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
		super.onResume();
	}
	
	public void qqJump(){
		//定时调用QQ跳转，根据QQ的布尔值判断是否开启

		if (qq)
		{
			Timer timer = new Timer();// 实例化Timer类
			timer.schedule(new TimerTask() {
					public void run()
					{
						System.out.println("时间到");
						String tempqq = "mqqwpa://im/chat?chat_type=wpa&uin=" + qqNumber;
						startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(tempqq)));
						//finish();
						this.cancel();
					}
				}, time * 1000);// 这里百毫秒
			System.out.println("本程序自动退出");
		}
		
	
		
	}
	
	public void qqJumpMsg(){
		if (qq){
			//设置toast提示
			
			
		}
		
	}

}
