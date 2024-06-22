import org.junit.jupiter.api.Test;

public class GroupRemovalTests extends TestBase {



    @Test
    public void CanRemoveGroup() {
        openGroupsPage();
        if (!isGroupPresent()) {
            createGroup("", "", "");

        }

        removeGroup();

    }


}
