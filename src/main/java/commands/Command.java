package commands;

public interface Command {
    public String getName();

    public String getDescription();

    public boolean execute(String arg);
}
