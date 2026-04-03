package com.url.shortner.service;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Stat;
import org.springframework.stereotype.Service;

@Service
public class RangeIdService {

    private final CuratorFramework client;

    private static final String COUNTER_PATH = "/shortener/counter";
    private static final int RANGE_SIZE = 1000;

    private long current = 0;
    private long max = -1;

    public RangeIdService(CuratorFramework client) {
        this.client = client;
        init();
    }

    private void init() {
        try {
            if (client.checkExists().forPath(COUNTER_PATH) == null) {
                client.create().creatingParentsIfNeeded()
                        .forPath(COUNTER_PATH, "0".getBytes());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public synchronized long nextId() {
        if (current > max) {
            allocateRange();
        }
        return current++;
    }
    private void allocateRange() {
        try {
            while (true) {
                byte[] data = client.getData().forPath(COUNTER_PATH);
                long currentValue = Long.parseLong(new String(data));

                long newValue = currentValue + RANGE_SIZE;

                // optimistic locking using version
                Stat stat = client.checkExists().forPath(COUNTER_PATH);

                try {
                    client.setData()
                            .withVersion(stat.getVersion())
                            .forPath(COUNTER_PATH, String.valueOf(newValue).getBytes());

                    // success → assign range
                    this.current = currentValue + 1;
                    this.max = newValue;

                    return;

                } catch (KeeperException.BadVersionException e) {
                    // retry if another instance updated it
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to allocate ID range", e);
        }
    }
}