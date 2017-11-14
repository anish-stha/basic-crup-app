package com.example.anish.crud.Services;

import android.util.Log;

import com.example.anish.crud.Models.DaoSession;
import com.example.anish.crud.Models.Hinter;
import com.example.anish.crud.Models.HinterDao;
import com.example.anish.crud.DAOapp;

import java.util.List;


public class DAOservices {
    private HinterDao hinterDao;

    public DAOservices(){
        DaoSession daoSession = DAOapp.getInstance().getDaoSession();
        this.hinterDao = daoSession.getHinterDao();
    }

    public Boolean insertInDB(Hinter hinter) {
        System.out.print(hinter.getId());

        try {
            hinterDao.insert(hinter);
            return true;
        }catch(Exception e)
        {
            System.out.println("Insert Exception:"+ e.getMessage());
            return false;
        }
    }

    public Hinter readSingleRecordFromDB(long hintId) {
        return hinterDao.load(hintId);
    }


    public int getCountFromDB(){
        return (int)hinterDao.count();
    }


    public List<Hinter> readAllFromDB(){
        return hinterDao.loadAll();
    }

    public boolean updateInDB(Hinter hinter){
        try {
            hinterDao.update(hinter);
            return true;
        }catch(Exception e)
        {
            System.out.println("UpdateInDB Exception:"+ e.getMessage());
            return false;
        }
    }
    public boolean deleteInDB(long hintId) {

        try {
            hinterDao.deleteByKey(hintId);
            return true;
        }catch(Exception e)
        {
            System.out.println("Deleting exception:"+ e.getMessage());
            return false;
        }
    }

    public List<Hinter> searchByWebsiteInDB(String name){
        return hinterDao.queryBuilder().where(HinterDao.Properties.Website.like(name + "%")).orderAsc().list();
    }
}
