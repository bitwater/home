package com.noway.b365crawl.bean;

import java.util.ArrayList;
import java.util.Iterator;

public class TennisGame {
	private String gameName;
	private String score;
	private String set;
	private String url;
	private String ctime;
	private ArrayList<TennisItem> tennisItems;
	
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getSet() {
		return set;
	}
	public void setSet(String set) {
		this.set = set;
	}
	public ArrayList<TennisItem> getTennisItems()
	{
		return tennisItems;
	}
	public void setTennisItems(ArrayList<TennisItem> tennisItems)
	{
		this.tennisItems = tennisItems;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCtime() {
		return ctime;
	}
	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
	@Override
	public String toString()
	{
		return gameName;
	}

}
