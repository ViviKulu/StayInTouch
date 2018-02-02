package backend;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.babimaji.stayintouch.model.Fellow;


@Database(entities = {Fellow.class},version = 4)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FellowDao fellowDao();
}
