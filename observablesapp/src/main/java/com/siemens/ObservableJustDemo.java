package com.siemens;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


import com.siemens.models.User;
import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
public class ObservableJustDemo {

	
public static void main(String[] args) {
		// TODO Auto-generated method stub
Observable.just('S').subscribe(System.out::println);
		
		Observable.just('R', 'x', 'J', 'a', 'v', 'a').subscribe(
				System.out::print, System.err::println, System.out::println);

		Observable.just(new User("Param","Eswari"))
		.map(u->u.getFirstName()+","+u.getLastName()).subscribe(System.out::println);
		

	}

}
