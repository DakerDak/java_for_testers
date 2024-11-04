package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GroupRemovalTests extends TestBase {

    @Test
    public void CanRemoveGroup2() {

        if (app.hbm().getCroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "", "", ""));
        }

        var oldGroups = app.hbm().getGroupList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        app.groups().removeGroup(oldGroups.get(index));
        var newGroups = app.hbm().getGroupList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.remove(index);
        Assertions.assertEquals(newGroups , expectedList);

    }





//    @Test
//    public void CanRemoveGroup() {
//
//        if (app.groups().getCount() == 0) {
//            app.groups().createGroup(new GroupData("", "", "", ""));
//        }
//
//        var oldGroups = app.groups().getList();
//        var rnd = new Random();
//        var index = rnd.nextInt(oldGroups.size());
//        app.groups().removeGroup(oldGroups.get(index));
//        var newGroups = app.groups().getList();
//        var expectedList = new ArrayList<>(oldGroups);
//        expectedList.remove(index);
//        Assertions.assertEquals(newGroups , expectedList);
//
//    }

    @Test
    void canRemoveAllGroupsAtOnce() {
        if (app.hbm().getCroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "", "", ""));
        }
        app.groups().removeAllGroups();
        Assertions.assertEquals(0, app.hbm().getCroupCount());
    }


}
