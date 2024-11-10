package one_to_many_many_to_one_bi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class SaveMovieAndSongs {
	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		EntityManager em=Persistence.createEntityManagerFactory("dev").createEntityManager();
		EntityTransaction et =em.getTransaction();
		List<Song>songs=new ArrayList<Song>();
		boolean flag = true;
		while(flag) {
			System.out.println("Do you want to add a song(Y/N)");
			char ch =sc.next().charAt(0);
			if(ch=='Y'||ch=='y') {
				Song s= new Song();
				System.out.println("Enter song name: ");
				s.setName(sc.next());
				System.out.println("Enter singer name: ");
				s.setSingername(sc.next());
				songs.add(s);
				
			}else {
				flag=false;
			}
		}
		Movie m=new Movie();
		System.out.println("Enter movie name: ");
		m.setName(sc.next());
		System.out.println("Enter director name: ");
		m.setDirectorname(sc.next());
		m.setSongs(songs);
		
		for(Song s:songs) 
			s.setMovie(m);
			
			et.begin();
			em.persist(m);
			for(Song s:songs)
			em.persist(s);
			et.commit();
			System.out.println("Done");
		
	}
}
