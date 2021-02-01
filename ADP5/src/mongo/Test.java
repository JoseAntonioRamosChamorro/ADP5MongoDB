package mongo;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

import java.util.Arrays;

public class Test {


	public static void insertarDocumentos(MongoCollection<Document>alumnos)
	{
		//insertar Individual
		alumnos.insertOne(Document.parse("{name:'Jose',species:'Human',gender:'male',dateOfBirth:'',yearOfBirth:'',ancestry:'gigant',eyeColour:'blue',hairColour:'green',wand:{wood:'vine',core:'fenix',lenght:''},patronus:'',hogwartsStudent:false,dateOfBirth:'',hogwartsStaff:false,alive:true,image:'none'}"));

		//insertar grupo
		alumnos.insertMany(Arrays.asList(
				Document.parse("{name:'Paul',species:'half-gian',gender:'male',dateOfBirth:'',yearOfBirth:'',ancestry:'gigant',eyeColour:'blue',hairColour:'green',wand:{wood:'vine',core:'fenix',lenght:''},patronus:'',hogwartsStudent:false,dateOfBirth:'',hogwartsStaff:false,alive:true,image:'none'}"),
				// otro usuario
				Document.parse("{name:'Pauka',species:'half-gian',gender:'female',dateOfBirth:'',yearOfBirth:'',ancestry:'gigant',eyeColour:'blue',hairColour:'green',wand:{wood:'vine',core:'fenix',lenght:''},patronus:'',hogwartsStudent:false,dateOfBirth:'',hogwartsStaff:false,alive:true,image:'none'}")
				));
		System.out.println("Insertado");

	}
	public static void consultarDocumentos(MongoCollection<Document> alumnos) {
		//humanos
		System.out.println("Humanos");
		FindIterable<Document> busquedahuman = alumnos.find(eq("species", "human"));
		for (Object alumno : busquedahuman) {
			System.out.println(((Document) alumno).toJson());			

		}

		//año nacimiento inferrior a 1979
		System.out.println("Nacidos antes de 1979");
		FindIterable<Document> busquedaAprobados = alumnos.find(lte("yearOfBirth", 1979));	
		for (Object alumo : busquedaAprobados) {
			System.out.println(((Document) alumo).toJson());
		}
		//año nacimiento inferrior a 1979
		System.out.println("Usuarios Holly");
		FindIterable<Document> busquedaholly = alumnos.find(eq("wand.wood", "holly"));	
		for (Object alumo : busquedaholly) {
			System.out.println(((Document) alumo).toJson());
		}

		//vivos y estudiantes
		System.out.println("Vivos y estudiantes de hogwartsStudent");
		FindIterable<Document> vivo = alumnos.find(and(eq("alive", true),gte("hogwartsStudent", true)));	
		for (Object alumo : vivo) {
			System.out.println(((Document) alumo).toJson());


		}



	}
	public static void actualizarDocumentos(MongoCollection<Document> alumnos) {
		alumnos.updateOne(eq("name", "Argus Filch"), set("eyeColour", "black"));
		System.out.println("cambiado");

	}
	public static void eliminarDocumentos(MongoCollection<Document>
	alumnos) {
		Bson filter = eq("name","Pauka");
		alumnos.deleteOne(filter);
		System.out.println("Eliminación completada");
	}
}
