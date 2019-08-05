package com.study.socket.api;

import io.netty.handler.codec.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.yeauty.annotation.*;
import org.yeauty.pojo.Session;

/**
 * websocket的具体动作
 *
 * @author luojihui
 * @date 2019/8/5
 */
@Component
@ServerEndpoint("/myWebSocket")
public class MyWebSocket {

    @OnOpen
    public void onOpen(Session session, HttpHeaders headers) {
        session.sendText("开启连接");
        System.out.println("new connection");
    }

    @OnClose
    public void onClose(Session session) {
        session.sendText("关闭连接");
        System.out.println("one connection closed");
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
    }

    @OnMessage
    public void OnMessage(Session session, String message) {
        System.out.println(message);
        session.sendText(message);
    }
}
