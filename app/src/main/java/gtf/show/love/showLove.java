package gtf.show.love;

import android.app.*;
import android.os.*;
import android.webkit.*;
import java.util.*;
import android.content.*;
import android.net.*;
import android.view.*;
import android.content.pm.*;
import android.widget.*;
import android.graphics.*;

public class showLove extends Activity 
{

// 定义各种全局变量
	boolean qq = true;
	boolean canExit = false;
	int time = 28;
	String qqNumber = "2071077382";
	String notLoveMe ="那好吧，肯呢个是我不够优秀😥";
	String LoveMe = "哦耶！😆😆😆";
    String askLoveTitle = "你愿意做我女朋友吗？";
    boolean love = false;
	ProgressDialog mProgressDialog;
	AlertDialog.Builder Dialog;
	AlertDialog.Builder musicAskDialog;
	WebView webView;
	String urlLove;
	ActionBar actionBar; 
	

    protected void onCreate(Bundle savedInstanceState)
    {
		mProgressDialog = new ProgressDialog(this);
		Dialog = new AlertDialog.Builder(this);
		musicAskDialog = new AlertDialog.Builder(this);
		View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(option);
        //ActionBar actionBar = getSupportActionBar();
       // actionBar.hide();
		/*Java代码设置强制全屏横屏*/
	//	requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
							 WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
		actionBar = getActionBar(); //得到ActionBar
		actionBar.hide(); //隐藏ActionBar



	    super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		Dialog.setCancelable(false);
		Dialog.setTitle("小提示");
		//获取Preferences
		SharedPreferences settingsRead = getSharedPreferences("data", 0);
//取出数据
		final int overTime =Integer.parseInt(settingsRead.getString("overTime", "0")) + 1;
		if(overTime <= 2){Dialog.setMessage("受手机屏幕比例不同所限，界面竖直方向应该会显示不全，但是可以上下拖动哒😂");
		}else{Dialog.setMessage("感谢你的再次观看，由于你已经经历2次选择，所以我认为这次打开的目的仅仅是想再看看，所以不再询问是否喜欢我。祝你生活愉快，动画结束5s后程序自动退出，你也可以按返回键立刻退出😋");
			canExit = true;

		}
		Dialog.setNegativeButton("了解", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which)
				{   Toast toast = Toast.makeText(showLove.this,  "OK,我们开始", Toast.LENGTH_SHORT);
					toast.show();
				}
			});
			
		musicAskDialog.setCancelable(false);
		musicAskDialog.setTitle("是否开启背景音乐?");
	    musicAskDialog.setMessage("这个背景音乐很不错滴！真的，如果环境允许非常建议开的！！！\n-如果选择开启的话，无论手机现在是否静音，程序将自动调整到一个较小的音量播放音乐，程序退出时将自动恢复之前的音量设置。 \n-如果选择关闭的话，程序将什么都不会做。 \n-做个选择吧😊");
		musicAskDialog.setPositiveButton("开启", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which)
				{   Intent intentStart = new Intent(showLove.this, MusicService.class);
					startService(intentStart);
					Toast toast = Toast.makeText(showLove.this,  "音乐的名字叫：未来的志愿书", Toast.LENGTH_SHORT);
					toast.show();
				}
			});	
		musicAskDialog.setNeutralButton("关闭",  new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					Toast toast = Toast.makeText(showLove.this,  "音乐的名字叫：未来的志愿书，有空你听听.", Toast.LENGTH_SHORT);
					toast.show();
				}
			});


//开始显示内置网页
		webView = (WebView)findViewById(R.id.webview);
		//urlLove ="http://www.baidu.com";
		urlLove = "file:///android_asset/Love.html";
		webView.loadUrl(urlLove);



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
		// webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);

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
					Dialog.show();
                    musicAskDialog.show();

					
				}

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
							seeOver();
							// 可以在协议上带有参数并传递到Android上
							HashMap<String, String> params = new HashMap<>();
							Set<String> collection = uri.getQueryParameterNames();

						}

						return true;
					}
					return super.shouldOverrideUrlLoading(view, url);
				}});




	}

	private void setCancelable(boolean p0)
	{
		// TODO: Implement this method
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

	/**
	 * 监听Back键按下事件,方法1:
	 * 注意:
	 * super.onBackPressed()会自动调用finish()方法,关闭
	 * 当前Activity.
	 * 若要屏蔽Back键盘,注释该行代码即可
	 */
    @Override
    public void onBackPressed()
	{
    	//super.onBackPressed();
		//finish();
		//android.os.Process.killProcess(android.os.Process.myPid());
		exitProgrames();
		System.out.println("按下了back键   onBackPressed()");    	
    }

	public void seeOver(){Timer timer = new Timer();// 实例化Timer类
			timer.schedule(new TimerTask() {
					public void run()
					{
						System.out.println("时间到");
//获取Preferences
						SharedPreferences settingsRead = getSharedPreferences("data", 0);
//取出数据
						final int overTime =Integer.parseInt(settingsRead.getString("overTime", "0")) + 1;
						String overTime1 = overTime + "";

//打开数据库
						SharedPreferences settings = getSharedPreferences("data", 0);
//处于编辑状态
						SharedPreferences.Editor editor = settings.edit();
//存放数据
						editor.putString("overTime", overTime1);
						//editor.putString("debugerAsk","true");
//完成提交
						editor.commit();
//jump ask activity
						if(overTime <= 2){
							Intent intent = new Intent(showLove.this, ask.class);
							startActivity(intent);
						this.cancel();
						}else{
							//toast("感谢你的再次观看，由于你已经经历2次选择，/n 我认为这次观看的目的仅仅是想再看看，所以不再询问。😊");
							//toast("祝你生活愉快，5s后程序退出，你也可以按返回键立刻退出😋");
							canExit = true;
							timerExit();
							
						}
					}
				}, time * 1000);// 这里百毫秒
			System.out.println("本程序自动退出");
		

	}
	
	public void timerExit()
	{
			Timer timer = new Timer();// 实例化Timer类
			timer.schedule(new TimerTask() {
					public void run()
					{	Intent intentStop = new Intent(showLove.this, MusicService.class);
						stopService(intentStop);
						//toast("已退出");
						Intent startMain = new Intent(Intent.ACTION_MAIN);
							startMain.addCategory(Intent.CATEGORY_HOME);
							startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							startActivity(startMain);
							android.os.Process.killProcess(android.os.Process.myPid());
					}
				}, 5000);// 这里百毫秒
			System.out.println("本程序自动退出");
		

	}

	public void exitProgrames()
	{
		if(canExit == false){Toast toast = Toast.makeText(showLove.this,  "看完吧，时间不长😊", Toast.LENGTH_SHORT);
		toast.show();
		}else{Intent intentStop = new Intent(showLove.this, MusicService.class);
			stopService(intentStop);
			Intent startMain = new Intent(Intent.ACTION_MAIN);
			startMain.addCategory(Intent.CATEGORY_HOME);
			startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(startMain);
			android.os.Process.killProcess(android.os.Process.myPid());
				   }
	}
	
	
	public void toast(String say){Toast toast = Toast.makeText(showLove.this,  say, Toast.LENGTH_LONG);
		toast.show();
	}



}

