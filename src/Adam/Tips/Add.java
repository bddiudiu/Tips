package Adam.Tips;

import java.text.DecimalFormat;
import com.gfan.sdk.statistics.Collector;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Add extends Activity {
	/** Called when the activity is first created. */
	private EditText field_billspay2;
	private EditText field_peoplenum2;
	private TextView view_totalpay2;
	private TextView view_eachpay2;
	private TextView view_tipspay2;
	private Button button_calc2;
	private Button button_quit2;
	private Button button_int2;
	private EditText field_addtips2;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        findviews();
        setListener();
        Collector.setAppClickCount("button_calc2");
        Bundle bunde = this.getIntent().getExtras();
        field_billspay2.setText(bunde.getString("KEY_BILLPAY"));
        field_peoplenum2.setText(bunde.getString("KEY_PEOPLENUM"));
    }
	
	private void findviews() {
		// TODO Auto-generated method stub
		view_totalpay2 = (TextView)findViewById(R.id.totlepay2);
    	view_eachpay2 = (TextView)findViewById(R.id.eachpay2);
    	view_tipspay2 = (TextView)findViewById(R.id.tipspay2);
    	field_billspay2 = (EditText)findViewById(R.id.billspay2);
    	field_peoplenum2 = (EditText)findViewById(R.id.peoplenum2);
    	button_calc2 = (Button)findViewById(R.id.btnOk2);
    	button_quit2 = (Button)findViewById(R.id.btnQuit2);
    	button_int2 = (Button)findViewById(R.id.btnInt2);
    	field_addtips2 = (EditText)findViewById(R.id.addtips);   
	}

	private void setListener() {
		// TODO Auto-generated method stub
		button_calc2.setOnClickListener(calcTips);
    	button_quit2.setOnClickListener(calcClose);
    	button_int2.setOnClickListener(calcint);
    }

    private OnClickListener calcint=new OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			// TODO Auto-generated method stub			
			DecimalFormat nf = new DecimalFormat("0.00");
//			double tips=Double.parseDouble(view_tipspay2.getText().toString());
//			double totalpay=Double.parseDouble(view_totalpay2.getText().toString());
//			double eachpay=Double.parseDouble(view_eachpay2.getText().toString());
//			view_tipspay2.setText(nf.format(Math.floor(tips)+1));
//			view_totalpay2.setText(nf.format(Math.floor(totalpay)+1));
//			view_eachpay2.setText(nf.format(Math.floor(eachpay)+1));
			
			double addtips=Double.parseDouble(field_addtips2.getText().toString());
			double bills=Double.parseDouble(field_billspay2.getText().toString());
			double tipstype=addtips/100; 
			double peoplenum=Double.parseDouble(field_peoplenum2.getText().toString());
			double tips=Math.ceil(bills*tipstype);
			double totalpay=Math.ceil(bills+tips);
			double eachpay=Math.ceil(totalpay/peoplenum);
			view_tipspay2.setText(nf.format(tips));
			view_totalpay2.setText(nf.format(totalpay));
			view_eachpay2.setText(nf.format(eachpay));
		}
	};
    
    private OnClickListener calcClose=new OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			// TODO Auto-generated method stub
			 finish();
		}
	};
	
    private OnClickListener calcTips = new OnClickListener() 
    {	
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			DecimalFormat nf = new DecimalFormat("0.00");
			
			try {
				double addtips=Double.parseDouble(field_addtips2.getText().toString());
				double bills=Double.parseDouble(field_billspay2.getText().toString());
				double tipstype=addtips/100; 
				double peoplenum=Double.parseDouble(field_peoplenum2.getText().toString());
				double tips=bills*tipstype;
				double totalpay=bills+tips;
				double eachpay=totalpay/peoplenum;
				view_tipspay2.setText(nf.format(tips));
				view_totalpay2.setText(nf.format(totalpay));
				view_eachpay2.setText(nf.format(eachpay));
			}
			catch (Exception  e) {
				// TODO Auto-generated catch block
				Toast.makeText(Add.this, R.string.input_error, Toast.LENGTH_SHORT)
				.show();
			}
		}
	};
	
	protected static final int MENU_About = Menu.FIRST; 
	protected static final int MENU_Quit = Menu.FIRST+1; 
	protected static final int MENU_Help = Menu.FIRST+2; 
	@Override   
	public boolean onCreateOptionsMenu(Menu menu) { 
	      super.onCreateOptionsMenu(menu); 
	      menu.add(0, MENU_About, 0, R.string.menu_about); 
	      menu.add(0, MENU_Help, 0, R.string.menu_help); 
	      menu.add(0, MENU_Quit, 0, R.string.menu_quit); 
	      return true; 
	 } 

	 @Override 
	public boolean onOptionsItemSelected(MenuItem item)
	 { 
	     super.onOptionsItemSelected(item); 
	     switch(item.getItemId()){ 
	          case MENU_About: 
	        	  openOptionsDialog(); 
	              break; 
	          case MENU_Help: 
	        	  Intent intent = new Intent(); 
	    		  intent.setClass(Add.this, Help.class); 
	    		  startActivity(intent);
	        	  break; 
	          case MENU_Quit: 
	              finish(); 
	              break; 
	       } 
	     return true; 
	 }
	 
	 private void openOptionsDialog(){
	    	new AlertDialog.Builder(this).setTitle(R.string.aboout_title)
	    	.setMessage(R.string.about_message)
	    	.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int i) {
					// TODO Auto-generated method stub	
				}
			}).setNegativeButton(R.string.homepage_label, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int i) {
					// TODO Auto-generated method stub
					Uri uri = Uri.parse(getString(R.string.homepage_uri));
					Intent intent = new Intent(Intent.ACTION_VIEW,uri);
					startActivity(intent);
				}
			})
	    	//弹窗对话框 需要导入import android.widget.Toast;
	    	//Toast.makeText(this, "BMI", Toast.LENGTH_SHORT)
			.show();
	    }
	 
	public void onStart() {
		super.onStart();
	}
	public void onResume() {
		super.onResume();
		Collector.onResume(this);
	}
	public void onPause() {
		super.onPause();
		Collector.onPause(this);
	}
	public void onStop() {
		super.onStop();
	}
	public void onRestart() {
		super.onRestart();
	}
	public void onDestroy() {
		super.onDestroy();
	}
}
