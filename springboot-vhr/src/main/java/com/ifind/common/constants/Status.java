package com.ifind.common.constants;

import java.util.concurrent.TimeUnit;

/**
 * 状态枚举
 *
 * @author yidujun
 * @date 2020/4/10 16:43
 */
public abstract class Status {

    /**
     * 过期时间相关枚举
     */
    public static enum ExpireEnum {
        // 未读消息的有效期为30天
        UNREAD_MSG(30L, TimeUnit.DAYS),
        // 图表数据的有效期为一天
        PICTURE_DATA(60 * 60 * 24L, TimeUnit.SECONDS);


        /**
         * 过期时间
         */
        private Long time;

        /**
         * 时间单位
         */
        private TimeUnit timeUnit;

        ExpireEnum(Long time, TimeUnit timeUnit) {
            this.time = time;
            this.timeUnit = timeUnit;
        }

        public Long getTime() {
            return time;
        }

        public TimeUnit getTimeUnit() {
            return timeUnit;
        }
    }
}
