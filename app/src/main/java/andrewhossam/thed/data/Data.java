package andrewhossam.thed.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Andrew on 5/29/2017.
 */

public class Data implements Parcelable {
    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Data> CREATOR = new Parcelable.Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };
    @SerializedName("image")
    public Image image;
    @SerializedName("id")
    String ID;
    @SerializedName("name")
    String name;
    @SerializedName("productDescription")
    String productDescription;
    @SerializedName("price")
    String price;

    public Data(String ID, String name, String productDescription, String price, Image image) {
        this.ID = ID;
        this.name = name;
        this.productDescription = productDescription;
        this.price = price;
        this.image = image;
    }

    protected Data(Parcel in) {
        ID = in.readString();
        name = in.readString();
        productDescription = in.readString();
        price = in.readString();
        image = (Image) in.readValue(Image.class.getClassLoader());
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ID);
        dest.writeString(name);
        dest.writeString(productDescription);
        dest.writeString(price);
        dest.writeValue(image);
    }

    public static class Image implements Parcelable {
        @SuppressWarnings("unused")
        public static final Parcelable.Creator<Image> CREATOR = new Parcelable.Creator<Image>() {
            @Override
            public Image createFromParcel(Parcel in) {
                return new Image(in);
            }

            @Override
            public Image[] newArray(int size) {
                return new Image[size];
            }
        };
        @SerializedName("link")
        String link;
        @SerializedName("height")
        String height;
        @SerializedName("width")
        String width;

        public Image(String link, String height, String width) {
            this.link = link;
            this.height = height;
            this.width = width;
        }

        protected Image(Parcel in) {
            link = in.readString();
            height = in.readString();
            width = in.readString();
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(link);
            dest.writeString(height);
            dest.writeString(width);
        }
    }
}
