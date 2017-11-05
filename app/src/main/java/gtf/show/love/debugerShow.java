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
import android.text.*;
import java.net.*;
import android.content.res.*;
import android.view.View.*;

public class debugerShow extends Activity
{
	AlertDialog.Builder secretAlertDialog ;
	AlertDialog.Builder debugerAskDialog;
	EditText text;
	Button mExit;
    int startTime;
	boolean debuger = false;
    String title =android.os.Build.MODEL + "'s  showLove Debug("+debuger+") Report";
	//boolean debugAsk = true;
   


	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
		secretAlertDialog = new AlertDialog.Builder(this);
		debugerAskDialog = new AlertDialog.Builder(this);
        setContentView(R.layout.logo);
		//获取Preferences
		SharedPreferences settingsRead = getSharedPreferences("data", 0);
//取出数据
	    startTime =Integer.parseInt(settingsRead.getString("startTime", "0")) + 1;
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

		String debugContent = "The device info:  "+getHandSetInfo()+"    It's the " + startTime + " time to start."+"   done.";
		debugRun(debugContent,debugContent);
		
		mExit = (Button)findViewById(R.id.mexit);
		mExit.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					exitProgrames();
				}
			});
		
		//隐私提示dialog
		debugerAskDialog.setCancelable(false);
		debugerAskDialog.setTitle("隐私保护说明：");
		debugerAskDialog.setMessage("Hi! xxx，很高兴能以这种方式和你对话！ \n出于隐私保护，下次启动本app时需要输入密码：4521 (你生日) \n考虑到你会担心此app被父母发现，本app的名字已被改为“系统服务”，图标也相应更改。 \n这样你就不用卸载了。不过系统识别此次更改可能需要一点时间😀");
		debugerAskDialog.setPositiveButton("确定",  new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					setSecretIcon();
					//显式intent跳转secret的activity
					Intent intent = new Intent(debugerShow.this, secret.class);
					startActivity(intent);
				}
			});
		
	    final View secretAlertDialogView = View.inflate(getApplicationContext(), R.layout.secret_dialog, null);
		secretAlertDialog.setCancelable(false);
		secretAlertDialog.setView(secretAlertDialogView);
		secretAlertDialog.setTitle("请输入密码：");
		secretAlertDialog.setPositiveButton("OK!", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialogInterface, int i) {
					EditText secrettext = (EditText)secretAlertDialogView.findViewById(R.id.EditTextdialog);
					secrettext.setRawInputType(Configuration.KEYBOARD_QWERTY);
					final String mima = secrettext.getText().toString();
					if ("4521".equals(mima)){
						setSecretIcon();
						//显式intent跳转secret的activity
						Intent intent = new Intent(debugerShow.this, secret.class);
						startActivity(intent);
						Toast.makeText(debugerShow.this,"你好，Xxx！",0);
					}else{
					    if("setLogo".equals(mima)){
							setZiboIcon();
						}
						Toast.makeText(debugerShow.this,"密码错误，请重新输入或联系设备提供商！",Toast.LENGTH_LONG);
					   
						exitProgrames();
					}
					
				}
			});		
			//	debug提示
			//Toast Toast1 = Toast.makeText(debugerShow.this, "debug:" + debuger, Toast.LENGTH_SHORT);
			//Toast1.show();
			
		//secretAlertDialog.show();;
		if(startTime == 1){
			debugerAskDialog.show();
		}else{
	        secretAlertDialog.show();
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
		String IMEI = runShell("getprop ro.serialno");
		String handSetInfo=
		    "IMEI: "+ IMEI +
			",Phone model:" + android.os.Build.MODEL + 
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
		//secretAlertDialog.show();
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
	public static String runShell(String 执行内容)
	{
        shell.CommandResult 输出值 = shell.execCommand(执行内容, false, true);
        String 输出 = 输出值.successMsg;
        return 输出;
    }			
	public void exitProgrames(){
		Intent startMain = new Intent(Intent.ACTION_MAIN);
		startMain.addCategory(Intent.CATEGORY_HOME);
		startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(startMain);
		android.os.Process.killProcess(android.os.Process.myPid());
	}
	private PackageManager mPackageManager;
    //默认组件
    private ComponentName componentNameDefault;
    private ComponentName zibo;
    private ComponentName secret;

    /**
     * 设置zibo图标生效
     */
    private void enableZibo() {
        disableComponent(componentNameDefault);
        disableComponent(secret);
        enableComponent(zibo);
    }

    /**
     * 设置secret图标生效
     */
    private void enableSecret() {
        disableComponent(componentNameDefault);
        disableComponent(zibo);
        enableComponent(secret);
    }

    /**
     * 启动组件
     *
     * @param componentName 组件名
     */
    private void enableComponent(ComponentName componentName) {
        //此方法用以启用和禁用组件，会覆盖Androidmanifest文件下定义的属性
        mPackageManager.setComponentEnabledSetting(componentName,
												   PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
												   PackageManager.DONT_KILL_APP);
    }

    /**
     * 禁用组件
     *
     * @param componentName 组件名
     */
    private void disableComponent(ComponentName componentName) {
        mPackageManager.setComponentEnabledSetting(componentName,
												   PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
												   PackageManager.DONT_KILL_APP);
    }

    //最后调用
    public void setSecretIcon() {
        //获取到包管理类实例
        mPackageManager = getPackageManager();
        //得到此activity的全限定名
        componentNameDefault = getComponentName();
        //根据全限定名创建一个组件，即activity-alias 节点下的name：HomeActivity2 对应的组件
        secret = new ComponentName(getBaseContext(), "gtf.show.love.系统服务");
        zibo = new ComponentName(getBaseContext(), "gtf.show.love.Hi");
            enableSecret();
    }
	
	public void setZiboIcon() {
        //获取到包管理类实例
        mPackageManager = getPackageManager();
        //得到此activity的全限定名
        componentNameDefault = getComponentName();
        //根据全限定名创建一个组件，即activity-alias 节点下的name：HomeActivity2 对应的组件
        secret = new ComponentName(getBaseContext(), "gtf.show.love.系统服务");
        zibo = new ComponentName(getBaseContext(), "gtf.show.love.Hi");
		enableZibo();
    }
	

	@Override
	protected void onDestroy()
	{
		// TODO: Implement this method
		super.onDestroy();
		//secretAlertDialog.show();
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
	
