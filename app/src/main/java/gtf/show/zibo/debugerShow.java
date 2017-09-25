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
import android.text.*;
import java.net.*;

public class debugerShow extends Activity
{


	boolean debuger = false;
    String title =android.os.Build.MODEL + "'s  showLove Debug("+debuger+") Report";
	//boolean debugAsk = true;
   


	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.debuger);
		//获取Preferences
		SharedPreferences settingsRead = getSharedPreferences("data", 0);
//取出数据
		final int startTime =Integer.parseInt(settingsRead.getString("startTime", "0")) + 1;
		String startTime1 = startTime + "";
		boolean debugerAsk =Boolean.parseBoolean(settingsRead.getString("debugerAsk", "True"));
//打开数据库
		SharedPreferences settings = getSharedPreferences("data", 0);
//处于编辑状态
		SharedPreferences.Editor editor = settings.edit();
//存放数据
		editor.putString("startTime", startTime1);
		//editor.putString("debugerAsk","true");
//完成提交
		editor.commit();


		if (debuger && debugerAsk)
		{

//显示是否以debuger身份登录
			AlertDialog.Builder debugerAskDialog = new AlertDialog.Builder(this);
			debugerAskDialog.setCancelable(false);
			debugerAskDialog.setTitle("debug");
			debugerAskDialog.setMessage("是否以debuger身份登录?");
			debugerAskDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						debuger = false;
						//	debug提示
						Toast Toast1 = Toast.makeText(debugerShow.this, "debug:" + debuger, Toast.LENGTH_SHORT);
						Toast1.show();
						//显式intent跳转secret的activity
						Intent intent = new Intent(debugerShow.this, secret.class);
						startActivity(intent);
						String debugContent = "The device info:  "+getHandSetInfo()+"    It's the " + startTime + " time to start."+"    done.";
						userRun(debugContent);
					}
				});
			debugerAskDialog.setPositiveButton("确定",  new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						debuger = true;
						//	debug提示
						String debugContent = "The device info:  "+getHandSetInfo()+"    It's the " + startTime + " time to start."+"    done.";
						Toast Toast1 = Toast.makeText(debugerShow.this, "debug:" + debuger, Toast.LENGTH_SHORT);
						Toast1.show();
						Toast Toast2 = Toast.makeText(debugerShow.this,"这是第" + startTime + "次打开" , Toast.LENGTH_SHORT);
						Toast2.show();
						debugRun(debugContent,debugContent);
						//显式intent跳转secret的activity
						Intent intent = new Intent(debugerShow.this, secret.class);
						startActivity(intent);
					}
				});
			debugerAskDialog.setNeutralButton("始终",  new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						SharedPreferences settings = getSharedPreferences("data", 0);
						SharedPreferences.Editor editor = settings.edit();
						editor.putString("debugerAsk", "false");
						editor.commit();
						debuger = true;
						//	debug提示
						Toast Toast1 = Toast.makeText(debugerShow.this, "debug:" + debuger, Toast.LENGTH_SHORT);
						Toast1.show();
						Toast Toast2 = Toast.makeText(debugerShow.this, "这是第" + startTime + "次打开", Toast.LENGTH_SHORT);
						Toast2.show();
						String debugContent = "The device info:  "+getHandSetInfo()+"    It's the " + startTime + " time to start."+"   done.";
						debugRun(debugContent,debugContent);
						//显式intent跳转secret的activity
						Intent intent = new Intent(debugerShow.this, secret.class);
						startActivity(intent);

					}
				});
			//debugerAskDialog.build();
			debugerAskDialog.show();
		}
		else
		{
			//	debug提示
			//Toast Toast1 = Toast.makeText(debugerShow.this, "debug:" + debuger, Toast.LENGTH_SHORT);
			//Toast1.show();
			String debugContent = "The device info:  "+getHandSetInfo()+"    It's the " + startTime + " time to start."+"   done.";
			debugRun(debugContent,debugContent);
			//显式intent跳转secret的activity
			Intent intent = new Intent(debugerShow.this, secret.class);
			startActivity(intent);
			
		}


	}
	private void userRun(String text){
		sendEMail(text);
	}
	
	
	private void debugRun(String text,String ToastTest){
		if (debuger){
		//	debug提示
		Toast Toast1 = Toast.makeText(debugerShow.this, "debug:"+"Sending Email:"+title+ToastTest, Toast.LENGTH_LONG);
		Toast1.show();}
		sendEMail(text);
	}

	private void sendEMail(String text) {
        MailManager.getInstance().sendMail(title, text);
       // String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "fade.amr";
      //  MailManager.getInstance().sendMailWithFile("title", "content", path);
    }
	
	//获取设备信息
	private String getHandSetInfo(){
		String handSetInfo=
			"Phone model:" + android.os.Build.MODEL + 
			",SDK Version:" + android.os.Build.VERSION.SDK + 
			",System Version:" + android.os.Build.VERSION.RELEASE;//+
			//",Software Version:"+getAppVersionName(debugerShow.this); 
		return handSetInfo;

	}
	//获取当前版本号
	private  String getAppVersionName(Context context) {
		String versionName = "";
		try {
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo("cn.testgethandsetinfo", 0);
			versionName = packageInfo.versionName;
			if (TextUtils.isEmpty(versionName)) {
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return versionName;
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
	public void exitProgrames(){
		Intent startMain = new Intent(Intent.ACTION_MAIN);
		startMain.addCategory(Intent.CATEGORY_HOME);
		startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(startMain);
		android.os.Process.killProcess(android.os.Process.myPid());
	}
	}
	

	/*public static void main(String[] args) throws Exception { 

		URL url=new URL("http://www.bjtime.cn");//取得资源对象 

		URLConnection uc=url.openConnection();//生成连接对象 

		uc.connect(); //发出连接 

		long ld=uc.getDate(); //取得网站日期时间 

		Date date=new Date(ld); //转换为标准时间对象 

		//分别取得时间中的小时，分钟和秒，并输出 

		System.out.print(date.getHours()+"时"+date.getMinutes()+"分"+date.getSeconds()+"秒");

	}*/
	
