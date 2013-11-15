package com.noway.b365crawl.test;

import java.util.Timer;

import com.noway.b365crawl.B365Crawl;

public class EggTimer {
    private final Timer timer = new Timer();

    private final int second;

    // 编写自己的任务方法，执行具体任务。
    public class MyTask extends java.util.TimerTask {

        @Override
        public void run() {
            playSound();
            // 终止此计时器，丢弃所有当前已安排的任务。
            //timer.cancel();
            
            B365Crawl b365Crawl = new B365Crawl();
            b365Crawl.run();
        }
    }

    // 构造方法，定义执行时间
    public EggTimer(int second) {
        //this.minutes = minutes * 1000 * 60;
    	this.second = second * 1000;
    }

    // 调用并执行任务
    public void start() {
        timer.schedule(new MyTask(), 0, second);
    }

    protected void playSound() {
        System.out.println("Your egg is ready!");
        // Start a new thread to play a sound...
    }
    
    // 主程序入口
    public static void main(String[] args) {
        EggTimer eggTimer = new EggTimer(60);
        eggTimer.start();
    }
}

