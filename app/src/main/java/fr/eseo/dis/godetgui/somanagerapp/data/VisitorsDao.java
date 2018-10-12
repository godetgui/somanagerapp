package fr.eseo.dis.godetgui.somanagerapp.data;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface VisitorsDao {

    @Query("SELECT * FROM visitors")
    public List<Visitors> dindAllVisitors();

    @Query("SELECT * FROM visitors WHERE idVisitor = :idVisitors")
    public Comm findVisitorsFromId(int idVisitors);
}

