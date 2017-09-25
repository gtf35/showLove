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
	String notLoveMe ="我走了，照顾好自己😥";
	String LoveMe = "哦耶！😆😆😆";
    String askLoveTitle = "你愿意做我女朋友吗？";
    boolean love = false;
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.mimi);



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
					Toast Toast1 = Toast.makeText(ask.this,notLoveMe, Toast.LENGTH_SHORT);
					Toast1.show();
					//	send email
					sendEMail(android.os.Build.MODEL+"'s  Don't Love me report!","She clicked Don't love me button !!!!");
					Intent intent = new Intent(ask.this, notLove.class);
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
	
	public void qqJumpMsg(){
		
			//设置toast提示
			Toast toast = Toast.makeText(ask.this,  "爱你😘😘😘😘😘😘", Toast.LENGTH_SHORT);
			toast.show();

		

	}

	public void askLoveMeOrNot(){

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

