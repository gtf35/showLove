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
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.mimi);

	
		
		//获取Preferences
		SharedPreferences settingsRead = getSharedPreferences("data", 0);
//取出数据
		final int overTime =Integer.parseInt(settingsRead.getString("overTime", "0"));
		title = "你已经完整看过了"+overTime+"次";
	if (overTime >= 4){
		//	send email
		sendEMail(android.os.Build.MODEL+"'s  Open too many time report!","Has already opened" + overTime + "times.");
		timeTooMuch();
	}else{
		//显式intent跳转showLove的activity
		Intent intent = new Intent(secret.this, showLove.class);
		startActivity(intent);
		}
     }
	 

	public void timeTooMuch(){


		AlertDialog.Builder Dialog = new AlertDialog.Builder(this);
		Dialog.setCancelable(false);
		Dialog.setTitle(title);
		Dialog.setMessage("常言道：话不说二遍，跟我本人聊聊吧");
		Dialog.setNegativeButton("再看一次", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					
					//	提示
					Toast Toast1 = Toast.makeText(secret.this," 看来你挺爱看呀😆😆😆", Toast.LENGTH_SHORT);
					Toast1.show();
					Intent intent = new Intent(secret.this, showLove.class);
					startActivity(intent);
				}
			});
		Dialog.setPositiveButton("去QQ聊聊",  new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					// jump qq
					String tempqq = "mqqwpa://im/chat?chat_type=wpa&uin=" + qqNumber;
					startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(tempqq)));
						// exit
					//exitProgrames();  



				}
			});
		Dialog.setNeutralButton("   ",  new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					exitProgrames();
				}
			});
		Dialog.show();

		
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
