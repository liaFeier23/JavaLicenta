package be.mailservice;

public class Mail {
    private String title;
    private String description;

    public Mail() {}
    public Mail(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public void setTitle(String title) {this.title = title;}
    public String getTitle() {return title;}

    public void setDescription(String description) {this.description = description;}
    public String getDescription() {return description;}
}
