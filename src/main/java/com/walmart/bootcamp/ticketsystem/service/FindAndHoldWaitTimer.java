package com.walmart.bootcamp.ticketsystem.service;

import java.util.TimerTask;
import java.util.Timer;

public class FindAndHoldWaitTimer  {
        Timer timer = new Timer();
        Boolean HoldTimer = false;
        TimerTask task = new TimerTask() {
                @Override
                public void run() {
                       HoldTimer = true;
                }
        };

       // timer.schedule(this, 0);
}
