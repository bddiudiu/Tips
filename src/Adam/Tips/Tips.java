package Adam.Tips;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import Adam.Tips.R;
import Adam.Tips.R.string;
import android.app.*;
import android.content.Intent;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;
import com.gfan.sdk.statistics.Collector;

/*
 * 此版本为beta版,包含取整BUG.
 * 已修改 getInt 代码 无法实现
 */

public class Tips extends Activity {
    /** Called when the activity is first created. */
	private final String[] countries = null;
	private EditText field_billspay;
	private EditText field_peoplenum;
	private TextView view_totalpay;
	private TextView view_eachpay;
	private TextView view_tipspay;
	private TextView view_choose;
	private Button button_calc;
	private Button button_quit;
	private Button button_int;
	private Button button_add;
	private Spinner spi_tips;
	
	private ArrayAdapter<String> adapter;
	private List<String> allCountries;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        findviews();
        setListener();
        fillspitips();
        Collector.onError(this);
    }
    
    private void fillspitips(){
    	allCountries = new ArrayList<String>(); 
    	
    	String[]countries = new String[]{Tips.this.getString(R.string.c1),Tips.this.getString(R.string.c2),
    			Tips.this.getString(R.string.c3),Tips.this.getString(R.string.c4),Tips.this.getString(R.string.c5),
    			Tips.this.getString(R.string.c6),Tips.this.getString(R.string.c7),Tips.this.getString(R.string.c8),
    			Tips.this.getString(R.string.c9),Tips.this.getString(R.string.c10),Tips.this.getString(R.string.c11),
    			Tips.this.getString(R.string.c12),Tips.this.getString(R.string.c13),Tips.this.getString(R.string.c14),
    			Tips.this.getString(R.string.c15),Tips.this.getString(R.string.c16),Tips.this.getString(R.string.c17),
    			Tips.this.getString(R.string.c18),Tips.this.getString(R.string.c19),Tips.this.getString(R.string.c20),
    			Tips.this.getString(R.string.c21),Tips.this.getString(R.string.c22),Tips.this.getString(R.string.c23),};
        for (int i = 0; i < countries.length; i++)
        { 
          allCountries.add(countries[i]);
          } 
        /* new ArrayAdapter物件并将allCountries传入 */ 
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allCountries);
        adapter.setDropDownViewResource(R.layout.myspinner_dropdown); 
        spi_tips.setAdapter(adapter);  
        
        spi_tips.setOnItemSelectedListener(new Spinner.OnItemSelectedListener()
	    { 
	      @Override
	      public void onItemSelected(AdapterView arg0, View arg1, int arg2, long arg3)
	      { 
	        /* 将所选mySpinner的值带入myTextView中 */ 
	    	  view_choose.setText(arg0.getSelectedItem().toString());
	        }
	      @Override 
	      public void onNothingSelected(AdapterView arg0)
	      {
	         
	      } 
	      });  
    } 
    
    private void findviews(){
    	view_totalpay = (TextView)findViewById(R.id.totlepay);
    	view_eachpay = (TextView)findViewById(R.id.eachpay);
    	view_tipspay=(TextView)findViewById(R.id.tipspay);
    	view_choose=(TextView)findViewById(R.id.choose);
    	field_billspay = (EditText)findViewById(R.id.billspay);
    	field_peoplenum=(EditText)findViewById(R.id.peoplenum);
    	button_calc = (Button)findViewById(R.id.btnOk);
    	button_quit = (Button)findViewById(R.id.btnQuit);
    	button_int = (Button)findViewById(R.id.btnInt);
    	button_add = (Button)findViewById(R.id.btnadd);
    	spi_tips=(Spinner)findViewById(R.id.tipsspi);    	
    }
    
    private void setListener(){
    	button_calc.setOnClickListener(calcTips);
    	button_quit.setOnClickListener(calcClose);
    	button_int.setOnClickListener(calcint);
    	button_add.setOnClickListener(add);
    }

    private OnClickListener add=new OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			// TODO Auto-generated method stub
	    	Intent intent = new Intent(); 
			intent.setClass(Tips.this, Add.class); 
			Bundle bundle = new Bundle();
			bundle.putString("KEY_BILLPAY", field_billspay.getText().toString());
			bundle.putString("KEY_PEOPLENUM", field_peoplenum.getText().toString());
			intent.putExtras(bundle);
			startActivity(intent);
		}
	};
    
    private OnClickListener calcint=new OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			// TODO Auto-generated method stub				
			DecimalFormat nf = new DecimalFormat("0.00");
//			double tips=Double.parseDouble(view_tipspay.getText().toString());
//			double totalpay=Double.parseDouble(view_totalpay.getText().toString());
//			double eachpay=Double.parseDouble(view_eachpay.getText().toString());
//			view_tipspay.setText(nf.format(Math.ceil(tips)));
//			view_totalpay.setText(nf.format(Math.ceil(totalpay)));
//			view_eachpay.setText(nf.format(Math.ceil(eachpay)));		
			
			String source = view_choose.getText().toString();
			int begin = source.indexOf("-");		
			int end = source.indexOf("%");		
			String target = source.substring(begin + 1,end);
			double bills=Double.parseDouble(field_billspay.getText().toString());
			double tipstype=Double.parseDouble(target)/100; 
			double peoplenum=Double.parseDouble(field_peoplenum.getText().toString());
			double tips=Math.ceil(bills*tipstype);
			double totalpay=Math.ceil(bills+tips);
			double eachpay=Math.ceil(totalpay/peoplenum);
			view_tipspay.setText(nf.format(tips));
			view_totalpay.setText(nf.format(totalpay));
			view_eachpay.setText(nf.format(eachpay));
			
			Collector.setAppClickCount("button_int");
		}
	};
    
    private OnClickListener calcClose=new OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			// TODO Auto-generated method stub
			 finish();
			 Collector.setAppClickCount("button_quit");
		}
	};
	
    private OnClickListener calcTips = new OnClickListener() 
    {	
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			DecimalFormat nf = new DecimalFormat("0.00");
			
			try {
				String source = view_choose.getText().toString();
				int begin = source.indexOf("-");		
				int end = source.indexOf("%");		
				String target = source.substring(begin + 1,end);
				double bills=Double.parseDouble(field_billspay.getText().toString());
				double tipstype=Double.parseDouble(target)/100; 
				double peoplenum=Double.parseDouble(field_peoplenum.getText().toString());
				double tips=bills*tipstype;
				double totalpay=bills+tips;
				double eachpay=totalpay/peoplenum;
				view_tipspay.setText(nf.format(tips));
				view_totalpay.setText(nf.format(totalpay));
				view_eachpay.setText(nf.format(eachpay));
			}
			catch (Exception  e) {
				// TODO Auto-generated catch block
				Toast.makeText(Tips.this, R.string.input_error, Toast.LENGTH_SHORT)
				.show();
			}
			Collector.setAppClickCount("button_calc");
		}
	};
	
	protected static final int MENU_About = Menu.FIRST; 
	protected static final int MENU_Quit = Menu.FIRST+2; 
	protected static final int MENU_Help = Menu.FIRST+1; 
	
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
	    		  intent.setClass(Tips.this, Help.class); 
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
	    	//Toast.makeText(this, "TIPS", Toast.LENGTH_SHORT)
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
		 fillspitips();
	 }
	 public void onDestroy() {
		 super.onDestroy();
	 }
}

