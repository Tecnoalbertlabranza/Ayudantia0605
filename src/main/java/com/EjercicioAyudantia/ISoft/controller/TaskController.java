package com.EjercicioAyudantia.ISoft.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;        

import com.EjercicioAyudantia.ISoft.model.Task;
import com.EjercicioAyudantia.ISoft.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.save(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getTasks(
            @RequestParam(required = false) String prioridad,
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String fechaLimite) {
        
        // Retorna la lista filtrada o completa si no hay parámetros
        List<Task> filteredTasks = taskService.findByFilters(prioridad, titulo, fechaLimite);
        return new ResponseEntity<>(filteredTasks, HttpStatus.OK);
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<Task> completeTask(@PathVariable Long id) {
        Task task = taskService.complete(id);
        return task != null ? ResponseEntity.ok(task) : ResponseEntity.notFound().build();
    }
}