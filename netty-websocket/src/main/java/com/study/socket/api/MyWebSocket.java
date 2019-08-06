package com.study.socket.api;

import io.netty.handler.codec.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.yeauty.annotation.*;
import org.yeauty.pojo.Session;

import java.util.concurrent.CopyOnWriteArraySet;

/**
 * websocket的具体动作
 *
 * @author luojihui
 * @date 2019/8/5
 */
@Component
@ServerEndpoint(value = "/myWebSocket", prefix = "netty")
public class MyWebSocket {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(MyWebSocket.class);

    /**
     * concurrent包的线程安全Set，存放每个客户端对应的MyWebSocket对象
     */
    private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    /**
     * 会话管理
     */
    private Session session;

    /**
     * 建立连接后
     *
     * @param session
     * @param headers
     */
    @OnOpen
    public void onOpen(Session session, HttpHeaders headers) {
        this.session = session;
        session.sendText("开启连接");
        webSocketSet.add(this);
        logger.info("有新连接加入!当前在线人数为" + getOnlineCount());
    }

    /**
     * 连接关闭后
     *
     * @param session
     */
    @OnClose
    public void onClose(Session session) {
        session.sendText("关闭连接");
        webSocketSet.remove(this);
        logger.info("有一连接关闭！当前连接人数为" + getOnlineCount());
    }

    /**
     * 发生错误时
     *
     * @param session
     * @param throwable
     */
    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
    }

    /**
     * 接收客户端消息
     *
     * @param session
     * @param message
     */
    @OnMessage
    public void OnMessage(Session session, String message) {
        // 群发消息
        sendInfo(session, message);
        logger.info("接收来自客户端" + session.id() + "的消息:" + message);
    }

    /**
     * 群发消息
     *
     * @param message 消息内容
     */
    private static void sendInfo(Session session, String message) {
        for (MyWebSocket item : webSocketSet) {
            item.sendMessage(session, message);
        }
    }

    /**
     * 推送消息
     *
     * @param message 消息内容
     */
    private void sendMessage(Session session, String message) {
        this.session.sendText("客户端:" + session.id() + "的消息：" + message);
    }

    /**
     * 获取当前在线连接数
     *
     * @return onlineCount 在线连接数
     */
    private static synchronized int getOnlineCount() {
        return webSocketSet.size();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
