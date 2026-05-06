package com.EjercicioAyudantia.ISoft.service;

import com.EjercicioAyudantia.ISoft.model.Task;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {
    private final List<Task> tasks = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong(1);

    public Task save(Task task) {
        task.setId(counter.getAndIncrement());
        task.setCompletada(false); 
        tasks.add(task);
        return task;
    }

    public List<Task> getAll() {
        return tasks;
    }
}