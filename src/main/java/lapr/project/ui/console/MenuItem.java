package lapr.project.ui.console;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */

public class MenuItem {
    private final String description;
    private Runnable ui;
    private int month;

    public MenuItem(String description,  Runnable ui)
    {
        if (StringUtils.isBlank(description))
            throw new IllegalArgumentException("MenuItem description cannot be null or empty.");
        if (Objects.isNull(ui))
            throw new IllegalArgumentException("MenuItem does not support a null UI.");

        this.description = description;
        this.ui = ui;
    }

    public MenuItem(String description,  int option)
    {
        if (StringUtils.isBlank(description))
            throw new IllegalArgumentException("MenuItem description cannot be null or empty.");

        this.description = description;
        this.month = option;
    }

    public void run()
    {
        this.ui.run();
    }

    public int getMonth()
    {
        return month;
    }

    public boolean hasDescription(String description)
    {
        return this.description.equals(description);
    }

    public String toString()
    {
        return this.description;
    }
}