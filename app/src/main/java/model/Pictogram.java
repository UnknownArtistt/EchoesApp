package model;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import org.checkerframework.common.aliasing.qual.Unique;

@Entity(tableName = "pictogram", indices = @Index(value = {"id"}, unique = true))
public class Pictogram {

    @PrimaryKey(autoGenerate = true)
    int id;
    @Unique
    int imagePath;
    int description;
    int typeId;

    public Pictogram(@Unique int imagePath, int description, int typeId) {
        this.imagePath = imagePath;
        this.description = description;
        this.typeId = typeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImagePath() {
        return imagePath;
    }

    public void setImagePath(int imagePath) {
        this.imagePath = imagePath;
    }

    public int getDescription() {
        return description;
    }

    public void setDescription(int description) {
        this.description = description;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
}
