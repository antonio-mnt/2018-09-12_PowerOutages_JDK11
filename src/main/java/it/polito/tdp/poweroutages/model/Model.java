package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.poweroutages.db.PowerOutagesDAO;

public class Model {
	
	
	private PowerOutagesDAO dao;
	private SimpleWeightedGraph<Nerc,DefaultWeightedEdge> grafo;
	private List<Nerc> nerc;
	private Map<Integer,Nerc> idMap;
	private List<Arco> archi;
	
	
	
	public Model() {
		dao = new PowerOutagesDAO();
	}
	
	public void creaGrafo() {
		
		this.nerc = new ArrayList<>(this.dao.loadAllNercs());
		
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		this.idMap = new HashMap<>();
		
		for(Nerc n: this.nerc) {
			this.idMap.put(n.getId(), n);
		}
		
		Graphs.addAllVertices(this.grafo, this.nerc);
		
		this.archi = new ArrayList<>(this.dao.loadArchi(this.idMap));
		
		for(Arco a: this.archi) {
			Graphs.addEdgeWithVertices(this.grafo, a.getN1(), a.getN2(), this.dao.getPeso(a.getN1(),a.getN2()));
		}
		
		
		
	}
	
	
	public int getNumeroVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int getNumeroArchi() {
		return this.grafo.edgeSet().size();
	}

	public List<Nerc> getNerc() {
		return nerc;
	}
	
	public List<Vicino> getVicini(Nerc ner){
		
		List<Nerc> vicini = new ArrayList<>(Graphs.neighborListOf(this.grafo, ner));
		List<Vicino> result = new  ArrayList<>();
		
		for(Nerc n: vicini) {
			Vicino v = new Vicino(n,(int)this.grafo.getEdgeWeight(this.grafo.getEdge(ner, n)));
			result.add(v);
		}
		
		return result;
	}
	
	public void getEventi() {
	this.dao.loadEventi(this.idMap);
	}
	
	

}
