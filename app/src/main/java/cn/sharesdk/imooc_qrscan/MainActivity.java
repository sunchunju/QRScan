package cn.sharesdk.imooc_qrscan;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button mStartScanBtn;
    private TextView mScanResultTv;
    private Button mCreateQRBtn;
    private ImageView mCreateResultIv;
    private EditText mTextEt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStartScanBtn = (Button) findViewById(R.id.bt_scan);
        mScanResultTv = (TextView) findViewById(R.id.tv_result);

        mCreateQRBtn = (Button) findViewById(R.id.bt_create);
        mCreateResultIv = (ImageView) findViewById(R.id.iv_result);
        mTextEt = (EditText) findViewById(R.id.et_text);
        mStartScanBtn.setOnClickListener(this);
        mCreateQRBtn.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK){
            Bundle bundle = data.getExtras();
            String result = bundle.getString("result");
            mScanResultTv.setText(result);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_scan:
                startActivityForResult(new Intent(MainActivity.this, CaptureActivity.class), 0);
                break;
            case R.id.bt_create:

                String content = mTextEt.getText().toString();
                if (content.equals("")){
                    Toast.makeText(MainActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    //logoBm先设为空
//                    Bitmap bitmap = EncodingUtils.createQRCode(content, 500, 500, null);
//                    mCreateResultIv.setImageBitmap(bitmap);
                    //logoBm不为空
                    Bitmap bitmap1 = EncodingUtils.createQRCode(content, 500, 500, BitmapFactory.decodeResource(getResources(), R.drawable.emo_im_heart));
                    mCreateResultIv.setImageBitmap(bitmap1);
                }


            default:
                break;
        }


    }
}
