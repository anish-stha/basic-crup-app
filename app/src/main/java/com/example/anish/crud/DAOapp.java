package com.example.anish.crud;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import com.example.anish.crud.Models.DaoMaster;
import com.example.anish.crud.Models.DaoSession;
import org.greenrobot.greendao.database.Database;

public class DAOapp extends  Application{

    static DaoSession daoSession;
    private static DAOapp instance;
    //switch between sqlite and sqlcipher
    private static final boolean ENCRYPTED = false;

    //Get app instance
    public static DAOapp getInstance(){
        if(instance==null)
            Log.d("appInstance", "getInstance: Null instance");
        return instance;
    }
    public static DAOapp get(Context context){return (DAOapp) context.getApplicationContext();}

    @Override
    public void onCreate(){
        super.onCreate();
        initDaoSession();
        instance = this;
    }

    private void initDaoSession() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "hinterdbencrypted" : "hinterdb");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession(){

        return daoSession;
    }

}

