package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupRemovalTests extends TestBase {



    @Test
    public void CanRemoveGroup() {

        if (!app.groups().isGroupPresent()) {
            app.groups().createGroup(new GroupData("", "", ""));

        }

        app.groups().removeGroup();

    }


}
