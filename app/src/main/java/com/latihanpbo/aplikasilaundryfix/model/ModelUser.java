package com.latihanpbo.aplikasilaundryfix.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_user")
public class ModelUser implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "username")
    public String username;

    @ColumnInfo(name = "password")
    public String password;

    @ColumnInfo(name = "birthday")
    public String birthday;

    @ColumnInfo(name = "fullname")
    public String fullname;

    public ModelUser() {
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday){
        this.birthday = birthday;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname){
        this.fullname = fullname;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.uid);
        dest.writeString(this.username);
        dest.writeString(this.password);

    }

    protected ModelUser(Parcel in) {
        this.uid = in.readInt();
        this.username = in.readString();
        this.password = in.readString();
    }

    public static final Parcelable.Creator<ModelUser> CREATOR = new Parcelable.Creator<ModelUser>() {
        @Override
        public ModelUser createFromParcel(Parcel source) {
            return new ModelUser(source);
        }

        @Override
        public ModelUser[] newArray(int size) {
            return new ModelUser[size];
        }
    };

}
