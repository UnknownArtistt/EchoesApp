package data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import model.User;

@Dao
public interface UserDAO {

    @Query("SELECT * FROM user")
    LiveData<List<User>> getUsers();

    @Query("SELECT * FROM user WHERE username = :user")
    LiveData<User> getUser(String user);

    @Query("SELECT password FROM user WHERE UPPER(username) = UPPER(:user)")

    String getPasswordLogin(String user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);
}
