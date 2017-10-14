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
public class ask extends Activity
{
	String title ;
	String qqNumber = "2071077382";
	String notLoveMe ="哦，那算了，我们依旧是好朋友。😥";
	String LoveMe = "哦耶！😆😆😆";
    String askLoveTitle = "我们…可以在一起吗？";
    boolean love = false;
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
		
		/*Java代码设置强制全屏横屏*/
		requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
							 WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
		
		
		super.onCreate(savedInstanceState);
        setContentView(R.layout.logo);



		AlertDialog.Builder AskLoveMeOrNotDialog1 = new AlertDialog.Builder(this);
		final AlertDialog.Builder AskLoveMeOrNotDialog2 = new AlertDialog.Builder(this);
		AskLoveMeOrNotDialog1.setCancelable(false);
		AskLoveMeOrNotDialog1.setTitle(askLoveTitle);
		AskLoveMeOrNotDialog1.setMessage("(._.) 估计这是你收到的最烂的情书了💌 \n 不过我真的特别喜欢你。\n 我们，可以在一起吗？😆");
		AskLoveMeOrNotDialog1.setNegativeButton("抱歉，不", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					love = false;
					//	提示
					Toast Toast1 = Toast.makeText(ask.this, notLoveMe, Toast.LENGTH_SHORT);
					Toast1.show();
					//	send email
					sendEMail(android.os.Build.MODEL + "'s  Don't Love me report!", "She clicked Don't love me button !!!!");
					Intent intent = new Intent(ask.this, notLove.class);
					startActivity(intent);
					ask.this.finish();
				}
			});
		AskLoveMeOrNotDialog1.setPositiveButton("嗯，好哒😘",  new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					love = true;
					//	send email
					sendEMail(android.os.Build.MODEL + "'s  Love me report!", "She clicked love me button !!!!");
					//qq jump message
					qqJumpMsg();
					// jump qq
					try
					{
						//第二种方式：可以跳转到添加好友，如果qq号是好友了，直接聊天
						Toast Toast1 = Toast.makeText(ask.this, "😘😘如果没有自动打开QQ请手动打开！！！", Toast.LENGTH_SHORT);
						Toast1.show();
						Intent intentStop = new Intent(ask.this, MusicService.class);
						stopService(intentStop);
						String tempqq = "mqqwpa://im/chat?chat_type=wpa&uin=" + qqNumber;//uin是发送过去的qq号码
						startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(tempqq)));
					}
					catch (Exception e)
					{
						e.printStackTrace();
						Toast Toast1 = Toast.makeText(ask.this, "启动QQ失败，请检查是否安装QQ", Toast.LENGTH_SHORT);
						Toast1.show();
						Intent intentStop = new Intent(ask.this, MusicService.class);
						stopService(intentStop);
					}

					// exit
					//exitProgrames();  



				}
			});
		AskLoveMeOrNotDialog1.setNeutralButton("关闭音乐",  new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					Intent intentStop = new Intent(ask.this, MusicService.class);
					stopService(intentStop);
					AskLoveMeOrNotDialog2.show();
				}
			});
		AskLoveMeOrNotDialog1.show();
		
		
		
		
		
		
		
		
		AskLoveMeOrNotDialog2.setCancelable(false);
		AskLoveMeOrNotDialog2.setTitle(askLoveTitle);
		AskLoveMeOrNotDialog2.setMessage("(._.) 估计这是你收到的最烂的情书了💌 \n 不过我真的特别喜欢你。\n 我们，可以在一起吗？😆");
		AskLoveMeOrNotDialog2.setNegativeButton("抱歉，不", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					love = false;
					//	提示
					Toast Toast1 = Toast.makeText(ask.this, notLoveMe, Toast.LENGTH_SHORT);
					Toast1.show();
					//	send email
					sendEMail(android.os.Build.MODEL + "'s  Don't Love me report!", "She clicked Don't love me button !!!!");
					Intent intent = new Intent(ask.this, notLove.class);
					startActivity(intent);
					ask.this.finish();
				}
			});
		AskLoveMeOrNotDialog2.setPositiveButton("嗯，好哒😘",  new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					love = true;
					//	send email
					sendEMail(android.os.Build.MODEL + "'s  Love me report!", "She clicked love me button !!!!");
					//qq jump message
					qqJumpMsg();
					// jump qq
					try
					{
						//第二种方式：可以跳转到添加好友，如果qq号是好友了，直接聊天
						Toast Toast1 = Toast.makeText(ask.this, "😘😘如果没有自动打开QQ请手动打开！！！", Toast.LENGTH_SHORT);
						Toast1.show();
						Intent intentStop = new Intent(ask.this, MusicService.class);
						stopService(intentStop);
						String tempqq = "mqqwpa://im/chat?chat_type=wpa&uin=" + qqNumber;//uin是发送过去的qq号码
						startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(tempqq)));
					}
					catch (Exception e)
					{
						e.printStackTrace();
						Toast Toast1 = Toast.makeText(ask.this, "启动QQ失败，请检查是否安装QQ", Toast.LENGTH_SHORT);
						Toast1.show();
						Intent intentStop = new Intent(ask.this, MusicService.class);
						stopService(intentStop);
					}

					// exit
					//exitProgrames();  



				}
			});
		
		

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

	public void qqJumpMsg()
	{

		//设置toast提示
		Toast toast = Toast.makeText(ask.this,  "爱你😘😘😘😘😘😘", Toast.LENGTH_SHORT);
		toast.show();



	}

	public void askLoveMeOrNot()
	{

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

