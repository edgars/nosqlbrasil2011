package br.com.redhat.infinispan.nosqlbrasil;

import java.util.ArrayList;
import java.util.List;

public class Session {
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpeaker() {
		return speaker;
	}
	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}
	public List<String> getComments() {
		return comments;
	}
	public void setComments(List<String> comments) {
		this.comments = comments;
	}
	private String name;
	private String speaker;
	private List<String> comments = new ArrayList<String>();
	
	public Session(String name, String speaker) {
		super();
		this.name = name;
		this.speaker = speaker;
	}
	
	public Session() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Session [name=" + name + ", speaker=" + speaker + ", comments="
				+ comments + "]";
	}
	
	
	
	
	
	
	
	

}
