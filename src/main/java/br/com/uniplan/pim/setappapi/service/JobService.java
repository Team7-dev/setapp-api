package br.com.uniplan.pim.setappapi.service;

import br.com.uniplan.pim.setappapi.dto.JobDto;
import br.com.uniplan.pim.setappapi.dto.TaskDto;
import br.com.uniplan.pim.setappapi.entity.Job;
import br.com.uniplan.pim.setappapi.entity.Task;
import br.com.uniplan.pim.setappapi.exception.BusinessException;
import br.com.uniplan.pim.setappapi.exception.FieldCannotBeNullException;
import br.com.uniplan.pim.setappapi.exception.FieldMustBeNullException;
import br.com.uniplan.pim.setappapi.exception.ResourceCannotBeNullException;
import br.com.uniplan.pim.setappapi.exception.ResourceNotFoundException;
import br.com.uniplan.pim.setappapi.exception.UniqueFieldContraintException;
import br.com.uniplan.pim.setappapi.repository.JobRepository;
import br.com.uniplan.pim.setappapi.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class JobService {

    private JobRepository jobRepository;

    @Autowired
    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<JobDto> findAll() {
        List<JobDto> jobsDto = new ArrayList<>();
        List<Job> jobs = jobRepository.findAll();
        for (Job job : jobs) {
            jobsDto.add(createJobDtoFromJobEntity(job));
        }
        return jobsDto;
    }

    public JobDto findById(Long id) {
        if (id == null) {
            throw new FieldCannotBeNullException("id");
        }
        Job job = jobRepository.findById(id);
        if (job == null) {
            throw new ResourceNotFoundException("job", id.toString());
        }
        JobDto jobDto = createJobDtoFromJobEntity(job);
        return jobDto;
    }

    private JobDto createJobDtoFromJobEntity(Job job) {
        JobDto jobDto = new JobDto();
        jobDto.setId(job.getId());
        jobDto.setName(job.getName());
        jobDto.setActive(job.getActive());
        if (job.getParentJob() != null) {
            jobDto.setParentJob(new JobDto());
            jobDto.getParentJob().setId(job.getParentJob().getId());
            jobDto.getParentJob().setName(job.getParentJob().getName());
            jobDto.getParentJob().setActive(job.getParentJob().getActive());
        }
        jobDto.setTasks(new ArrayList<>());
        for (Task task : job.getTasks()) {
            TaskDto taskDto = new TaskDto();
            taskDto.setId(task.getId());
            taskDto.setName(task.getName());
            taskDto.setWeight(task.getWeight());
            taskDto.setCompleted(task.getCompleted());
            taskDto.setCreatedAt(DateUtil.getStringFromDate(task.getCreatedAt()));
            jobDto.getTasks().add(taskDto);
        }
        return jobDto;
    }

    public Long create(JobDto jobDto) {
        validateOnCreate(jobDto);
        Job job = new Job();
        return jobRepository.persist(job);
    }

    private void validateOnCreate(JobDto jobDto) {
        if (jobDto == null) {
            throw new ResourceCannotBeNullException();
        }
        if (jobDto.getId() != null) {
            throw new FieldMustBeNullException("id");
        }
        if (StringUtils.isBlank(jobDto.getName())) {
            throw new FieldCannotBeNullException("name");
        } else {
            Long count = jobRepository.countJobsByName(jobDto.getName());
            if (count > 0) {
                throw new UniqueFieldContraintException("name");
            }
        }
        if (jobDto.getParentJob() != null) {
            if (jobDto.getParentJob().getId() == null) {
                throw new FieldCannotBeNullException("parentJobDto.id");
            }
            Job job = jobRepository.findById(jobDto.getParentJob().getId());
            if (job == null) {
                throw new ResourceNotFoundException("job", jobDto.getParentJob().getId().toString());
            }
        }
    }

    public void update(JobDto jobDto) {
        Job job = new Job();
        Job parentJob = new Job();
        validateOnUpdate(jobDto, job, parentJob);

        job.setName(jobDto.getName());
        job.setActive(jobDto.getActive());
        job.setParentJob(parentJob);

        if (jobDto.getTasks() == null || jobDto.getTasks().isEmpty()) {
            job.setTasks(null);
        } else {
            removeDeletedTasks(job, jobDto);
            includeNewTasks(job, jobDto);
            updateExistingTasks(job, jobDto);
        }

        jobRepository.merge(job);
    }

    private void removeDeletedTasks(Job job, JobDto jobDto) {
        Iterator<Task> iterator = job.getTasks().iterator();
        while (iterator.hasNext()) {
            Task task = iterator.next();
            boolean taskWasDeleted = true;
            for (TaskDto taskDto : jobDto.getTasks()) {
                if (task.getId().equals(taskDto.getId())) {
                    taskWasDeleted = false;
                    break;
                }
            }
            if (taskWasDeleted) {
                iterator.remove();
            }
        }
    }

    private void includeNewTasks(Job job, JobDto jobDto) {
        for (TaskDto taskDto : jobDto.getTasks()) {
            if (taskDto.getId() == null) {
                Task task = new Task();
                updateTaskPropertiesFromTaskDto(task, taskDto);
                job.getTasks().add(task);
            }
        }
    }

    private void updateTaskPropertiesFromTaskDto(Task task, TaskDto taskDto) {
        task.setId(taskDto.getId());
        task.setName(taskDto.getName());
        task.setWeight(taskDto.getWeight());
        task.setCompleted(taskDto.getCompleted());
        task.setCreatedAt(DateUtil.getDateFromString(taskDto.getCreatedAt(), "createdAt"));
    }

    private void updateExistingTasks(Job job, JobDto jobDto) {
        for (TaskDto taskDto : jobDto.getTasks()) {
            if (taskDto.getId() != null) {
                Task task = getTaskFromList(taskDto.getId(), job.getTasks());
                if (task != null) {
                    updateTaskPropertiesFromTaskDto(task, taskDto);
                } else {
                    String[] objects = {taskDto.getId().toString(), job.getName()};
                    throw new BusinessException("task.not.associated.with.job", objects);
                }
            }
        }
    }

    private Task getTaskFromList(Long id, List<Task> tasks) {
        Task taskFromList = null;
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                taskFromList = task;
                break;
            }
        }
        return taskFromList;
    }

    private void validateOnUpdate(JobDto jobDto, Job job, Job parentJob) {
        if (jobDto == null) {
            throw new ResourceCannotBeNullException();
        }
        if (jobDto.getId() == null) {
            throw new FieldCannotBeNullException("id");
        }
        job = jobRepository.findById(jobDto.getId());
        if (job == null) {
            throw new ResourceNotFoundException("job", jobDto.getId().toString());
        }
        if (StringUtils.isBlank(jobDto.getName())) {
            throw new FieldCannotBeNullException("name");
        } else {
            Long count = jobRepository.countJobsByNameExceptWithId(jobDto.getName(), jobDto.getId());
            if (count > 0) {
                throw new UniqueFieldContraintException("name");
            }
        }
        if (jobDto.getParentJob() != null) {
            if (jobDto.getParentJob().getId() == null) {
                throw new FieldCannotBeNullException("parentJob.id");
            }
            if (jobDto.getId().equals(jobDto.getParentJob().getId())) {
                throw new BusinessException("job.cannot.reference.itself");
            }
            parentJob = jobRepository.findById(jobDto.getParentJob().getId());
            if (parentJob == null) {
                throw new ResourceNotFoundException("job", jobDto.getParentJob().getId().toString());
            }
            if (parentJob.getParentJob() != null && parentJob.getParentJob().getId().equals(jobDto.getId())) {
                throw new BusinessException("cross.dependencies.between.jobs");
            }
        }
    }

    public void deleteById(Long id) {
        if (id == null) {
            throw new FieldCannotBeNullException("id");
        }
        Job job = jobRepository.findById(id);
        if (job == null) {
            throw new ResourceNotFoundException("job", id.toString());
        }
        jobRepository.remove(job);
    }

}
