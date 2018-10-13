package fr.eseo.dis.godetgui.somanagerapp.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.media.Image;
import android.support.annotation.NonNull;

@Entity(tableName = "projects")
public class Projects {

    @PrimaryKey
    @NonNull
    private int idProject;

    @NonNull
    private String nom;


    @NonNull
    private boolean isConfidential;

    @NonNull
    private String poster;


    @NonNull
    private boolean isDone;

    public Projects(@NonNull int idProject, @NonNull String nom, @NonNull boolean isConfidential, @NonNull String poster,@NonNull boolean isDone) {
        this.idProject = idProject;
        this.nom = nom;
        this.isConfidential = isConfidential;
        this.poster = poster;
        this.isDone = isDone;
    }

    @NonNull
    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(@NonNull int idProject) {
        this.idProject = idProject;
    }

    @NonNull
    public String getNom() {
        return nom;
    }

    public void setNom(@NonNull String nom) {
        this.nom = nom;
    }

    @NonNull
    public boolean isConfidential() {
        return isConfidential;
    }

    public void setConfidential(@NonNull boolean confidential) {
        isConfidential = confidential;
    }

    @NonNull
    public String getPoster() {
        return poster;
    }

    public void setPoster(@NonNull String poster) {
        this.poster = poster;
    }

    @NonNull
    public boolean isDone() {
        return isDone;
    }

    public void setDone(@NonNull boolean done) {
        isDone = done;
    }
}
