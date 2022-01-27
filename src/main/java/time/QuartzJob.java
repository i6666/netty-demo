package time;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 用quartz来实现
 * @author zhuang.ma
 * @date 2021/9/9
 */
public class QuartzJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("数据库扫描开启==========>>>>>>>>>>>>>");
    }

    public static void main(String[] args) throws SchedulerException {
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(QuartzJob.class).withIdentity("job1", "group1").build();

        //触发器,每三秒执行一次
        SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group3")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever()).build();


        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        //将任务及触发器放入调度器
        scheduler.scheduleJob(jobDetail,simpleTrigger);

        //调度器开始调度任务
        scheduler.start();

    }

}
