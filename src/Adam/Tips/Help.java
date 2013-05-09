package Adam.Tips;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Help extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help);
	}
	
	protected static final int MENU_About = Menu.FIRST; 
	protected static final int MENU_Back = Menu.FIRST+1; 
	@Override   
	public boolean onCreateOptionsMenu(Menu menu) { 
	      super.onCreateOptionsMenu(menu); 
	      menu.add(0, MENU_About, 0, "关于"); 
	      menu.add(0, MENU_Back, 0, "返回"); 
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
	          case MENU_Back: 
	        	  Intent intent = new Intent(); 
	    		  intent.setClass(Help.this, Tips.class); 
	    		  startActivity(intent);
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
	
}
