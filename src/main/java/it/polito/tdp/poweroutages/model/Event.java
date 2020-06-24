package it.polito.tdp.poweroutages.model;

import java.time.LocalDateTime;

public class Event implements Comparable<Event>{
	
	public enum EventType {
		INIZIO_INTERRUZIONE,
		FINE_INTERRUZIONE
	}
	
	private EventType type;
	private LocalDateTime data;
	private Nerc nerc;
	
	public Event(EventType type, LocalDateTime data, Nerc nerc) {
		super();
		this.type = type;
		this.data = data;
		this.nerc = nerc;
	}
	public EventType getType() {
		return type;
	}
	public void setType(EventType type) {
		this.type = type;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public Nerc getNerc() {
		return nerc;
	}
	public void setNerc(Nerc nerc) {
		this.nerc = nerc;
	}
	@Override
	public String toString() {
		return "Event [type=" + type + ", data=" + data + ", nerc=" + nerc + "]\n";
	}
	@Override
	public int compareTo(Event o) {
		return this.data.compareTo(o.data);
	}
	
	
	
	
	

}
