package mongo;


import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Principal extends Test{
	public static void main(String[] args) {
		MongoClient conexion = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase database = conexion.getDatabase("ADP5");
		MongoCollection<Document> alumnos = database.getCollection("harry-potter");
		insertarDocumentos(alumnos);
		consultarDocumentos(alumnos);
		actualizarDocumentos(alumnos);
		eliminarDocumentos(alumnos);
	}

}


