package andrewhossam.thed;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mugen.Mugen;
import com.mugen.MugenCallbacks;
import com.mugen.attachers.BaseAttacher;

import java.util.ArrayList;

import andrewhossam.thed.data.Data;
import andrewhossam.thed.data.RespondObject;
import andrewhossam.thed.tools.MyAdapter;

public class MainActivity extends AppCompatActivity {
    int pageNo = 0;
    Context context;
    RequestQueue queue;
    ArrayList<Data> list = new ArrayList<>();
    MyAdapter myAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    void loadNextDataFromApi(final int i) {
        Log.d("pageNO", "loadNextDataFromApi: " + i);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://limitless-forest-98976.herokuapp.com/?page=" + i,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        RespondObject respondObject = gson.fromJson(response, new TypeToken<RespondObject>() {
                        }.getType());
                        Log.v("daa", response);
                        list.addAll(respondObject.getData());
                        if (i == 0) {
                            myAdapter = new MyAdapter(MainActivity.this, list, context);
                            mRecyclerView.setAdapter(myAdapter);
                        } else {
                            myAdapter.notifyDataSetChanged();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Network Error", Toast.LENGTH_LONG).show();
            }
        });
        stringRequest.setShouldCache(true);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        stringRequest.setTag(1);
        queue.add(stringRequest);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        queue = Volley.newRequestQueue(this);
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        loadNextDataFromApi(pageNo++);

        BaseAttacher baseAttacher = Mugen.with(mRecyclerView, new MugenCallbacks() {
            @Override
            public void onLoadMore() {
//                Toast.makeText(context, "Loading more", Toast.LENGTH_SHORT).show();
                loadNextDataFromApi(pageNo++);
            }

            @Override
            public boolean isLoading() {
                return false;
            }

            @Override
            public boolean hasLoadedAllItems() {
                return false;
            }
        }).start();
        baseAttacher.setLoadMoreEnabled(true);
        baseAttacher.setLoadMoreOffset(2);


    }
}
