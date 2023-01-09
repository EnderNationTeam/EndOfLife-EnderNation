package de.mxscha.en.endoflife.utils.manager.home;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class HomeList {
    private List<Home> list;

    public HomeList() {
    }

    public HomeList(List<Home> list) {
        this.list = list;
    }

    public String toJson() {
        return new Gson().toJson(this, HomeList.class);
    }

    public HomeList fromJson(String json) {
        return new Gson().fromJson(json, HomeList.class);
    }

    public List<Home> getList() {
        if(this.list == null)
            this.list = new ArrayList<>();
        return this.list;
    }

    public void add(Home home) {
        if(this.list == null)
            this.list = new ArrayList<>();
        this.list.removeIf(homes -> homes.getName().equals(home.getName()));
        this.list.add(home);
    }

    public void remove(String home) {
        this.list.removeIf(homes -> homes.getName().equals(home));
    }
}