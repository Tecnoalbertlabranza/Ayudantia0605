package com.EjercicioAyudantia.ISoft.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.EjercicioAyudantia.ISoft.model.Task;

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
    
    public List<Task> findByFilters(String prioridad, String titulo, String fechaLimite) {
        return tasks.stream()
        
            .filter(t -> prioridad == null || t.getPrioridad().equalsIgnoreCase(prioridad))

            .filter(t -> titulo == null || t.getTitulo().toLowerCase().contains(titulo.toLowerCase()))

            .filter(t -> fechaLimite == null || t.getFechaLimite().equals(fechaLimite))
            .collect(Collectors.toList());
    }

    public List<Task> getAll() {
        return tasks;
    }

    public Task complete(Long id) {
    return tasks.stream()
            .filter(t -> t.getId().equals(id))
            .findFirst()
            .map(t -> {
                t.setCompletada(true);
                return t;
            }).orElse(null);
}
}