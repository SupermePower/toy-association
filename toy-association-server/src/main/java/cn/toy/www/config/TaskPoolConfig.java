package cn.toy.www.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.*;

/**
 * 线程池配置
 */
@Configuration
@EnableAsync
public class TaskPoolConfig {

    public static final String TASK_EXECUTOR = "taskExecutor";

    @Bean(TASK_EXECUTOR)
    public Executor taskExecutor() {
        final int corePoolSize = 2;
        final int maximumPoolSize = 4;
        final long keepAliveTime = 1;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(10);
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("taskExecutor shooting --- %d").setDaemon(false).build();
        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                workQueue,
                threadFactory,
                handler
        );

        return threadPoolExecutor;
    }
}
