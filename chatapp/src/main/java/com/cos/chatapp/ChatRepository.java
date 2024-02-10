package com.cos.chatapp;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;

import reactor.core.publisher.Flux;

//ReactiveMongoRepository를 상속하여 비동기적인 MongoDB와의 상호작용을 위함
public interface ChatRepository extends ReactiveMongoRepository<Chat,String>{

	
	
	@Tailable // 커서를 안닫고 계속 유지한다. 밑의 함수가 실행된다.
	/*
	 * 이 어노테이션은 MongoDB의 tailable 커서 기능을 활용하여 컬렉션의 변경 사항을 실시간으로 읽을 수 있게 해줍니다.
	 * */
	//@Query는 SQL문법에 매칭 되는 어노테이션임 
	@Query("{sender:?0, receiver:?1}")
	//Flux (흐름)
	//Flux는 여러 개의 데이터를 비동기적으로, 논블로킹 방식으로 처리할 수 있습니다. 
	//주로 비동기적인 이벤트 스트림이나 데이터 스트림을 다룰 때 사용
	Flux<Chat> mFindBySender(String sender,String receiver); 
}
