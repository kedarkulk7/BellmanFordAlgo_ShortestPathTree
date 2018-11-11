package utd.ds.assign5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bellman_Ford_Algorithm_ShortestPathtree {

	static boolean change = false;
	static String s = "a ";

	public static void main(String[] args) {

		//Bellman-Ford Algorithm
		Map<String, Vertex> vertexDisMap = new HashMap<>();
		List<Edge> edgeList = new ArrayList<>();

		vertexDisMap.put("a", new Vertex("a"));
		vertexDisMap.put("b", new Vertex("b"));
		vertexDisMap.put("c", new Vertex("c"));
		vertexDisMap.put("d", new Vertex("d"));
		vertexDisMap.put("e", new Vertex("e"));
		vertexDisMap.put("f", new Vertex("f"));
		vertexDisMap.put("g", new Vertex("g"));
		vertexDisMap.put("h", new Vertex("h"));
		vertexDisMap.put("i", new Vertex("i"));
		vertexDisMap.put("j", new Vertex("j"));

		edgeList.add( new Edge("a","b",6));
		edgeList.add( new Edge("a","d",7));
		edgeList.add( new Edge("b","d",8));
		edgeList.add( new Edge("b","c",5));
		edgeList.add( new Edge("c","b",-2));
		edgeList.add( new Edge("b","e",-4));
		edgeList.add( new Edge("d","c",-3));
		edgeList.add( new Edge("d","e",9));
		edgeList.add( new Edge("e","c",7));
		edgeList.add( new Edge("e","a",2));
		edgeList.add( new Edge("a","f",1));
		edgeList.add( new Edge("f","g",6));
		edgeList.add( new Edge("f","i",7));
		edgeList.add( new Edge("g","i",8));
		edgeList.add( new Edge("g","h",5));
		edgeList.add( new Edge("h","g",-2));
		edgeList.add( new Edge("g","j",-4));
		edgeList.add( new Edge("i","h",-3));
		edgeList.add( new Edge("i","j",9));
		edgeList.add( new Edge("j","h",7));
		edgeList.add( new Edge("j","f",2));


		vertexDisMap.get("a").sourceVertex = null;
		vertexDisMap.get("a").value = 0;

		for(int i = 0; i < vertexDisMap.size(); i++) {

			vertexDisMap.forEach((k1,v1) ->{
				change = false;
				edgeList.forEach(edge -> {

					int intvalue = Integer.MAX_VALUE;
					if(vertexDisMap.get(edge.fromVertex).value != Integer.MAX_VALUE) {
						intvalue = vertexDisMap.get(edge.fromVertex).value+edge.weight;
					}
					if( (vertexDisMap.get(edge.toVertex).value > (intvalue))) {
						change = true;
						Vertex ver = new Vertex(edge.toVertex);
						ver.value = vertexDisMap.get(edge.fromVertex).value + edge.weight;
						ver.sourceVertex = edge.fromVertex;
						vertexDisMap.put(edge.toVertex, ver);
					}
				});

			});
			if(change == false) {
				break;
			}
		}		


		List<String> strList = new ArrayList<>();

		if(change == true) {
			System.out.println("Negative cycle found");
		}else {
			System.out.println("Starting vertex is a ");

			Map<String, Integer> vertexDis = new HashMap<>();
			vertexDisMap.forEach((k,v) ->{
				vertexDis.put(k, v.value);
			});

			vertexDisMap.forEach((k,v) ->{
				if(!k.equalsIgnoreCase("a")) {
					strList.add(v.sourceVertex+"->"+k+"("+(vertexDis.get(k)-vertexDis.get(v.sourceVertex))+"), distance of "+k+" from a is "+v.value);
				}
			});

		}

		Collections.sort(strList);

		strList.forEach(line -> {
			System.out.println(line);
		});

	}

	public static class Edge{
		public String fromVertex;
		public String toVertex;
		public int weight;

		public Edge(String fv, String tv, int w) {
			this.fromVertex = fv;
			this.toVertex = tv;
			this.weight = w;
		}
	}

	public static class Vertex{
		public int value = Integer.MAX_VALUE;
		public String sourceVertex;
		public Vertex(String vertex) {
			this.sourceVertex = vertex;
		}
	}
}