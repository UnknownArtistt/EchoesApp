package model;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import org.checkerframework.common.aliasing.qual.Unique;

import java.util.Date;

@Entity(tableName = "reminder", indices = @Index(value = {"id"}, unique = true))
public class Reminder {

    @PrimaryKey(autoGenerate = true)
    int id;
    @Unique
    String text;
    String time;
    String username;

    public Reminder(@Unique String text, String time, String username) {
        this.text = text;
        this.time = time;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
