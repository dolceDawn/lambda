public class Apple {

    private String name;

    private String color;

    public Apple init () {
        name = "hello";
        color = "red";
        Apple app = new Apple();
        app.setName("hello");
        app.setColor("red");
        return app;
    }

    public Apple() {
    }

    public Apple(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
