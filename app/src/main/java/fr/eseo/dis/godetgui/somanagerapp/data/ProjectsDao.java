package fr.eseo.dis.godetgui.somanagerapp.data;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ProjectsDao {

    @Query("SELECT * FROM projects")
    public List<Projects> findAllProjects();

    @Query("SELECT * FROM projects WHERE idProject = :idProject")
    public Projects findProjectsFromId(int idProject);


}

