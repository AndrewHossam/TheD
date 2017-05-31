package andrewhossam.thed;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import andrewhossam.thed.data.Data;
import andrewhossam.thed.tools.httptohttps;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        int densityDpi = (int) getResources().getDisplayMetrics().density;

        final Data data = (Data) getIntent().getExtras().get("data");
        Data.Image image = data.image;
        ((TextView) findViewById(R.id.name)).setText(data.getName());
        ((TextView) findViewById(R.id.price)).setText("$" + data.getPrice());
        ((TextView) findViewById(R.id.detialsDescription)).setText(data.getProductDescription());
        ImageView imageView = (ImageView) findViewById(R.id.photo);
        Picasso.with(this).load(Uri.parse(httptohttps.convert(image.getLink()))).resize(Integer.parseInt(image.getWidth()) * densityDpi, Integer.parseInt(image.getHeight()) * densityDpi).into(imageView);

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.share_fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, data.getName() + "price is $" + data.getPrice());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });

    }
}
