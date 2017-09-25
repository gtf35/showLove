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
//import android.support.v7.app.AlertDialog;

public class showLove extends Activity 
{

// 定义各种全局变量
	boolean qq = true;
	int time = 27;
	String qqNumber = "2071077382";
	String notLoveMe ="我走了，照顾好自己😥";
	String LoveMe = "哦耶！😆😆😆";
    String askLoveTitle = "你愿意做我女朋友吗？";
    boolean love = false;
	
	
	

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

		this.setCancelable(false);// 设置点击屏幕Dialog不消失

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
		Toast toast = Toast.makeText(showLove.this,  "屏幕显示不全可以上下拖动", Toast.LENGTH_SHORT);
//img toast
//定义一个ImageView
		ImageView imageView = new ImageView(showLove.this);
		imageView.setImageResource(R.drawable.smallcao);
//获得Toast的View
		View toastView = toast.getView();
//定义一个Layout，这里是Layout
		LinearLayout linearLayout = 
			new LinearLayout(showLove.this);
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
    public void onBackPressed() {
    	//super.onBackPressed();
		//finish();
		//android.os.Process.killProcess(android.os.Process.myPid());
       exitProgrames();
		System.out.println("按下了back键   onBackPressed()");    	
    }
	
	public void seeOver(){
		//定时调用QQ跳转，根据QQ的布尔值判断是否开启

		if (qq)
		{
			Timer timer = new Timer();// 实例化Timer类
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
						
						askLoveMeOrNot();
						this.cancel();
					}
				}, time * 1000);// 这里百毫秒
			System.out.println("本程序自动退出");
		}
		
	
		
	}
	
	public void qqJumpMsg(){
		if (qq){
			//设置toast提示
			Toast toast = Toast.makeText(showLove.this,  "爱你😘😘😘😘😘😘", Toast.LENGTH_SHORT);
			toast.show();
			
		}
		
	}
	
	public void askLoveMeOrNot(){
		
		AlertDialog.Builder AskLoveMeOrNotDialog = new AlertDialog.Builder(this);
		AskLoveMeOrNotDialog.setCancelable(false);
		AskLoveMeOrNotDialog.setTitle("那么：");
		AskLoveMeOrNotDialog.setMessage(askLoveTitle);
		AskLoveMeOrNotDialog.setNegativeButton("抱歉，不", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					love = false;
					//	提示
					Toast Toast1 = Toast.makeText(showLove.this,notLoveMe, Toast.LENGTH_SHORT);
					Toast1.show();
					Intent intent = new Intent(showLove.this, notLove.class);
					startActivity(intent);
				}
			});
		AskLoveMeOrNotDialog.setPositiveButton("嗯，好哒😘",  new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					love = true;
					//	send email
					sendEMail(android.os.Build.MODEL+"'s  Love me report!","She clicked love me button !!!!");
					//qq jump message
					qqJumpMsg();
					// jump qq
					String tempqq = "mqqwpa://im/chat?chat_type=wpa&uin=" + qqNumber;
					startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(tempqq)));
					// exit
					//exitProgrames();  
					
					

				}
			});
		AskLoveMeOrNotDialog.setNeutralButton("   ",  new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					exitProgrames();
				}
			});
		AskLoveMeOrNotDialog.show();
		
		
	}
	
	private void sendEMail(String title,String text) {
        MailManager.getInstance().sendMail(title, text);
		}
	
	public void exitProgrames(){
		Intent startMain = new Intent(Intent.ACTION_MAIN);
		startMain.addCategory(Intent.CATEGORY_HOME);
		startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(startMain);
		android.os.Process.killProcess(android.os.Process.myPid());
	}

}
