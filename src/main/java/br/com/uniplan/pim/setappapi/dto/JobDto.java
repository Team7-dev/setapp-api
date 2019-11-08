package br.com.uniplan.pim.setappapi.dto;

import java.util.List;

public class JobDto {

    private Long id;

    private String name;

    private Boolean active;

    private JobDto parentJob;

    private List<TaskDto> tasks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public JobDto getParentJob() {
        return parentJob;
    }

    public void setParentJob(JobDto parentJob) {
        this.parentJob = parentJob;
    }

    public List<TaskDto> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDto> tasks) {
        this.tasks = tasks;
    }

}
