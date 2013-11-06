package com.noway.b365crawl.test;

import java.util.Timer;

public class EggTimer {
    private final Timer timer = new Timer();

    private final int minutes;

    // 编写自己的任务方法，执行具体任务。
    public class MyTask extends java.util.TimerTask {

        @Override
        public void run() {
            playSound();
            // 终止此计时器，丢弃所有当前已安排的任务。
            //timer.cancel();
        }
    }

    // 构造方法，定义执行时间
    public EggTimer(int minutes) {
        //this.minutes = minutes * 1000 * 60;
    	this.minutes = minutes * 100 * 60;
    }

    // 调用并执行任务
    public void start() {
        timer.schedule(new MyTask(), 0, minutes);
    }

    protected void playSound() {
        System.out.println("Your egg is ready!");
        // Start a new thread to play a sound...
    }
    
    // 主程序入口
    public static void main(String[] args) {
        EggTimer eggTimer = new EggTimer(1);
        eggTimer.start();
    }
}

