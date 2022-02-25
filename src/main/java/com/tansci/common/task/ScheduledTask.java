package com.tansci.common.task;

import com.tansci.common.constant.Constants;
import com.tansci.domain.system.TaskConfig;
import com.tansci.service.system.TaskContextService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;

/**
 * @ClassName： ScheduledTask.java
 * @ClassPath： com.tansci.common.task.ScheduledTask.java
 * @Description： 定时任务
 * @Author： tanyp
 * @Date： 2022/2/25 9:30
 **/
@Slf4j
@Component
public class ScheduledTask implements SchedulingConfigurer {

    private volatile ScheduledTaskRegistrar registrar;

    private final ConcurrentHashMap<String, ScheduledFuture<?>> scheduledFutures = new ConcurrentHashMap<>();

    private final ConcurrentHashMap<String, CronTask> cronTasks = new ConcurrentHashMap<>();

    @Autowired
    private TaskContextService taskContextService;

    @Override
    public void configureTasks(ScheduledTaskRegistrar registrar) {
        registrar.setScheduler(Executors.newScheduledThreadPool(Constants.DEFAULT_THREAD_POOL));
        this.registrar = registrar;
    }

    @PreDestroy
    public void destroy() {
        this.registrar.destroy();
    }

    /**
     * @MonthName： refreshTask
     * @Description： 初始化任务
     * 1、从数据库获取执行任务的集合【TaskConfig】
     * 2、通过调用 【refresh】 方法刷新任务列表
     * 3、每次数据库中的任务发生变化后重新执行【1、2】
     * @Author： tanyp
     * @Date： 2022/2/25 9:42
     * @Param： [tasks]
     * @return： void
     **/
    public void refreshTask(List<TaskConfig> tasks) {
        // 删除已经取消任务
        scheduledFutures.keySet().forEach(key -> {
            if (Objects.isNull(tasks) || tasks.size() == 0) {
                scheduledFutures.get(key).cancel(false);
                scheduledFutures.remove(key);
                cronTasks.remove(key);
                return;
            }
            tasks.forEach(task -> {
                if (!Objects.equals(key, task.getTaskId())) {
                    scheduledFutures.get(key).cancel(false);
                    scheduledFutures.remove(key);
                    cronTasks.remove(key);
                    return;
                }
            });
        });

        // 添加新任务、更改执行规则任务
        tasks.forEach(item -> {
            String expression = item.getExpression();
            // 任务表达式为空则跳过
            if (StringUtils.isEmpty(expression)) {
                return;
            }

            // 任务已存在并且表达式未发生变化则跳过
            if (scheduledFutures.containsKey(item.getTaskId()) && cronTasks.get(item.getTaskId()).getExpression().equals(expression)) {
                return;
            }

            // 任务执行时间发生了变化，则删除该任务
            if (scheduledFutures.containsKey(item.getTaskId())) {
                scheduledFutures.get(item.getTaskId()).cancel(false);
                scheduledFutures.remove(item.getTaskId());
                cronTasks.remove(item.getTaskId());
            }

            CronTask task = new CronTask(new Runnable() {
                @Override
                public void run() {
                    // 执行业务逻辑
                    try {
                        log.info("====执行单个任务，任务ID【{}】执行规则【{}】=======", item.getTaskId(), item.getExpression());
                        taskContextService.execute(item.getCode());
                    } catch (Exception e) {
                        log.error("执行任务异常，异常信息：{}", e);
                    }
                }
            }, expression);
            ScheduledFuture<?> future = registrar.getScheduler().schedule(task.getRunnable(), task.getTrigger());
            cronTasks.put(item.getTaskId(), task);
            scheduledFutures.put(item.getTaskId(), future);
        });
    }

}
