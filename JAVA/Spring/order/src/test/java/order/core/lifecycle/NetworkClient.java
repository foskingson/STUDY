package order.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class NetworkClient {
    private String url;

    public NetworkClient() {
        System.out.println("생성자 url= "+ url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void connect(){
        System.out.println("connet : "+url);
    }

    public void call(String message){
        System.out.println("call: "+url+" message: "+ message);
    }

    public void disconnect(){
        System.out.println("close: "+ url);
    }

    
    @PostConstruct
    public void init() {
        connect();
        call("초기화");
    }


    @PreDestroy
    public void close() {
        System.out.println("aaaa");
        disconnect();
    }
}
