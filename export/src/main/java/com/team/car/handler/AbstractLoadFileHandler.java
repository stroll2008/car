package com.team.car.handler;

import java.io.File;
import java.util.List;

/**
 * Created by ysw on 14-12-14.
 */
public abstract class AbstractLoadFileHandler {

    public abstract List<String> getFiles();

    public abstract File loadFile(List<String> list);

    public abstract void outFile();

    public  abstract void doFile();

    public void handle(){
        try {
            loadFile(getFiles());
            doFile();
            outFile();
        }catch (Exception e){
          e.printStackTrace();
        }

    }
}
