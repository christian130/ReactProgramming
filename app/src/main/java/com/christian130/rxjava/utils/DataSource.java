package com.christian130.rxjava.utils;

import com.christian130.rxjava.models.Task;

import java.util.ArrayList;
import java.util.List;

public class DataSource {

    public static List<Task> createTaskList(){
        List<Task> tasks = new ArrayList<Task>();
        tasks.add(new Task("description 0",true,3));
        tasks.add(new Task("description 1",false,2));
        tasks.add(new Task("description 2",true,1));
        tasks.add(new Task("description 3",false,0));
        tasks.add(new Task("description 4",true,5));

        return tasks;
    }
}
