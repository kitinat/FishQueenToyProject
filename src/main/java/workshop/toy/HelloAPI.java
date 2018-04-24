package workshop.toy;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloAPI {

    @GetMapping("hello/Than")
    public Message SayHi(@PathVariable String name) {

        Message message = new Message();
        message.name = "Hello" + name;
        return message;
    }

    Class Message {
        public String name;

    }



}

