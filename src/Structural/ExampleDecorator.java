package Structural;

public class ExampleDecorator {
    public static void main(String[] args){
        WebPage myPage = new BasicWebPage();
        myPage = new AuthorizedWebpage(myPage);
        myPage = new AuthenticatedWebPage(myPage);
        myPage.display();
    }
}

// Decorator pattern works like a stack of objects, not like a tree (composite pattern)
// The idea is to make it possible to add functionalities to a object without having multiple statically defined
// combination of objects, like inheritance

// Component interface
interface WebPage {
    void display();
}

// Base concrete component class
// A Base class that may be decorated
class BasicWebPage implements WebPage {

    private String html = "<html></html>";
    private String stylesheet = "";
    private String scripts = "";
    @Override
    public void display() {
        System.out.println("Rendering basic web page.");
    }
}

// Abstract decorator class
// It implements the same interface as BasicWebPage, so it is interoperable
abstract class WebPageDecorator implements WebPage {
    protected WebPage page; // Only one instance, to stack, not to create a tree

    public WebPageDecorator(WebPage webpage) {
        this.page = webpage;
    }

    @Override
    public void display() {
        this.page.display();
    }
}

// Decorator concrete class
class AuthorizedWebpage extends WebPageDecorator {

    public AuthorizedWebpage(WebPage decoratedPage) {
        super(decoratedPage);
    }
    public void authorizeUser() {
        System.out.println("Authorizing user...");
    }

    @Override
    public void display() {
        super.display();
        this.authorizeUser();
    }
}

class AuthenticatedWebPage extends WebPageDecorator {

    public AuthenticatedWebPage(WebPage decoratedWebpage) {
        super(decoratedWebpage);
    }

    public void authenticateUser() {
        System.out.println("Authenticating user...");
    }

    @Override
    public void display(){
        super.display();
        this.authenticateUser();
    }
}