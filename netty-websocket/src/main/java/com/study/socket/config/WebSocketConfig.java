package com.study.socket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yeauty.standard.ServerEndpointExporter;

/**
 * 配置websocket
 *
 * @author luojihui
 * @date 2019/8/5
 */
@Configuration
public class WebSocketConfig {

    /**
     * 启用WebSocket功能
     *
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
