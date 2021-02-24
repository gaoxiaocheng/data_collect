package com.machine.record.core;

import com.machine.record.quartz.*;
import org.springframework.context.annotation.Configuration;

/**
 * 定时任务配置类
 */
@Configuration
public class QuartzConfig {
//    @Bean
//    public JobDetail commonUrlQuartz() {
//        return JobBuilder.newJob(CommonUrlQuartz.class)
//                .withIdentity("commonurl")
//                .storeDurably().build();
//    }
//
//    @Bean
//    public JobDetail wechatHistoryQuartz() {
//        return JobBuilder.newJob(WechatHistoryQuartz.class)
//                .withIdentity("wechat_history")
//                .storeDurably().build();
//    }
//    @Bean
//    public JobDetail CommonSqlQuartz() {
//        return JobBuilder.newJob(CommonsqlQuartz.class)
//                .withIdentity("commonsql")
//                .storeDurably().build();
//    }
//
//    @Bean
//    public JobDetail SixCommonSqlQuartz() {
//        return JobBuilder.newJob(SixCommonsqlQuartz.class)
//                .withIdentity("sixcommonsql")
//                .storeDurably().build();
//    }
//    @Bean
//    public JobDetail SevenCommonSqlQuartz() {
//        return JobBuilder.newJob(SevenCommonsqlQuartz.class)
//                .withIdentity("sevencommonsql")
//                .storeDurably().build();
//    }
//    @Bean
//    public JobDetail EightCommonSqlQuartz() {
//        return JobBuilder.newJob(EightCommonsqlQuartz.class)
//                .withIdentity("eightcommonsql")
//                .storeDurably().build();
//    }
//    @Bean
//    public JobDetail NineCommonSqlQuartz() {
//        return JobBuilder.newJob(NineCommonsqlQuartz.class)
//                .withIdentity("ninecommonsql")
//                .storeDurably().build();
//    }
//    @Bean
//    public JobDetail HalfTwelveCommonSqlQuartz() {
//        return JobBuilder.newJob(HalfTwelveCommonsqlQuartz.class)
//                .withIdentity("halftwelvecommonsql")
//                .storeDurably().build();
//    }
//
//
//
//
//
//    @Bean
//    public JobDetail CommonCubeQuartz() {
//        return JobBuilder.newJob(CommonCubeQuartz.class)
//                .withIdentity("commoncube")
//                .storeDurably().build();
//    }
//
//    @Bean
//    public JobDetail wechatRealTimeQuartz() {
//        return JobBuilder.newJob(WechatRealTimeQuartz.class)
//                .withIdentity("wechat_realtime")
//                .storeDurably().build();
//    }
//
//    @Bean
//    public Trigger commonUrlTrigger() {
//        Trigger trigger = TriggerBuilder.newTrigger()
//                .forJob(commonUrlQuartz())
//                .withIdentity("commonurl")
//                .startNow()
//                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 6-20/1 * * ? *"))
//                .build();
//        return trigger;
//    }
//
//    @Bean
//    public Trigger WechatHistoryTrigger() {
//        Trigger trigger = TriggerBuilder.newTrigger()
//                .forJob(wechatHistoryQuartz())
//                .withIdentity("wechat_history")
//                .startNow()
//                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 8 * * ? *"))
//                .build();
//        return trigger;
//    }
//
//    @Bean
//    public Trigger CommonsqlTrigger() {
//        Trigger trigger = TriggerBuilder.newTrigger()
//                .forJob(CommonSqlQuartz())
//                .withIdentity("commonsql")
//                .startNow()
//                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 7,10,13,16,19,22 * * ? *"))
//                .build();
//        return trigger;
//    }
//    @Bean
//    public Trigger SixCommonsqlTrigger() {
//        Trigger trigger = TriggerBuilder.newTrigger()
//                .forJob(SixCommonSqlQuartz())
//                .withIdentity("sixcommonsql")
//                .startNow()
//                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 6 * * ? *"))
//                .build();
//        return trigger;
//    }
//    @Bean
//    public Trigger SevenCommonsqlTrigger() {
//        Trigger trigger = TriggerBuilder.newTrigger()
//                .forJob(SevenCommonSqlQuartz())
//                .withIdentity("sevencommonsql")
//                .startNow()
//                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 7 * * ? *"))
//                .build();
//        return trigger;
//    }
//    @Bean
//    public Trigger EightCommonsqlTrigger() {
//        Trigger trigger = TriggerBuilder.newTrigger()
//                .forJob(EightCommonSqlQuartz())
//                .withIdentity("eightcommonsql")
//                .startNow()
//                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 8 * * ? *"))
//                .build();
//        return trigger;
//    }
//
//
//    @Bean
//    public Trigger NineCommonsqlTrigger() {
//        Trigger trigger = TriggerBuilder.newTrigger()
//                .forJob(NineCommonSqlQuartz())
//                .withIdentity("ninecommonsql")
//                .startNow()
//                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 9 * * ? *"))
//                .build();
//        return trigger;
//    }
//
//
//    @Bean
//    public Trigger halfTwelveCommonsqlTrigger() {
//        Trigger trigger = TriggerBuilder.newTrigger()
//                .forJob(HalfTwelveCommonSqlQuartz())
//                .withIdentity("halftwelvecommonsql")
//                .startNow()
//                .withSchedule(CronScheduleBuilder.cronSchedule("0 30 12 * * ? *"))
//                .build();
//        return trigger;
//    }
//    @Bean
//    public Trigger CommonCubeTrigger() {
//        Trigger trigger = TriggerBuilder.newTrigger()
//                .forJob(CommonCubeQuartz())
//                .withIdentity("commoncube")
//                .startNow()
//                .withSchedule(CronScheduleBuilder.cronSchedule("0 20 8 * * ? *"))
//                .build();
//        return trigger;
//    }
//
//    @Bean
//    public Trigger WechatRealtimeTrigger() {
//        Trigger trigger = TriggerBuilder.newTrigger()
//                .forJob(wechatRealTimeQuartz())
//                .withIdentity("wechat_realtime")
//                .startNow()
//                //.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever())
//                .withSchedule(CronScheduleBuilder.cronSchedule("0 5 * * * ? *"))
//                .build();
//        return trigger;
//    }
}
