package data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import model.Pictogram;

@Dao
public interface PictogramDAO {

    @Query("SELECT * FROM pictogram")
    LiveData<List<Pictogram>> getPictograms();

    @Query("SELECT * FROM pictogram")
    List<Pictogram> getPictogramsSync();

    @Query("SELECT * FROM pictogram WHERE description = :pictogram")
    LiveData<Pictogram> getPictogram(String pictogram);

    @Query("SELECT * FROM pictogram WHERE typeId = :typeId")
    LiveData<List<Pictogram>> getFilter(int typeId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Pictogram pictogram);

    @Update
    void update(Pictogram pictogram);

    @Delete
    void delete(Pictogram pictogram);

    @Query("SELECT * FROM pictogram WHERE description LIKE '%' || :t || '%'")
    LiveData<List<Pictogram>> search(String t);
}
