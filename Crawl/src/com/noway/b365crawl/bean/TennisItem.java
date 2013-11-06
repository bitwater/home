package com.noway.b365crawl.bean;

import java.util.ArrayList;

public class TennisItem
{
	private String item;
	private String url;
	private ArrayList<TennisOdd> tennisOdds;
	private String ctime;
	
	public String getItem()
	{
		return item;
	}
	public void setItem(String item)
	{
		this.item = item;
	}
	public String getUrl()
	{
		return url;
	}
	public void setUrl(String url)
	{
		this.url = url;
	}
	public ArrayList<TennisOdd> getTennisOdds()
	{
		return tennisOdds;
	}
	public void setTennisOdds(ArrayList<TennisOdd> tennisOdds)
	{
		this.tennisOdds = tennisOdds;
	}
	public String getCtime()
	{
		return ctime;
	}
	public void setCtime(String ctime)
	{
		this.ctime = ctime;
	}
	
}
