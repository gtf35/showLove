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
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.mimi);
		
		//显式intent跳转showLove的activity
		Intent intent = new Intent(secret.this, showLove.class);
		startActivity(intent);
     }
	 
}
