package andrewhossam.thed.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Andrew on 5/30/2017.
 */

public class RespondObject {
    @SerializedName("data")
    ArrayList<Data> data;
    @SerializedName("count")
    int count;

    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
