package data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import model.Reminder;

@Dao
public interface ReminderDAO {

    @Query("SELECT * FROM reminder")
    LiveData<List<Reminder>> getReminders();

    @Query("SELECT * FROM reminder WHERE username = :username")
    LiveData<List<Reminder>> getReminder(String username);

    @Query("SELECT password FROM user WHERE UPPER(username) = UPPER(:user)")
    String getPasswordLogin(String user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Reminder reminder);

    @Update
    void update(Reminder reminder);

    @Delete
    void delete(Reminder reminder);
}
