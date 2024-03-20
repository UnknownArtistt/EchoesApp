package data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import model.Pictogram;
import model.Reminder;
import model.User;

@Database(entities = {User.class, Reminder.class, Pictogram.class}, version = 9, exportSchema = false)
public abstract class DBAccess extends RoomDatabase {

    private static volatile DBAccess INSTANCE;

    public abstract UserDAO getUserDAO();
    public abstract ReminderDAO getReminderDAO();
    public abstract PictogramDAO getPictogramDAO();

    public static DBAccess obtainInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized ((DBAccess.class)) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context,
                                    DBAccess.class, "echoesdb.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}


