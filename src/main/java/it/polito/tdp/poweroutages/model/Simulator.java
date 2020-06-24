package it.polito.tdp.poweroutages.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;


public class Simulator {
	
	
	private PriorityQueue<Event> queue = new PriorityQueue<>();
	
	private int K = 3;
	
	private Map<Nerc,Integer> mappa;
	
	private int catastrofe;
	
	
	public Map<Nerc, Integer> getMappa() {
		return mappa;
	}


	public int getCatastrofe() {
		return catastrofe;
	}




	public void run(int K,List<Nerc> nerc, List<Event> eventi, SimpleWeightedGraph<Nerc,DefaultWeightedEdge> g ) {
		
		this.K = K;
		this.mappa = new HashMap<>();
		this.catastrofe = 0;
		
		for(Nerc n: nerc) {
			this.mappa.put(n, 0);
		}
		
		this.queue.clear();
		
		for(Event e: eventi) {
			this.queue.add(e);
		}
		
		while(!this.queue.isEmpty()) {
			Event e = this.queue.poll();
			processEvent(e);
		}
		
		
		
	}


	private void processEvent(Event e) {
		switch(e.getType()) {
		
		case INIZIO_INTERRUZIONE:
			
			break;
			
		case FINE_INTERRUZIONE:
			
			break;
		
		}
		
	}
	

}
