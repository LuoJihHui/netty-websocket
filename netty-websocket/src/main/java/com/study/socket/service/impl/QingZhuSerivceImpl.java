package com.study.socket.service.impl;

import com.study.socket.service.QingZhuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 业务逻辑实现
 *
 * @author luojihui
 * @date 2019/8/7
 */
@Service
public class QingZhuSerivceImpl implements QingZhuService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(QingZhuSerivceImpl.class);

    /**
     * 测试
     */
    @Override
    public String test() {
        logger.info("这是具体业务逻辑层！");
        return "这里是具体业务逻辑！";
    }
}
