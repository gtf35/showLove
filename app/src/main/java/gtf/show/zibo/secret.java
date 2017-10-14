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
public class secret extends Activity
{
	String title ;
	String qqNumber = "2071077382";
	boolean debuger = false;
	long SPLASH_DELAY_MILLIS = 2000;
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {

		/*Java代码设置强制全屏横屏*/
		requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
							 WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏


		super.onCreate(savedInstanceState);
        setContentView(R.layout.logo);

		Intent intentStop = new Intent(secret.this, MusicService.class);
		stopService(intentStop);

		//获取Preferences
		SharedPreferences settingsRead = getSharedPreferences("data", 0);
//取出数据
		
		 
		
		final int overTime =Integer.parseInt(settingsRead.getString("overTime", "0"));
		final int startTime =Integer.parseInt(settingsRead.getString("startTime", "0")) + 1;
		title ="你已经打开"+ startTime+"次,  完整看过了" + overTime + "次";
		if (startTime >= 3)
		{
			//	send email
			sendEMail(android.os.Build.MODEL + "'s  Open too many time report!", "Has already opened" + overTime + "times.");
			timeTooMuch();
		}
		else
		{
			// 使用Handler的postDelayed方法，3秒后执行跳转到MainActivity 
			new Handler().postDelayed(new Runnable() {
					public void run()
					{
						//显式intent跳转showLove的activity
						Intent intent = new Intent(secret.this, showLove.class);
						startActivity(intent);
						secret.this.finish();
					}
				}, SPLASH_DELAY_MILLIS);
		}
	}


	public void timeTooMuch()
	{


		AlertDialog.Builder Dialog = new AlertDialog.Builder(this);
		Dialog.setCancelable(false);
		Dialog.setTitle(title);
		Dialog.setMessage("很高兴你喜欢这个App，看了这么多次。😊");
		Dialog.setNegativeButton("再看一次", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which)
				{

					//	提示
					Toast Toast1 = Toast.makeText(secret.this, " 看来你挺爱看呀😆😆😆", Toast.LENGTH_SHORT);
					Toast1.show();
					Intent intent = new Intent(secret.this, showLove.class);
					startActivity(intent);
					secret.this.finish();
				}
			});
		Dialog.setPositiveButton("去QQ聊聊",  new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					// jump qq
					try
					{
						Toast Toast1 = Toast.makeText(secret.this, "😘😘如果没有自动打开QQ请手动打开！！！", Toast.LENGTH_SHORT);
						Toast1.show();
						String tempqq = "mqqwpa://im/chat?chat_type=wpa&uin=" + qqNumber;//uin是发送过去的qq号码
						startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(tempqq)));
					}
					catch (Exception e)
					{
						e.printStackTrace();
						Toast Toast1 = Toast.makeText(secret.this, "启动QQ失败，请检查是否安装QQ", Toast.LENGTH_SHORT);
						Toast1.show();

					}	// exit
					//exitProgrames();  



				}
			});
		Dialog.setNeutralButton("说明&关于",  new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which)
				{	
					//显式intent跳转secret的activity
					Intent intent = new Intent(secret.this, about.class);
					startActivity(intent);
					secret.this.finish();
				}
			});
		Dialog.show();


	}

	private void sendEMail(String title, String text)
	{
        MailManager.getInstance().sendMail(title, text);
	}


	
	public void exitProgrames()
	{
		Intent startMain = new Intent(Intent.ACTION_MAIN);
		startMain.addCategory(Intent.CATEGORY_HOME);
		startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(startMain);
		android.os.Process.killProcess(android.os.Process.myPid());
	}
}

