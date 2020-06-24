package it.polito.tdp.poweroutages.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.polito.tdp.poweroutages.model.Arco;
import it.polito.tdp.poweroutages.model.Event;
import it.polito.tdp.poweroutages.model.Event.EventType;
import it.polito.tdp.poweroutages.model.Nerc;

public class PowerOutagesDAO {
	
	public List<Nerc> loadAllNercs() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	
	public List<Arco> loadArchi(Map<Integer,Nerc> idMap) {

		String sql = "SELECT nerc_one, nerc_two " + 
				"FROM nercrelations";
		List<Arco> archi = new ArrayList<>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				int n1 = res.getInt("nerc_one");
				int n2 = res.getInt("nerc_two");
				Arco a = new Arco(idMap.get(n1),idMap.get(n2));
				archi.add(a);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return archi;
	}
	
	
	public Integer getPeso(Nerc n1, Nerc n2) {

		String sql = "select sum(peso) AS p  " + 
				"FROM (SELECT COUNT(distinct YEAR(p1.date_event_began), YEAR(p2.date_event_began), " + 
				"MONTH (p1.date_event_began), MONTH(p2.date_event_began)) AS peso " + 
				"FROM poweroutages AS p1, poweroutages AS p2 " + 
				"WHERE p1.nerc_id = ? AND p2.nerc_id = ?  " + 
				"AND YEAR(p1.date_event_began) = YEAR(p2.date_event_began) " + 
				"AND MONTH (p1.date_event_began) = MONTH(p2.date_event_began) " + 
				"GROUP BY YEAR(p1.date_event_began), YEAR(p2.date_event_began), " + 
				"MONTH (p1.date_event_began), MONTH(p2.date_event_began)) AS d ";
		
		Integer peso = 0;

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, n1.getId());
			st.setInt(2, n2.getId());
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Integer p = res.getInt("p");
				if(p == null) {
					peso = 0;
				}else {
					peso = p;
				}
				
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return peso;
	}
	
	
	public List<Event> loadEventi(Map<Integer,Nerc> idMap) {

		String sql = "SELECT nerc_id, date_event_began AS inizio, date_event_finished AS fine " + 
				"FROM poweroutages ";
		List<Event> eventi = new ArrayList<>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				int n1 = res.getInt("nerc_id");
				Event e = new Event(EventType.INIZIO_INTERRUZIONE,res.getTimestamp("inizio").toLocalDateTime(),idMap.get(n1));
				Event ev = new Event(EventType.FINE_INTERRUZIONE,res.getTimestamp("inizio").toLocalDateTime(),idMap.get(n1));

				eventi.add(e);
				eventi.add(ev);
				//System.out.println(e+"\n"+ev+" "+eventi.size()+"\n");
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return eventi;
	}
	
}
