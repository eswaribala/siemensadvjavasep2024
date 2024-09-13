package com.siemens;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import io.reactivex.Observable;
public class ObservableFromDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//from iterable
		List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));

		Observable<Integer> observable = Observable.fromIterable(list);

		observable.subscribe(item -> System.out.println(item), error -> error.printStackTrace(), 
		     () -> System.out.println("Done"));		

		// from(Iterable)
		Path resources = Paths.get("src", "main", "java");
		try (DirectoryStream<Path> dStream = Files.newDirectoryStream(resources)) {
			Observable<Path> dirObservable = Observable.fromIterable(dStream);
			dirObservable.subscribe(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
