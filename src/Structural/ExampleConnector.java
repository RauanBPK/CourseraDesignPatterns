//package Structural;
//
//// Esse arquivo não compila/roda pq faltam alguns detalhes, tipo implementação de classes ou funções
//// Por enquanto ta aqui só pra referencia sobre o Design Pattern "Connector"
//
//public class ExampleConnector  {
//    public static void main(String[] args){
//        String webHost = "Host: https://google.com\n\r";
//        WebService service = new WebService(webHost);
//        WebAdapter adapter = new WebAdapter();
//        adapter.connect(service);
//        WebClient client = new WebClient(adapter);
//        client.doWork();
//    }
//}
//
//// Target Interface - Implemented by the connector
//interface WebRequester {
//    public int request(Object obj);
//}
//
//class WebAdapter implements WebRequester {
//
//    public void connect(WebService currentService){
//        this.service = currentService;
//    }
//
//    @Override
//    public int request(Object request) {
//        Json result = this.toJson(request);
//        Json response = service.request(result);
//        if (response != null)
//            return 200;
//        return 500;
//    }
//
//    private Json toJson(Object input){
//        // Transforms input into Json
//    }
//}
//
//class WebClient {
//    private WebRequester webRequester;
//
//    public WebClient(WebRequester webRequester){
//        this.webRequester = webRequester;
//    }
//
//    private Object makeObject(){ ... } // Make an object to be used at the request
//
//    public void doWork() {
//        Object object = makeObject();
//        int status = webRequester.request(object);
//
//        if (status == 200){
//            System.out.println("OK");
//        } else {
//            System.out.println("NOT OK");
//        }
//    }
//}
