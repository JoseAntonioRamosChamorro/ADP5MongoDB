package mongo;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

import java.util.Arrays;
public class Principal {

	public static void main(String[] args) {
		 MongoClient conexion = MongoClients.create("mongodb://localhost:27017");
		 MongoDatabase database = conexion.getDatabase("ADP5");
		 MongoCollection<Document> alumnos = database.getCollection("harry-potter");
	//	insertarDocumentos(alumnos);
		consultarDocumentos(alumnos);
		actualizarDocumentos(alumnos);
		eliminarDocumentos(alumnos);
	}
	

	public static void insertarDocumentos(MongoCollection<Document>alumnos)
	 {
		//insertar Individual
		Document documento = new Document("name", "Jose").append("species", "human").append("gender", "male")
				.append("house", "").append("dateOfBirth", "05-01-1997").append("yearOfBirth", "1997").append("ancestry", "")
				.append("eyeColour", "brown").append("hairColour", "Black").append("patronus", "").append("hogwartsStudent", false)
				.append("hogwartsStaff", false).append("alive", true);
		//insertar grupo
		alumnos.insertOne(documento);
		alumnos.insertMany(Arrays.asList(
				Document.parse("{ name: 'Paul', species: 'half-gian', gender: 'male', dateOfBirth: ' ', yearOfBirth: ' "
						+ "', ancestry: 'gigant ', eyeColour: 'blue ', hairColour: 'green ', patronus: ' "
						+ "', hogwartsStudent: false, dateOfBirth: ' ', hogwartsStaff: false , alive: true  }"),
			// otro usuario
				Document.parse("{  name: 'Rebeka', species: 'elf', gender: 'female', dateOfBirth: ' ', yearOfBirth: '"
						+ " ', ancestry: 'elf ', eyeColour: 'green ', hairColour: 'blue ', patronus: ' "
						+ "', hogwartsStudent: false, dateOfBirth: ' ', hogwartsStaff: false , alive: true  }")
				));
		System.out.println("Insertado");
		System.out.println(documento);
		}
	public static void consultarDocumentos(MongoCollection<Document> alumnos) {
		//humanos
		System.out.println("Humanos");
		FindIterable busquedahuman = alumnos.find(eq("species", "human"));
		for (Object alumno : busquedahuman) {
			System.out.println(((Document) alumno).toJson());			
		
		}
		
		//año nacimiento inferrior a 1979
		System.out.println("Nacidos antes de 1979");
		FindIterable busquedaAprobados = alumnos.find(lte("yearOfBirth", 1979));	
		for (Object alumo : busquedaAprobados) {
			System.out.println(((Document) alumo).toJson());
	}
		//año nacimiento inferrior a 1979
		System.out.println("Usuarios Holly");
		FindIterable busquedaholly = alumnos.find(eq("wand wood:", "holly"));	
		for (Object alumo : busquedaholly) {
			System.out.println(((Document) alumo).toJson());
	}
	
		//vivos y estudiantes
		System.out.println("Vivos y estudiantes de hogwartsStudent");
		FindIterable vivo = alumnos.find(and(eq("alive", true),gte("hogwartsStudent", true)));	
		for (Object alumo : vivo) {
			System.out.println(((Document) alumo).toJson());
	}
		
		
		
	}
	public static void actualizarDocumentos(MongoCollection<Document> alumnos) {
		alumnos.updateOne(eq("name", "Harry Potter"), set("species", "human"));
		System.out.println("cambiado");
		
	}
	public static void eliminarDocumentos(MongoCollection<Document>
	alumnos) {
		Bson filter = eq("species","humano");
		alumnos.deleteOne(filter);
	}
	}


