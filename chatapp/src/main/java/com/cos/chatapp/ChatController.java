package com.cos.chatapp;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;


@RequiredArgsConstructor //@RequiredArgsConstructor는 lombok에 해당하며,final 혹은 @NotNull이 붙은 필드의 생성자를 자동으로 만들어준다.
@RestController //데이터를 리턴해주는 서버
public class ChatController {
	
	private final ChatRepository chatRepository;
	
	@GetMapping(value="/sender/{sender}/receiver/{receiver}",produces=MediaType.TEXT_EVENT_STREAM_VALUE)
	//produce는 클라이언트에게 반환하는 HTTP Header의 형식을 지정하는 파라미터이다.
	//MediaType.TEXT_EVENT_STREAM_VALUE는 SSE 프로토콜 형식으로 반환하겠다는 뜻임
	/*
	 * @PathVariable에 관한 설명
	 * @GetMapping에서 {sender}라고 동적으로 설정한 변수를 @PathVariable를통해서 변수로서 파라미터를 설정하는 것을 의미한다.
	 * */
	public Flux<Chat> getMsg(@PathVariable String sender, @PathVariable String receiver) {
		return chatRepository.mFindBySender(sender, receiver);
		
	}
}
