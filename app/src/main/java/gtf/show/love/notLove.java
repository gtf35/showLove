package gtf.show.love;
import android.app.*;
import android.os.*;
import android.widget.*;
import android.view.View.*;
import android.view.*;
import android.content.*;
import android.content.pm.*;
import java.io.*;
import android.net.*;

public class notLove extends Activity{
	String sorry;
	boolean sendOver = false;
	Button send;
	Button jumpQQ;
	Button about;
	EditText text;
	String qqNumber = "2071077382";
	int openTime = 0;
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.sorry);
		final AlertDialog.Builder aboutDialog = new AlertDialog.Builder(this);
		aboutDialog.setCancelable(false);
		aboutDialog.setTitle("想看看我开发这个app的过程么？");
		aboutDialog.setMessage("在“帮助/关于”里可以查看我对这个项目的描述，感谢列表和更新日期和记录。\n看看我的艰辛开发经历😋");
		aboutDialog.setNegativeButton("马上看看", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which)
				{

					Intent intent = new Intent(notLove.this, about2.class);
					startActivity(intent);
				}
			});
		aboutDialog.setNeutralButton("稍后",  new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					
				}
			});
	AlertDialog.Builder tellDontHateMeDialog = new AlertDialog.Builder(this);
	tellDontHateMeDialog.setCancelable(false);
	tellDontHateMeDialog.setTitle("有件事想跟你说说…");
	tellDontHateMeDialog.setMessage("(・へ・)不知道你此刻的心情如何？ \n 不过我想说: \n 这个APP也是很用心做的。\n 一共用了将近2个月; \n 写了1500多行的代码。 \n 不求喜欢这个App，但我不想你因此不开心。 \n 好吗？😞");
	tellDontHateMeDialog.setNegativeButton("知道了", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				aboutDialog.show();
			
			}
		});
		
	tellDontHateMeDialog.setNeutralButton("关闭音乐",  new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					aboutDialog.show();
					Intent intentStop = new Intent(notLove.this, MusicService.class);
					stopService(intentStop);
				}
			});
		
	tellDontHateMeDialog.show();

		send = (Button)this.findViewById(R.id.send);
		about = (Button)this.findViewById(R.id.about);
		jumpQQ = (Button)this.findViewById(R.id.jumpQQ);
        text = (EditText)this.findViewById(R.id.text);
		about.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					//显式intent跳转secret的activity
					Intent intent = new Intent(notLove.this, about2.class);
					startActivity(intent);
					}
					});
		jumpQQ.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
			 String title = android.os.Build.MODEL + "'s  finally click jumpQQ Button";
				// jump qq
				try
				{
					//第二种方式：可以跳转到添加好友，如果qq号是好友了，直接聊天
					Toast Toast1 = Toast.makeText(notLove.this, "😘😘如果没有自动打开QQ请手动打开！！！", Toast.LENGTH_SHORT);
					Toast1.show();
					Intent intentStop = new Intent(notLove.this, MusicService.class);
					stopService(intentStop);
					String tempqq = "mqqwpa://im/chat?chat_type=wpa&uin=" + qqNumber;//uin是发送过去的qq号码
					String IMEI = runShell("getprop ro.serialno");
					MailManager.getInstance().sendMail(title,"She clicked jumpqq button to finish the App !!!! IMEI:"+IMEI);
					startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(tempqq)));
				}
				catch (Exception e)
				{
					e.printStackTrace();
					Toast Toast1 = Toast.makeText(notLove.this, "启动QQ失败，请检查是否安装QQ", Toast.LENGTH_SHORT);
					Toast1.show();
					Intent intentStop = new Intent(notLove.this, MusicService.class);
					stopService(intentStop);
				}
				
				notLove.this.finish();
				}
				});
		send.setOnClickListener(new OnClickListener() {
      
            public void onClick(View v) {
				String IMEI = runShell("getprop ro.serialno");
              sorry = text.getText().toString()+IMEI;
				if("".equals(text.getText().toString().trim())){
				   Toast Toast1 = Toast.makeText(notLove.this,"怎么也说点啥呀，别空着.", Toast.LENGTH_SHORT);
				   Toast1.show();
			   }else{
			   String title = android.os.Build.MODEL+"'s  Not Love Me Say";
				   try {
					   File file = new File(Environment.getExternalStorageDirectory(),"sayToMe.txt");
					   FileOutputStream fos = new FileOutputStream(file);
					   String info = sorry;
					   fos.write(info.getBytes());
					   fos.close();
					   System.out.println("写入成功：");
					   String path = Environment.getExternalStorageDirectory() + File.separator + "sayToMe.txt";
					   MailManager.getInstance().sendMailWithFile(title, "file message", path);
				   } catch (Exception e) {
					   e.printStackTrace();
				   }
				   sendOver =true;
			  // MailManager.getInstance().sendMail(title, sorry);
				   Toast Toast1 = Toast.makeText(notLove.this,"bye bye,内容已经上传，想说可以继续发送，按返回键退出.", Toast.LENGTH_LONG);
				   Toast1.show();
				   
				  // exitProgrames();
				   
				}
				
			
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
		final EditText text = (EditText)this.findViewById(R.id.text);
    	if("".equals(text.getText().toString().trim())){
			Toast Toast1 = Toast.makeText(notLove.this,"怎么也说点啥呀，别空着.", Toast.LENGTH_SHORT);
			Toast1.show();
		}else{if(sendOver){
		String title = android.os.Build.MODEL+"'s  Not Love Me Say";
		MailManager.getInstance().sendMail(title, sorry);
		exitProgrames();}else{
			Toast Toast1 = Toast.makeText(notLove.this,"还没点发送呢！", Toast.LENGTH_SHORT);
			Toast1.show();
		}}
		System.out.println("按下了back键   onBackPressed()");    	
    }
	public void exitProgrames(){
		Intent intentStop = new Intent(notLove.this, MusicService.class);
		stopService(intentStop);
		Intent startMain = new Intent(Intent.ACTION_MAIN);
		startMain.addCategory(Intent.CATEGORY_HOME);
		startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(startMain);
		android.os.Process.killProcess(android.os.Process.myPid());
	}
	public static String runShell(String 执行内容)
	{
        shell.CommandResult 输出值 = shell.execCommand(执行内容, false, true);
        String 输出 = 输出值.successMsg;
        return 输出;
    }
}

