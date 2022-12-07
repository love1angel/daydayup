package com.njupt.cn.control;

import com.njupt.cn.model.Person;
import com.njupt.cn.util.ConfigReader;
import com.njupt.cn.view.MainView;

import java.lang.ref.SoftReference;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainController {
    private ConfigReader util = ConfigReader.getInstance();
    private List<Person> persons;
    private MainView view = MainView.getInstance();

    private AtomicBoolean flag = new AtomicBoolean(false);

    public void init() {
        try {
            persons = util.init();
            for (int i = 0; i < persons.size(); i++) {
                System.out.println(persons.get(i));
            }
        } catch (Exception e) {
            System.out.println("init failed");
            return;
        }

        view.setReference(new SoftReference<MainController>(this));
        view.init();
    }

    public void onStartClicked() {
        if (flag.getAcquire()) {
            return;
        }

        flag.setRelease(true);

        Person person = startProgress();
        if (person == null) {
            view.setAwardedInfo("all have awarded, pls click reset");
            return;
        }

        while (flag.getAcquire()) {
            person = startProgress();
            if (person == null)
                view.setAwardedInfo("all have awarded, pls click reset");
            else
                view.setAwardedInfo("正在抽选中: " + person.getName());

            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onEndClicked() {
        if (!flag.getAcquire()) {
            return;
        }

        flag.setRelease(false);
        view.setAwardedInfo("正在结算中...");

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Person person = startProgress();
        if (person == null)
            view.setAwardedInfo("all have awarded, pls click reset");
        else {
            view.setAwardedInfo("中奖者: " + person.getName());
            for (int i = 0; i < persons.size(); i++) {
                if (person.getId() == persons.get(i).getId()) {
                    persons.get(i).setAwarded(true);
                }
            }
        }
    }

    public Person startProgress() {
        int cnt = 0;
        for (int i = 0; i < persons.size(); ++i) {
            if (!persons.get(i).isAwarded())
                ++cnt;
        }
        System.out.println("total not awarded: " + cnt);
        if (cnt == 0) {
            return null;
        }

        Random r = new Random();
        int idx = r.nextInt(cnt);
        System.out.println("random: " + idx);
        // next int no equal to cnt, bound is less, so we should need ++ it
        ++idx;
        for (int i = 0; i < persons.size(); ++i) {
            if (!persons.get(i).isAwarded() && --idx == 0) {
                return persons.get(i);
            }
        }
        return null;
    }
}
