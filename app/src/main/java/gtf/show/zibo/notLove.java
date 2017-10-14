package gtf.show.zibo;
import android.app.*;
import android.os.*;
import android.widget.*;
import android.view.View.*;
import android.view.*;
import android.content.*;
import android.content.pm.*;
import java.io.*;

public class notLove extends Activity{
	String sorry;
	boolean sendOver = false;
@Override
    protected void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.sorry);
	AlertDialog.Builder tellDontHateMeDialog = new AlertDialog.Builder(this);
	tellDontHateMeDialog.setCancelable(true);
	tellDontHateMeDialog.setTitle("有件事想跟你说说…");
	tellDontHateMeDialog.setMessage("(・へ・)不知道你此刻的心情如何？ \n 不过我想说: \n 这个APP也是很用心做的。\n 一共花了48h以上; \n 写了1000+行的代码。 \n 不祈求喜欢这个App，但我不想你因此不开心。 \n 好吗？😞");
	tellDontHateMeDialog.setNegativeButton("知道了", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				
			
			}
		});
	tellDontHateMeDialog.show();

		Button send =(Button)this.findViewById(R.id.send);
        final EditText text = (EditText)this.findViewById(R.id.text);
		send.setOnClickListener(new OnClickListener() {
      
            public void onClick(View v) {
              sorry = text.getText().toString();
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
}

